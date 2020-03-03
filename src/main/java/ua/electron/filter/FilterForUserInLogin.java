package ua.electron.filter;

import org.apache.log4j.Logger;
import ua.electron.entity.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/login", "/BLOCKED", "/registration"})
public class FilterForUserInLogin implements Filter {

    private static final Logger LOGGER = Logger.getLogger(FilterForUserInLogin.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (session.getAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED)) != null) {
            LOGGER.info("User trying to go on login or registration or BLOCKED page, do redirect to electronics page");
            resp.sendRedirect(req.getContextPath() + "/electronics");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
