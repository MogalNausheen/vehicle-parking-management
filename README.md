# Vehicle Parking Management System

A console-based **Vehicle Parking Management System** developed using Core Java. The application manages vehicle entries, five parking slots, vehicle exits, entry history, and parking reports using object-oriented programming concepts and text-file storage.

## Features

* Record vehicle entry
* Supports Two-Wheeler and Four-Wheeler vehicles
* Automatically allocates the first available parking slot
* Maintains a fixed capacity of 5 parking slots
* Prevents the same vehicle from being parked twice at the same time
* Displays the current status of all five parking slots
* Records vehicle exits
* Prevents an already exited entry from being exited again
* Maintains entry history
* Generates parking reports
* Stores parking records in a text file
* Stores generated reports separately
* Automatically creates the required data folder

## Technologies Used

* **Language:** Java
* **Concepts:** Core Java, OOP
* **File Handling:** Java NIO / BufferedReader / BufferedWriter
* **Data Storage:** Text files
* **Development Environment:** Eclipse IDE
* **Version Control:** Git & GitHub

## OOP Concepts Used

### Encapsulation

The fields in `ParkingEntry` are private and accessed through getter methods.

### Inheritance

`TwoWheelerEntry` and `FourWheelerEntry` extend the abstract `ParkingEntry` class.

### Abstraction

`ParkingEntry` is an abstract class and defines the `getVehicleType()` method that is implemented by its subclasses.

### Polymorphism

A `ParkingEntry` reference can refer to either a `TwoWheelerEntry` or `FourWheelerEntry` object.

### Composition

`ParkingService` manages the collection of parking entries and the active vehicle numbers.

## Project Structure

```text
src/
└── com/
    └── codegnan/
        └── vehicleparkingmanagement/
            ├── Main.java
            ├── ParkingEntry.java
            ├── ParkingStatus.java
            ├── TwoWheelerEntry.java
            ├── FourWheelerEntry.java
            ├── ParkingService.java
            └── FileManager.java
```

## Class Responsibilities

| Class              | Responsibility                                       |
| ------------------ | ---------------------------------------------------- |
| `Main`             | Handles the console menu and user input              |
| `ParkingEntry`     | Abstract base class for parking records              |
| `ParkingStatus`    | Defines `PARKED` and `EXITED` statuses               |
| `TwoWheelerEntry`  | Represents two-wheeler parking entries               |
| `FourWheelerEntry` | Represents four-wheeler parking entries              |
| `ParkingService`   | Handles parking, slot allocation, exits, and reports |
| `FileManager`      | Handles reading and writing parking data             |

## Parking Slots

The system maintains **5 parking slots**.

When a vehicle enters:

1. The vehicle number is validated.
2. The system checks whether the vehicle is already parked.
3. Available slots are checked.
4. The first available slot is assigned.
5. The parking record is saved.

If all five slots are occupied, the system displays:

```text
Parking full
```

## Vehicle Exit

When a vehicle exits:

1. The user enters the parking entry ID.
2. The system searches for the corresponding entry.
3. The entry must currently have `PARKED` status.
4. Its status is changed to `EXITED`.
5. The updated records are saved.

Exited records remain available in the entry history.

## File Storage

The application creates a `data` folder at runtime.


data/
├── entries.txt
└── report.txt


### `entries.txt`

Parking records are stored using the following format:


id|vehicleNumber|type|slotNumber|status


Example:


1|KA01AB1234|TwoWheeler|1|PARKED
2|KA02CD5678|FourWheeler|2|PARKED
3|KA03EF9012|TwoWheeler|3|EXITED


### `report.txt`

The application generates a report containing:

* Total parking slots
* Occupied slots
* Free slots
* Total recorded entries
* Exited entries
* Active vehicle count

The generated `data/` folder is excluded from Git using `.gitignore`.

## How to Run

1. Clone the repository.
2. Import the project into Eclipse or another Java IDE.
3. Make sure the package structure is preserved.
4. Run `Main.java`.
5. Use the console menu to manage the parking system.

### Menu


===== Vehicle Parking Management System =====

1. Record vehicle entry
2. View five slots
3. Record vehicle exit
4. View entry history
5. View reports
0. Exit


## GitHub Repository

This project is maintained using Git and GitHub for version control.

## Author

Mogal Nausheen Begum
**Mogal Nausheen Begum**

B.Tech – Computer Science and Engineering
