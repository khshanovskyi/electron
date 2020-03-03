package ua.electron.service.topLevel.orderService.impl;

import ua.electron.dao.IOrderDao;
import ua.electron.dao.IProductDao;
import ua.electron.dao.IUserDao;
import ua.electron.entity.*;
import ua.electron.service.lowerLevel.orderInfo.*;
import ua.electron.service.topLevel.orderService.IOrderService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements IOrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    private IOrderDao orderDao;
    private IUserDao userDao;
    private IProductDao productDao;

    private LastUserOrder lastUserOrder = new LastUserOrder();
    private AllUserOrders allUserOrders = new AllUserOrders();

    private UserOrdersWithStatusInProcessing inProcessing = new UserOrdersWithStatusInProcessing();
    private UserOrdersWithStatusWaitingAnswer waitingAnswer = new UserOrdersWithStatusWaitingAnswer();
    private UserOrdersWithStatusProcessed processed = new UserOrdersWithStatusProcessed();
    private UserOrdersWithStatusFormed formed = new UserOrdersWithStatusFormed();
    private UserOrdersWithStatusSent sent = new UserOrdersWithStatusSent();
    private UserOrdersWithStatusGot got = new UserOrdersWithStatusGot();
    private UserOrdersWithStatusCanceled canceled = new UserOrdersWithStatusCanceled();

    public OrderServiceImpl(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderServiceImpl(IOrderDao orderDao, IUserDao userDao, IProductDao productDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    @Override
    public void createNewOrder(Order order) {
        orderDao.createOrder(order);
    }

    @Override
    public Optional<FullOrderInfo> getLastUserOrder(int userId) {
        if (lastUserOrder.getFullOrderInfo(orderDao, userId,userDao,productDao).isPresent()){
            LOGGER.trace("Optional FullOrderInfo is present");
            return lastUserOrder.getFullOrderInfo(orderDao, userId,userDao,productDao);
        }
       return Optional.empty();
    }

    @Override
    public List<FullOrderInfo> getAllUserOrders(int userId) {
        LOGGER.trace("Return  All User Orders");
        return allUserOrders.getAllUserOrders(orderDao, userId, userDao, productDao);
    }

    @Override
    public List<FullOrderInfo> getAllUserOrdersWithStatusInProcessing(int userId) {
        return inProcessing.getAllUserOrdersWhereStatusIs(orderDao, userId,userDao,productDao);
    }

    @Override
    public List<FullOrderInfo> getAllUserOrdersWithStatusWaitingAnswer(int userId) {
        return waitingAnswer.getAllUserOrdersWhereStatusWaitingAnswer(orderDao, userId,userDao,productDao);
    }

    @Override
    public List<FullOrderInfo> getAllUserOrdersWithStatusProcessed(int userId) {
        return processed.getAllUserOrdersWhereStatusIs(orderDao, userId,userDao,productDao);
    }

    @Override
    public List<FullOrderInfo> getAllUserOrdersWithStatusFormed(int userId) {
        return formed.getAllUserOrdersWhereStatusIs(orderDao, userId,userDao,productDao);
    }

    @Override
    public List<FullOrderInfo> getAllUserOrdersWithStatusSent(int userId) {
        return sent.getAllUserOrdersWhereStatusIs(orderDao, userId,userDao,productDao);
    }

    @Override
    public List<FullOrderInfo> getAllUserOrdersWithStatusGot(int userId) {
        return got.getAllUserOrdersWhereStatusIs(orderDao, userId,userDao,productDao);
    }

    @Override
    public List<FullOrderInfo> getAllUserOrdersWithStatusCanceled(int userId) {
        return canceled.getAllUserOrdersWhereStatusIs(orderDao, userId,userDao,productDao);
    }


}
