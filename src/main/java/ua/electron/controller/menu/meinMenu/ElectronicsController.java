package ua.electron.controller.menu.meinMenu;

import ua.electron.service.topLevel.productService.IProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/electronics")
public class ElectronicsController extends HttpServlet {

    private static Logger LOGGER = Logger.getLogger(ElectronicsController.class);

    private IProductService productService;

    @Override
    public void init()  {
        productService = (IProductService) getServletContext().getAttribute("productService");
        LOGGER.trace("productService init");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List productList = productService.productList(req);
        req.setAttribute("productsList", productList);
        req.getRequestDispatcher("WEB-INF/jsp/electronics.jsp").forward(req,resp);
    }

}
