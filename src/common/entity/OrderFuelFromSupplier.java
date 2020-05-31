package common.entity;

public class OrderFuelFromSupplier {
    private String OrderNumber, CompanyName, StationManagerName;
    private int StationNum,Quantity;

    public OrderFuelFromSupplier(String OrderNumber, String CompanyName, String StationManagerName, int StationNum, int Quantity){
        this.CompanyName=CompanyName;
        this.OrderNumber=OrderNumber;
        this.Quantity=Quantity;
        this.StationManagerName=StationManagerName;
        this.StationNum=StationNum;
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
