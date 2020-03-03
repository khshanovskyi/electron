package ua.electron.controller.login;

import ua.electron.entity.Constant;
import ua.electron.entity.User;
import ua.electron.service.topLevel.userService.IUserService;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/authorization")
public class AuthorizationController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AuthorizationController.class);

    private IUserService userService;

    @Override
    public void init() {
        userService = (IUserService) getServletContext().getAttribute("userService");
        LOGGER.trace("Init in AuthorizationController worked");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("EMAIL_LOGIN");
        String password = req.getParameter("PASSWORD_LOGIN");
        HttpSession session = req.getSession();

        Optional<User> userOptional = userService.loginInAccount(email, password);
        if (userOptional.isPresent()) {
            if (userService.checkUserState(email)) {
                session.setAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED), userOptional.get());
                Cookie cookieUser = new Cookie(String.valueOf(Constant.IN_LOGIN), userOptional.get().getEmail());
                resp.addCookie(cookieUser);
                session.removeAttribute(String.valueOf(ua.electron.entity.Constant.GUEST));
                LOGGER.trace("Have UNBLOCKED user in login");
                resp.sendRedirect(req.getContextPath() + "/electronics");
            } else {
                session.setAttribute(String.valueOf(Constant.USER_IS_BLOCKED), userOptional.get());
                LOGGER.info("Have BLOCKED user in login");
                resp.sendRedirect(req.getContextPath() + "/BLOCKED");
            }
        } else {
            LOGGER.info("User introduced not correct data");
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
