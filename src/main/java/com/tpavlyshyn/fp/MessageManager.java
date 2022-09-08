package com.tpavlyshyn.fp;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    public static final Locale DEFAULT_LOCALE = new Locale("en", "");

    private static final String RESOURCE_FILE_NAME = "messages";
    public static final String INCORRECT_LOGIN = "message.incorrect_login";
    public static final String LOGIN_IS_NOT_AVAILABLE = "message.login_not_available";
    public static final String DATA_IS_NOT_VALID = "message.data_is_not_valid";
    public static final String CAN_NOT_SEND_MESSAGE = "message.can`t_send_to_email";


    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_FILE_NAME);

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }


    public static void changeLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_FILE_NAME, locale);
    }
}
