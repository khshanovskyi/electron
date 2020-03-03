package ua.electron.handler.validation;

public interface IValidationInputDataForRegistration {

    boolean emailValidation(String email);

    boolean nameValidation(String name);

    boolean phoneNumberValidation(String phoneNumber);

    boolean passwordValidation(String password);

    boolean passwordConfirmValidation(String password, String passwordConfirm);
}
