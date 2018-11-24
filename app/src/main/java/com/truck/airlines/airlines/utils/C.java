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
    int FRAGMENT_ABOUT_US = 11;

    int FRAGMENT_POST_LOAD = 12;
    int FRAGMENT_SEARCH_LOAD = 13 ;
    int FRAGMENT_DRAG =14 ;
    String BUNDLE = "bundle";
    String TAG_FRAGMENT_SPLASH = "TAG_FRAGMENT_SPLASH";
    String TAG_FRAGMENT_OTP = "TAG_FRAGMENT_OTP";
    String TAG_FRAGMENT_REGISTER = "TAG_FRAGMENT_REGISTER";
    String TAG_FRAGMENT_USER_TYPE = "TAG_FRAGMENT_USER_TYPE";

    String TAG_FRAGMENT_POST_TRUCK = "TAG_FRAGMENT_POST_TRUCK";
    String TAG_FRAGMENT_POST_LOAD = "TAG_FRAGMENT_POST_LOAD";
    String TAG_FRAGMENT_PROFILE = "TAG_FRAGMENT_PROFILE";
    String TAG_FRAGMENT_TOTAL_TRUCK = "TAG_FRAGMENT_TOTAL_TRUCK";

    String TAG_FRAGMENT_KYC_DOCUMENT ="TAG_FRAGMENT_KYC_DOCUMENT" ;
    String TAG_FRAGMENT_TOTAL_LOAD ="TAG_FRAGMENT_TOTAL_LOAD" ;
    String TAG_FRAGMENT_OFFLINE_REGISTRATION ="TAG_FRAGMENT_OFFLINE_REGISTRATION" ;

    String TAG_FRAGMENT_LANGUAGE_SELECT  ="FRAGMENT_LANGUAGE_SELECT" ;
    String TAG_FRAGMENT_ABOUT_US  ="TAG_FRAGMENT_ABOUT_US" ;
    long SPLASH_LOADER_TIME = 100;


    String AUTH_TOKEN = "token";
    String BASE_URL = "http://dataheadstudio.com/vms/api/";

    String API_CHECK_NUMBER = BASE_URL + "otp/generate";

    String API_USER_LOGIN = BASE_URL + "users/user_login";
    String API_USER_REGISTER = BASE_URL + "user/create";
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
    String TRANSPORTER = "1";
    String TRUCK_OPERATOR = "2";
    String CUSTOMER = "3";
    String DATE_FORMAT = "dd/MMM/yyyy";
    String API_GET_ADDRESS = "http://postalpincode.in/api/pincode/";
    String USER = "user";
    String NUMBER_FORMAT = "+91-";
    String PAN="PAN";

    String AADHAR="AADHAR";
    String PASSPORT="PASSPORT";
    String VISITING_CARD="VISITING_CARD";
    String IS_LOGIN = "is_login" ;
    String FONT = "robo.ttf";
    String LANGUAGE = "language" ;
    String IS_FIRST_TIME_NOT_APP_OPEN = "IS_FIRST_TIME_NOT_APP_OPEN" ;
    String ADDRESS ="address" ;
    int RESULT_ADDRESS = 11;
    int REQUEST_ADDRESS =01 ;
    String ADDRESS_DESTINATION ="ADDRESS_DESTINATION" ;
    String ADDRESS_SOURCE ="ADDRESS_SOURCE" ;
    String ADDRESS_FOR = "ADDRESS_FOR";
}
