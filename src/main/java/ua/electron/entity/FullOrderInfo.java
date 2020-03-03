package ua.electron.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

public class FullOrderInfo {

    private int orderId;
    private int userId;
    private String email;
    private String firstName;
    private String secondName;
    private String city;
    private int phoneNumber;
    private List<Product> productList;
    private String[] quantity;
    private int totalPrice;
    private String deliveryAddress;
    private Date date;
    private Time time;
    private String status;
    private String comment;
    private String deliveryService;
    private String deliveryNote;

    public FullOrderInfo() {
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getCity() {
        return city;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
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

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setQuantity(String[] quantity) {
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
    public String toString() {
        return "FullOrderInfo{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", productList=" + productList +
                ", quantity=" + Arrays.toString(quantity) +
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
