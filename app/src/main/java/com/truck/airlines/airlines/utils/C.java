package com.truck.airlines.airlines.utils;

/**
 * Created by aditya.singh on 4/20/2018.
 */

public interface C {


    String FRAGMENT_ACTION = "action";
    int FRAGMENT_SPLASH = 0;
    int FRAGMENT_OTP = 1;
    int FRAGMENT_REGISTER = 2;
    String BUNDLE = "bundle";
    String TAG_FRAGMENT_SPLASH = "TAG_FRAGMENT_SPLASH";
    String TAG_FRAGMENT_OTP = "TAG_FRAGMENT_OTP";
    String TAG_FRAGMENT_REGISTER = "TAG_FRAGMENT_REGISTER";

    long SPLASH_LOADER_TIME = 500;
    String AUTH_TOKEN = "token";
    String BASE_URL = "http://truck.dataheadstudio.com//api/users";
    String API_CHECK_NUMBER = BASE_URL + "/check_phone";
    String API_USER_LOGIN= BASE_URL + "/user_login ";
    String STATUS_SUCCESS = "RDS001";
}
