package ua.electron.listener;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ua.electron.dao.IOrderDao;
import ua.electron.dao.IProductDao;
import ua.electron.dao.IUserDao;
import ua.electron.dao.impl.OrderDaoMySql;
import ua.electron.dao.impl.ProductDaoMySql;
import ua.electron.dao.impl.UserDaoMySql;
import ua.electron.service.topLevel.orderService.IOrderService;
import ua.electron.service.topLevel.orderService.impl.OrderServiceImpl;
import ua.electron.service.topLevel.productService.IProductService;
import ua.electron.service.topLevel.shoppingCartService.ICookieShoppingCartService;
import ua.electron.service.topLevel.shoppingCartService.IPresenterProductFromCookieService;
import ua.electron.service.topLevel.shoppingCartService.impl.CookieShoppingCartServiceImpl;
import ua.electron.service.topLevel.shoppingCartService.impl.PresenterProductFromCookieServiceImpl;
import ua.electron.service.topLevel.userService.IUserService;
import ua.electron.service.topLevel.productService.impl.ProductServiceImpl;
import ua.electron.service.topLevel.userService.impl.UserServiceImpl;
import ua.electron.service.topLevel.showDetails.IDetailsService;
import ua.electron.service.topLevel.showDetails.impl.DetailsServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Set;

@WebListener
public class InitializationListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(InitializationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/electron.ua?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false");
        config.setUsername("root");
        config.setPassword("root");
        config.addDataSourceProperty("autoReconnect",true);
        config.addDataSourceProperty("tcpKeepAlive", true);
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", 250);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        config.addDataSourceProperty("useServerPrepStmts", true);
        config.addDataSourceProperty("cacheResultSetMetadata", true);
        config.setMaximumPoolSize(16);
        config.setMinimumIdle(0);
        config.setIdleTimeout(30000);
        HikariDataSource dataSources = new HikariDataSource(config);

        //UserDaoObj with UserDaoMySql realization
        IUserDao userDao = new UserDaoMySql(dataSources);
        //Service for user with UserServiceImpl realization
        IUserService IUserService = new UserServiceImpl(userDao);

        //ProductDaoObj with ProductDaoMySql realization
        IProductDao productDao = new ProductDaoMySql(dataSources);
        //Service for product with ProductServiceImpl realization
        IProductService productService = new ProductServiceImpl(productDao);
        //Uniq categories and brands from DB for aside in UI
        Set<String> categories = productDao.allCategory();
        Set<String> brands = productDao.allBrand();

        //Service for product details with DetailsServiceImpl realization
        IDetailsService detailsService = new DetailsServiceImpl(productDao);

        //Service for installing and removing cookies for products in shopping cart
        ICookieShoppingCartService cookieShoppingCartService = new CookieShoppingCartServiceImpl(productDao);

        //Service for show product in shopping cart
        IPresenterProductFromCookieService presenterProductFromCookieService  =  new PresenterProductFromCookieServiceImpl(productDao);

        //Order DAO
        IOrderDao orderDao = new OrderDaoMySql(dataSources);
        //Service for order-actions controller
        IOrderService orderService = new OrderServiceImpl(orderDao);
        //Service for order controller (show details about order)
        IOrderService orderInfoService = new OrderServiceImpl(orderDao, userDao, productDao);

        //context for user
        servletContext.setAttribute("userService", IUserService);
        //context for product
        servletContext.setAttribute("productService", productService);
        servletContext.setAttribute("categories", categories);
        servletContext.setAttribute("brands", brands);
        //context for details of product
        servletContext.setAttribute("detailsService", detailsService);
        //context for shopping cart
        servletContext.setAttribute("cookieShoppingCartService", cookieShoppingCartService);
        //context for presenter product in shopping cart
        servletContext.setAttribute("presenterProductFromCookieService", presenterProductFromCookieService);
        //context for order
        servletContext.setAttribute("orderService", orderService);
        //context for order, show order details
        servletContext.setAttribute("orderInfoService", orderInfoService);

        //install Resource Bundle with Ukrainian language
        servletContext.setAttribute("bundle", "default__en_US_uk_UA");

        LOGGER.info("ContextListener initialized!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("ContextListener destroyed!");
    }
}
