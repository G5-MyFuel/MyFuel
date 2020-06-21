package entity;

import org.omg.CORBA.INTERNAL;

/**
 * @author Adi Lampert
 * @see ManagerNotifications - The entity
 */

public class ManagerNotifications {
    String orderNumber,FuelType;
    Integer stationNumber,Quantity;


    public ManagerNotifications(String orderNumber, Integer stationNumber, String FuelType, Integer Quantity){
        this.orderNumber=orderNumber;
        this.stationNumber=stationNumber;
        this.FuelType=FuelType;
        this.Quantity=Quantity;
    }

    /**
     * Getters
     * **/
    public String getOrderNumber() { return orderNumber; }
    public Integer getStationNumber() { return stationNumber; }
    public String getFuelType() { return FuelType; }
    public Integer getQuantity() { return Quantity; }

    /**
     * Setters
     * **/
    public void setStationNumber(Integer stationNumber) { this.stationNumber = stationNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber;}
    public void setFuelType(String fuelType) { FuelType = fuelType; }
    public void setQuantity(Integer quantity) { Quantity = quantity; }

    @Override
    public String toString() {
        return "ManagerNotifications{" +
                "orderNumber='" + orderNumber + '\'' +
                ", stationNumber=" + stationNumber +
                '}';
    }
}
