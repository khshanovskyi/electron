package ua.electron.service.lowerLevel.orderCreator;

import ua.electron.entity.Order;
import ua.electron.service.topLevel.shoppingCartService.impl.PresenterProductFromCookieServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface IOrderCreator {

    Optional<Order> orderValidatorAndCreator(HttpServletRequest request,
                                             PresenterProductFromCookieServiceImpl presenterProductFromCookieService);
}
