package com.example.bsep.util;

import java.util.regex.Pattern;

public class Constants {

    public static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!%@#$^*?_~]).+$"); //contains letters, capital letter,numbers and special characters
    public static final Pattern EMAIL_REGEX = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    public static final String PATH = "src/main/resources/keystore.jks";
    public static final char[] PASSWORD = "password".toCharArray();
}
