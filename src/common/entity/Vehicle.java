package common.entity;

public class Vehicle {

    private String VehicleID;
    private String GasType;

    public Vehicle(String vehicleID, String gasType) {
        VehicleID = vehicleID;
        GasType = gasType;
    }

    public String getVehicleID() {
        return VehicleID;
    }

    public String getGasType() {
        return GasType;
    }

    public void setVehicleID(String vehicleID) {
        VehicleID = vehicleID;
    }

    public void setGasType(String gasType) {
        GasType = gasType;
    }
}
