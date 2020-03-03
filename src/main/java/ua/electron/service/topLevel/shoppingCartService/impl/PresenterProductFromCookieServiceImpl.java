package ua.electron.service.topLevel.shoppingCartService.impl;

import ua.electron.dao.IProductDao;
import ua.electron.service.lowerLevel.shoppingCartActions.CookieInIntegers;
import ua.electron.entity.Product;
import ua.electron.service.topLevel.shoppingCartService.IPresenterProductFromCookieService;
import ua.electron.service.lowerLevel.shoppingCartActions.PriceCounter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PresenterProductFromCookieServiceImpl implements IPresenterProductFromCookieService {

    private IProductDao productDao;
    private Set<Product> productSet = new HashSet<>();
    private Cookie[] cookies;
    private List<Product> productList;

    public PresenterProductFromCookieServiceImpl(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Set<Product> productCookieSet(HttpServletRequest request) {
        if (!productSet.isEmpty()) {
            productSet.clear();
        }
        cookies = request.getCookies();
        productList = productDao.allProducts();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                for (Product product : productList) {
                    if (cookie.getName().equals(String.valueOf(product.getIdOfProduct()))) {
                        productSet.add(productDao.getProductById(Integer.parseInt(cookie.getName())).get());
                    }
                }
            }
        }
        return productSet;
    }

    @Override
    public Set<CookieInIntegers> cookieInIntegersSet(HttpServletRequest request) {
        cookies = request.getCookies();
        productList = productDao.allProducts();
        int prodId;
        int prodQuantity;
        Set<CookieInIntegers> cookieSet = new HashSet<>();

        for (Cookie cookie : cookies) {
            for (Product product : productList) {
                if (cookie.getName().equals(String.valueOf(product.getIdOfProduct()))) {
                    prodId = Integer.parseInt(cookie.getName());
                    prodQuantity = Integer.parseInt(cookie.getValue());
                    cookieSet.add(new CookieInIntegers(prodId, prodQuantity));
                }
            }
        }
        return cookieSet;
    }

    @Override
    public Set<PriceCounter> totalPriceForOneProduct(HttpServletRequest request) {
        Set<PriceCounter> counterTotalPriceForOneProduct = new HashSet<>();
        cookies = request.getCookies();
        productList = productDao.allProducts();

        for (Cookie cookie : cookies) {
            for (Product product : productList) {
                if (cookie.getName().equals(String.valueOf(product.getIdOfProduct()))) {
                    int prodQuantity = Integer.parseInt(cookie.getValue());
                    counterTotalPriceForOneProduct.add(new PriceCounter(product.getIdOfProduct(),
                            prodQuantity, prodQuantity * product.getPrice()));
                }
            }
        }
        return counterTotalPriceForOneProduct;
    }

    @Override
    public int totalPriceForAllProduct(Set<PriceCounter> totalPricePerOneProd) {
        int allPrice = 0;
        for (PriceCounter counter: totalPricePerOneProd) {
            allPrice += counter.getProductPrice();
        }
        return allPrice;
    }
}
