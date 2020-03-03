package ua.electron.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Order {

    private int id;
    private int userId;
    private String productId;
    private String quantity;
    private int totalPrice;
    private String deliveryAddress;
    private Date date;
    private Time time;
    private String status;
    private String comment;
    private String deliveryService;
    private String deliveryNote;

    public Order() {
    }

    public int getTotalPrice() {
        return totalPrice;
    }


    public int getId() {
        return id;
    }


    public int getUserId() {
        return userId;
    }


    public String getProductId() {
        return productId;
    }


    public String getQuantity() {
        return quantity;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public Date getDate() {
        return date;
    }


    public Time getTime() {
        return time;
    }


    public String getStatus() {
        return status;
    }


    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDeliveryService() {
        return deliveryService;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryService(String deliveryService) {
        this.deliveryService = deliveryService;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                userId == order.userId &&
                totalPrice == order.totalPrice &&
                Objects.equals(productId, order.productId) &&
                Objects.equals(quantity, order.quantity) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                Objects.equals(date, order.date) &&
                Objects.equals(time, order.time) &&
                Objects.equals(status, order.status) &&
                Objects.equals(comment, order.comment) &&
                Objects.equals(deliveryService, order.deliveryService) &&
                Objects.equals(deliveryNote, order.deliveryNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, quantity, totalPrice, deliveryAddress, date, time, status, comment, deliveryService, deliveryNote);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId='" + productId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", totalPrice=" + totalPrice +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", deliveryService='" + deliveryService + '\'' +
                ", deliveryNote='" + deliveryNote + '\'' +
                '}';
    }
}
