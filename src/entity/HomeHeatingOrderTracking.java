package entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class HomeHeatingOrderTracking extends RecursiveTreeObject<HomeHeatingOrderTracking> {
    public String OrderDate;
    public  String OrderTime;
    public OrderDeliveryStatus status;
    public String statusStr;
    public String expectedDeliveryDate;


    public HomeHeatingOrderTracking(String orderDate, String orderTime, String statusStr, String expectedDeliveryDate) {
        this.OrderDate = orderDate;
        this.OrderTime = orderTime;
        this.statusStr = statusStr;
        status = OrderDeliveryStatus.valueOf(statusStr);
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }

    public OrderDeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(OrderDeliveryStatus status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
}
