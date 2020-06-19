package entity;

/**
 * @see ManagerNotifications - the form's entity class
 */
public class ManagerNotifications {
    String orderNumber;

    public ManagerNotifications(String orderNumber){
        this.orderNumber=orderNumber;
    }

    public String getOrderNumber() { return orderNumber; }

    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber;}

    @Override
    public String toString() {
        return "ManagerNotifications{" +
                "orderNumber='" + orderNumber + '\'' +
                '}';
    }
}
