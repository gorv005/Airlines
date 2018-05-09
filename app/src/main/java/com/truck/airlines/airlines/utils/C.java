package com.truck.airlines.airlines.utils;

/**
 * Created by aditya.singh on 4/20/2018.
 */

public interface C {


    String FRAGMENT_ACTION = "action";
    int FRAGMENT_SPLASH = 0;
    int FRAGMENT_OTP = 1;
    int FRAGMENT_USER_TYPE = 2;
    int FRAGMENT_REGISTER = 3;
    int FRAGMENT_POST_TRUCK = 4;
    int FRAGMENT_PROFILE = 5;

    int FRAGMENT_TOTAL_TRUCK = 6;
    int FRAGMENT_KYC_DOCUMENT = 7;
    int FRAGMENT_TOTAL_LOADS = 8;
    int FRAGMENT_OFFFLINE_REGISTER =9 ;
    int FRAGMENT_LANGUAGE_SELECT =10;

    String BUNDLE = "bundle";
    String TAG_FRAGMENT_SPLASH = "TAG_FRAGMENT_SPLASH";
    String TAG_FRAGMENT_OTP = "TAG_FRAGMENT_OTP";
    String TAG_FRAGMENT_REGISTER = "TAG_FRAGMENT_REGISTER";
    String TAG_FRAGMENT_USER_TYPE = "TAG_FRAGMENT_USER_TYPE";
    String TAG_FRAGMENT_POST_TRUCK = "TAG_FRAGMENT_POST_TRUCK";
    String TAG_FRAGMENT_PROFILE = "TAG_FRAGMENT_PROFILE";
    String TAG_FRAGMENT_TOTAL_TRUCK = "TAG_FRAGMENT_TOTAL_TRUCK";

    String TAG_FRAGMENT_KYC_DOCUMENT ="TAG_FRAGMENT_KYC_DOCUMENT" ;
    String TAG_FRAGMENT_TOTAL_LOAD ="TAG_FRAGMENT_TOTAL_LOAD" ;
    String TAG_FRAGMENT_OFFLINE_REGISTRATION ="TAG_FRAGMENT_OFFLINE_REGISTRATION" ;
    String TAG_FRAGMENT_LANGUAGE_SELECT  ="FRAGMENT_LANGUAGE_SELECT" ;

    long SPLASH_LOADER_TIME = 500;
    String AUTH_TOKEN = "token";

    String BASE_URL = "http://truck.dataheadstudio.com/api/";
    String API_CHECK_NUMBER = BASE_URL + "users/check_phone";
    String API_USER_LOGIN = BASE_URL + "users/user_login";


    String API_USER_REGISTER = BASE_URL + "users/user_register";
    String API_MATERIAL_TYPE = BASE_URL + "trucks/material_type";
    String API_TRUCK_TYPE = BASE_URL + "trucks/truck_type";

    String API_WEIGHT = BASE_URL + "trucks/weight";
    String API_TRUCK_LOAD = BASE_URL + "trucks/load";
    String API_TRUCK_POST = BASE_URL + "trucks/truckpost";

    String API_TRUCK_LIST = BASE_URL + "trucks/get_trucks";
    String API_LOAD_LIST = BASE_URL + "trucks/get_loads";

    String STATUS_SUCCESS = "RDS001";
    String MOBILE_NUMBER = "MOBILE_NUMBER";
    String USER_TYPE = "USER_TYPE";
    String CUSTOMER = "customer";
    String TRUCK_OPERATOR = "truckoperator";
    String TRANSPORTER = "transporter";
    String DATE_FORMAT = "dd/MMM/yyyy";
    String API_GET_ADDRESS = "http://postalpincode.in/api/pincode/";
    String USER = "user";
    String NUMBER_FORMAT = "+91-";
    String PAN="PAN";
    String AADHAR="AADHAR";
    String PASSPORT="PASSPORT";
    String VISITING_CARD="VISITING_CARD";
    String IS_LOGIN = "is_login" ;

    String FONT = "Mont.otf";
    String LANGUAGE = "language" ;
    String IS_FIRST_TIME_NOT_APP_OPEN = "IS_FIRST_TIME_NOT_APP_OPEN" ;
}
