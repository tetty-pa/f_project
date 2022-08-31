package com.tpavlyshyn.fp.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {


    public boolean matchPattern(String data, String currentPattern) {
        Pattern pattern = Pattern.compile(currentPattern);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }
}
