package ua.electron.filter;

import org.apache.log4j.Logger;
import ua.electron.entity.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/order-success")
public class AccessToOrderInfoFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AccessToOrderInfoFilter.class);

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getSession().getAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED)) == null){
            LOGGER.info("Customer without authorization trying to going on order-success page! Do redirect to login page!");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
