package com.truck.airlines.airlines.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.OpenableColumns;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.pojos.SideMenuItem;
import com.truck.airlines.airlines.pojos.TruckType;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * Created by aditya.singh on 3/1/2017.
 */


public class Util {
    private static String preShareKeyForSignature = "QD32VdbRuMa0iI0q9q7cH6FIHGcNWGdEZOLyK669";
    private static String signatureAlgorithm = "HmacSHA256";

    public static boolean isNetworkConnectivity(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static boolean isValidMail(String email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidMobile(String phone) {
        return (android.util.Patterns.PHONE.matcher(phone).matches() && (phone.length() >= 10 && phone.length() < 15));
    }

    public static int getAge(String _year, String _month, String _day) {

        Calendar dateOfYourBirth = new GregorianCalendar(Integer.parseInt(_year), Integer.parseInt(_month) - 1, Integer.parseInt(_day));
        Calendar today = Calendar.getInstance();
        int yourAge = today.get(Calendar.YEAR) - dateOfYourBirth.get(Calendar.YEAR);
        dateOfYourBirth.add(Calendar.YEAR, yourAge);
        if (today.before(dateOfYourBirth)) {
            yourAge--;
        }

        return yourAge;
    }

    public static boolean isCameraPermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {

                return true;
            } else {


                // ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation

            return true;
        }


    }


    public static Bitmap rotateImageIfRequired(Bitmap img, String selectedImage) throws IOException {

        ExifInterface ei = new ExifInterface(selectedImage);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    public static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    public static boolean isValidPassword(String password) {

        Pattern pattern;
        Matcher matcher;

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,16}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static ArrayList<SideMenuItem> getSideMenuList(Context context) {
        ArrayList<SideMenuItem> sideMenuItems = new ArrayList<SideMenuItem>();
//        if (SharedPreference.getInstance(context).getLoginUser(C.USER).getData().getUserGroup().equals(C.TRANSPORTER) ||
//                SharedPreference.getInstance(context).getLoginUser(C.USER).getData().getUserGroup().equals(C.TRUCK_OWENER)) {

        int id = Integer.parseInt(SharedPreference.getInstance(context).getLoginUser(C.USER).getUtId());

        switch (id) {

            case 2:
                break;
            case 3:
                sideMenuItems.add(new SideMenuItem(R.string.post_a_load, R.drawable.truck_left));
                sideMenuItems.add(new SideMenuItem(R.string.my_loads, R.drawable.truck_left));


                break;
            case 4:
                sideMenuItems.add(new SideMenuItem(R.string.add_truck, R.drawable.truck_left));
                sideMenuItems.add(new SideMenuItem(R.string.my_trucks, R.drawable.truck_left));

                break;
            case 5:
                sideMenuItems.add(new SideMenuItem(R.string.post_a_load, R.drawable.truck_left));
                sideMenuItems.add(new SideMenuItem(R.string.my_loads, R.drawable.truck_left));

                break;
            case 6:

                sideMenuItems.add(new SideMenuItem(R.string.truck_post, R.drawable.truck_left));

                break;
        }
        sideMenuItems.add(new SideMenuItem(R.string.profile, R.drawable.truck_left));
        sideMenuItems.add(new SideMenuItem(R.string.about_us, R.drawable.truck_left));
        sideMenuItems.add(new SideMenuItem(R.string.policy_info, R.drawable.truck_left));
        sideMenuItems.add(new SideMenuItem(R.string.contact_us, R.drawable.truck_left));
        sideMenuItems.add(new SideMenuItem(R.string.logout, R.drawable.truck_left));

//        } else {
//            sideMenuItems.add(new SideMenuItem(R.string.post_a_load, R.drawable.truck_left));
//            sideMenuItems.add(new SideMenuItem(R.string.search_truck, R.drawable.truck_left));
//            sideMenuItems.add(new SideMenuItem(R.string.profile, R.drawable.truck_left));
//            sideMenuItems.add(new SideMenuItem(R.string.my_loads, R.drawable.truck_left));
//            sideMenuItems.add(new SideMenuItem(R.string.kyc_document, R.drawable.truck_left));
//            sideMenuItems.add(new SideMenuItem(R.string.about_us, R.drawable.truck_left));
//            sideMenuItems.add(new SideMenuItem(R.string.policy_info, R.drawable.truck_left));
//            sideMenuItems.add(new SideMenuItem(R.string.contact_us, R.drawable.truck_left));
//            sideMenuItems.add(new SideMenuItem(R.string.logout, R.drawable.truck_left));
//        }

        return sideMenuItems;
    }


   public static String getDateFromUnixTime(String unixSeconds){
        Date date = new java.util.Date(Long.parseLong(unixSeconds)*1000L);
// the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
// give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
    public static ArrayList<SideMenuItem> getmainMenuList(Context context) {
        ArrayList<SideMenuItem> sideMenuItems = new ArrayList<SideMenuItem>();
//        if (SharedPreference.getInstance(context).getLoginUser(C.USER).getData().getUserGroup().equals(C.TRANSPORTER) ||
//                SharedPreference.getInstance(context).getLoginUser(C.USER).getData().getUserGroup().equals(C.TRUCK_OWENER)) {

        int id = Integer.parseInt(SharedPreference.getInstance(context).getLoginUser(C.USER).getUtId());

        switch (id) {
            case 1:

                break;
            case 2:
                break;
            case 3:
                sideMenuItems.add(new SideMenuItem(R.string.post_a_load, R.drawable.truck_left));
                sideMenuItems.add(new SideMenuItem(R.string.my_loads, R.drawable.truck_left));
                break;
            case 4:
                sideMenuItems.add(new SideMenuItem(R.string.add_truck, R.drawable.truck_left));
                sideMenuItems.add(new SideMenuItem(R.string.my_trucks, R.drawable.truck_left));

                break;
            case 5:
                sideMenuItems.add(new SideMenuItem(R.string.post_a_load, R.drawable.truck_left));
                sideMenuItems.add(new SideMenuItem(R.string.my_loads, R.drawable.truck_left));
                break;
            case 6:
                sideMenuItems.add(new SideMenuItem(R.string.truck_post, R.drawable.truck_left));
                break;
        }
//        } else {
//            sideMenuItems.add(new SideMenuItem(R.string.post_a_load, R.drawable.truck_left));
//            sideMenuItems.add(new SideMenuItem(R.string.search_truck, R.drawable.truck_left));
//            sideMenuItems.add(new SideMenuItem(R.string.my_loads, R.drawable.truck_left));
//        }

        return sideMenuItems;
    }


    public static String getMessageTimn(long mili) {
//        SimpleDateFormat sdf;
//        Timestamp stamp = new Timestamp(mili);
//        Date date = new Date(stamp.getTime());
//        if (System.currentTimeMillis() - 3600 * 1000 * 24 > mili)
//            sdf = new SimpleDateFormat("h:mm a");
//        else
//            sdf = new SimpleDateFormat("dd MMM");
////        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//        return sdf.format(date);

//mili = mili -3600 * 1000 * 24;
        Calendar smsTime = Calendar.getInstance();
        smsTime.setTimeInMillis(mili);

        Calendar now = Calendar.getInstance();

        final String timeFormatString = "h:mm aa";
        final String dateTimeFormatString = "dd MMM";
        final long HOURS = 60 * 60 * 60;
        if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE)) {
            return "" + DateFormat.format(timeFormatString, smsTime);
        } else {
            return "" + DateFormat.format(dateTimeFormatString, smsTime);
        }
//        else if (now.get(Calendar.YEAR) == smsTime.get(Calendar.YEAR)) {
//            return DateFormat.format(dateTimeFormatString, smsTime).toString();
//        } else {
//            return DateFormat.format("MMMM dd yyyy, h:mm aa", smsTime).toString();
//        }

    }

    public static String getDateInNumber(String s) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        Date date = null;
        try {
            date = sdf.parse(s);
            sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return s;
    }

    public static String getDateInNumberRevered(String s) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        Date date = null;
        try {
            date = sdf.parse(s);
            sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return s;
    }

    public static String priceWithoutDecimal(String price) {
        double amount = Double.parseDouble(price);
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(amount);
    }

    public static String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String getDateInString(String s) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        Date date = null;
        try {
            date = sdf.parse(s);
            sdf = new SimpleDateFormat("dd-MMM-yyyy");
            sdf.applyPattern("d MMM");
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return s;
    }


    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static long getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        Log.e("Daay  ", calendar.getTimeInMillis() + "");
        return calendar.getTimeInMillis();
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {

        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        //  Bitmap newBitmap=   Bitmap.createScaledBitmap(realImage,(int)(realImage.getWidth()*0.9), (int)(realImage.getHeight()*0.9), true);
        return newBitmap;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + 10 + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    public static void hideKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null)
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    public static Typeface getFont(Activity activity, String fontName) {
        Typeface font = Typeface.createFromAsset(activity.getAssets(), fontName);
        return font;
    }


    public static String getTime(Date date) {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        newDateFormat.applyPattern("EEE, d MMM");
        String strDate = newDateFormat.format(date);
        return strDate;
    }


    public static String getTimeFromString(String date) {
        try {
            Date start = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
                    .parse(date);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            newDateFormat.applyPattern("EEE, d MMM");
            String strDate = newDateFormat.format(start);
            return strDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDateWithYear(String date) {
        try {
            Date start = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
                    .parse(date);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            newDateFormat.applyPattern("EEE, d MMM yyyy");
            String strDate = newDateFormat.format(start);
            return strDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getNextDate(String date) {
        try {
            Date start = new SimpleDateFormat("dd-MMMM-yyyy", Locale.ENGLISH)
                    .parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(start);
            c.add(Calendar.DATE, 1);
            start = c.getTime();
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
            date = newDateFormat.format(start);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getProperDate(Date date) {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String strDate = newDateFormat.format(date);
        return strDate;
    }

    public static String getDateFromString(String date) {

        try {
            Date start = new SimpleDateFormat("dd/MMM/yyyy").parse(date);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = newDateFormat.format(start);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDurationBetweenTimes(String departureTime, String arrivalTime) {
        String duration = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            Date startDate = simpleDateFormat.parse(departureTime);
            Date endDate = simpleDateFormat.parse(arrivalTime);

            long difference = endDate.getTime() - startDate.getTime();
            if (difference < 0) {
                Date dateMax = simpleDateFormat.parse("24:00");
                Date dateMin = simpleDateFormat.parse("00:00");
                difference = (dateMax.getTime() - startDate.getTime()) + (endDate.getTime() - dateMin.getTime());
            }
            int days = (int) (difference / (1000 * 60 * 60 * 24));
            int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
            int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
//            Log.i("log_tag", "Hours: " + hours + ", Mins: " + min);
            if (hours < 10)
                duration = "0" + hours + "h";
            else
                duration = hours + "h";
            if (min < 10)
                duration = duration + " 0" + min + "m";
            else
                duration = duration + " " + min + "m";
            return duration;
        } catch (Exception e) {

        }

        return duration;
    }


    public static String getTimeAM() {

        Calendar smsTime = Calendar.getInstance();
        smsTime.setTimeInMillis(System.currentTimeMillis());

        Calendar now = Calendar.getInstance();

        final String dateTimeFormatString = "yyyy-MM-dd hh:mm:ss";

        return "" + DateFormat.format(dateTimeFormatString, smsTime);


    }

    public static Time getTimeDuration(String departureTime) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            Date startDate = simpleDateFormat.parse(departureTime);
            Time ppstime = new Time(startDate.getTime());

            return ppstime;

        } catch (Exception e) {

        }

        return null;
    }


    public static String getNationality(String nationality) {
        String strNationality = "";
        switch (nationality) {
            case "Nepalese":
                strNationality = "NP";
                break;
            case "Indian":
                strNationality = "IN";
                break;
            case "Foreigner":
                strNationality = "US";
                break;
            case "Foreigner (Resident)":
                strNationality = "US";
                break;
        }
        return strNationality;
    }

    public static long getMilisecond(String depatureDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        Date date = null;
        try {
            date = sdf.parse(depatureDate);

            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }


    public static Calendar getTime(String da, String dateFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        Date date = null;
        try {
            date = sdf.parse(da);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;

    }

    public static String getDateFromFormats(String da, String dateFormat, String requiredFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        Date date = null;
        try {
            date = sdf.parse(da);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sdf = new SimpleDateFormat(requiredFormat);
        return sdf.format(date);
    }

    public static int getMonthNumber(String mon) {

        Date date = null;
        try {
            date = new SimpleDateFormat("MMMM").parse(mon);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            return cal.get(Calendar.MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean checkValidReturnDate(String startDate, String endDate) {
        try {
            Date start = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
                    .parse(startDate);

            Date end = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
                    .parse(endDate);
            if (start.after(end))
                return false;
            else
                return true;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;

    }

    public static boolean isDepatureAfterArrivalDate(String startDate, String endDate) {

        try {
            Date start = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
                    .parse(startDate);
            Date end = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
                    .parse(endDate);

            System.out.println(start);
            System.out.println(end);

            if (start.compareTo(end) > 0) {
                System.out.println("start is after end");
            }
            return start.compareTo(end) > 0;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getClass(String flightClassCode) {
        return flightClassCode;
    }


//    public static ArrayList<SideMenuItem> getSideMenuList(boolean isLogin, String userType) {
//        ArrayList<SideMenuItem> sideMenuItems = new ArrayList<SideMenuItem>();
//
//        if (isLogin && userType.equals(C.DOCTOR)) {
//            sideMenuItems.add(new SideMenuItem(R.string.queue, R.drawable.queue));
//            sideMenuItems.add(new SideMenuItem(R.string.emr_and_tracker, R.drawable.menu_general));
//            sideMenuItems.add(new SideMenuItem(R.string.profile, R.drawable.icon_profile));
//            sideMenuItems.add(new SideMenuItem(R.string.setting, R.drawable.icon_settting));
////            sideMenuItems.add(new SideMenuItem(R.string.awareness, R.drawable.menu_general));
////            sideMenuItems.add(new SideMenuItem(R.string.notification, R.drawable.icon_settting));
//
//        } else if (isLogin && userType.equals(C.PATIENT)) {
//            sideMenuItems.add(new SideMenuItem(R.string.dependent, R.drawable.menu_children));
//            sideMenuItems.add(new SideMenuItem(R.string.emr_and_tracker, R.drawable.menu_general));
//            sideMenuItems.add(new SideMenuItem(R.string.profile, R.drawable.icon_profile));
//            sideMenuItems.add(new SideMenuItem(R.string.setting, R.drawable.icon_settting));
////            sideMenuItems.add(new SideMenuItem(R.string.awareness, R.drawable.menu_general));
////            sideMenuItems.add(new SideMenuItem(R.string.notification, R.drawable.icon_settting));
//
//        } else
//            sideMenuItems.add(new SideMenuItem(R.string.login, R.drawable.icon_settting));
////        if (isLogin) {
////            sideMenuItems.add(new SideMenuItem(R.string.my_bookings, R.drawable.booking));
////            sideMenuItems.add(new SideMenuItem(R.string.my_account, R.drawable.account));
////        }
//////        sideMenuItems.add(new SideMenuItem(R.string.cargo_tracking, R.drawable.cargo));
////        sideMenuItems.add(new SideMenuItem(R.string.our_destinations, R.drawable.destination));
////        sideMenuItems.add(new SideMenuItem(R.string.about_us, R.drawable.about));
////        sideMenuItems.add(new SideMenuItem(R.string.contact_us, R.drawable.contact));
////        if (isLogin)
////            sideMenuItems.add(new SideMenuItem(R.string.logout, R.drawable.logout));
//        return sideMenuItems;
//    }


    public static SpannableString setSomeColorRed2(String s) {

        SpannableString ss1 = new SpannableString(s);
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, s.indexOf("-"), 0);
        return ss1;
    }

    public static String getSignature(String methodName, String parameter, String timestamp) {
        StringBuffer data = new StringBuffer();
        data.append(methodName).append(timestamp);
        if (null != parameter && !parameter.equals("")) {
            data.append(parameter);
        }
        String signature = calculateHMAC(data.toString());
        return signature;
    }


//

    private static String calculateHMAC(String data) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(preShareKeyForSignature.getBytes(), signatureAlgorithm);
            Mac mac = Mac.getInstance(signatureAlgorithm);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            String derivedSignature = Base64.encodeToString(rawHmac, Base64.DEFAULT);
            return derivedSignature;
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public static boolean isDatesequal(String departDate, String returnDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date date1 = null;
        try {
            date1 = sdf.parse(departDate);

            Date date2 = sdf.parse(returnDate);

            System.out.println("date1 : " + sdf.format(date1));
            System.out.println("date2 : " + sdf.format(date2));
            if (date1.compareTo(date2) == 0) {
                System.out.println("Date1 is equal to Date2");
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isDepartSmallThanReturn(String departDate, String returnDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date date1 = null;
        try {
            date1 = sdf.parse(departDate);

            Date date2 = sdf.parse(returnDate);

            System.out.println("date1 : " + sdf.format(date1));
            System.out.println("date2 : " + sdf.format(date2));

            if (date1.compareTo(date2) > 0) {
                System.out.println("Date1 is after Date2");
                return false;

            } else if (date1.compareTo(date2) < 0) {
                System.out.println("Date1 is before Date2");
                return true;

            } else if (date1.compareTo(date2) == 0) {
                System.out.println("Date1 is equal to Date2");
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    //    public static Map<String, String> getHeader() {
//        HashMap<String, String> headers = new HashMap<String, String>();
////        String authToken = SharedPreference.getInstance(activity).getString(C.AUTH_TOKEN);
////        headers.put("authtoken", authToken);
//        headers.put("Accept", "application/json");
//        headers.put("Content-Type", "application/json");
//        return headers;


    public static boolean isDateSmallThanCurrent(String departDate, String returnDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date date1 = null;
        try {
            date1 = sdf.parse(departDate);

            Date date2 = sdf.parse(returnDate);

            System.out.println("date1 : " + sdf.format(date1));
            System.out.println("date2 : " + sdf.format(date2));

            if (date1.compareTo(date2) > 0) {
                System.out.println("Date1 is after Date2");
                return false;

            } else if (date1.compareTo(date2) < 0) {
                System.out.println("Date1 is before Date2");
                return true;

            } else if (date1.compareTo(date2) == 0) {
                System.out.println("Date1 is equal to Date2");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Dialog getProgressDialog(Context context, int msg) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.progress_dialog);
        TextView text = (TextView) dialog.findViewById(R.id.tvMsg);
        text.setText(context.getResources().getString(msg));
        ImageView image = (ImageView) dialog.findViewById(R.id.ivLoader);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotation);
        image.setAnimation(animation);
        animation.start();
        return dialog;
    }

    public static Map<String, String> getHeader(Context context) {
        HashMap<String, String> headers = new HashMap<String, String>();
//        if (SharedPreference.getInstance(context).getLoginUser(C.USER) != null) {
//            String authToken = SharedPreference.getInstance(context).getLoginUser(C.USER).getToken();
//            headers.put("authtoken", authToken);
//        }
        headers.put("Authorization", "Basic YWRtaW46MTIzNDU2");
        headers.put("X-API-KEY", "VMSFORNEEDPLUS@2018");
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        System.out.println("Headers : " + headers.toString());
        return headers;
    }


    public static String getFileName(Uri uri, Activity activity) {
        Cursor returnCursor = activity.getContentResolver().query(uri, null, null, null, null);
        /*
         * Get the column indexes of the data in the Cursor,
         * move to the first row in the Cursor, get the data,
         * and display it.
         */
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        return returnCursor.getString(nameIndex);

    }

    public static String miliSecToDate(String s) {

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");

            long milliSeconds = Long.parseLong(s);
            System.out.println(milliSeconds);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            return formatter.format(calendar.getTime());

        } catch (Exception e) {

            return s;
        }

    }

    public static ArrayList<TruckType> getTruckSize() {
        ArrayList<TruckType> truckSize = new ArrayList<>();
        truckSize.add(new TruckType("16 feet"));
        truckSize.add(new TruckType("18 feet"));
        truckSize.add(new TruckType("20 feet"));
        truckSize.add(new TruckType("22 feet"));
        truckSize.add(new TruckType("24 feet"));
        truckSize.add(new TruckType("26 feet"));
        truckSize.add(new TruckType("28 feet"));
        truckSize.add(new TruckType("30 feet"));
        truckSize.add(new TruckType("32 feet"));

        return truckSize;
    }


    public static ArrayList<TruckType> getTruckCapacity() {
        ArrayList<TruckType> truckSize = new ArrayList<>();
        truckSize.add(new TruckType("3 MT"));
        truckSize.add(new TruckType("6 MT"));
        truckSize.add(new TruckType("8 MT"));
        truckSize.add(new TruckType("10 MT"));
        truckSize.add(new TruckType("12 MT"));
        truckSize.add(new TruckType("14 MT"));

        return truckSize;
    }
/*
    public static void showAlert(Context context, String title, String msg, String btnText, int img) {


        final LayoutInflater factory = LayoutInflater.from(context);
        final View deleteDialogView = factory.inflate(
                R.layout.dialog_alert, null);
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(deleteDialogView);
        dialog.setCancelable(false);

        TextView tvMsg = (TextView) deleteDialogView.findViewById(R.id.tvMsg);
        tvMsg.setText(msg);

        TextView tvTitle = (TextView) deleteDialogView.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        ImageView ivAlertImage = (ImageView) deleteDialogView.findViewById(R.id.ivAlertImage);
        ivAlertImage.setImageResource(img);
        Button btnDone = (Button) deleteDialogView.findViewById(R.id.btnDone);
        btnDone.setText(btnText);


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();

            }
        });


        dialog.show();


    }
*/

}
