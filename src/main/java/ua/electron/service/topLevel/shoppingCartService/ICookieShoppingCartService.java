package ua.electron.service.topLevel.shoppingCartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICookieShoppingCartService {

     void cookieInstall(HttpServletRequest req, HttpServletResponse resp);

     void coolieRemoveAllProduct(HttpServletRequest req, HttpServletResponse resp);
}
