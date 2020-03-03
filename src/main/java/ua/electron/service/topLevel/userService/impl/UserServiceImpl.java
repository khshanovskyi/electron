package ua.electron.service.topLevel.userService.impl;

import ua.electron.dao.IUserDao;
import ua.electron.entity.Constant;
import ua.electron.entity.User;
import ua.electron.service.lowerLevel.userUpdater.IUserUpdater;
import ua.electron.service.lowerLevel.userUpdater.impl.UserUpdaterImpl;
import ua.electron.service.topLevel.userService.IUserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER  = Logger.getLogger(UserServiceImpl.class);

    private IUserDao userDao;

    public UserServiceImpl(IUserDao IUserDao) {
        this.userDao = IUserDao;
    }

    @Override
    public Optional<User> loginInAccount(String email, String password) {
        Optional<User> userOpt = userDao.getUserByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)){
            LOGGER.info("Authorization success");
            return userOpt;
        }
        LOGGER.info("Authorization not performed!");
        return Optional.empty();
    }

    @Override
    public boolean checkUserState(String email) {
        Optional<User> userOpt = userDao.getUserByEmail(email);
        return userOpt.get().getState().equals(String.valueOf(Constant.UNBLOCKED));
    }

    @Override
    public boolean checkUserSession(HttpServletRequest request) {
        return (request.getSession().getAttribute(String.valueOf(Constant.USER_IS_UNBLOCKED)) != null);
    }

    @Override
    public Optional<User> checkEmailExisting(String email) {
        Optional<User> userOpt ;
        if (email != null){
            userOpt = userDao.getUserByEmail(email);
            return userOpt;
        }
        return Optional.empty();
    }

    @Override
    public void createNewUser(User user) {
        userDao.create(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return  userDao.getUserByEmail(email);
    }

    @Override
    public void  updateUserPersonalInfo(User user) {
         userDao.update(user);
    }
}