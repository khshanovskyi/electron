package ua.electron.service.lowerLevel.orderInfo;

import ua.electron.dao.IOrderDao;
import ua.electron.dao.IProductDao;
import ua.electron.dao.IUserDao;
import ua.electron.entity.*;
import ua.electron.entity.builder.FullOrderInfoBuilder;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class UserOrdersWithStatusProcessed {


    private FullOrderInfoBuilder fullOrderInfoBuilder = new FullOrderInfoBuilder();
    private List<FullOrderInfo> allOrderList = new ArrayList<>();
    private String[] stringProductsId;

    public List<FullOrderInfo> getAllUserOrdersWhereStatusIs(IOrderDao orderDao, int userId, IUserDao userDao,
                                                             IProductDao productDao) {
        clearAllOrderList();
        List<Order> orderList = orderDao.allUserOrdersInfoWithSearchingStatus(userId,String.valueOf(Constant.PROCESSED));
        User user = userDao.getUserById(userId).get();

        for (Order order : orderList) {
            stringProductsId = order.getProductId().split(",");
            String[] stringProductsQuantity = order.getQuantity().split(",");

            allOrderList.add(createFullOrderInfo(order.getId(), user.getUserId(), user.getEmail(), user.getFirstName(),
                    user.getSecondName(), user.getCity(), user.getPhoneNumber(), getProductList(productDao),
                    stringProductsQuantity, order.getTotalPrice(), order.getDeliveryAddress(), order.getDate(), order.getTime(),
                    order.getStatus(), order.getComment(), order.getDeliveryService(), order.getDeliveryNote()));
        }
        return allOrderList;
    }

    private FullOrderInfo createFullOrderInfo(int orderId, int userId, String email, String firstName, String secondName, String city,
                                              int phoneNumber, List<Product> productList, String[] productsQuantity,
                                              int totalPrice, String deliveryAddress, Date date, Time time, String status,
                                              String comment, String deliveryService, String deliveryNote) {
        fullOrderInfoBuilder.buildOrderId(orderId)
                .buildUserId(userId)
                .buildUserEmail(email)
                .buildFirstName(firstName)
                .buildSecondName(secondName)
                .buildCity(city)
                .buildPhoneNumber(phoneNumber)
                .buildProductList(productList)
                .buildQuantity(productsQuantity)
                .buildTotalPrice(totalPrice)
                .buildDeliveryAddress(deliveryAddress)
                .buildDate(date)
                .buildTime(time)
                .buildStatus(status)
                .buildComment(comment)
                .buildDeliveryService(deliveryService)
                .buildDeliveryNote(deliveryNote);
        return fullOrderInfoBuilder.build();
    }

    private void clearAllOrderList() {
        if (!allOrderList.isEmpty()) {
            allOrderList.clear();
        }
    }

    private List<Product> getProductList(IProductDao productDao) {
        List<Product> productList = new ArrayList<>();
        for (String str : stringProductsId) {
            int productId = Integer.parseInt(str);
            productList.add(productDao.getProductById(productId).get());
        }
        return productList;
    }
}
