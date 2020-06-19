package entity;

import org.omg.CORBA.INTERNAL;

/**
 * @see ManagerNotifications - the form's entity class
 */
public class ManagerNotifications {
    String orderNumber;
    Integer stationNumber;

    public ManagerNotifications(String orderNumber, Integer stationNumber){
        this.orderNumber=orderNumber;
        this.stationNumber=stationNumber;
    }

    /**
     * Getters
     * **/
    public String getOrderNumber() { return orderNumber; }
    public Integer getStationNumber() { return stationNumber; }

    /**
     * Setters
     * **/
    public void setStationNumber(Integer stationNumber) { this.stationNumber = stationNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber;}

    @Override
    public String toString() {
        return "ManagerNotifications{" +
                "orderNumber='" + orderNumber + '\'' +
                ", stationNumber=" + stationNumber +
                '}';
    }
}
