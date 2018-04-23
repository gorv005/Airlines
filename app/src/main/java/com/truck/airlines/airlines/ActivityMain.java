package com.truck.airlines.airlines;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.truck.airlines.airlines.adapter.AdapterSideMenu;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.Location;
import com.truck.airlines.airlines.pojos.ResponseList;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.Util;
import com.truck.airlines.airlines.webservice.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    private AdapterSideMenu adapterSideMenu;
    boolean isSource=false;
    private ArrayList truckList;
    private Dialog dialog;

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
        adapterSideMenu = new AdapterSideMenu(this, Util.getSideMenuList());
        listView.setAdapter(adapterSideMenu);


        getTruckList();
    }


    void initialize(){

        spinnerMaterialtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerNoOfTruck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTruckType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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

                }
            }
        });
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
                            etSourceCity.setText(responsePost.getPostOffice().get(0).getName() + ", " + responsePost.getPostOffice().get(0).getTaluk() + ", " +
                                    responsePost.getPostOffice().get(0).getDistrict() + ", " + responsePost.getPostOffice().get(0).getState());
                        }
                        else {
                            etDestinationCity.setText(responsePost.getPostOffice().get(0).getName() + ", " + responsePost.getPostOffice().get(0).getTaluk() + ", " +
                                    responsePost.getPostOffice().get(0).getDistrict() + ", " + responsePost.getPostOffice().get(0).getState());

                        }

                    }
                    else
                    {
                        showDialog(responsePost.getMessage());

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
        }, 2, C.API_GET_ADDRESS+pincode, Util.getHeader(this));


    }
    private void showDialog(String msg) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
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


    public void getTruckList() {


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
                    ResponseList alArrayList = gson.fromJson(response, ResponseList.class);

                    System.out.println("Total Truck :" + alArrayList.getData().size());

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
}
