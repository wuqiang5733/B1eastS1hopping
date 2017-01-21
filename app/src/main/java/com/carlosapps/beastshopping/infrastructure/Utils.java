package com.carlosapps.beastshopping.infrastructure;


public class Utils {
    public static final String FIRE_BARS_BASE_URL = "https://beastshopping-7e15f.firebaseio.com/";
//    public static final String FIRE_BARS_BASE_URL = "https://beastshopping-70633.firebaseio.com/";
    public static final String FIRE_BASE_USER_REFERENCE = FIRE_BARS_BASE_URL + "users/";

    public static String encodeEmail(String userEmail){
        return userEmail.replace(".",",");
    }
}
