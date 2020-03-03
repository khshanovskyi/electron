package ua.electron.service.lowerLevel.orderInfo;

import ua.electron.dao.IOrderDao;
import ua.electron.dao.IProductDao;
import ua.electron.dao.IUserDao;
import ua.electron.entity.*;
import ua.electron.entity.builder.FullOrderInfoBuilder;

import java.util.ArrayList;
import java.util.List;

public class UserOrdersWithStatusFormed {

    private FullOrderInfoBuilder fullOrderInfoBuilder = new FullOrderInfoBuilder();
    private List<FullOrderInfo> allOrderList = new ArrayList<>();
    private String[] stringProductsId;

    public List<FullOrderInfo> getAllUserOrdersWhereStatusIs(IOrderDao orderDao, int userId, IUserDao userDao,
                                                             IProductDao productDao) {
        clearAllOrderList();
        List<Order> orderList = orderDao.allUserOrdersInfoWithSearchingStatus(userId,String.valueOf(Constant.FORMED));
        User user = userDao.getUserById(userId).get();

        for (Order order : orderList) {
            stringProductsId = order.getProductId().split(",");
            String[] stringProductsQuantity = order.getQuantity().split(",");

            fullOrderInfoBuilder.buildOrderId(order.getId())
                    .buildUserId(user.getUserId())
                    .buildUserEmail(user.getEmail())
                    .buildFirstName(user.getFirstName())
                    .buildSecondName(user.getSecondName())
                    .buildCity(user.getCity())
                    .buildPhoneNumber(user.getPhoneNumber())
                    .buildProductList(getProductList(productDao))
                    .buildQuantity(stringProductsQuantity)
                    .buildTotalPrice(order.getTotalPrice())
                    .buildDeliveryAddress(order.getDeliveryAddress())
                    .buildDate(order.getDate())
                    .buildTime(order.getTime())
                    .buildStatus(order.getStatus())
                    .buildComment(order.getComment())
                    .buildDeliveryService(order.getDeliveryService())
                    .buildDeliveryNote(order.getDeliveryNote());

            allOrderList.add(fullOrderInfoBuilder.build());
        }
        return allOrderList;
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
