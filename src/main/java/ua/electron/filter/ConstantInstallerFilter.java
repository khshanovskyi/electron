package ua.electron.filter;

import ua.electron.entity.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/about-my-orders"})
public class ConstantInstallerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();

        if (session.getAttribute(String.valueOf(Constant.NOTHING)) == null){
            session.setAttribute("NOTHING", String.valueOf(Constant.NOTHING));
            session.setAttribute("IN_PROCESSING", String.valueOf(Constant.IN_PROCESSING));
            session.setAttribute("WAITING_ANSWER", String.valueOf(Constant.WAITING_ANSWER));
            session.setAttribute("PROCESSED", String.valueOf(Constant.PROCESSED));
            session.setAttribute("FORMED", String.valueOf(Constant.FORMED));
            session.setAttribute("SENT", String.valueOf(Constant.SENT));
            session.setAttribute("GOT", String.valueOf(Constant.GOT));
            session.setAttribute("CANCELED", String.valueOf(Constant.CANCELED));
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
