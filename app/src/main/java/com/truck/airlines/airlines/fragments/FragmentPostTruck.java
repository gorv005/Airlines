package com.truck.airlines.airlines.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.Location;
import com.truck.airlines.airlines.pojos.MaterialType;
import com.truck.airlines.airlines.pojos.TruckType;
import com.truck.airlines.airlines.pojos.TruckTypeResponse;
import com.truck.airlines.airlines.pojos.WeightResponse;
import com.truck.airlines.airlines.pojos.WeightType;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.Util;
import com.truck.airlines.airlines.webservice.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPostTruck extends Fragment {

    @BindView(R.id.etSourcePincode)
    EditText etSourcePincode;
    @BindView(R.id.etSourceCity)
    EditText etSourceCity;
    @BindView(R.id.etDestinationPincode)
    EditText etDestinationPincode;
    @BindView(R.id.etDestinationCity)
    EditText etDestinationCity;
    @BindView(R.id.etWeight)
    EditText etWeight;
    @BindView(R.id.etTruckType)
    EditText etTruckType;
    @BindView(R.id.etDateOfPost)
    EditText etDateOfPost;
    @BindView(R.id.etVehiclePart1)
    EditText etVehiclePart1;
    @BindView(R.id.etVehiclePart2)
    EditText etVehiclePart2;
    @BindView(R.id.etVehiclePart3)
    EditText etVehiclePart3;
    @BindView(R.id.etDriverName)
    EditText etDriverName;
    @BindView(R.id.etMobileNumber)
    EditText etMobileNumber;
    @BindView(R.id.etNoOfTruck)
    EditText etNoOfTruck;
    @BindView(R.id.spinnerWeight)
    Spinner spinnerWeight;
    @BindView(R.id.spinnerTruckType)
    Spinner spinnerTruckType;
    @BindView(R.id.spinnerNoOfTruck)
    Spinner spinnerNoOfTruck;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    private Dialog dialog;
    boolean isSource=false;
    List<TruckType> truckTypesList;
    List<WeightType> weightTypesList;
    String[] noOfTruck = new String[]{
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
    public FragmentPostTruck() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_truck, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initialize();
        getWeightList();
    }

    void initialize(){
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

        spinnerNoOfTruck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    etNoOfTruck.setText(noOfTruck[position]);
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
                getActivity(),R.layout.spinner_item_new,specialityList){
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
        spinnerTruckType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    etTruckType.setText(truckTypesList.get(position).getTruckType());

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

                }
            }
        });

    }

    private void getAddress(String pincode) {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();


        VolleyService volleyService = new VolleyService(getActivity());
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
        }, Request.Method.GET, C.API_GET_ADDRESS+pincode, Util.getHeader(getActivity()));


    }
    private void showDialog(String msg) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
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
        } else  if (etWeight.getText().toString().length() == 0) {
            etWeight.setError(this.getResources().getString(R.string.required_field));
            etWeight.requestFocus();
            return false;
        } else  if (etTruckType.getText().toString().length() == 0) {
            etTruckType.setError(this.getResources().getString(R.string.required_field));
            etTruckType.requestFocus();
            return false;
        } else  if (etVehiclePart1.getText().toString().length() == 0) {
            etVehiclePart1.setError(this.getResources().getString(R.string.required_field));
            etVehiclePart1.requestFocus();
            return false;
        } else  if (etVehiclePart2.getText().toString().length() == 0) {
            etVehiclePart2.setError(this.getResources().getString(R.string.required_field));
            etVehiclePart2.requestFocus();
            return false;
        }
        else  if (etVehiclePart3.getText().toString().length() == 0) {
            etVehiclePart3.setError(this.getResources().getString(R.string.required_field));
            etVehiclePart3.requestFocus();
            return false;
        }
        else  if (etDriverName.getText().toString().length() == 0) {
            etDriverName.setError(this.getResources().getString(R.string.required_field));
            etDriverName.requestFocus();
            return false;
        }
        else  if (etMobileNumber.getText().toString().length() == 0) {
            etMobileNumber.setError(this.getResources().getString(R.string.required_field));
            etMobileNumber.requestFocus();
            return false;
        }
        else  if (etMobileNumber.getText().toString().length() <14) {
            etMobileNumber.setError(this.getResources().getString(R.string.enter_valid_phone_number));
            etMobileNumber.requestFocus();
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

    public void getTruckType() {


        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();


        JSONObject obj = null;
        try {
            obj = new JSONObject("{\"ff\":\"ff\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyService volleyService = new VolleyService(getActivity());
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
                        truckType.setId(0);
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
        }, "truckType", C.API_TRUCK_TYPE, Util.getHeader(getActivity()), obj);

    }


    void setspinnerItemForTruck(List<TruckType> list){



        etTruckType.setHint(getString(R.string.select));
        final ArrayAdapter<TruckType> spinnerDisabilityArrayAdapter = new ArrayAdapter<TruckType>(
                getActivity(), R.layout.spinner_item_new, list) {
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


        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();


        JSONObject obj = null;
        try {
            obj = new JSONObject("{\"ff\":\"ff\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyService volleyService = new VolleyService(getActivity());
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
                        truckType.setId(0);
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
        }, "truckType", C.API_WEIGHT, Util.getHeader(getActivity()), obj);

    }

    void setspinnerItemForWeight(List<WeightType> list){

        etWeight.setHint(getString(R.string.select));
        final ArrayAdapter<WeightType> spinnerDisabilityArrayAdapter = new ArrayAdapter<WeightType>(
                getActivity(), R.layout.spinner_item_new, list) {
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
