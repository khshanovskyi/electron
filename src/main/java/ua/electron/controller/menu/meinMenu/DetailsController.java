package ua.electron.controller.menu.meinMenu;

import ua.electron.service.topLevel.showDetails.IDetailsService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/details")
public class DetailsController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DetailsController.class);

    private IDetailsService detailsService;

    @Override
    public void init() {
        detailsService = (IDetailsService) getServletContext().getAttribute("detailsService");
        LOGGER.trace("detailsService init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("prodForDetails") != null){
            LOGGER.trace("Product for details is not null");
            int idProduct = Integer.parseInt(req.getParameter("prodForDetails"));
            req.setAttribute("productDetails", detailsService.getDetails(idProduct));
            req.getRequestDispatcher("WEB-INF/jsp/details.jsp").forward(req,resp);
        } else {
           LOGGER.trace("Product for details is null, doing redirect to electronics servlet");
           resp.sendRedirect(req.getContextPath() + "/electronics");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
