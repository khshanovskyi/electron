package ua.electron.dao.impl;

import ua.electron.dao.ConnectionManager;
import ua.electron.dao.IUserDao;
import ua.electron.entity.User;
import ua.electron.entity.builder.UserBuilder;
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

public class UserDaoMySql extends ConnectionManager implements IUserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoMySql.class);

    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?;";
    private static final String CREATE_NEW_USER = "INSERT INTO users " +
            "(email, first_name, last_name, city, phone_number, password, role, state) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET email = ?, " +
            "first_name = ?, last_name = ?, city = ?, phone_number =?, password = ?, role = ?, state =? " +
            "WHERE id = ?;";

    public UserDaoMySql(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<User> allUsers(){
        List<User> result = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){
                result.add(extractUser(resultSet));
            }
        }catch (SQLException e){
            LOGGER.error("cannot get users from DB");
            throw new DaoException("cannot get users from DB", e);
        }
        return result;
    }

    @Override
    public Optional<User> getUserById(int userId)  {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(GET_USER_BY_ID);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                return Optional.of(extractUser(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get user from DB, user id = " + userId);
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(GET_USER_BY_EMAIL);
            statement.setString(1, email);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(extractUser(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get user from DB, user email = " + email);
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }return Optional.empty();
    }

    @Override
    public void create(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(CREATE_NEW_USER);
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getSecondName());
            statement.setString(4, user.getCity());
            statement.setInt(5, user.getPhoneNumber());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getRole());
            statement.setString(8, user.getState());

            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOGGER.error("problem with creating user " + user);
            throw  new DaoException("problem with creating user ", e);
        } finally {
            close(connection);
            close(statement);
        }
    }

    @Override
    public void delete(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_USER_BY_ID);

            statement.setInt(1, userId);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOGGER.error("problem with deleting user " + userId);
        } finally {
            close(connection);
            close(statement);
        }
    }

    @Override
    public User update(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_USER_BY_ID);
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            statement.setString(1,user.getEmail());
            statement.setString(2,user.getFirstName());
            statement.setString(3,user.getSecondName());
            statement.setString(4,user.getCity());
            statement.setInt(5,user.getPhoneNumber());
            statement.setString(6,user.getPassword());
            statement.setString(7,user.getRole());
            statement.setString(8,user.getState());
            statement.setInt(9,user.getUserId());

            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOGGER.error("Problem with updating user " + user.getUserId());
            throw new DaoException("Cannot update user" + user, e);
        }finally {
            close(connection);
            close(statement);
        }
        return user;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        UserBuilder userBuilder = new UserBuilder();

        userBuilder.buildUserId(resultSet.getInt("id"));
        userBuilder.buildUserEmail(resultSet.getString("email"));
        userBuilder.buildUserFirstName(resultSet.getString("first_name"));
        userBuilder.buildUserSecondName(resultSet.getString("last_name"));
        userBuilder.buildUserCity(resultSet.getString("city"));
        userBuilder.buildUserPhoneNumber(resultSet.getInt("phone_number"));
        userBuilder.buildUserPassword(resultSet.getString("password"));
        userBuilder.buildUserRole(resultSet.getString("role"));
        userBuilder.buildUserState(resultSet.getString("state"));

        return userBuilder.build();
    }
}
