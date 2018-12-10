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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.truck.airlines.airlines.ActivitySearchAddress;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.adapter.AdapterMaterialName;
import com.truck.airlines.airlines.adapter.AdapterMaterialType;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.Material;
import com.truck.airlines.airlines.pojos.MaterialNameRequest;
import com.truck.airlines.airlines.pojos.MaterialNameResponse;
import com.truck.airlines.airlines.pojos.MaterialType;
import com.truck.airlines.airlines.pojos.MaterialTypeResponse;
import com.truck.airlines.airlines.pojos.PostLoad;
import com.truck.airlines.airlines.pojos.PostTruck;
import com.truck.airlines.airlines.pojos.ResponseApi;
import com.truck.airlines.airlines.pojos.TruckPost;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.SharedPreference;
import com.truck.airlines.airlines.utils.Util;
import com.truck.airlines.airlines.webservice.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPostTruck extends Fragment {


    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.etSourceCity)
    TextView etSourceCity;
    @BindView(R.id.etDestinationCity)
    TextView etDestinationCity;
    @BindView(R.id.etMaterialtype)
    TextView etMaterialtype;
    @BindView(R.id.etMaterialName)
    TextView etMaterialName;
    @BindView(R.id.etWeight)
    TextView etWeight;
    @BindView(R.id.etDateOfLoad)
    TextView etDateOfLoad;
    @BindView(R.id.content_activity_home)
    RelativeLayout contentActivityHome;
    Unbinder unbinder;
    Calendar myCalendar = Calendar.getInstance(Locale.US);
    String dateOFLoad,materialId;
    private Dialog dialog;
    List<MaterialType> materialTypesList;
    List<Material> materialList;
    Material material;
    String sLat,sLong,dLat,dLong;
    public FragmentPostTruck() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_truck, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initialize();

    }


    void  initialize(){
        etDateOfLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalender();
            }
        });
        etMaterialtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMatirialType();

            }
        });
        etMaterialName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMaterialName();
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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllValid()) {

                    TruckPost truckPost = new TruckPost();
                    truckPost.setUId(SharedPreference.getInstance(getActivity()).getLoginUser(C.USER).getUId());
                    truckPost.setMaterialTypeId(materialId);
                    truckPost.setMaterialId(material.getId());
                    truckPost.setSourceAddress(etSourceCity.getText().toString());
                    truckPost.setDestinationAddress(etDestinationCity.getText().toString());
                    truckPost.setLatS(sLat);
                    truckPost.setLngS(sLong);
                    truckPost.setLatD(dLat);
                    truckPost.setLngD(dLong);
                    truckPost.setDate(dateOFLoad);
                    truckPost.setCreatedBy(SharedPreference.getInstance(getActivity()).getLoginUser(C.USER).getUId());
                    truckPost.setModifiedBy(SharedPreference.getInstance(getActivity()).getLoginUser(C.USER).getUId());
                    doPost(truckPost);
                }
            }
        });

    }


    private void doPost(TruckPost postTruck) {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();

        HashMap<String, TruckPost> stringUserHashMap = new HashMap<>();
        stringUserHashMap.put("vmsdata", postTruck);
        final Gson gson = new Gson();
        String json = gson.toJson(stringUserHashMap);
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
                    ResponseApi responsePost = gson.fromJson(response.toString(), ResponseApi.class);
                    if (responsePost.getStatus()) {
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

//                Intent intent = new Intent(getActivity(), ActivityContainer.class);
//                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
//                startActivity(intent);
//                dialog.dismiss();
            }
        }, "otp", C.API_TRUCK_POST, Util.getHeader(getActivity()), obj);

    }
    void emptyFields() {

        etMaterialtype.setText("");
        etMaterialName.setText("");
        etSourceCity.setText("");
        etDestinationCity.setText("");
        etDateOfLoad.setText("");
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
    public void getMaterialName() {


        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();

        MaterialNameRequest materialNameRequest=new MaterialNameRequest();
        materialNameRequest.setMaterialTypeId(materialId);
        HashMap<String, MaterialNameRequest> stringUserHashMap = new HashMap<>();
        stringUserHashMap.put("vmsdata", materialNameRequest);
        final Gson gson = new Gson();
        String json = gson.toJson(stringUserHashMap);
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

                Gson gson = new Gson();
                try {
                    MaterialNameResponse alArrayList = gson.fromJson(response, MaterialNameResponse.class);

                    if (alArrayList.getStatus()) {

                        materialList = alArrayList.getMaterials();
                        setspinnerItemForMetetailName(materialList);
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
        }, "truckType", C.API_MATERIAL_NAME, Util.getHeader(getActivity()), obj);

    }

    void setspinnerItemForMetetailName(final List<Material> list) {
        final AdapterMaterialName spinnerDisabilityArrayAdapter = new AdapterMaterialName(getActivity(), list);
        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(
                R.layout.layout_material_type, null);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(deleteDialogView);
        dialog.setCancelable(true);

        TextView tvTitle = (TextView) deleteDialogView.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.select_material_type);

        GridView gvMaterialType = (GridView) deleteDialogView.findViewById(R.id.gvMaterialType);
        gvMaterialType.setAdapter(spinnerDisabilityArrayAdapter);
        gvMaterialType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* vehicleId = "" + vehicletypeList.get(position).getId();*/
                material = materialList.get(position);
                String data = materialList.get(position).getName();
                etMaterialName.setText(data);
                dialog.dismiss();
            }
        });

        dialog.show();


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
        } else if (etMaterialName.getText().toString().length() == 0) {
            etMaterialName.setError(this.getResources().getString(R.string.required_field));
            etMaterialName.requestFocus();
            return false;
        } else if (etDateOfLoad.getText().toString().length() == 0) {
            etDateOfLoad.setError(this.getResources().getString(R.string.required_field));
            etDateOfLoad.requestFocus();
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

                    if (alArrayList.getStatusCode()) {

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
        final AdapterMaterialType spinnerDisabilityArrayAdapter = new AdapterMaterialType(getActivity(), list);
        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(
                R.layout.layout_material_type, null);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(deleteDialogView);
        dialog.setCancelable(true);

        TextView tvTitle = (TextView) deleteDialogView.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.select_material_type);

        GridView gvMaterialType = (GridView) deleteDialogView.findViewById(R.id.gvMaterialType);
        gvMaterialType.setAdapter(spinnerDisabilityArrayAdapter);
        gvMaterialType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                materialId = "" + materialTypesList.get(position).getId();
                String data = materialTypesList.get(position).getMaterialName();
                etMaterialtype.setText(data);
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    private void updateLabel() {

        String myFormat = C.DATE_FORMAT;
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        etDateOfLoad.setText(sdf.format(myCalendar.getTime()));
        dateOFLoad = "" + myCalendar.getTimeInMillis()/1000L;
    }
    public void setSource(String stringExtra,String slat,String sLong) {
        etSourceCity.setText(stringExtra);
        this.sLat=slat;
        this.sLong=sLong;
    }

    public void setDestination(String stringExtra,String dlat,String dLong) {
        etDestinationCity.setText(stringExtra);
        this.dLat=dlat;
        this.dLong=dLong;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

}
