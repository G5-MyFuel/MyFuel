package common.entity;

import java.util.Date;

public class OrderFuelFromSupplier {
    private String OrderNumber, CompanyName, StationManagerName;
    private int StationNum,Quantity;
    private Date OrderDate;
    private String OrderStatus;
    public OrderFuelFromSupplier(String OrderNumber, String CompanyName, String StationManagerName, int StationNum, Date OrderDate, int Quantity,String OrderStatus){
        this.CompanyName=CompanyName;
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
    public String getCompanyName() {
        return CompanyName;
    }
    public String getStationManagerName() {
        return StationManagerName;
    }
    public int getStationNum() {
        return StationNum;
    }
    public int getQuantity() {
        return Quantity;
    }

}
