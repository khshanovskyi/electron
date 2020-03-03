package ua.electron.service.topLevel.userService;

import ua.electron.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface IUserService {

    Optional<User> loginInAccount (String email, String password);

    boolean checkUserState(String email);

    boolean checkUserSession(HttpServletRequest request);

    Optional<User> checkEmailExisting(String email);

    void createNewUser (User user);

    Optional<User> getUserByEmail(String email);

    void  updateUserPersonalInfo(User user);
}
