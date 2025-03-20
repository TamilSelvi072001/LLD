import Modal.Task;
import Modal.TaskStatus;
import Modal.User;
import Service.TaskManager;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TaskManagerDemo {
    public static void main(String[] args) {
        TaskManager taskManager=TaskManager.getInstance();

        User user1=new User("1","user1", "tamilselvi072001@gmail.com");
        User user2=new User("2","User2", "user2@gmail.com");
        User user3=new User("3","User3", "user3@gmail.com");

        Task task1 = new Task("1", "Task 1", "Description 1", LocalDateTime.now(), "1", user1, TaskStatus.PENDING);
        Task task2 = new Task("2", "Task 2", "Description 2", LocalDateTime.now(), "2", user2,TaskStatus.PENDING);
        Task task3 = new Task("3", "Task 3", "Description 3", LocalDateTime.now(), "1", user1,TaskStatus.PENDING);
        Task task4 = new Task("4", "Task 4", "Description 4", LocalDateTime.now(), "2", user2,TaskStatus.PENDING);

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);
        task3.setTaskStatus(TaskStatus.COMPLETED);
        taskManager.updateTask(task3);
        List<Task> searchResults = taskManager.searchTask("Task");
        System.out.println("Search Results:");
        for (Task task : searchResults) {
            System.out.println(task.getTitle());
        }

        taskManager.unassignTask(user2,task4);

        List<Task> taskHistory = taskManager.getHistory(user1);
        System.out.println("Task History for " + user1.getName() + ":");
        for (Task task : taskHistory) {
            System.out.println(task.getTitle());
        }

        // Delete a task
        taskManager.deleteTask("3");
        System.out.println("debug");
    }
}