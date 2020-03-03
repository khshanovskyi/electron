package ua.electron.controller.order;

import org.apache.log4j.Logger;
import ua.electron.entity.Constant;
import ua.electron.entity.Order;
import ua.electron.service.topLevel.orderService.IOrderService;
import ua.electron.service.topLevel.shoppingCartService.ICookieShoppingCartService;
import ua.electron.service.topLevel.shoppingCartService.impl.PresenterProductFromCookieServiceImpl;
import ua.electron.service.lowerLevel.orderCreator.IOrderCreator;
import ua.electron.service.lowerLevel.orderCreator.impl.OrderCreatorImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/order-actions")
public class OrderActionsController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(OrderActionsController.class);

    private IOrderService orderService;
    private PresenterProductFromCookieServiceImpl presenterProductFromCookieService;
    private ICookieShoppingCartService cookieShoppingCartService;

    @Override
    public void init() {
        orderService = (IOrderService) getServletContext().getAttribute("orderService");
        LOGGER.trace("orderService initialization");
        presenterProductFromCookieService = (PresenterProductFromCookieServiceImpl) getServletContext().
                getAttribute("presenterProductFromCookieService");
        LOGGER.trace("presenterProductFromCookieService initialization");
        cookieShoppingCartService = (ICookieShoppingCartService) getServletContext().getAttribute("cookieShoppingCartService");
        LOGGER.trace("cookieShoppingCartService initialization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED)) != null) {
            IOrderCreator orderCreator = new OrderCreatorImpl();
            if (req.getParameter("createOrder") != null) {
                Optional<Order> orderOpt = orderCreator.orderValidatorAndCreator(req, presenterProductFromCookieService);
                if (orderOpt.isPresent()) {
                    Order order = orderOpt.get();
                    orderService.createNewOrder(order);
                    cookieShoppingCartService.coolieRemoveAllProduct(req, resp);
                    LOGGER.info("Order created! All cookie removed!");
                    resp.sendRedirect(req.getContextPath() + "/order-success");
                }
            }
        } else {
            LOGGER.trace("User doesn't have authorization! Redirect to login page.");
            resp.sendRedirect(req.getContextPath() + "/login");
        }

    }
}
