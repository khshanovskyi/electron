package ua.electron.handler.impl;

import ua.electron.handler.IDataEnteredHandler;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataEnteredHandlerImpl implements IDataEnteredHandler {

    private static final Logger LOGGER = Logger.getLogger(DataEnteredHandlerImpl.class);

    private static Pattern checkEmail = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    private static Pattern checkName = Pattern.compile("[A-Za-zА-Яа-яёЁЇїІіЄєҐґ]{2,15}");
    private static Pattern checkPhoneNumber = Pattern.compile("[0-9]{10}");
    private static Pattern checkPassword = Pattern.compile("[A-Za-z0-9]{6,21}");

    @Override
    public boolean emailValidation(String email) {
        if (email != null){
            Matcher matcher = checkEmail.matcher(email);
            LOGGER.trace("Email validation success");
            return matcher.matches();
        }
        LOGGER.trace("Email validation failed");
        return false;
    }

    @Override
    public boolean nameValidation(String name) {
        if (name != null){
            Matcher matcher = checkName.matcher(name);
            LOGGER.trace("Name validation success");
            return matcher.matches();
        }
        LOGGER.trace("Name validation failed");
        return false;
    }

    @Override
    public boolean phoneNumberValidation(String phoneNumber) {
        if (phoneNumber != null){
            Matcher matcher = checkPhoneNumber.matcher(phoneNumber);
            LOGGER.trace("Phone number validation success");
            return matcher.matches();
        }
        LOGGER.trace("Phone number validation failed");
        return false;
    }

    @Override
    public boolean passwordValidation(String password) {
        if (password != null){
            Matcher matcher = checkPassword.matcher(password);
            LOGGER.trace("Password validation success");
            return matcher.matches();
        }
        LOGGER.trace("Password validation failed");
        return false;
    }

    @Override
    public boolean passwordConfirmValidation(String password, String passwordConfirm) {
        if (password != null && passwordConfirm != null){
            return password.equals(passwordConfirm);
        }
        LOGGER.trace("Passwords NOT confirmed");
        return false;
    }
}
