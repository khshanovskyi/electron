package ua.electron.dao.impl;

import ua.electron.dao.ConnectionManager;
import ua.electron.dao.IOrderDao;
import ua.electron.entity.Order;
import ua.electron.entity.builder.OrderBuilder;
import ua.electron.exception.DaoException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoMySql extends ConnectionManager implements IOrderDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDaoMySql.class);

    private static final String CREATE_NEW_ORDER = "INSERT INTO orders" +
            "(user_id, products_id, quantity, total_price, delivery_address, order_data, order_time, status, comment," +
            " delivery_service, delivery_note)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final  String GET_LAST_USER_ORDER = "SELECT * FROM `electron.ua`.orders WHERE user_id = ?" +
            " and id=(SELECT MAX(id) FROM `electron.ua`.orders where user_id = ?);";
    private static final  String GET_ALL_USER_ORDERS = "SELECT * FROM `electron.ua`.orders WHERE user_id = ? ORDER BY id DESC;";
    private static final  String GET_ALL_USER_ORDERS_WITH_SEARCHING_STATUS =
            "SELECT * FROM `electron.ua`.orders WHERE user_id = ? and status = ? order by id desc;";

    public OrderDaoMySql(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void createOrder(Order order) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(CREATE_NEW_ORDER);
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getProductId());
            statement.setString(3, order.getQuantity());
            statement.setInt(4, order.getTotalPrice());
            statement.setString(5, order.getDeliveryAddress());
            statement.setDate(6,order.getDate());
            statement.setTime(7,order.getTime());
            statement.setString(8, order.getStatus());
            statement.setString(9, order.getComment());
            statement.setString(10, order.getDeliveryService());
            statement.setString(11, order.getDeliveryNote());

            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOGGER.error("problem with creating order " + order);
            throw  new DaoException("problem with creating order ", e);
        } finally {
            close(connection);
            close(statement);
        }
    }

    @Override
    public Optional<Order> lastUserOrderInfo(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(GET_LAST_USER_ORDER);
            statement.setInt(1, userId);
            statement.setInt(2, userId);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                return Optional.of(extractOrder(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get a product from DB " + e);
            throw new DaoException("Cannot get a product from DB ", e);
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> allUserOrdersInfo(int userId) {
        List<Order> orders = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(GET_ALL_USER_ORDERS);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                orders.add(extractOrder(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }

        return orders;
    }

    @Override
    public List<Order> allUserOrdersInfoWithSearchingStatus(int userId, String status) {
        List<Order> orders = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(GET_ALL_USER_ORDERS_WITH_SEARCHING_STATUS);
            statement.setInt(1, userId);
            statement.setString(2, status);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                orders.add(extractOrder(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }

        return orders;
    }

    private Order extractOrder(ResultSet resultSet) throws SQLException {
        OrderBuilder orderBuilder = new OrderBuilder();

        orderBuilder.buildId(resultSet.getInt("id"));
        orderBuilder.buildUserId(resultSet.getInt("user_id"));
        orderBuilder.buildProductId(resultSet.getString("products_id"));
        orderBuilder.buildQuantity(resultSet.getString("quantity"));
        orderBuilder.buildTotalPrice(resultSet.getInt("total_price"));
        orderBuilder.buildDeliveryAddress(resultSet.getString("delivery_address"));
        orderBuilder.buildDate(resultSet.getDate("order_data"));
        orderBuilder.buildTime(resultSet.getTime("order_time"));
        orderBuilder.buildOrderStatus(resultSet.getString("status"));
        orderBuilder.buildComment(resultSet.getString("comment"));
        orderBuilder.buildDeliveryService(resultSet.getString("delivery_service"));
        orderBuilder.buildDeliveryNote(resultSet.getString("delivery_note"));

        return orderBuilder.buildOrder();
    }
}
