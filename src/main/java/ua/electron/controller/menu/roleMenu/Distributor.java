package ua.electron.controller.menu.roleMenu;

import ua.electron.entity.Constant;
import ua.electron.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/distributor")
public class Distributor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED));

        if (user != null){
            if (user.getRole().equals(String.valueOf(Constant.USER))){
                resp.sendRedirect(req.getContextPath() + "/user-interface");
            }else if (user.getRole().equals(String.valueOf(Constant.MANAGER))){
                req.getRequestDispatcher("WEB-INF/jsp/manager_interface.jsp").forward(req,resp);
            }else if (user.getRole().equals(String.valueOf(Constant.STOREKEEPER))){
                req.getRequestDispatcher("WEB-INF/jsp/storekeeper_interface.jsp").forward(req,resp);
            }else if (user.getRole().equals(String.valueOf(Constant.ADMIN))){
                req.getRequestDispatcher("WEB-INF/jsp/admin_interface.jsp").forward(req,resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/electronics");
        }
    }
}
