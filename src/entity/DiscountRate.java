package entity;

/**
 * Discount Rate class
 *
 * @author Nir Asulin
 */
public class DiscountRate {

    private String subscriptionType;
    private String currentDiscountRate;
    private String newDiscountRate;

    /**
     * empty Constructor
     */
    public DiscountRate() {

    }

    /**
     * Constructor
     *
     * @param subscriptionType
     * @param currentDiscountRate
     * @param newDiscountRate
     */
    public DiscountRate(String subscriptionType, String currentDiscountRate, String newDiscountRate) {
        this.subscriptionType = subscriptionType;
        this.currentDiscountRate = currentDiscountRate;
        this.newDiscountRate = newDiscountRate;
    }

    /**
     * toString
     *
     * @return String - Produces a class string
     */
    @Override
    public String toString() {
        return "DiscountRate{" +
                "subscriptionType='" + subscriptionType + '\'' +
                ", currentDiscountRate='" + currentDiscountRate + '\'' +
                ", newDiscountRate='" + newDiscountRate + '\'' +
                '}';
    }

    /**
     * get Subscription Type method
     *
     * @return String - SubscriptionType
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * set subscription Type method
     *
     * @param subscriptionType
     */
    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    /**
     * get Current Discount Rate method
     *
     * @return String - CurrentDiscountRate
     */
    public String getCurrentDiscountRate() {
        return currentDiscountRate;
    }

    /**
     * set current Discount Rate method
     *
     * @param currentDiscountRate
     */
    public void setCurrentDiscountRate(String currentDiscountRate) {
        this.currentDiscountRate = currentDiscountRate;
    }

    /**
     * get get New Discount Rate Type method
     *
     * @return String - getNewDiscountRate
     */
    public String getNewDiscountRate() {
        return newDiscountRate;
    }

    /**
     * set new Discount Rate method
     *
     * @param newDiscountRate
     */
    public void setNewDiscountRate(String newDiscountRate) {
        this.newDiscountRate = newDiscountRate;
    }
}