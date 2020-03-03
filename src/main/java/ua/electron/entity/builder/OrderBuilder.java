package ua.electron.entity.builder;

import ua.electron.entity.Order;

import java.sql.Date;
import java.sql.Time;

public class OrderBuilder {
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

    public void buildId(int id) {
        this.id = id;
    }

    public OrderBuilder buildUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public OrderBuilder buildProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public OrderBuilder buildQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderBuilder buildTotalPrice(int totalPrice){
        this.totalPrice = totalPrice;
        return this;
    }

    public OrderBuilder buildDeliveryAddress(String deliveryAddress){
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public OrderBuilder buildDate(Date date){
        this.date = date;
        return this;
    }

    public OrderBuilder buildTime(Time time){
        this.time = time;
        return this;
    }

    public OrderBuilder buildOrderStatus(String orderStatus){
        this.status = orderStatus;
        return this;
    }

    public OrderBuilder buildComment(String comment){
        this.comment = comment;
        return this;
    }

    public OrderBuilder buildDeliveryService(String deliveryService){
        this.deliveryService = deliveryService;
        return this;
    }

    public OrderBuilder buildDeliveryNote(String deliveryNote){
        this.deliveryNote = deliveryNote;
        return this;
    }

    public Order buildOrder() {
        Order order = new Order();

        order.setId(id);
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        order.setDeliveryAddress(deliveryAddress);
        order.setDate(date);
        order.setTime(time);
        order.setStatus(status);
        order.setComment(comment);
        order.setDeliveryService(deliveryService);
        order.setDeliveryNote(deliveryNote);

        return order;
    }

}
