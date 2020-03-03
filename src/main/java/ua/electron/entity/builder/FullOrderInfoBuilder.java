package ua.electron.entity.builder;

import ua.electron.entity.FullOrderInfo;
import ua.electron.entity.Product;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

public class FullOrderInfoBuilder {

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

    public FullOrderInfoBuilder buildOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public FullOrderInfoBuilder buildUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public FullOrderInfoBuilder buildUserEmail(String email) {
        this.email = email;
        return this;
    }

    public FullOrderInfoBuilder buildFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public FullOrderInfoBuilder buildSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public FullOrderInfoBuilder buildCity(String city) {
        this.city = city;
        return this;
    }

    public FullOrderInfoBuilder buildPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public FullOrderInfoBuilder buildProductList(List<Product> productList) {
        this.productList = productList;
        return this;
    }

    public FullOrderInfoBuilder buildQuantity(String[] quantity) {
        this.quantity = quantity;
        return this;
    }

    public FullOrderInfoBuilder buildTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public FullOrderInfoBuilder buildDeliveryAddress(String deliveryAddress){
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public FullOrderInfoBuilder buildDate(Date date) {
        this.date = date;
        return this;
    }

    public FullOrderInfoBuilder buildTime(Time time) {
        this.time = time;
        return this;
    }

    public FullOrderInfoBuilder buildStatus(String status){
        this.status = status;
        return this;
    }

    public FullOrderInfoBuilder buildComment(String comment){
        this.comment = comment;
        return this;
    }

    public FullOrderInfoBuilder buildDeliveryService(String deliveryService){
        this.deliveryService = deliveryService;
        return this;
    }

    public FullOrderInfoBuilder buildDeliveryNote(String deliveryNote){
        this.deliveryNote = deliveryNote;
        return this;
    }

    public FullOrderInfo build() {
        FullOrderInfo fullOrderInfo = new FullOrderInfo();

        fullOrderInfo.setOrderId(orderId);
        fullOrderInfo.setUserId(userId);
        fullOrderInfo.setEmail(email);
        fullOrderInfo.setFirstName(firstName);
        fullOrderInfo.setSecondName(secondName);
        fullOrderInfo.setCity(city);
        fullOrderInfo.setPhoneNumber(phoneNumber);
        fullOrderInfo.setProductList(productList);
        fullOrderInfo.setQuantity(quantity);
        fullOrderInfo.setTotalPrice(totalPrice);
        fullOrderInfo.setDeliveryAddress(deliveryAddress);
        fullOrderInfo.setDate(date);
        fullOrderInfo.setTime(time);
        fullOrderInfo.setStatus(status);
        fullOrderInfo.setComment(comment);
        fullOrderInfo.setDeliveryService(deliveryService);
        fullOrderInfo.setDeliveryNote(deliveryNote);

        return fullOrderInfo;
    }
}
