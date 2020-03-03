package ua.electron.service.lowerLevel.orderInfo;

import ua.electron.dao.IOrderDao;
import ua.electron.dao.IProductDao;
import ua.electron.dao.IUserDao;
import ua.electron.entity.FullOrderInfo;
import ua.electron.entity.Order;
import ua.electron.entity.Product;
import ua.electron.entity.User;
import ua.electron.entity.builder.FullOrderInfoBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LastUserOrder {

    private FullOrderInfoBuilder fullOrderInfoBuilder = new FullOrderInfoBuilder();
    private List<Product> productList = new ArrayList<>();

    public Optional<FullOrderInfo> getFullOrderInfo(IOrderDao orderDao, int userId, IUserDao userDao, IProductDao productDao) {
        clearProductLis();
        Order order = orderDao.lastUserOrderInfo(userId).get();
        User user = userDao.getUserById(userId).get();
        String[] stringProductsQuantity = order.getQuantity().split(",");
        String[] stringProductsId = order.getProductId().split(",");
        int productId;

        for (String str : stringProductsId) {
            productId = Integer.parseInt(str);
            productList.add(productDao.getProductById(productId).get());
        }

        fullOrderInfoBuilder.buildOrderId(order.getId())
                .buildUserId(user.getUserId())
                .buildUserEmail(user.getEmail())
                .buildFirstName(user.getFirstName())
                .buildSecondName(user.getSecondName())
                .buildCity(user.getCity())
                .buildPhoneNumber(user.getPhoneNumber())
                .buildProductList(productList)
                .buildQuantity(stringProductsQuantity)
                .buildTotalPrice(order.getTotalPrice())
                .buildDeliveryAddress(order.getDeliveryAddress())
                .buildDate(order.getDate())
                .buildTime(order.getTime())
                .buildStatus(order.getStatus())
                .buildComment(order.getComment());

        return Optional.of(fullOrderInfoBuilder.build());
    }

    private void clearProductLis(){
        if (!productList.isEmpty()) {
            productList.clear();
        }
    }
}
