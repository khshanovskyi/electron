package ua.electron.controller.shoppingCart;

import org.apache.log4j.Logger;
import ua.electron.service.topLevel.shoppingCartService.IPresenterProductFromCookieService;
import ua.electron.service.lowerLevel.shoppingCartActions.PriceCounter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/shopping-cart")
public class ShoppingCartController extends HttpServlet {

    private static Logger LOGGER = Logger.getLogger(ShoppingCartController.class);

    private IPresenterProductFromCookieService presenterProductFromCookieService;

    @Override
    public void init(){
        presenterProductFromCookieService = (IPresenterProductFromCookieService) getServletContext().
                getAttribute("presenterProductFromCookieService");
        LOGGER.trace("presenterProductFromCookieService initialization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("presenterProductFromCookieService", presenterProductFromCookieService.productCookieSet(req));
        req.setAttribute("cookieIntegerSet", presenterProductFromCookieService.cookieInIntegersSet(req));
        Set<PriceCounter> totalPricePerOneProd = presenterProductFromCookieService.totalPriceForOneProduct(req);
        req.setAttribute("totalPricePerOneProd", totalPricePerOneProd);
        req.setAttribute("allPrice", presenterProductFromCookieService.totalPriceForAllProduct(totalPricePerOneProd));

        req.getRequestDispatcher("WEB-INF/jsp/shopping_cart.jsp").forward(req,resp);
    }
}
