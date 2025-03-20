package Service;

import Modal.Task;
import Modal.TaskStatus;
import Modal.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class TaskManager {
       private static TaskManager instance=null;
       private final Map<String, List<Task>> userTask;
       private final Map<String,Task> tasks;

       private TaskManager(){
           tasks=new ConcurrentHashMap<>();
           userTask=new ConcurrentHashMap<>();
       }

       public static TaskManager getInstance(){
           if(instance==null){
               instance=new TaskManager();
           }
           return instance;
       }

        public void createTask(Task task){
            tasks.put(task.getTaskId(),task);
            assignTask(task.getAssignedUser(),task);
        }

        public void updateTask(Task updatedTask){
            Task existingTask=tasks.get(updatedTask.getTaskId());
            if(existingTask!=null){
                synchronized (existingTask){
                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setDescription(updatedTask.getDescription());
                    existingTask.setDueDate(updatedTask.getDueDate());
                    existingTask.setPriority(updatedTask.getPriority());
                    existingTask.setTaskStatus(updatedTask.getTaskStatus());
                    User previousUser = existingTask.getAssignedUser();
                    User newUser = updatedTask.getAssignedUser();
                    if (!previousUser.equals(newUser)) {
                        unassignTask(previousUser, existingTask);
                        assignTask(newUser, existingTask);
                    }

                }
            }

        }

        public List<Task> searchTask(String keyword){
           List<Task> matchingTask=new ArrayList<>();
            for(Task task : tasks.values()){
                if(task.getTitle().contains(keyword) || task.getDescription().contains(keyword)){
                    matchingTask.add(task);
                }
            }
            return matchingTask;
        }

        public void unassignTask(User exisitingUser,Task exisitingTask){
           List<Task> task=userTask.get(exisitingUser.getUser_id());
            if (task != null) {
                task.remove(exisitingTask);
            }
        }

        public void assignTask(User assignedUser, Task task){
            userTask.computeIfAbsent(assignedUser.getUser_id(), k -> new ArrayList<>()).add(task);
        }

        public List<Task> getHistory(User user){
           return userTask.getOrDefault(user.getUser_id(),new ArrayList<>());
        }

        public List<Task> FilterTask(String priority,LocalDateTime startDate,LocalDateTime endDate,User assignedUser){
            List<Task> filteredTask=new ArrayList<>();
            for(Task task:tasks.values()){
                if(Objects.equals(task.getPriority(), priority) && task.getAssignedUser()==assignedUser && !task.getDueDate().isBefore(startDate) && !task.getDueDate().isAfter(endDate)){
                    filteredTask.add(task);
                }
            }
            return filteredTask;
        }

        public void deleteTask(String taskId){
           if(tasks.containsKey(taskId)){
               tasks.remove(taskId);
               return;
           }
            System.out.println("The task is not present");
        }

        private void markComplete(String taskId){
           Task currentTask=tasks.get(taskId);
           if(currentTask.getTaskStatus().equals(TaskStatus.COMPLETED)){
               System.out.println("Task is already completed");
           }
            synchronized (currentTask) {
                currentTask.setTaskStatus(TaskStatus.COMPLETED);
            }
        }
}
