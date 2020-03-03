package ua.electron.controller.order;

import ua.electron.entity.Constant;
import ua.electron.entity.User;
import ua.electron.service.topLevel.orderService.IOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/about-my-orders")
public class OrdersAllInfoForUserController extends HttpServlet {

    private IOrderService orderInfoService;

    @Override
    public void init() {
        orderInfoService = (IOrderService) getServletContext().getAttribute("orderInfoService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED));

        req.setAttribute("allUserOrder", orderInfoService.getAllUserOrders(user.getUserId()));
        req.setAttribute("statusInProcessing", orderInfoService.getAllUserOrdersWithStatusInProcessing
                (user.getUserId()));
        req.setAttribute("statusWaitingAnswer", orderInfoService.getAllUserOrdersWithStatusWaitingAnswer
                (user.getUserId()));
        req.setAttribute("statusProcessed", orderInfoService.getAllUserOrdersWithStatusProcessed
                (user.getUserId()));
        req.setAttribute("statusFormed", orderInfoService.getAllUserOrdersWithStatusFormed
                (user.getUserId()));
        req.setAttribute("statusSent", orderInfoService.getAllUserOrdersWithStatusSent
                (user.getUserId()));
        req.setAttribute("statusGot", orderInfoService.getAllUserOrdersWithStatusGot
                (user.getUserId()));
        req.setAttribute("statusCanceled", orderInfoService.getAllUserOrdersWithStatusCanceled
                (user.getUserId()));

        req.getRequestDispatcher("WEB-INF/jsp/orders_info.jsp").forward(req, resp);
    }
}
