package com.codegnan.vehicleparkingmanagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingService {

	private List<ParkingEntry> entries = new ArrayList<>();
	
	private Set<String> activeVehicleNumbers = new HashSet<>();
	
	public ParkingService() throws IOException {
		
		entries = FileManager.loadEntries();
		
		rebuildActiveVehicles();
	}

	private void rebuildActiveVehicles() {
		
		activeVehicleNumbers.clear();
		
		for(ParkingEntry entry : entries) {
			
			if(entry.getStatus() == ParkingStatus.PARKED) {
				
				activeVehicleNumbers.add(
						entry.getVehicleNumber());
			}
		}
		
	}
	
	public int[] buildSlots() {
		
		int[] slots = new int[5];
		for(ParkingEntry entry : entries) {
			
			if(entry.getStatus() == ParkingStatus.PARKED) {
				
				int slotNumber = entry.getSlotNumber();
			
				int index = slotNumber - 1;
				
				slots[index] = entry.getId();
			}
		}
		
		return slots;
	}
	
	public void parkVehicle(String vehicleNumber, String vehicleType)
	        throws IOException {

	    vehicleNumber =
	            vehicleNumber.trim().toUpperCase();

	    if (vehicleNumber.isEmpty()) {
	        System.out.println("Vehicle number is required.");
	        return;
	    }

	    if (vehicleNumber.contains("|") ||
	            vehicleNumber.contains("\n") ||
	            vehicleNumber.contains("\r")) {

	        System.out.println("Invalid vehicle number.");
	        return;
	    }

	    if (activeVehicleNumbers.contains(vehicleNumber)) {
	        System.out.println("Vehicle is already parked.");
	        return;
	    }

	    int[] slots = buildSlots();

	    int freeSlot = -1;

	    for (int i = 0; i < slots.length; i++) {

	        if (slots[i] == 0) {
	            freeSlot = i + 1;
	            break;
	        }
	    }

	    if (freeSlot == -1) {
	        System.out.println("Parking full");
	        return;
	    }

	    int newId = 1;

	    for (ParkingEntry entry : entries) {

	        if (entry.getId() >= newId) {
	            newId = entry.getId() + 1;
	        }
	    }

	    ParkingEntry entry;

	    if (vehicleType.equals("TwoWheeler")) {

	        entry = new TwoWheelerEntry(
	                newId,
	                vehicleNumber,
	                freeSlot,
	                ParkingStatus.PARKED
	        );

	    } else if (vehicleType.equals("FourWheeler")) {

	        entry = new FourWheelerEntry(
	                newId,
	                vehicleNumber,
	                freeSlot,
	                ParkingStatus.PARKED
	        );

	    } else {

	        System.out.println("Invalid vehicle type.");
	        return;
	    }

	    entries.add(entry);

	    activeVehicleNumbers.add(vehicleNumber);

	    FileManager.saveEntries(entries);

	    System.out.println(
	            "Vehicle parked successfully. Slot: "
	                    + freeSlot
	    );
	}
	
	public void recordExit(int entryId) throws IOException {

	    ParkingEntry foundEntry = null;

	    for (ParkingEntry entry : entries) {

	        if (entry.getId() == entryId) {

	            foundEntry = entry;
	            break;
	        }
	    }

	    if (foundEntry == null) {

	        System.out.println("Entry ID not found.");
	        return;
	    }

	    if (foundEntry.getStatus() != ParkingStatus.PARKED) {

	        System.out.println("Vehicle has already exited.");
	        return;
	    }

	    foundEntry.exit();

	    FileManager.saveEntries(entries);

	    rebuildActiveVehicles();

	    System.out.println("Vehicle exit recorded successfully.");
	}
	
	public void showReport() throws IOException {

	    int[] slots = buildSlots();

	    int occupiedSlots = 0;

	    for (int slot : slots) {

	        if (slot != 0) {
	            occupiedSlots++;
	        }
	    }

	    int freeSlots = 5 - occupiedSlots;

	    int exitedEntries = 0;

	    for (ParkingEntry entry : entries) {

	        if (entry.getStatus() == ParkingStatus.EXITED) {
	            exitedEntries++;
	        }
	    }

	    int totalEntries = entries.size();

	    int activeVehicleCount =
	            activeVehicleNumbers.size();


	    String report =
	            "Vehicle Parking Management Report\n" +
	            "---------------------------------\n" +
	            "Total Slots: 5\n" +
	            "Occupied Slots: " + occupiedSlots + "\n" +
	            "Free Slots: " + freeSlots + "\n" +
	            "Total Recorded Entries: " + totalEntries + "\n" +
	            "Exited Entries: " + exitedEntries + "\n" +
	            "Active Vehicle Count: " + activeVehicleCount + "\n";


	    System.out.println(report);

	    FileManager.saveReport(report);
	}

	public List<ParkingEntry> getEntries() {

	    return new ArrayList<>(entries);
	}
 }
