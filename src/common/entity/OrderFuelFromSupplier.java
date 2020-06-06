package common.entity;

import java.util.Date;

public class OrderFuelFromSupplier {
    private String OrderNumber, FuelType, StationManagerName;
    private int StationNum,Quantity;
    private Date OrderDate;
    private String OrderStatus;

    public OrderFuelFromSupplier(String OrderNumber, String CompanyName, String StationManagerName, int StationNum, Date OrderDate, int Quantity,String OrderStatus){
        this.FuelType=CompanyName;
        this.OrderNumber=OrderNumber;
        this.Quantity=Quantity;
        this.StationManagerName=StationManagerName;
        this.StationNum=StationNum;
        this.OrderDate = OrderDate;
        this.OrderStatus = OrderStatus;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }
    public String getFuelType() {
        return FuelType;
    }
    public String getStationManagerName() {
        return StationManagerName;
    }
    public Integer getStationNum() { return StationNum; }
    public Integer getQuantity() {
        return Quantity;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public void setFuelType(String companyName) {
        FuelType = companyName;
    }

    public void setStationManagerName(String stationManagerName) {
        StationManagerName = stationManagerName;
    }

    public void setStationNum(int stationNum) {
        StationNum = stationNum;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }
}
