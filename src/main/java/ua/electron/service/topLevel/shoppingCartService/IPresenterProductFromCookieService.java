package ua.electron.service.topLevel.shoppingCartService;

import ua.electron.service.lowerLevel.shoppingCartActions.CookieInIntegers;
import ua.electron.entity.Product;
import ua.electron.service.lowerLevel.shoppingCartActions.PriceCounter;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface IPresenterProductFromCookieService {

    Set<Product> productCookieSet(HttpServletRequest request);

    Set<CookieInIntegers> cookieInIntegersSet(HttpServletRequest request);

    Set<PriceCounter> totalPriceForOneProduct(HttpServletRequest request);

    int totalPriceForAllProduct(Set<PriceCounter> totalPricePerOneProd);
}
