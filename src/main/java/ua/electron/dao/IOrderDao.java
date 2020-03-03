package ua.electron.dao;

import ua.electron.entity.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderDao {

    void createOrder (Order order);

    Optional<Order> lastUserOrderInfo(int userId);

    List<Order> allUserOrdersInfo(int userId);

    List<Order> allUserOrdersInfoWithSearchingStatus(int userId, String status);

//    List allOrders();
}
