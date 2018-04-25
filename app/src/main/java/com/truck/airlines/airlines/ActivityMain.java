package com.truck.airlines.airlines;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.truck.airlines.airlines.adapter.AdapterSideMenu;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.Location;
import com.truck.airlines.airlines.pojos.MaterialType;
import com.truck.airlines.airlines.pojos.MaterialTypeResponse;
import com.truck.airlines.airlines.pojos.PostLoad;
import com.truck.airlines.airlines.pojos.Response;
import com.truck.airlines.airlines.pojos.SideMenuItem;
import com.truck.airlines.airlines.pojos.TruckType;
import com.truck.airlines.airlines.pojos.TruckTypeResponse;
import com.truck.airlines.airlines.pojos.WeightResponse;
import com.truck.airlines.airlines.pojos.WeightType;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.SharedPreference;
import com.truck.airlines.airlines.utils.Util;
import com.truck.airlines.airlines.webservice.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etSourcePincode)
    EditText etSourcePincode;
    @BindView(R.id.etSourceCity)
    EditText etSourceCity;
    @BindView(R.id.etDestinationPincode)
    EditText etDestinationPincode;
    @BindView(R.id.etDestinationCity)
    EditText etDestinationCity;
    @BindView(R.id.etMaterialtype)
    EditText etMaterialtype;
    @BindView(R.id.etWeight)
    EditText etWeight;
    @BindView(R.id.etTruckType)
    EditText etTruckType;
    @BindView(R.id.etDateOfLoad)
    EditText etDateOfLoad;
    @BindView(R.id.etNoOfTruck)
    EditText etNoOfTruck;
    @BindView(R.id.spinnerMaterialtype)
    Spinner spinnerMaterialtype;
    @BindView(R.id.spinnerWeight)
    Spinner spinnerWeight;
    @BindView(R.id.spinnerTruckType)
    Spinner spinnerTruckType;
    @BindView(R.id.spinnerNoOfTruck)
    Spinner spinnerNoOfTruck;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.lvMenuItem)
    ListView listView;

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvMobile)
    TextView tvMobile;

    private AdapterSideMenu adapterSideMenu;
    boolean isSource=false;
    private ArrayList truckList;
    private Dialog dialog;
    List<TruckType> truckTypesList;
    List<WeightType> weightTypesList;
    List<MaterialType> materialTypesList;
    String weightId,truckTypeId, materialId,mNoOfTruck,dateOFLoad;
    String[] noOfTruck = new String[]{
            "Select",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"

    };
    Calendar myCalendar = Calendar.getInstance(Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.menu);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        adapterSideMenu = new AdapterSideMenu(this, Util.getSideMenuList(ActivityMain.this));
        listView.setAdapter(adapterSideMenu);
        initialize();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                SideMenuItem sideMenuItem = adapterSideMenu.getItem(position);

                if (sideMenuItem.getNameResourse() == R.string.post_truck) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_POST_TRUCK);
                    startActivity(intent);
                }
                else if (sideMenuItem.getNameResourse() == R.string.profile) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_PROFILE);
                    startActivity(intent);
                }

                else if (sideMenuItem.getNameResourse() == R.string.search_truck) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_TOTAL_TRUCK);
                    startActivity(intent);
                }
                else if (sideMenuItem.getNameResourse() == R.string.my_loads) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_TOTAL_LOADS);
                    startActivity(intent);
                }
                else if (sideMenuItem.getNameResourse() == R.string.kyc_document) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_KYC_DOCUMENT);
                    startActivity(intent);
                }
                else if (sideMenuItem.getNameResourse() == R.string.kyc_document) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_KYC_DOCUMENT);
                    startActivity(intent);
                }
                else  if(sideMenuItem.getNameResourse() == R.string.logout)
                {
                    SharedPreference.getInstance(ActivityMain.this).clearData();
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_OTP);
                    startActivity(intent);
                }
            }
        });


        tvName.setText(SharedPreference.getInstance(this).getLoginUser(C.USER).getData().getFirstName()+" "+SharedPreference.getInstance(this).getLoginUser(C.USER).getData().getLastName());
        tvMobile.setText(SharedPreference.getInstance(this).getLoginUser(C.USER).getData().getPhone());

        getMatirialType();
        //getMatirialType();
      //  getWeightList();
    }


    void initialize(){

        etDateOfLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalender();
            }
        });
        spinnerMaterialtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position!=0){
                                etMaterialtype.setText(materialTypesList.get(position).getMaterialName());
                                materialId=""+materialTypesList.get(position).getId();
                                getWeightList();
                            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerNoOfTruck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position!=0){
                        etNoOfTruck.setText(noOfTruck[position]);
                        mNoOfTruck=""+noOfTruck[position];

                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        etNoOfTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerNoOfTruck.performClick();
            }
        });
        final List<String> specialityList = new ArrayList<>(Arrays.asList(noOfTruck));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item_new,specialityList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNoOfTruck.setAdapter(spinnerArrayAdapter);


        etTruckType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerTruckType.performClick();
            }
        });
        etWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerWeight.performClick();
            }
        });
        etMaterialtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerMaterialtype.performClick();
            }
        });
        spinnerTruckType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    etTruckType.setText(truckTypesList.get(position).getTruckType());
                    truckTypeId=""+truckTypesList.get(position).getId();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    etWeight.setText(weightTypesList.get(position).getWeight());
                    weightId=""+weightTypesList.get(position).getId();

                    getTruckType();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etSourcePincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(etSourcePincode.getText().toString().length()==6)
                {
                    isSource=true;
                    getAddress(etSourcePincode.getText().toString());
                }
                else
                {
                    etSourceCity.setText("");

                }
            }
        });
        etDestinationPincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(etDestinationPincode.getText().toString().length()==6)
                {                    isSource=false;

                    getAddress(etDestinationPincode.getText().toString());
                }
                else
                {
                    etDestinationCity.setText("");

                }
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAllValid()){

                    PostLoad postLoad=new PostLoad();
                    postLoad.setWeightId(weightId);
                    postLoad.setTruckTypeId(truckTypeId);
                    postLoad.setMaterialTypeId(materialId);
                    postLoad.setSourcePincode(etSourcePincode.getText().toString());
                    postLoad.setSourceCity(etSourceCity.getText().toString());
                    postLoad.setDestinationPincode(etDestinationPincode.getText().toString());
                    postLoad.setDestinationCity(etDestinationCity.getText().toString());
                    postLoad.setNoOfTruck(mNoOfTruck);
                    postLoad.setDate(dateOFLoad);
                    doLoad(postLoad);
                }
            }
        });
    }

    private void doLoad(PostLoad postLoad) {

        dialog = Util.getProgressDialog(this, R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();

        final Gson gson = new Gson();
        String json = gson.toJson(postLoad);
        JSONObject obj = null;
        try {
            obj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyService volleyService = new VolleyService(this);
        volleyService.postDataVolley(new IResult() {
            @Override
            public void notifySuccess(String requestType, String response) {
                Log.e("Response :", response.toString());
                dialog.dismiss();

                try {
                    Gson gson = new Gson();
                    Response responsePost = gson.fromJson(response.toString(), Response.class);
                    if (responsePost.getStatus().equals(C.STATUS_SUCCESS)) {
                        emptyFields();
                        showDialog(responsePost.getMessage());



                    } else {
//                        Util.showAlert(getActivity(), getString(R.string.alert), responsePost.getMessage(), getString(R.string.ok), R.drawable.warning);
                        showDialog(responsePost.getMessage());
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, String error) {
                dialog.dismiss();

                Log.e("Response :", error.toString());
                showDialog("ServerError :"+error.toString());

//
//                Intent intent = new Intent(getActivity(), ActivityContainer.class);
//                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
//                startActivity(intent);
//
//                dialog.dismiss();

            }
        }, "otp", C.API_TRUCK_LOAD, Util.getHeader(this), obj);

    }

    void emptyFields(){
        etWeight.setText("");
        etTruckType.setText("");
        etMaterialtype.setText("");
        etNoOfTruck.setText("");
        etSourcePincode.setText("");
        etSourceCity.setText("");
        etDestinationPincode.setText("");
        etDestinationCity.setText("");
        etDateOfLoad.setText("");
    }

    private void getAddress(String pincode) {

        dialog = Util.getProgressDialog(this, R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();


        VolleyService volleyService = new VolleyService(this);
        volleyService.getRequest(new IResult() {
            @Override
            public void notifySuccess(String requestType, String response) {
                Log.e("Response :", response.toString());
                dialog.dismiss();

                try {
                    Gson gson = new Gson();
                    Location responsePost = gson.fromJson(response.toString(), Location.class);
                    if (responsePost.getStatus().equals("Success")) {

                        if(isSource) {
                            etSourceCity.setText(responsePost.getPostOffice().get(0).getName() + ", " +
                                    responsePost.getPostOffice().get(0).getDistrict() + ", " + responsePost.getPostOffice().get(0).getState());
                        }
                        else {
                            etDestinationCity.setText(responsePost.getPostOffice().get(0).getName()  + ", " +
                                    responsePost.getPostOffice().get(0).getDistrict() + ", " + responsePost.getPostOffice().get(0).getState());

                        }

                    }
                    else
                    {
                        showDialog(getString(R.string.no_pincode_found));

                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, String error) {

                Log.e("Response :", error.toString());
                showDialog("ServerError :"+error.toString());

//
//                Intent intent = new Intent(getActivity(), ActivityContainer.class);
//                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
//                startActivity(intent);
//
                dialog.dismiss();

            }
        }, Request.Method.GET, C.API_GET_ADDRESS+pincode, Util.getHeader(this));


    }
    private void showDialog(String msg) {

        AlertDialog.Builder builder;
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }*/

        builder = new AlertDialog.Builder(this);

        builder.setTitle("Alert")
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void openCalender() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
      //  myCalendar.set(Calendar.YEAR, myCalendar.get(Calendar.MONTH) +1);
       // myCalendar.setTimeInMillis(System.currentTimeMillis());
     //   datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        //datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis()+1000*60*60*24*30);


        datePickerDialog.show();


    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {

        String myFormat = C.DATE_FORMAT;
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        etDateOfLoad.setText(sdf.format(myCalendar.getTime()));
        dateOFLoad=""+myCalendar.getTimeInMillis();

    }
    public boolean isAllValid() {

        if (etSourcePincode.getText().toString().length() == 0) {
            etSourcePincode.setError(this.getResources().getString(R.string.required_field));
            etSourcePincode.requestFocus();
            return false;
        } else  if (etDestinationPincode.getText().toString().length() == 0) {
            etDestinationPincode.setError(this.getResources().getString(R.string.required_field));
            etDestinationPincode.requestFocus();
            return false;
        } else  if (etMaterialtype.getText().toString().length() == 0) {
            etMaterialtype.setError(this.getResources().getString(R.string.required_field));
            etMaterialtype.requestFocus();
            return false;
        }else  if (etWeight.getText().toString().length() == 0) {
            etWeight.setError(this.getResources().getString(R.string.required_field));
            etWeight.requestFocus();
            return false;
        } else  if (etTruckType.getText().toString().length() == 0) {
            etTruckType.setError(this.getResources().getString(R.string.required_field));
            etTruckType.requestFocus();
            return false;
        } else  if (etDateOfLoad.getText().toString().length() == 0) {
            etDateOfLoad.setError(this.getResources().getString(R.string.required_field));
            etDateOfLoad.requestFocus();
            return false;
        } else  if (etNoOfTruck.getText().toString().length() == 0) {
            etNoOfTruck.setError(this.getResources().getString(R.string.required_field));
            etNoOfTruck.requestFocus();
            return false;
        }
        else  if (etSourcePincode.getText().toString().length() < 6) {
            etSourcePincode.setError(this.getResources().getString(R.string.please_enter_valid_pincode));
            etSourcePincode.requestFocus();
            return false;
        }
        else  if (etDestinationPincode.getText().toString().length() < 6) {
            etDestinationPincode.setError(this.getResources().getString(R.string.please_enter_valid_pincode));
            etDestinationPincode.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    public void getMatirialType() {


        dialog = Util.getProgressDialog(this, R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();


        JSONObject obj = null;
        try {
            obj = new JSONObject("{\"ff\":\"ff\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyService volleyService = new VolleyService(this);
        volleyService.postDataVolley(new IResult() {
            @Override
            public void notifySuccess(String requestType, String response) {
                Log.e("Response :", response.toString());
                dialog.dismiss();

                Gson gson = new Gson();
                try {
                    MaterialTypeResponse alArrayList = gson.fromJson(response, MaterialTypeResponse.class);

                    if(alArrayList.getStatusCode().equals(C.STATUS_SUCCESS)) {
                        MaterialType truckType=new MaterialType();
                        truckType.setId("0");
                        truckType.setMaterialName(getString(R.string.select));
                        alArrayList.getData().set(0,truckType);
                        materialTypesList=alArrayList.getData();
                        setspinnerItemForMaterial(materialTypesList);
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, String error) {

                Log.e("Response :", error.toString());
                dialog.dismiss();


            }
        }, "truckType", C.API_MATERIAL_TYPE, Util.getHeader(this), obj);

    }
    void setspinnerItemForMaterial(List<MaterialType> list){

        etMaterialtype.setHint(getString(R.string.select));
        final ArrayAdapter<MaterialType> spinnerDisabilityArrayAdapter = new ArrayAdapter<MaterialType>(
                this, R.layout.spinner_item_new, list) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerDisabilityArrayAdapter.setDropDownViewResource(R.layout.dialog_spinner_dropdown_item);
        spinnerMaterialtype.setAdapter(spinnerDisabilityArrayAdapter);
    }

    public void getTruckType() {


        dialog = Util.getProgressDialog(this, R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();


        JSONObject obj = null;
        try {
            obj = new JSONObject("{\"ff\":\"ff\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyService volleyService = new VolleyService(this);
        volleyService.postDataVolley(new IResult() {
            @Override
            public void notifySuccess(String requestType, String response) {
                Log.e("Response :", response.toString());
                dialog.dismiss();

                Gson gson = new Gson();
                try {
                    TruckTypeResponse alArrayList = gson.fromJson(response, TruckTypeResponse.class);
                    if(alArrayList.getStatusCode().equals(C.STATUS_SUCCESS)) {
                        TruckType truckType=new TruckType();
                        truckType.setId("0");
                        truckType.setTruckType(getString(R.string.select));
                        alArrayList.getData().set(0,truckType);
                        truckTypesList=alArrayList.getData();
                        setspinnerItemForTruck(truckTypesList);
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, String error) {

                Log.e("Response :", error.toString());
                dialog.dismiss();


            }
        }, "truckType", C.API_TRUCK_TYPE, Util.getHeader(this), obj);

    }


    void setspinnerItemForTruck(List<TruckType> list){



       etTruckType.setHint(getString(R.string.select));
        final ArrayAdapter<TruckType> spinnerDisabilityArrayAdapter = new ArrayAdapter<TruckType>(
                this, R.layout.spinner_item_new, list) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerDisabilityArrayAdapter.setDropDownViewResource(R.layout.dialog_spinner_dropdown_item);
        spinnerTruckType.setAdapter(spinnerDisabilityArrayAdapter);
    }
    public void getWeightList() {


        dialog = Util.getProgressDialog(this, R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();


        JSONObject obj = null;
        try {
            obj = new JSONObject("{\"ff\":\"ff\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyService volleyService = new VolleyService(this);
        volleyService.postDataVolley(new IResult() {
            @Override
            public void notifySuccess(String requestType, String response) {
                Log.e("Response :", response.toString());
                dialog.dismiss();

                Gson gson = new Gson();
                try {
                    WeightResponse alArrayList = gson.fromJson(response, WeightResponse.class);

                    if(alArrayList.getStatusCode().equals(C.STATUS_SUCCESS)) {
                        WeightType truckType=new WeightType();
                        truckType.setId("0");
                        truckType.setWeight(getString(R.string.select));
                        alArrayList.getData().set(0,truckType);
                        weightTypesList=alArrayList.getData();
                        setspinnerItemForWeight(weightTypesList);
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, String error) {

                Log.e("Response :", error.toString());
                dialog.dismiss();


            }
        }, "truckType", C.API_WEIGHT, Util.getHeader(this), obj);

    }

    void setspinnerItemForWeight(List<WeightType> list){

        etWeight.setHint(getString(R.string.select));
        final ArrayAdapter<WeightType> spinnerDisabilityArrayAdapter = new ArrayAdapter<WeightType>(
                this, R.layout.spinner_item_new, list) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerDisabilityArrayAdapter.setDropDownViewResource(R.layout.dialog_spinner_dropdown_item);
        spinnerWeight.setAdapter(spinnerDisabilityArrayAdapter);
    }

}
