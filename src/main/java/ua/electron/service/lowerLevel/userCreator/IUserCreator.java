package ua.electron.service.lowerLevel.userCreator;

import ua.electron.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface IUserCreator {
    Optional<User> validationAndCreatingNewUser(HttpServletRequest request);
}
