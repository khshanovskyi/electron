package ua.electron.controller.login;

import ua.electron.service.topLevel.userService.IUserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    private IUserService userService;

    @Override
    public void init(){
        userService = (IUserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try{
            if (userService.checkUserSession(req)){
               resp.sendRedirect(req.getContextPath() + "/electronics");
            }else{
                req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req,resp);
            }

        }catch (ServletException | IOException e){
            LOGGER.error(e);
        }
    }
}

