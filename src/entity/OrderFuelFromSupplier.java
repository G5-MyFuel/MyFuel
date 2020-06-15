package entity;

import Contollers.OrderFromSupplierController;

import java.util.Date;

/**
 * @author Adi Lampert
 * @see OrderFuelFromSupplier - The entity
 */

public class OrderFuelFromSupplier {
    private String OrderNumber, FuelType, userFirstName,userLastName, OrderStatus, GasCompanyName;
    private int StationNumber, Quantity;
    private Date OrderDate;

    public OrderFuelFromSupplier(String OrderNumber, String OrderStatus, String userFirstName, String userLastName, int StationNumber, Date OrderDate, String FuelType, int Quantity ) {
        this.OrderNumber = OrderNumber;
        this.OrderStatus=OrderStatus;
        this.userFirstName=userFirstName;
        this.userLastName=userLastName;
        this.StationNumber=StationNumber;
        this.OrderDate=OrderDate;
        this.FuelType=FuelType;
        this.Quantity=Quantity;
    }

    /**
     Setters
     */

    public String getOrderNumber() {
        return OrderNumber;
    }

    public String getFuelType() {
        return FuelType;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public String getGasCompanyName() {
        return GasCompanyName;
    }

    public Integer getStationNumber() {
        return StationNumber;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    /**
     Getters
     */
    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public void setGasCompanyName(String gasCompanyName) {
        GasCompanyName = gasCompanyName;
    }

    public void setStationNumber(int stationNumber) {
        StationNumber = stationNumber;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    /**
     To String
     */
    @Override
    public String toString() {
        return "OrderFuelFromSupplier{" +
                "OrderNumber='" + OrderNumber + '\'' +
                ", FuelType='" + FuelType + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", OrderStatus='" + OrderStatus + '\'' +
                ", GasCompanyName='" + GasCompanyName + '\'' +
                ", StationNumber=" + StationNumber +
                ", Quantity=" + Quantity +
                ", OrderDate=" + OrderDate +
                '}';
    }
}
