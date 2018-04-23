package com.truck.airlines.airlines.utils;

/**
 * Created by aditya.singh on 4/20/2018.
 */

public interface C {


    String FRAGMENT_ACTION = "action";
    int FRAGMENT_SPLASH = 0;
    int FRAGMENT_OTP = 1;
    int  FRAGMENT_USER_TYPE = 2;
    int FRAGMENT_REGISTER = 3;
    String BUNDLE = "bundle";
    String TAG_FRAGMENT_SPLASH = "TAG_FRAGMENT_SPLASH";
    String TAG_FRAGMENT_OTP = "TAG_FRAGMENT_OTP";
    String TAG_FRAGMENT_REGISTER = "TAG_FRAGMENT_REGISTER";
    String TAG_FRAGMENT_USER_TYPE = "TAG_FRAGMENT_USER_TYPE";


    long SPLASH_LOADER_TIME = 500;
    String AUTH_TOKEN = "token";
    String BASE_URL = "http://truck.dataheadstudio.com/api/";
    String API_CHECK_NUMBER = BASE_URL + "users/check_phone";
    String API_USER_LOGIN= BASE_URL + "users/user_login";
    String API_USER_REGISTER= BASE_URL + "users/user_register";
    String API_MATERIAL_TYPE= BASE_URL + "trucks/material_type";
    String API_TRUCK_TYPE= BASE_URL + "trucks/truck_type";
    String API_WEIGHT= BASE_URL + "trucks/weight";


    String STATUS_SUCCESS = "RDS001";
    String MOBILE_NUMBER ="MOBILE_NUMBER" ;
    String USER_TYPE = "USER_TYPE";
    String CUSTOMER = "customer";
    String TRUCK_OPERATOR = "truckoperator";
    String TRANSPORTER = "transporter";

    String API_GET_ADDRESS = "http://postalpincode.in/api/pincode/";
    String USER = "user";
}
