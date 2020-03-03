package ua.electron.service.lowerLevel.orderCreator.impl;

import ua.electron.entity.Constant;
import ua.electron.entity.Order;
import ua.electron.entity.User;
import ua.electron.entity.builder.OrderBuilder;
import ua.electron.service.topLevel.shoppingCartService.impl.PresenterProductFromCookieServiceImpl;
import ua.electron.service.lowerLevel.orderCreator.IOrderCreator;
import ua.electron.service.lowerLevel.shoppingCartActions.PriceCounter;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.Set;

public class OrderCreatorImpl implements IOrderCreator {

    private OrderBuilder orderBuilder = new OrderBuilder();

    @Override
    public Optional<Order> orderValidatorAndCreator(HttpServletRequest request,
                                                    PresenterProductFromCookieServiceImpl presenterProductFromCookieService) {
        User user;
        Set<PriceCounter> counterTotalPriceForOneProduct = presenterProductFromCookieService.totalPriceForOneProduct(request);
        int userId;
        StringBuilder productsIdBuilder = new StringBuilder();
        StringBuilder quantityBuilder = new StringBuilder();
        int totalPrice = 0;
        long d = System.currentTimeMillis();
        long t = System.currentTimeMillis();
        Date date = new Date(d);
        Time time = new Time(t);

        if (request.getParameter("createOrder") != null) {
            user = (User) request.getSession().getAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED));
            userId = user.getUserId();
            for (PriceCounter counter : counterTotalPriceForOneProduct) {
                productsIdBuilder.append(counter.getProductIdFromCookie()).append(",");
                quantityBuilder.append(counter.getProductQuantityFromCookie()).append(",");
                totalPrice += counter.getProductPrice();
            }
            String productsId = productsIdBuilder.toString();
            String quantity = quantityBuilder.toString();

            orderBuilder.buildUserId(userId)
                    .buildProductId(productsId)
                    .buildQuantity(quantity)
                    .buildTotalPrice(totalPrice)
                    .buildDeliveryAddress(String.valueOf(Constant.NOTHING))
                    .buildDate(date)
                    .buildTime(time)
                    .buildOrderStatus(String.valueOf(Constant.IN_PROCESSING))
                    .buildComment(String.valueOf(Constant.NOTHING))
                    .buildDeliveryService(String.valueOf(Constant.NOTHING))
                    .buildDeliveryNote(String.valueOf(Constant.NOTHING));

            return Optional.of(orderBuilder.buildOrder());
        }
        return Optional.empty();
    }
}
