package com.codegnan.vehicleparkingmanagement;

public abstract class ParkingEntry {

	private int id;
	private String vehicleNumber;
	private int slotNumber;
	private ParkingStatus status;
	
	public ParkingEntry(int id, String vehicleNumber, int slotNumber, ParkingStatus status) {
		super();
		this.id = id;
		this.vehicleNumber = vehicleNumber;
		this.slotNumber = slotNumber;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public ParkingStatus getStatus() {
		return status;
	}

	public void setStatus(ParkingStatus status) {
		this.status = status;
	}
	
	public void exit() {
		this.status = ParkingStatus.EXITED;
	}
	
	public abstract String getVehicleType();
	
	
	
}
