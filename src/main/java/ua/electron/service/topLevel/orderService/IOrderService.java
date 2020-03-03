package ua.electron.service.topLevel.orderService;

import ua.electron.entity.FullOrderInfo;
import ua.electron.entity.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

    void createNewOrder(Order order);

    Optional<FullOrderInfo> getLastUserOrder(int userId);

    List<FullOrderInfo> getAllUserOrders(int userId);

    List<FullOrderInfo> getAllUserOrdersWithStatusInProcessing(int userId);

    List<FullOrderInfo> getAllUserOrdersWithStatusWaitingAnswer(int userId);

    List<FullOrderInfo> getAllUserOrdersWithStatusProcessed(int userId);

    List<FullOrderInfo> getAllUserOrdersWithStatusFormed(int userId);

    List<FullOrderInfo> getAllUserOrdersWithStatusSent(int userId);

    List<FullOrderInfo> getAllUserOrdersWithStatusGot(int userId);

    List<FullOrderInfo> getAllUserOrdersWithStatusCanceled(int userId);
}
