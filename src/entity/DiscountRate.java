package entity;

public class DiscountRate {

    private String subscriptionType;
    private String currentDiscountRate;
    private String newDiscountRate;

    public DiscountRate() {

    }

    public DiscountRate(String subscriptionType, String currentDiscountRate, String newDiscountRate) {
        this.subscriptionType = subscriptionType;
        this.currentDiscountRate = currentDiscountRate;
        this.newDiscountRate = newDiscountRate;
    }

    @Override
    public String toString() {
        return "DiscountRate{" +
                "subscriptionType='" + subscriptionType + '\'' +
                ", currentDiscountRate='" + currentDiscountRate + '\'' +
                ", newDiscountRate='" + newDiscountRate + '\'' +
                '}';
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getCurrentDiscountRate() {
        return currentDiscountRate;
    }

    public void setCurrentDiscountRate(String currentDiscountRate) {
        this.currentDiscountRate = currentDiscountRate;
    }

    public String getNewDiscountRate() {
        return newDiscountRate;
    }

    public void setNewDiscountRate(String newDiscountRate) {
        this.newDiscountRate = newDiscountRate;
    }
}
