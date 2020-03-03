package ua.electron.dao;

import ua.electron.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUserDao {

    List<User> allUsers() throws SQLException;

    Optional<User> getUserById(int userId);

    Optional<User> getUserByEmail(String email);

    void create(User user);

    void delete (int userId);

    User update (User user);

}
