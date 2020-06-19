package entity;

public enum OrderDeliveryStatus {
    CONFIRMED_ORDER("CONFIRMED_ORDER"),
    PREPARING_TO_SHIP("PREPARING_TO_SHIP"),
    IN_TRANSIT("IN_TRANSIT"),
    COMPLETED("COMPLETED");

    private String statusStr;

    OrderDeliveryStatus(String s) {
        this.statusStr = s;
    }

    public String getOrderDeliveryStatus() {
        return statusStr;
    }
}
