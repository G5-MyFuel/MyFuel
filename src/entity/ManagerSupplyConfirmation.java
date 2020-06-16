package entity;

public class ManagerSupplyConfirmation {
    private String OrderNumber, companyName, FuelType, orderStatus;
    Integer StationNum, Quantity;

    public ManagerSupplyConfirmation(String orderNumber, String companyName, Integer stationNum, String fuelType, Integer quantity, String orderStatus) {
        this.OrderNumber = orderNumber;
        this.companyName = companyName;
        this.StationNum = stationNum;
        this.FuelType = fuelType;
        this.Quantity = quantity;
        this.orderStatus = orderStatus;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getStationNum() {
        return StationNum;
    }

    public void setStationNum(int stationNum) {
        StationNum = stationNum;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public String getOrderStatus() {return orderStatus; }

    @Override
    public String toString() {
        return "ManagerSupplyConfirmation{" +
                "OrderNumber='" + OrderNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", StationNum='" + StationNum + '\'' +
                ", FuelType='" + FuelType + '\'' +
                ", Quantity='" + Quantity + '\'' +
                '}';
    }
}
