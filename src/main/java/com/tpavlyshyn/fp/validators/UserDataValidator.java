package com.tpavlyshyn.fp.validators;


public class UserDataValidator extends Validator{

    private static final String LOGIN_PATTERN = "(.+){6,}@(\\S+)";
    private static final String PASSWORD_PATTERN = "([a-zA-Z0-9_]+){7,}";
    private static final String NAME_PATTERN = "[A-Za-zА-Яа-я]+";


    public boolean checkData(String login, String password, String firstName, String lastName) {
        if (login == null || login.isEmpty()) {
            return false;
        }
        if (password == null || password.isEmpty()) {
            return false;
        }
        if (firstName == null || firstName.isEmpty()) {
            return false;
        }
        if (lastName == null || lastName.isEmpty()) {
            return false;
        }

        boolean isLoginValid = matchPattern(login, LOGIN_PATTERN);
        boolean isPasswordValid = matchPattern(password, PASSWORD_PATTERN);
        boolean isFirstNameValid = matchPattern(firstName, NAME_PATTERN);
        boolean isLastNameValid = matchPattern(lastName, NAME_PATTERN);

        return isLoginValid && isFirstNameValid && isPasswordValid && isLastNameValid;
    }

    public boolean checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return matchPattern(password, PASSWORD_PATTERN);
    }
    public boolean checkName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return matchPattern(name, NAME_PATTERN);
    }

}
