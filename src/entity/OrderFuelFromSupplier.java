package entity;

import Contollers.OrderFromSupplierController;

import java.util.Date;

/**
 * @author Adi Lampert
 * @see OrderFuelFromSupplier - The entity
 */

public class OrderFuelFromSupplier {
    private String OrderNum, FuelType, userFirstName,userLastName, OrderStatus,GasCompanyName;
    private int StationNumber, Quantity;
    private Date OrderDate;

    public OrderFuelFromSupplier(String OrderNumber, String OrderStatus, String userFirstName, String userLastName, int StationNumber, Date OrderDate, String FuelType, int Quantity, String GasCompanyName) {
        this.OrderNum = OrderNumber;
        this.OrderStatus=OrderStatus;
        this.userFirstName=userFirstName;
        this.userLastName=userLastName;
        this.StationNumber=StationNumber;
        this.OrderDate=OrderDate;
        this.FuelType=FuelType;
        this.Quantity=Quantity;
        this.GasCompanyName=GasCompanyName;
    }

    /**
     Getters
     */

    public String getOrderNum() {
        return OrderNum;
    }

    public String getGasCompanyName() {
        return GasCompanyName;
    }

    public String getOrderNumber() {
        return OrderNum;
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


    public int getStationNumber() {
        return StationNumber;
    }

    public int getQuantity() {
        return Quantity;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    /**
     Setter
     */
    public void setOrderNum(String orderNum) {
        OrderNum = orderNum;
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

    public void setStationNumber(int stationNumber) {
        StationNumber = stationNumber;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public void setGasCompanyName(String gasCompanyName) {
        GasCompanyName = gasCompanyName;
    }

    /**
     To String
     */
    @Override
    public String toString() {
        return "OrderFuelFromSupplier{" +
                "OrderNumber='" + OrderNum + '\'' +
                ", FuelType='" + FuelType + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", OrderStatus='" + OrderStatus + '\'' +
                ", StationNumber=" + StationNumber +
                ", Quantity=" + Quantity +
                ", OrderDate=" + OrderDate +
                '}';
    }
}
