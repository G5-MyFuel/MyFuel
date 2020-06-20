package entity;

import Contollers.OrderFromSupplierController;

import java.util.Date;

/**
 * @author Adi Lampert
 * @see OrderFuelFromSupplier - The entity
 */

public class OrderFuelFromSupplier {
    private String OrderNum, FuelType, userFirstName,userLastName, OrderStatus,GasCompanyName
            ,managerID,userEmail,inventory95,inventoryDiesel,inventoryScooter;
    private Integer StationNumber, Quantity;
    private Date OrderDate;

    public OrderFuelFromSupplier(String OrderNumber, String OrderStatus, String userFirstName, String userLastName, Integer StationNumber,
                                 Date OrderDate, String FuelType, Integer Quantity, String GasCompanyName, String managerID,
                                 String userEmail, String inventory95, String inventoryDiesel, String inventoryScooter) {
        this.OrderNum = OrderNumber;
        this.OrderStatus=OrderStatus;
        this.userFirstName=userFirstName;
        this.userLastName=userLastName;
        this.StationNumber=StationNumber;
        this.OrderDate=OrderDate;
        this.FuelType=FuelType;
        this.Quantity=Quantity;
        this.GasCompanyName=GasCompanyName;
        this.managerID=managerID;
        this.userEmail=userEmail;
        this.inventory95=inventory95;
        this.inventoryDiesel=inventoryDiesel;
        this.inventoryScooter=inventoryScooter;
    }

    /**
     Getters
     */

    public String getManagerID() {
        return managerID;
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
    public Integer getStationNumber() {
        return StationNumber;
    }
    public Integer getQuantity() {
        return Quantity;
    }
    public Date getOrderDate() {
        return OrderDate;
    }
    public String getUserEmail() { return userEmail; }
    public String getInventory95() { return inventory95; }
    public String getInventoryDiesel() { return inventoryDiesel; }
    public String getInventoryScooter() { return inventoryScooter; }

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
     * To String
     */
    @Override
    public String toString() {
        return "OrderFuelFromSupplier{" +
                "OrderNum='" + OrderNum + '\'' +
                ", FuelType='" + FuelType + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", OrderStatus='" + OrderStatus + '\'' +
                ", GasCompanyName='" + GasCompanyName + '\'' +
                ", managerID='" + managerID + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", StationNumber=" + StationNumber +
                ", Quantity=" + Quantity +
                ", OrderDate=" + OrderDate +
                '}';
    }
}
