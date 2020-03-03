package ua.electron.controller.menu.roleMenu;

import org.apache.log4j.Logger;
import ua.electron.entity.Constant;
import ua.electron.entity.User;
import ua.electron.service.lowerLevel.userUpdater.IUserUpdater;
import ua.electron.service.lowerLevel.userUpdater.impl.UserUpdaterImpl;
import ua.electron.service.topLevel.userService.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user-interface")
public class UserInterfaceController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserInterfaceController.class);

    private IUserService userService;

    @Override
    public void init(){
        userService = (IUserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        IUserUpdater iUserUpdater = new UserUpdaterImpl();
        Optional<User> user;

        if (req.getParameter("SUBMIT_CHANGE_EMAIL") != null){
            if (!req.getParameter(String.valueOf(Constant.E_MAIL)).isEmpty()) {
                if (!userService.checkEmailExisting(req.getParameter(String.valueOf(Constant.E_MAIL))).isPresent()) {
                    user = iUserUpdater.updateUserEmail(req,resp,userService);
                    user.ifPresent(value -> userService.updateUserPersonalInfo(value));
                    LOGGER.trace("Updating user email");
                    resp.sendRedirect(req.getContextPath() + "/distributor");
                }else {
                    resp.sendRedirect(req.getContextPath() + "/distributor");
                }
            }else{
                resp.sendRedirect(req.getContextPath() + "/distributor");
            }
        }

        if (req.getParameter("SUBMIT_CHANGE_PHONE_NUM") != null){
            if (!req.getParameter(String.valueOf(Constant.CHANGE_PHONE_NUM)).isEmpty()) {
                user = iUserUpdater.updateUserPhoneNumber(req, userService);
                user.ifPresent(value -> userService.updateUserPersonalInfo(value));
                LOGGER.trace("Updating user phone number");
                resp.sendRedirect(req.getContextPath() + "/distributor");
            }else {
                resp.sendRedirect(req.getContextPath() + "/distributor");
            }
        }

        if (req.getParameter("SUBMIT_CHANGE_PASSWORD") != null){
            if (!req.getParameter(String.valueOf(Constant.OLD_PASSWORD)).isEmpty() &&
                    !req.getParameter(String.valueOf(Constant.NEW_PASSWORD)).isEmpty() &&
                    !req.getParameter(String.valueOf(Constant.NEW_PASSWORD_CONFIRM)).isEmpty()) {
                user = iUserUpdater.updateUserPassword(req, userService);
                user.ifPresent(value -> userService.updateUserPersonalInfo(value));
                LOGGER.trace("Updating user password");
                resp.sendRedirect(req.getContextPath() + "/distributor");
            }else {
                resp.sendRedirect(req.getContextPath() + "/distributor");
            }
        }

        if (req.getParameter("SUBMIT_CHANGE_NAME_SURNAME") != null){
            if (!req.getParameter(String.valueOf(Constant.CHANGE_NAME)).isEmpty() &&
                    !req.getParameter(String.valueOf(Constant.CHANGE_SURNAME)).isEmpty()) {
                user = iUserUpdater.updateUserNameAndSurname(req, userService);
                user.ifPresent(value -> userService.updateUserPersonalInfo(value));
                LOGGER.trace("Updating user name and surname");
                resp.sendRedirect(req.getContextPath() + "/distributor");
            }else if (!req.getParameter(String.valueOf(Constant.CHANGE_NAME)).isEmpty() &&
                    req.getParameter(String.valueOf(Constant.CHANGE_SURNAME)).isEmpty()){
                user = iUserUpdater.updateUserName(req, userService);
                user.ifPresent(value -> userService.updateUserPersonalInfo(value));
                LOGGER.trace("Updating user name");
                resp.sendRedirect(req.getContextPath() + "/distributor");
            }else if (req.getParameter(String.valueOf(Constant.CHANGE_NAME)).isEmpty() &&
                    !req.getParameter(String.valueOf(Constant.CHANGE_SURNAME)).isEmpty()){
                user = iUserUpdater.updateUserSurname(req, userService);
                user.ifPresent(value -> userService.updateUserPersonalInfo(value));
                LOGGER.trace("Updating user surname");
                resp.sendRedirect(req.getContextPath() + "/distributor");
            }else{
                resp.sendRedirect(req.getContextPath() + "/distributor");
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/user_interface.jsp").forward(req, resp);
    }

}
