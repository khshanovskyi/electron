package ua.electron.service.lowerLevel.userUpdater;

import ua.electron.entity.User;
import ua.electron.service.topLevel.userService.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface IUserUpdater {

    Optional<User> updateUserEmail(HttpServletRequest request, HttpServletResponse response, IUserService userService);

    Optional<User> updateUserPhoneNumber(HttpServletRequest request, IUserService userService);

    Optional<User> updateUserPassword(HttpServletRequest request, IUserService userService);

    Optional<User> updateUserNameAndSurname(HttpServletRequest request, IUserService userService);

    Optional<User> updateUserName(HttpServletRequest request, IUserService userService);

    Optional<User> updateUserSurname(HttpServletRequest request, IUserService userService);
}
