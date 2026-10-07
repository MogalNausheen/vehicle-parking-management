package com.codegnan.vehicleparkingmanagement;

public class TwoWheelerEntry extends ParkingEntry{

	public TwoWheelerEntry(int id, String vehicleNumber, int slotNumber, ParkingStatus status) {
		super(id, vehicleNumber, slotNumber, status);
		
	}

	@Override
	public String getVehicleType() {
		return "TwoWheeler";
	}

}
