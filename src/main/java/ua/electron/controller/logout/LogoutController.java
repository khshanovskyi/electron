package ua.electron.controller.logout;

import org.apache.log4j.Logger;
import ua.electron.entity.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    private static Logger LOGGER = Logger.getLogger(LogoutController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession();
        if (req.getParameter(String.valueOf(Constant.LOGOUT)) != null){
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals(String.valueOf(Constant.IN_LOGIN))){
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    LOGGER.trace("Cookie with user info invalidated");
                }
            }
            session.removeAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED));
            LOGGER.trace("User session invalidated");
        }
        resp.sendRedirect(req.getContextPath() + "/electronics");
    }
}
