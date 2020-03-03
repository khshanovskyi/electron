package ua.electron.service.lowerLevel.userUpdater.impl;

import ua.electron.entity.Constant;
import ua.electron.entity.User;
import ua.electron.entity.builder.UserBuilder;
import ua.electron.handler.IDataEnteredHandler;
import ua.electron.handler.impl.DataEnteredHandlerImpl;
import ua.electron.service.lowerLevel.userUpdater.IUserUpdater;
import ua.electron.service.topLevel.userService.IUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UserUpdaterImpl implements IUserUpdater {

    private IDataEnteredHandler dataEnteredHandler = new DataEnteredHandlerImpl();
    private Optional<User> oldUserInfo;
    private Cookie[] cookies;
    private String oldEmail;
    private UserBuilder userBuilder = new UserBuilder();
    private User user;

    /**
     *  Method for update user email. User input new email and password for accept this updating operation.
     *  After in this method we are checking email is correct or not and we are comparing entered password with user
     *  password from DB if this data is correct we are starting to create a new User object. If not correct -
     *  return empty optional.
     */
    @Override
    public Optional<User> updateUserEmail(HttpServletRequest request, HttpServletResponse response, IUserService userService) {
        oldUserInfo = userService.getUserByEmail(returnUserEmailFromCookie(request));
        String newEmail = request.getParameter(String.valueOf(Constant.E_MAIL));
        String passwordConfirm = request.getParameter(String.valueOf(Constant.PASSWORD_CONFIRM));

        if (dataEnteredHandler.emailValidation(newEmail) &&
        dataEnteredHandler.passwordConfirmValidation(oldUserInfo.get().getPassword(), passwordConfirm)){
            user = createUser(oldUserInfo.get().getUserId(), newEmail,oldUserInfo.get().getFirstName(),
                    oldUserInfo.get().getSecondName(), oldUserInfo.get().getCity(), oldUserInfo.get().getPhoneNumber(),
                    oldUserInfo.get().getPassword(), oldUserInfo.get().getRole(), oldUserInfo.get().getState());
            updateUserSession(request,user);
            updateCookieUserInLogin(request,response,user.getEmail());
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUserPhoneNumber(HttpServletRequest request, IUserService userService) {
        oldUserInfo = userService.getUserByEmail(returnUserEmailFromCookie(request));
        String  newProneNumberStr = request.getParameter(String.valueOf(Constant.CHANGE_PHONE_NUM));

        if (dataEnteredHandler.phoneNumberValidation(newProneNumberStr)){
            int newPhoneNumber = Integer.parseInt(newProneNumberStr);
            user = createUser(oldUserInfo.get().getUserId(), oldUserInfo.get().getEmail(),oldUserInfo.get().getFirstName(),
                    oldUserInfo.get().getSecondName(), oldUserInfo.get().getCity(), newPhoneNumber,
                    oldUserInfo.get().getPassword(), oldUserInfo.get().getRole(), oldUserInfo.get().getState());
            updateUserSession(request,user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUserPassword(HttpServletRequest request, IUserService userService) {
        oldUserInfo = userService.getUserByEmail(returnUserEmailFromCookie(request));
        String oldPassword = request.getParameter(String.valueOf(Constant.OLD_PASSWORD));
        String newPassword = request.getParameter(String.valueOf(Constant.NEW_PASSWORD));
        String confirmNewPassword = request.getParameter(String.valueOf(Constant.NEW_PASSWORD_CONFIRM));

        if (oldPassword.equals(oldUserInfo.get().getPassword())){
            if (dataEnteredHandler.passwordValidation(newPassword)){
                if (dataEnteredHandler.passwordConfirmValidation(newPassword, confirmNewPassword)){
                    user = createUser(oldUserInfo.get().getUserId(), oldUserInfo.get().getEmail(),oldUserInfo.get().getFirstName(),
                            oldUserInfo.get().getSecondName(), oldUserInfo.get().getCity(), oldUserInfo.get().getPhoneNumber(),
                            newPassword, oldUserInfo.get().getRole(), oldUserInfo.get().getState());
                    updateUserSession(request,user);
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUserNameAndSurname(HttpServletRequest request, IUserService userService) {
        oldUserInfo = userService.getUserByEmail(returnUserEmailFromCookie(request));
        String newName = request.getParameter(String.valueOf(Constant.CHANGE_NAME));
        System.out.println(newName);
        String newSurname = request.getParameter(String.valueOf(Constant.CHANGE_SURNAME));
        System.out.println(newSurname);

        if (dataEnteredHandler.nameValidation(newName) && dataEnteredHandler.nameValidation(newSurname)){
            user = createUser(oldUserInfo.get().getUserId(), oldUserInfo.get().getEmail(), newName,
                    newSurname, oldUserInfo.get().getCity(), oldUserInfo.get().getPhoneNumber(),
                    oldUserInfo.get().getPassword(), oldUserInfo.get().getRole(), oldUserInfo.get().getState());
            updateUserSession(request,user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUserName(HttpServletRequest request, IUserService userService) {
        oldUserInfo = userService.getUserByEmail(returnUserEmailFromCookie(request));
        String newName = request.getParameter(String.valueOf(Constant.CHANGE_NAME));

        if (dataEnteredHandler.nameValidation(newName)){
            user = createUser(oldUserInfo.get().getUserId(), oldUserInfo.get().getEmail(), newName,
                    oldUserInfo.get().getSecondName(), oldUserInfo.get().getCity(), oldUserInfo.get().getPhoneNumber(),
                    oldUserInfo.get().getPassword(), oldUserInfo.get().getRole(), oldUserInfo.get().getState());
            updateUserSession(request,user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUserSurname(HttpServletRequest request, IUserService userService) {
        oldUserInfo = userService.getUserByEmail(returnUserEmailFromCookie(request));
        String newSurname = request.getParameter(String.valueOf(Constant.CHANGE_SURNAME));

        if (dataEnteredHandler.nameValidation(newSurname)){
            user = createUser(oldUserInfo.get().getUserId(), oldUserInfo.get().getEmail(), oldUserInfo.get().getFirstName(),
                    newSurname, oldUserInfo.get().getCity(), oldUserInfo.get().getPhoneNumber(),
                    oldUserInfo.get().getPassword(), oldUserInfo.get().getRole(), oldUserInfo.get().getState());
            updateUserSession(request,user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    private  User createUser(int userId, String email, String firstName, String secondName, String city, int phoneNumber,
                             String password, String role, String state){
        userBuilder.buildUserId(userId);
        userBuilder.buildUserEmail(email)
                .buildUserFirstName(firstName)
                .buildUserSecondName(secondName)
                .buildUserCity(city)
                .buildUserPhoneNumber(phoneNumber)
                .buildUserPassword(password)
                .buildUserRole(role)
                .buildUserState(state);
        return userBuilder.build();
    }

    private String returnUserEmailFromCookie(HttpServletRequest request){
        cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(String.valueOf(Constant.IN_LOGIN))){
                oldEmail = cookie.getValue();
            }
        }
        return oldEmail;
    }

    private void updateUserSession(HttpServletRequest request, User user){
        HttpSession session = request.getSession();
        session.removeAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED));
        session.setAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED), user);
    }

    private void updateCookieUserInLogin(HttpServletRequest request, HttpServletResponse response, String userEmail){
        cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(String.valueOf(Constant.IN_LOGIN))){
               cookie.setValue(userEmail);
               response.addCookie(cookie);
            }
        }
    }
}
