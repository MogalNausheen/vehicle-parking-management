package com.codegnan.vehicleparkingmanagement;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            ParkingService service =
                    new ParkingService();

            Scanner scanner =
                    new Scanner(System.in);

            showMenu(scanner, service);

            scanner.close();

        } catch (IOException e) {

            System.out.println(
                    "File error: " + e.getMessage()
            );
        }
    }


    public static void showMenu(
            Scanner scanner,
            ParkingService service) {

        int choice;

        do {

            System.out.println();
            System.out.println(
                    "===== Vehicle Parking Management System ====="
            );

            System.out.println("1. Record vehicle entry");
            System.out.println("2. View five slots");
            System.out.println("3. Record vehicle exit");
            System.out.println("4. View entry history");
            System.out.println("5. View reports");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

            case 1:

                System.out.print("Enter vehicle number: ");
                String vehicleNumber = scanner.nextLine();

                System.out.println("Select vehicle type:");
                System.out.println("1. TwoWheeler");
                System.out.println("2. FourWheeler");

                System.out.print("Enter vehicle type: ");
                int vehicleTypeChoice = scanner.nextInt();
                scanner.nextLine();

                String vehicleType;

                if (vehicleTypeChoice == 1) {

                    vehicleType = "TwoWheeler";

                } else if (vehicleTypeChoice == 2) {

                    vehicleType = "FourWheeler";

                } else {

                    System.out.println("Invalid vehicle type.");
                    break;
                }

                try {

                    service.parkVehicle(
                            vehicleNumber,
                            vehicleType
                    );

                } catch (IOException e) {

                    System.out.println(
                            "File error: " + e.getMessage()
                    );
                }

                break;

            case 2:

                int[] slots = service.buildSlots();

                System.out.println();
                System.out.println("===== Parking Slots =====");

                for (int i = 0; i < slots.length; i++) {

                    int slotNumber = i + 1;

                    if (slots[i] == 0) {

                        System.out.println(
                                "Slot " + slotNumber + ": FREE"
                        );

                    } else {

                        System.out.println(
                                "Slot " + slotNumber +
                                ": OCCUPIED (Entry ID: " +
                                slots[i] + ")"
                        );
                    }
                }

                break;

            case 3:

                System.out.print("Enter entry ID: ");

                int entryId = scanner.nextInt();
                scanner.nextLine();

                try {

                    service.recordExit(entryId);

                } catch (IOException e) {

                    System.out.println(
                            "File error: " + e.getMessage()
                    );
                }

                break;

            case 4:

                System.out.println();
                System.out.println("===== Entry History =====");

                List<ParkingEntry> entries =
                        service.getEntries();

                if (entries.isEmpty()) {

                    System.out.println("No entries found.");

                } else {

                    for (ParkingEntry entry : entries) {

                        System.out.println(
                                "ID: " + entry.getId() +
                                " | Vehicle: " +
                                entry.getVehicleNumber() +
                                " | Type: " +
                                entry.getVehicleType() +
                                " | Slot: " +
                                entry.getSlotNumber() +
                                " | Status: " +
                                entry.getStatus()
                        );
                    }
                }

                break;

            case 5:

                try {

                    service.showReport();

                } catch (IOException e) {

                    System.out.println(
                            "File error: " + e.getMessage()
                    );
                }

                break;

                case 0:
                    System.out.println(
                            "Exiting program..."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 0);
    }
}