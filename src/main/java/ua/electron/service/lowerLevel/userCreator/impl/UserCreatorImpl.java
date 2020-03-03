package ua.electron.service.lowerLevel.userCreator.impl;

import ua.electron.entity.Constant;
import ua.electron.entity.User;
import ua.electron.entity.builder.UserBuilder;
import ua.electron.handler.IDataEnteredHandler;
import ua.electron.handler.impl.DataEnteredHandlerImpl;
import ua.electron.service.lowerLevel.userCreator.IUserCreator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserCreatorImpl implements IUserCreator {

    private static final Logger LOGGER = Logger.getLogger(UserCreatorImpl.class);

    private IDataEnteredHandler dataEnteredHandler = new DataEnteredHandlerImpl();
    private UserBuilder userBuilder = new UserBuilder();

    @Override
    public Optional<User> validationAndCreatingNewUser(HttpServletRequest request) {
        String email = request.getParameter(String.valueOf(Constant.REGISTRATION_EMAIL));
        String firstName = request.getParameter(String.valueOf(Constant.REGISTRATION_FIRST_NAME));
        String lastName = request.getParameter(String.valueOf(Constant.REGISTRATION_SECOND_NAME));
        String city = request.getParameter(String.valueOf(Constant.REGISTRATION_CITY));
        String phoneNumber = request.getParameter(String.valueOf(Constant.REGISTRATION_PHONE_NUMBER));
        String password = request.getParameter(String.valueOf(Constant.REGISTRATION_PASSWORD));
        String passwordConfirm = request.getParameter(String.valueOf(Constant.REGISTRATION_PASSWORD_CONFIRM));

        if (dataEnteredHandler.emailValidation(email) &&
        dataEnteredHandler.nameValidation(firstName) &&
        dataEnteredHandler.nameValidation(lastName) &&
        dataEnteredHandler.phoneNumberValidation(phoneNumber) &&
        dataEnteredHandler.passwordValidation(password) &&
        dataEnteredHandler.passwordConfirmValidation(password, passwordConfirm)){

            LOGGER.info("Entered data in registration fields is correct");

            userBuilder.buildUserEmail(email)
            .buildUserFirstName(firstName)
            .buildUserSecondName(lastName)
            .buildUserCity(city)
            .buildUserPhoneNumber(Integer.parseInt(phoneNumber))
            .buildUserPassword(password)
            .buildUserRole(String.valueOf(Constant.USER))
            .buildUserState(String.valueOf(Constant.UNBLOCKED));

            return Optional.of(userBuilder.build());
        }
        return Optional.empty();
    }
}
