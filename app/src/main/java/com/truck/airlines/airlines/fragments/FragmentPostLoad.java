package com.truck.airlines.airlines.fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.truck.airlines.airlines.ActivitySearchAddress;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.Location;
import com.truck.airlines.airlines.pojos.MaterialType;
import com.truck.airlines.airlines.pojos.MaterialTypeResponse;
import com.truck.airlines.airlines.pojos.PostLoad;
import com.truck.airlines.airlines.pojos.Response;
import com.truck.airlines.airlines.pojos.TruckType;
import com.truck.airlines.airlines.pojos.TruckTypeResponse;
import com.truck.airlines.airlines.pojos.WeightResponse;
import com.truck.airlines.airlines.pojos.WeightType;
import com.truck.airlines.airlines.utils.C;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPostLoad extends Fragment {
    @BindView(R.id.etSourceCity)
    TextView etSourceCity;
    @BindView(R.id.etDestinationCity)
    TextView etDestinationCity;
    @BindView(R.id.etMaterialtype)
    TextView etMaterialtype;
    @BindView(R.id.etWeight)
    TextView etWeight;
    @BindView(R.id.etTruckType)
    TextView etTruckType;
    @BindView(R.id.etDateOfLoad)
    TextView etDateOfLoad;
    @BindView(R.id.etNumberOfTruck)
    TextView etNoOfTruck;

    //    @BindView(R.id.spinnerMaterialtype)
//    Spinner spinnerMaterialtype;
//    @BindView(R.id.spinnerWeight)
//    Spinner spinnerWeight;
//    @BindView(R.id.spinnerTruckType)
//    Spinner spinnerTruckType;
//    @BindView(R.id.spinnerNoOfTruck)
//    Spinner spinnerNoOfTruck;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    private ArrayList truckList;
    private Dialog dialog;
    List<TruckType> truckTypesList;
    List<WeightType> weightTypesList;
    List<MaterialType> materialTypesList;
    String weightId, truckTypeId, materialId, mNoOfTruck, dateOFLoad;
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
    boolean isSource = false;


    public FragmentPostLoad() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_load, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        etMaterialtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMatirialType();
            }
        });
        initialize();
    }

    void initialize() {

        etDateOfLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalender();
            }
        });
//        spinnerMaterialtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position != 0) {
//                    etMaterialtype.setText(materialTypesList.get(position).getMaterialName());
//                    materialId = "" + materialTypesList.get(position).getId();
//                    getWeightList();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        spinnerNoOfTruck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position != 0) {
//                    etNoOfTruck.setText(noOfTruck[position]);
//                    mNoOfTruck = "" + noOfTruck[position];
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        etNoOfTruck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                spinnerNoOfTruck.performClick();
//            }
//        });
        final List<String> specialityList = new ArrayList<>(Arrays.asList(noOfTruck));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), R.layout.spinner_item_new, specialityList) {
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
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerNoOfTruck.setAdapter(spinnerArrayAdapter);


//        etTruckType.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                spinnerTruckType.performClick();
//            }
//        });
//        etWeight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                spinnerWeight.performClick();
//            }
//        });
//        etMaterialtype.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        spinnerTruckType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position != 0) {
//                    etTruckType.setText(truckTypesList.get(position).getTruckType());
//                    truckTypeId = "" + truckTypesList.get(position).getId();
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        spinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position != 0) {
//                    etWeight.setText(weightTypesList.get(position).getWeight());
//                    weightId = "" + weightTypesList.get(position).getId();
//
//                    getTruckType();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllValid()) {

                    PostLoad postLoad = new PostLoad();
                    postLoad.setWeightId(weightId);
                    postLoad.setTruckTypeId(truckTypeId);
                    postLoad.setMaterialTypeId(materialId);
                    postLoad.setSourceCity(etSourceCity.getText().toString());
                    postLoad.setDestinationCity(etDestinationCity.getText().toString());
                    postLoad.setNoOfTruck(mNoOfTruck);
                    postLoad.setDate(dateOFLoad);
                    doLoad(postLoad);
                }
            }
        });

        etSourceCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivitySearchAddress.class);
                intent.putExtra(C.ADDRESS_FOR, C.ADDRESS_SOURCE);
                startActivityForResult(intent, C.REQUEST_ADDRESS);
            }
        });


        etDestinationCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivitySearchAddress.class);
                intent.putExtra(C.ADDRESS_FOR, C.ADDRESS_DESTINATION);
                startActivityForResult(intent, C.REQUEST_ADDRESS);
            }
        });
    }


    private void doLoad(PostLoad postLoad) {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
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

        VolleyService volleyService = new VolleyService(getActivity());
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
                showDialog("ServerError :" + error.toString());

//
//                Intent intent = new Intent(getActivity(), ActivityContainer.class);
//                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
//                startActivity(intent);
//
//                dialog.dismiss();

            }
        }, "otp", C.API_TRUCK_LOAD, Util.getHeader(getActivity()), obj);

    }

    void emptyFields() {
        etWeight.setText("");
        etTruckType.setText("");
        etMaterialtype.setText("");
        etNoOfTruck.setText("");
        etSourceCity.setText("");
        etDestinationCity.setText("");
        etDateOfLoad.setText("");
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

                        if (isSource) {
                            etSourceCity.setText(responsePost.getPostOffice().get(0).getName() + ", " +
                                    responsePost.getPostOffice().get(0).getDistrict() + ", " + responsePost.getPostOffice().get(0).getState());
                        } else {
                            etDestinationCity.setText(responsePost.getPostOffice().get(0).getName() + ", " +
                                    responsePost.getPostOffice().get(0).getDistrict() + ", " + responsePost.getPostOffice().get(0).getState());

                        }

                    } else {
                        showDialog(getString(R.string.no_pincode_found));

                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, String error) {

                Log.e("Response :", error.toString());
                showDialog("ServerError :" + error.toString());

//
//                Intent intent = new Intent(getActivity(), ActivityContainer.class);
//                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
//                startActivity(intent);
//
                dialog.dismiss();

            }
        }, Request.Method.GET, C.API_GET_ADDRESS + pincode, Util.getHeader(getActivity()));


    }

    private void showDialog(String msg) {

        AlertDialog.Builder builder;
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }*/

        builder = new AlertDialog.Builder(getActivity());

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
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
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
        dateOFLoad = "" + myCalendar.getTimeInMillis();

    }

    public boolean isAllValid() {

        if (etSourceCity.getText().toString().length() == 0) {
            etSourceCity.setError(this.getResources().getString(R.string.required_field));
            etSourceCity.requestFocus();
            return false;
        } else if (etDestinationCity.getText().toString().length() == 0) {
            etDestinationCity.setError(this.getResources().getString(R.string.required_field));
            etDestinationCity.requestFocus();
            return false;
        } else if (etMaterialtype.getText().toString().length() == 0) {
            etMaterialtype.setError(this.getResources().getString(R.string.required_field));
            etMaterialtype.requestFocus();
            return false;
        } else if (etWeight.getText().toString().length() == 0) {
            etWeight.setError(this.getResources().getString(R.string.required_field));
            etWeight.requestFocus();
            return false;
        } else if (etTruckType.getText().toString().length() == 0) {
            etTruckType.setError(this.getResources().getString(R.string.required_field));
            etTruckType.requestFocus();
            return false;
        } else if (etDateOfLoad.getText().toString().length() == 0) {
            etDateOfLoad.setError(this.getResources().getString(R.string.required_field));
            etDateOfLoad.requestFocus();
            return false;
        } else if (etNoOfTruck.getText().toString().length() == 0) {
            etNoOfTruck.setError(this.getResources().getString(R.string.required_field));
            etNoOfTruck.requestFocus();
            return false;
        }


        return true;
    }

    public void getMatirialType() {


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
                    MaterialTypeResponse alArrayList = gson.fromJson(response, MaterialTypeResponse.class);

                    if (alArrayList.getStatusCode().equals(C.STATUS_SUCCESS)) {
                        MaterialType truckType = new MaterialType();
                        truckType.setId("0");
                        truckType.setMaterialName(getString(R.string.select));
                        alArrayList.getData().set(0, truckType);
                        materialTypesList = alArrayList.getData();
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
        }, "truckType", C.API_MATERIAL_TYPE, Util.getHeader(getActivity()), obj);

    }

    void setspinnerItemForMaterial(final List<MaterialType> list) {

        final ArrayAdapter<MaterialType> spinnerDisabilityArrayAdapter = new ArrayAdapter<MaterialType>(
                getActivity(), R.layout.spinner_item_new, list);


        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(
                R.layout.layout_material_type, null);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().

                setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(deleteDialogView);
        dialog.setCancelable(true);


        GridView gvMaterialType = (GridView) deleteDialogView.findViewById(R.id.gvMaterialType);
        gvMaterialType.setAdapter(spinnerDisabilityArrayAdapter);
        gvMaterialType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etMaterialtype.setText(list.get(position).getMaterialName());
                materialId = "" + materialTypesList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dialog.show();


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
                    if (alArrayList.getStatusCode().equals(C.STATUS_SUCCESS)) {
                        TruckType truckType = new TruckType();
                        truckType.setId("0");
                        truckType.setTruckType(getString(R.string.select));
                        alArrayList.getData().set(0, truckType);
                        truckTypesList = alArrayList.getData();
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


    void setspinnerItemForTruck(List<TruckType> list) {


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
//        spinnerTruckType.setAdapter(spinnerDisabilityArrayAdapter);
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

                    if (alArrayList.getStatusCode().equals(C.STATUS_SUCCESS)) {
                        WeightType truckType = new WeightType();
                        truckType.setId("0");
                        truckType.setWeight(getString(R.string.select));
                        alArrayList.getData().set(0, truckType);
                        weightTypesList = alArrayList.getData();
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

    void setspinnerItemForWeight(List<WeightType> list) {

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
//        spinnerWeight.setAdapter(spinnerDisabilityArrayAdapter);
    }
}
