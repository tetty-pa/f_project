package com.tpavlyshyn.fp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator {

/*
    private static final String LOGIN_PATTERN = "([a-zA-Z_.]+){6,}@([a-zA-Z_.]+){3,}";
*/
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

    private boolean matchPattern(String data, String currentPattern) {
        Pattern pattern = Pattern.compile(currentPattern);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }
}
