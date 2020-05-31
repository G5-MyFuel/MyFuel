package common.entity;

import java.util.Objects;


/**
 * @author itay
 * @see Vehicle - the form's entity class
 */

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


    //need to implements comparator for table view.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return VehicleID.equals(vehicle.VehicleID) &&
                GasType.equals(vehicle.GasType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(VehicleID, GasType);
    }
}
