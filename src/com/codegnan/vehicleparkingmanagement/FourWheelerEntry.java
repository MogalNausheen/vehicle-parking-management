package com.codegnan.vehicleparkingmanagement;

public class FourWheelerEntry extends ParkingEntry{

	public FourWheelerEntry(int id, String vehicleNumber, int slotNumber, ParkingStatus status) {
		super(id, vehicleNumber, slotNumber, status);
		
	}

	@Override
	public String getVehicleType() {
		return "FourWheeler";
	}

}
