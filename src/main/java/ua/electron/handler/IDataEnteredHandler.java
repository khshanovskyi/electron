package ua.electron.handler;

import java.util.Optional;

public interface IDataEnteredHandler {

    boolean emailValidation(String email);

    boolean nameValidation(String name);

    boolean phoneNumberValidation(String phoneNumber);

    boolean passwordValidation(String password);

    boolean passwordConfirmValidation(String password, String passwordConfirm);
}
