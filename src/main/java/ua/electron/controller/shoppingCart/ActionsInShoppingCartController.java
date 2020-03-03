package ua.electron.controller.shoppingCart;

import ua.electron.service.topLevel.shoppingCartService.ICookieShoppingCartService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shopping-cart-actions")
public class ActionsInShoppingCartController extends HttpServlet {

    private ICookieShoppingCartService cookieShoppingCartService;

    @Override
    public void init() {
        cookieShoppingCartService = (ICookieShoppingCartService) getServletContext().getAttribute("cookieShoppingCartService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        cookieShoppingCartService.cookieInstall(req,resp);

        resp.sendRedirect(req.getContextPath() + req.getParameter("URLFromRequest"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        doPost(req, resp);
    }
}
