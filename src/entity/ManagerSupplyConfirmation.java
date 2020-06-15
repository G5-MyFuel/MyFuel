package entity;

public class ManagerSupplyConfirmation {
    private String OrderNumber,companyName,FuelType;
    int StationNum,Quantity;
    public ManagerSupplyConfirmation(String orderNumber, String companyName, int stationNum, String fuelType, int quantity) {
        OrderNumber = orderNumber;
        this.companyName = companyName;
        StationNum = stationNum;
        FuelType = fuelType;
        Quantity = quantity;
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

    public int getStationNum() {
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

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
