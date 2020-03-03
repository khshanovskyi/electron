package ua.electron.handler.validation.impl;

import ua.electron.handler.validation.IValidationInputDataForRegistration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationInputDataForRegistrationImpl implements IValidationInputDataForRegistration {

    private static Pattern checkEmail = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    private static Pattern checkName = Pattern.compile("[A-Za-zА-Яа-яЁё]{2,15}");
    private static Pattern checkPhoneNumber = Pattern.compile("[0-9]{10}");
    private static Pattern checkPassword = Pattern.compile("[A-Za-z0-9]{6,21}");

    @Override
    public boolean emailValidation(String email) {
        if (email != null){
            Matcher matcher = checkEmail.matcher(email);
            return matcher.matches();
        }
        return false;
    }

    @Override
    public boolean nameValidation(String name) {
        if (name != null){
            Matcher matcher = checkName.matcher(name);
            return matcher.matches();
        }
        return false;
    }

    @Override
    public boolean phoneNumberValidation(String phoneNumber) {
        if (phoneNumber != null){
            Matcher matcher = checkPhoneNumber.matcher(phoneNumber);
            return matcher.matches();
        }
        return false;
    }

    @Override
    public boolean passwordValidation(String password) {
        if (password != null){
            Matcher matcher = checkPassword.matcher(password);
            return matcher.matches();
        }
        return false;
    }

    @Override
    public boolean passwordConfirmValidation(String password, String passwordConfirm) {
        if (password != null && passwordConfirm != null){
            return password.equals(passwordConfirm);
        }
        return false;
    }
}
