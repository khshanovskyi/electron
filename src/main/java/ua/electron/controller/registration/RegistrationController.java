package ua.electron.controller.registration;

import ua.electron.entity.Constant;
import ua.electron.entity.User;
import ua.electron.service.topLevel.userService.IUserService;
import ua.electron.service.lowerLevel.userCreator.IUserCreator;
import ua.electron.service.lowerLevel.userCreator.impl.UserCreatorImpl;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@WebServlet("/registration-controller")
public class RegistrationController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    private IUserService userService;

    @Override
    public void init() {
        userService = (IUserService) getServletContext().getAttribute("userService");
        LOGGER.trace("Init in RegistrationController worked");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        IUserCreator userCreator = new UserCreatorImpl();
        Optional<User> userOpt = userCreator.validationAndCreatingNewUser(req);
        HttpSession session = req.getSession();
        User user;

        if (userService.checkEmailExisting(req.getParameter(String.valueOf(Constant.REGISTRATION_EMAIL))).isPresent()){
            LOGGER.info("User with this email already exist");
            resp.sendRedirect(req.getContextPath() + "/registration");
        }else {
            try{
                user = userOpt.get();
                userService.createNewUser(user);
                LOGGER.info("User created and added to DB");
                session.setAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED), user);
                resp.sendRedirect(req.getContextPath() + "/login");
            }catch (NoSuchElementException e){
                LOGGER.error("NoSuchElementException in registration");
                resp.sendRedirect(req.getContextPath() + "/registration");
            }
        }
    }
}
