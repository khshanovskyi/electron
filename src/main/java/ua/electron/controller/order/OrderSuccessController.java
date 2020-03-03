package ua.electron.controller.order;

import ua.electron.entity.Constant;
import ua.electron.entity.User;
import ua.electron.service.topLevel.orderService.IOrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order-success")
public class OrderSuccessController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(OrderSuccessController.class);

    private IOrderService orderInfoService;

    @Override
    public void init() {
        orderInfoService = (IOrderService) getServletContext().getAttribute("orderInfoService");
        LOGGER.trace("orderService initialization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED));

        if(orderInfoService.getLastUserOrder(user.getUserId()).isPresent()){
            req.setAttribute("lastUserOrder", orderInfoService.getLastUserOrder(user.getUserId()).get());
            req.getRequestDispatcher("WEB-INF/jsp/order_success.jsp").forward(req, resp);
        }else{
            resp.sendRedirect(req.getContextPath() + "/electronics");
        }
    }
}
