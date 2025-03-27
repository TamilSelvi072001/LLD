# Requirement Ideas that I thought
- Number of Elevator
- User request -> 2 request
    - Elevator to come to the users place
    - user request to elevator for destination
- Lock when elevator is moving
- Elevator state
    - IDLE
    - MOVE
- Direction
    - UP
    - DOWN
    - NON_MOVE
- Type of elevator like service, passenger
- Capacity management of lift
- Floor management of lift (ground,basement)
- Handling multiple request at same time
- Movement optimization
- Power failure handling
- Emergency stop button


## Proper Requirement

# Elevator System - Interview Requirements and Discussion Points

## 1. Functional Requirements

### Core Functionality
- Design a system to manage multiple elevators in a building
- Support elevator calls from different floors
- Handle passenger requests efficiently
- Manage elevator movement and state

### Specific Features
1. Support multiple floors
2. Handle simultaneous elevator requests
3. Implement weight limitations
4. Manage different elevator states
5. Provide emergency stop functionality

## 2. System Design Considerations

### Elevator Allocation Strategies
- Discuss different approaches:
    - Nearest elevator
    - Least recently used
    - Predictive allocation based on historical data
- Trade-offs between simplicity and optimization

### Concurrency Handling
- Managing multiple simultaneous requests
- Thread-safety considerations
- Preventing race conditions

## 3. Technical Requirements

### Key Classes and Their Responsibilities
1. **ElevatorSystem**
    - Manage overall elevator system
    - Coordinate elevator allocation
    - Maintain list of elevators and floors

2. **Elevator**
    - Track current floor, direction, and state
    - Manage request queue
    - Handle weight limitations
    - Implement movement logic

3. **Floor**
    - Represent individual building floors
    - Manage floor-specific buttons
    - Initiate elevator calls

4. **Request**
    - Represent passenger travel requests
    - Store source and destination floors
    - Track passenger count and weight

## 4. Advanced Functionality Discussions

### Optimization Scenarios
- Peak hour traffic management
- Emergency scenarios
- Maintenance mode handling
- Energy efficiency considerations

### Performance Optimization
- Minimize average waiting time
- Reduce unnecessary elevator movements
- Predictive request handling

## 5. Edge Cases and Error Handling

### Potential Scenarios to Discuss
- Maximum weight limit exceeded
- Emergency stop situations
- Power failure scenarios
- Maintenance requirements
- Handling malfunctioning elevators

## 6. Design Pattern Considerations

### Potential Patterns
1. Singleton (for ElevatorSystem)
2. State Pattern (for Elevator states)
3. Strategy Pattern (for allocation algorithms)
4. Observer Pattern (for request notifications)

## 7. Scalability Considerations
- Supporting variable number of elevators
- Handling buildings with multiple elevator banks
- Extensibility of the current design

## 8. Interview Discussion Points

### Technical Deep Dive
- Time complexity of elevator allocation
- Space complexity of request management
- Synchronization mechanisms
- Potential bottlenecks in the design

### Design Choices Justification
- Why choose certain data structures?
- Reasoning behind allocation strategies
- Trade-offs in design decisions

## 9. Real-world Constraints
- Different building types (residential, commercial, high-rise)
- Accessibility requirements
- Safety regulations
- Energy efficiency standards

## 10. Possible Follow-up Challenges
- Implement priority queuing
- Add logging and monitoring
- Create a simulation of elevator system behavior
- Develop unit tests for critical components

## Sample Interview Questions
1. How would you optimize elevator allocation during peak hours?
2. Explain your approach to handling concurrent elevator requests
3. How would you implement an emergency stop mechanism?
4. Discuss potential improvements to the current design
5. How would you make the system more energy-efficient?

## Key Metrics to Consider
- Average waiting time
- Energy consumption
- Request processing efficiency
- System responsiveness
- Fault tolerance

## Elevator System Class Definitions

### ElevatorSystem
```
class ElevatorSystem {
    -List<Elevator> elevators
    -List<Floor> floors
    +addElevator(Elevator elevator)
    +requestElevator(int floorNumber)
    +scheduleElevator(Request request)
}
```

### Elevator
```
class Elevator {
    -int currentFloor
    -ElevatorDirection currentDirection
    -ElevatorState currentState
    -List<Request> requestQueue
    -int maxWeight
    -int currentWeight
    +move()
    +stop()
    +addRequest(Request request)
    +removeRequest()
    +checkWeight()
    +emergencyStop()
}
```

### Floor
```
class Floor {
    -int floorNumber
    -List<Button> buttons
    +callElevator(ElevatorDirection direction)
}
```

### Request
```
class Request {
    -int sourceFloor
    -int destinationFloor
    -int numberOfPassengers
    -int totalWeight
}
```

### Button
```
class Button {
    -ButtonType type
    -Floor associatedFloor
    +press()
}
```

### ElevatorController
```
class ElevatorController {
    -ElevatorSystem elevatorSystem
    +processRequest(Request request)
    +optimizeElevatorAllocation()
}
```

### Enumerations

#### ElevatorDirection
```
enum ElevatorDirection {
    UP,
    DOWN,
    IDLE
}
```

#### ElevatorState
```
enum ElevatorState {
    MOVING,
    STOPPED,
    MAINTENANCE,
    OUT_OF_SERVICE
}
```

#### ButtonType
```
enum ButtonType {
    UP,
    DOWN,
    FLOOR_SELECTION,
    EMERGENCY
}
```

    <<enumeration>> ButtonType {
        UP
        DOWN
        FLOOR_SELECTION
        EMERGENCY
    }

    ElevatorSystem "1" *-- "many" Elevator : contains
    ElevatorSystem "1" *-- "many" Floor : manages
    Elevator "1" -- "many" Request : processes
    Floor "1" *-- "many" Button : has
    ElevatorController "1" -- "1" ElevatorSystem : controls
    
    note "The system manages multiple elevators,\nhandling requests and optimizing allocation" as SystemNote


## Design Patterns Used

### 1. Singleton Pattern
**Class:** `ElevatorSystem`
**Implementation:**
```java
private static ElevatorSystem instance;
private static synchronized ElevatorSystem getInstance() {
    if (instance == null) {
        instance = new ElevatorSystem();
    }
    return instance;
}
```
**Purpose:** Ensures only one instance of the ElevatorSystem exists, providing a global point of access.

### 2. Strategy Pattern
**Classes:** `ElevatorController`
**Implementation:**
- The `findBestElevator()` method demonstrates a strategy for elevator allocation
- The `optimizeElevatorAllocation()` method allows for potential future allocation strategies

### 3. State Pattern
**Enums:** `ElevatorState`, `ElevatorDirection`
**Class:** `Elevator`
**Purpose:** Manages different states of an elevator (MOVING, STOPPED, MAINTENANCE, etc.)
- State changes are controlled in methods like `move()`, `stop()`, `emergencyStop()`

### 4. Observer Pattern (Potential Implementation)
**Potential Implementation:**
- The `Button` class could be extended to implement a true observer pattern
- Would allow floors to notify the elevator system about button presses

### 5. Factory Method Pattern (Potential Improvement)
**Suggested Implementation:**
```java
public class ElevatorFactory {
    public Elevator createElevator(int id, int maxWeight) {
        return new Elevator(id, maxWeight);
    }
}
```
- Would provide a centralized way of creating elevator instances

### 6. Composition Pattern
**Evidence:**
- `ElevatorSystem` contains lists of `Elevator` and `Floor`
- `Floor` contains a list of `Button`
- `Elevator` contains a list of `Request`



