package com.truck.airlines.airlines.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.truck.airlines.airlines.ActivityContainer;
import com.truck.airlines.airlines.BuildConfig;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.adapter.AdapterDistricts;
import com.truck.airlines.airlines.adapter.AdapterState;
import com.truck.airlines.airlines.adapter.AdapterTowns;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.District;
import com.truck.airlines.airlines.pojos.Response;
import com.truck.airlines.airlines.pojos.ResponseLocation;
import com.truck.airlines.airlines.pojos.State;
import com.truck.airlines.airlines.pojos.Town;
import com.truck.airlines.airlines.pojos.User;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.Util;
import com.truck.airlines.airlines.webservice.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUserRegistration extends Fragment {


    @BindView(R.id.etFirstName)
    EditText etFirstName;
    //    @BindView(R.id.etLastName)
//    EditText etLastName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    //    @BindView(R.id.etTransporterName)
//    EditText etTransporterName;

    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.etPinCode)
    EditText etPinCode;

    @BindView(R.id.tvTown)
    TextView tvTown;
    @BindView(R.id.tvDistrict)
    TextView tvDistrict;
    @BindView(R.id.tvState)
    TextView tvState;

    @BindView(R.id.btnContinue)
    Button btnContinue;
    @BindView(R.id.spinerState)
    Spinner spinerState;
    @BindView(R.id.spinerDistrict)
    Spinner spinerDistrict;
    @BindView(R.id.spinerTown)
    Spinner spinerTown;
    Unbinder unbinder;
    @BindView(R.id.etPanNumber)
    EditText etPanNumber;
    @BindView(R.id.etLicenseNumber)
    EditText etLicenseNumber;

    private Bundle bundle;
    private String phone;
    private String userType;
    private Dialog dialog;
    private User user;
    private String stateId;
    private String districtId;
    private String townId;

    private AdapterState adapterState;
    private AdapterDistricts adapterDistrict;
    private AdapterTowns adapterTown;


    public FragmentUserRegistration() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        userType = bundle.getString(C.USER_TYPE);
        phone = bundle.getString(C.MOBILE_NUMBER);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_registration, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        if (BuildConfig.DEBUG) {
            etFirstName.setText("Aditya");
//            etLastName.setText("Singh");
            etEmail.setText("ady@gmail.cm");
//            etTransporterName.setText("Adasf");
            etPanNumber.setText("GHSFP3782K");
            etAddress.setText("Sector 56");
            etPinCode.setText("201301");

            tvDistrict.setText("Noida");
            tvState.setText("UP");
        }

        if (!userType.equals(C.DRIVER)) {
            etLicenseNumber.setVisibility(View.GONE);
        }

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isAllValid()) {
                    doRegister(user);
                }
            }
        });


        if (Util.isNetworkConnectivity(getActivity())) {
            getStateList();
        } else {
            Toast.makeText(getActivity(), R.string.please_connect_to_the_internet, Toast.LENGTH_SHORT).show();
        }

        tvState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinerState.performClick();
            }
        });

        tvDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinerDistrict.performClick();
            }
        });
        tvTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinerTown.performClick();
            }
        });

    }

    private void getStateList() {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();

        HashMap<String, String> stringUserHashMap = new HashMap<>();
        stringUserHashMap.put("vmsdata", "hello");
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
                    ResponseLocation responsePost = gson.fromJson(response.toString(), ResponseLocation.class);
                    if (responsePost.isStatus()) {

                        loadState(responsePost.getStates());

                    } else {
                        showDialog(responsePost.getError());
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, String error) {
                dialog.dismiss();

                Log.e("Response :", error.toString());
//                showDialog("ServerError :" + error.toString());
            }
        }, "otp", C.API_STATE_LIST, Util.getHeader(getActivity()), obj);


    }

    private void loadState(ArrayList<State> states) {

        states.add(0, new State("Select State"));
        adapterState = new AdapterState(getActivity(), states);
        spinerState.setAdapter(adapterState);
        spinerState.setSelection(0);

        spinerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    tvState.setText(adapterState.getItem(position).getFullname());
                    stateId = "";
                    return;
                }
                tvState.setText(adapterState.getItem(position).getFullname());
                stateId = adapterState.getItem(position).getId();

//                loadDistrictList(stateId);
                loadTownList(stateId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadDistrictList(String stateId) {


        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();


        HashMap<String, String> stringUserHashMap = new HashMap<>();
        stringUserHashMap.put("state_id", stateId);
        HashMap<String, HashMap<String,String>> map = new HashMap<>();
        map.put("vmsdata", stringUserHashMap);
        final Gson gson = new Gson();
        String json = gson.toJson(map);
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
                    ResponseLocation responsePost = gson.fromJson(response.toString(), ResponseLocation.class);
                    if (responsePost.isStatus()) {

                        loadDistrct(responsePost.getDistricts());

                    } else {
                        showDialog(responsePost.getError());
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
        }, "otp", C.API_DISTRICT_LIST, Util.getHeader(getActivity()), obj);


    }


    private void loadDistrct(ArrayList<District> districts) {

        districts.add(0, new District("Select District"));
        adapterDistrict = new AdapterDistricts(getActivity(), districts);
        spinerDistrict.setAdapter(adapterDistrict);
        spinerDistrict.setSelection(0);

        spinerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    tvDistrict.setText(adapterDistrict.getItem(position).getFullname());
                    districtId = "";
                    return;
                }
                tvDistrict.setText(adapterDistrict.getItem(position).getFullname());
                districtId = adapterDistrict.getItem(position).getId();

                loadTownList(districtId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void loadTownList(String districtId) {


        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();


        HashMap<String, String> stringUserHashMap = new HashMap<>();
        stringUserHashMap.put("state_id", districtId);

        HashMap<String, HashMap<String, String>> hashMap = new HashMap<>();
        hashMap.put("vmsdata", stringUserHashMap);

        final Gson gson = new Gson();
        String json = gson.toJson(hashMap);
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
                    ResponseLocation responsePost = gson.fromJson(response.toString(), ResponseLocation.class);
                    if (responsePost.isStatus()) {

                        loadTown(responsePost.getTowns());

                    } else {
                        showDialog(responsePost.getError());
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
        }, "otp", C.API_TOWN_LIST, Util.getHeader(getActivity()), obj);


    }


    private void loadTown(ArrayList<Town> towns) {

        towns.add(0, new Town("Select Town"));
        adapterTown = new AdapterTowns(getActivity(), towns);
        spinerTown.setAdapter(adapterTown);
        spinerTown.setSelection(0);

        spinerTown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    tvTown.setText(adapterTown.getItem(position).getFullname());
                    districtId = "";
                    return;
                }
                tvTown.setText(adapterTown.getItem(position).getFullname());
                townId = adapterTown.getItem(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void doRegister(User user) {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();

        HashMap<String, User> stringUserHashMap = new HashMap<>();
        stringUserHashMap.put("vmsdata", user);
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
                    Response responsePost = gson.fromJson(response.toString(), Response.class);
                    if (responsePost.getStatus().equals(C.STATUS_SUCCESS)) {
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

                Log.e("Response :", error.toString());
                dialog.dismiss();

//                Intent intent = new Intent(getActivity(), ActivityContainer.class);
//                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
//                startActivity(intent);
//                dialog.dismiss();

            }
        }, "otp", C.API_USER_REGISTER, Util.getHeader(getActivity()), obj);

    }

    public boolean isAllValid() {

        user = new User();
        if (etFirstName.getText().toString().length() == 0) {
            etFirstName.setError(getActivity().getResources().getString(R.string.first_name_required));
            etFirstName.requestFocus();
            return false;
        } else if (etFirstName.getText().toString().trim().length() < 3) {
            etFirstName.setError(getActivity().getResources().getString(R.string.first_name_should_be_more_then_3_character));
            etFirstName.requestFocus();
            return false;
        } else if (etFirstName.getText().toString().trim().startsWith(".")) {
            etFirstName.setError(getActivity().getResources().getString(R.string.name_could_not_starts_with_dot));
            etFirstName.requestFocus();
            return false;
        }
//        else if (etLastName.getText().toString().length() == 0) {
//            etLastName.setError(getActivity().getResources().getString(R.string.last_name_required));
//            etLastName.requestFocus();
//            return false;
//        } else if (etLastName.getText().toString().trim().length() < 3) {
//            etLastName.setError(getActivity().getResources().getString(R.string.last_name_should_be_more_then_3_character));
//            etLastName.requestFocus();
//            return false;
//        } else if (etLastName.getText().toString().trim().startsWith(".")) {
//            etLastName.setError(getActivity().getResources().getString(R.string.name_could_not_starts_with_dot));
//            etLastName.requestFocus();
//            return false;
//        }
        else if (etEmail.getText().toString().length() == 0) {
            etEmail.setError(getActivity().getResources().getString(R.string.email_is_required));
            etEmail.requestFocus();
            return false;
        } else if (!Util.isValidMail(etEmail.getText().toString())) {
            etEmail.setError(getActivity().getResources().getString(R.string.please_enter_valid_email));
            etEmail.requestFocus();
            return false;
        }
//        else if (etTransporterName.getText().toString().length() == 0 && etTransporterName.getVisibility() == View.VISIBLE) {//||etConfirmPassword.getText().toString().length() == 0
//            etTransporterName.setError(getActivity().getResources().getString(R.string.transporter_required));
//            etTransporterName.requestFocus();
//            return false;
//        }
        else if (etPanNumber.getText().toString().length() == 0) {
            etPanNumber.setError(getActivity().getResources().getString(R.string.pan_number_required));
            etPanNumber.requestFocus();
            return false;
        } else if (userType.equals(C.DRIVER) && etLicenseNumber.getText().toString().length() == 0) {
            etLicenseNumber.setError(getActivity().getResources().getString(R.string.license_number_required));
            etLicenseNumber.requestFocus();
            return false;
        } else if (tvState.getText().toString().length() <= 0) {
            tvState.setError(getActivity().getResources().getString(R.string.select_state));
            tvState.requestFocus();
            if (adapterState == null) {
                getStateList();
            }
            return false;
        } else if (tvTown.getText().toString().length() <= 0) {
            tvTown.setError(getActivity().getResources().getString(R.string.select_town));
            tvTown.requestFocus();

            return false;
        } else if (etAddress.getText().toString().length() <= 3) {//||etConfirmPassword.getText().toString().length() == 0
            etAddress.setError(getActivity().getResources().getString(R.string.address_line1));
            etAddress.requestFocus();
            return false;
        }
//        else if (etAddressLineTwo.getText().toString().length() == 0) {//||etConfirmPassword.getText().toString().length() == 0
//            etAddressLineTwo.setError(getActivity().getResources().getString(R.string.address_line2));
//            etAddressLineTwo.requestFocus();
//            return false;
//        }
        else if (etPinCode.getText().toString().length() < 6) {//||etConfirmPassword.getText().toString().length() == 0
            etPinCode.setError(getActivity().getResources().getString(R.string.please_enter_valid_pincode));
            etPinCode.requestFocus();
            return false;
        }

        user.setFirstName(etFirstName.getText().toString());
//        user.setLastName(etLastName.getText().toString());
        user.setEmail(etEmail.getText().toString());
//        user.setTransportName(etTransporterName.getText().toString());
        user.setPanNo(etPanNumber.getText().toString());
        user.setAddress1(etAddress.getText().toString());
//        user.setAddress2(etAddressLineTwo.getText().toString());
        user.setPincode(etPinCode.getText().toString());
        user.setLicenseNumber(etLicenseNumber.getText().toString());
        user.setTown(townId);
        user.setDistric(districtId);
        user.setState(stateId);
        user.setPhone(phone);
        user.setUserType(userType);
        return true;
    }


    private void showDialog(String msg) {

        AlertDialog.Builder builder;
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }*/
        builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getString(R.string.alert))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent(getActivity(), ActivityContainer.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(C.MOBILE_NUMBER, phone);
                        intent.putExtra(C.BUNDLE, bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_OTP);
                        startActivity(intent);
                        getActivity().finish();

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
