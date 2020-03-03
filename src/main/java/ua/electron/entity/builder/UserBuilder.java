package ua.electron.entity.builder;

import ua.electron.entity.User;

import java.util.Objects;

public class UserBuilder {

    private int userId;
    private String email;
    private String firstName;
    private String secondName;
    private String city;
    private int phoneNumber;
    private String password;
    private String role;
    private String state;

    public void buildUserId(int userId) {
        this.userId = userId;
    }

    public UserBuilder buildUserEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder buildUserFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder buildUserSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public UserBuilder buildUserCity(String city) {
        this.city = city;
        return this;
    }

    public UserBuilder buildUserPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder buildUserPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder buildUserRole(String role) {
        this.role = role;
        return this;
    }

    public void buildUserState(String state) {
        this.state = state;
    }

    public User build() {
        User user = new User();

        user.setUserId(userId);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setCity(city);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setRole(role);
        user.setState(state);

        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBuilder that = (UserBuilder) o;
        return userId == that.userId &&
                phoneNumber == that.phoneNumber &&
                Objects.equals(email, that.email) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(city, that.city) &&
                Objects.equals(password, that.password) &&
                Objects.equals(role, that.role) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, firstName, secondName, city, phoneNumber, password, role, state);
    }

    @Override
    public String toString() {
        return "UserBuilder{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
