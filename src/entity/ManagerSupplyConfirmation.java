package entity;
/**
 * @author Adi Lampert
 * @see ManagerSupplyConfirmation - The entity
 */
public class ManagerSupplyConfirmation {
    private String OrderNumber, companyName, FuelType, orderStatus, managerID;
    Integer StationNum, Quantity;

    /**
     * Constructor
     * @param orderNumber
     * @param companyName
     * @param stationNum
     * @param fuelType
     * @param quantity
     * @param orderStatus
     * @param managerID
     */
    public ManagerSupplyConfirmation(String orderNumber, String companyName, Integer stationNum, String fuelType, Integer quantity, String orderStatus, String managerID) {
        this.OrderNumber = orderNumber;
        this.companyName = companyName;
        this.StationNum = stationNum;
        this.FuelType = fuelType;
        this.Quantity = quantity;
        this.orderStatus = orderStatus;
        this.managerID=managerID;
    }

    /**
     *
     * Getters and Setters
     */
    public String getManagerID() { return managerID; }

    public void setManagerID(String managerID) { this.managerID = managerID; }

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

    /**
     * To string
     * @return
     */
    @Override
    public String toString() {
        return "ManagerSupplyConfirmation{" +
                "OrderNumber='" + OrderNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", FuelType='" + FuelType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", managerID='" + managerID + '\'' +
                ", StationNum=" + StationNum +
                ", Quantity=" + Quantity +
                '}';
    }
}
