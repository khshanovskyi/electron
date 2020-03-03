package ua.electron.service.topLevel.shoppingCartService.impl;

import ua.electron.dao.IProductDao;
import ua.electron.entity.Product;
import ua.electron.service.topLevel.shoppingCartService.ICookieShoppingCartService;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CookieShoppingCartServiceImpl implements ICookieShoppingCartService {

    private static final Logger LOGGER = Logger.getLogger(CookieShoppingCartServiceImpl.class);

    private Cookie[] cookies;
    private IProductDao productDao;

    public CookieShoppingCartServiceImpl(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void cookieInstall(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("addToShoppingCartIdOfProduct") != null){
            if (checkCookieExist(req)){
                LOGGER.trace("Add cookie");
                cookieCreate(req,resp);
            }
        }
        if (req.getParameter("removeProduct") != null){
            LOGGER.trace("Remove ONE cookie");
            oneCookieRemove(req,resp);
        }
        if(req.getParameter("removeAllProduct") != null){
            LOGGER.trace("Remove All cookie");
            coolieRemoveAllProduct(req,resp);
        }
        if (req.getParameter("plusBtn") != null){
            LOGGER.trace("Add one cookie to cookie value");
            moreProductQuantity(req,resp);
        }
        if (req.getParameter("minusBtn") != null){
            LOGGER.trace("Delete one cookie from value");
            lessProductQuantity(req,resp);
        }
    }

    @Override
    //this method for order success controller
    public void coolieRemoveAllProduct(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> productList = productDao.allProducts();
        cookies = req.getCookies();
        for (Product product: productList) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(String.valueOf(product.getIdOfProduct()))){
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
    }

    private boolean checkCookieExist(HttpServletRequest request) {
        cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(request.getParameter("addToShoppingCartIdOfProduct"))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void cookieCreate(HttpServletRequest request, HttpServletResponse response) {
        Cookie newCookie = new Cookie(request.getParameter("addToShoppingCartIdOfProduct"), "1");
        response.addCookie(newCookie);
    }

    private void oneCookieRemove(HttpServletRequest request, HttpServletResponse response) {
        cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(request.getParameter("removeProduct"))) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    private void moreProductQuantity(HttpServletRequest request, HttpServletResponse response) {
        cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(request.getParameter("plusBtn"))) {
                    int newValue = Integer.parseInt(cookie.getValue()) + 1;
                    cookie.setValue(String.valueOf(newValue));
                    response.addCookie(cookie);
                }
            }
        }
    }

    private void lessProductQuantity(HttpServletRequest request, HttpServletResponse response) {
        cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(request.getParameter("minusBtn"))) {
                    int newValue = Integer.parseInt(cookie.getValue());
                    if (newValue > 1){
                        newValue -= 1;
                        cookie.setValue(String.valueOf(newValue));
                        response.addCookie(cookie);
                    }
                }
            }
        }
    }

}
