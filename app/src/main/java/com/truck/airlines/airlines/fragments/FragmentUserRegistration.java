package com.truck.airlines.airlines.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.truck.airlines.airlines.ActivityContainer;
import com.truck.airlines.airlines.BuildConfig;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.Location;
import com.truck.airlines.airlines.pojos.Response;
import com.truck.airlines.airlines.pojos.User;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.Util;
import com.truck.airlines.airlines.webservice.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUserRegistration extends Fragment {


    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLastName)
    EditText etLastName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etTransporterName)
    EditText etTransporterName;
    @BindView(R.id.etPanNumber)
    EditText etPanNumber;
    @BindView(R.id.etAddressLineOne)
    EditText etAddressLineOne;
    @BindView(R.id.etAddressLineTwo)
    EditText etAddressLineTwo;
    @BindView(R.id.etPinCode)
    EditText etPinCode;
    @BindView(R.id.etArea)
    EditText etArea;
    @BindView(R.id.etCity)
    EditText etCity;
    @BindView(R.id.etDistrict)
    EditText etDistrict;
    @BindView(R.id.etState)
    EditText etState;
    @BindView(R.id.btnContinue)
    Button btnContinue;

    private Bundle bundle;
    private String phone;
    private String userType;
    private Dialog dialog;
    private User user;

    public FragmentUserRegistration() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle =getArguments();
        userType = bundle.getString(C.USER_TYPE);
        phone = bundle.getString(C.MOBILE_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_registration, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        if(BuildConfig.DEBUG)
        {
            etFirstName.setText("Aditya");
            etLastName.setText("Singh");
            etEmail.setText("ady@gmail.cm");
            etTransporterName.setText("Adasf");
            etPanNumber.setText("GHSFP3782K");
            etAddressLineOne.setText("Sector 56");
            etAddressLineTwo.setText("F Block");
            etPinCode.setText("201301");
            etArea.setText("56 Sector");
            etCity.setText("Noida");
            etDistrict.setText("Noida");
            etState.setText("UP");
        }

        etPinCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(etPinCode.getText().toString().length()==6)
                {
                    getAddress(etPinCode.getText().toString());
                }
                else
                {
                    etArea.setText("");
                    etCity.setText("");
                    etDistrict.setText("");
                    etState.setText("");
                }
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isAllValid()) {
                    doRegister(user);
                }
            }
        });

    }

    private void doRegister(User user) {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();

        final Gson gson = new Gson();
        String json = gson.toJson(user);
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
                showDialog("ServerError :"+error.toString());

//
//                Intent intent = new Intent(getActivity(), ActivityContainer.class);
//                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
//                startActivity(intent);
//
//                dialog.dismiss();

            }
        }, "otp", C.API_USER_REGISTER, Util.getHeader(getActivity()), obj);

    }

    public boolean isAllValid() {

         user =new User();
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
        } else if (etLastName.getText().toString().length() == 0) {
            etLastName.setError(getActivity().getResources().getString(R.string.last_name_required));
            etLastName.requestFocus();
            return false;
        } else if (etLastName.getText().toString().trim().length() < 3) {
            etLastName.setError(getActivity().getResources().getString(R.string.last_name_should_be_more_then_3_character));
            etLastName.requestFocus();
            return false;
        } else if (etLastName.getText().toString().trim().startsWith(".")) {
            etLastName.setError(getActivity().getResources().getString(R.string.name_could_not_starts_with_dot));
            etLastName.requestFocus();
            return false;
        } else if (etEmail.getText().toString().length() == 0) {
            etEmail.setError(getActivity().getResources().getString(R.string.email_is_required));
            etEmail.requestFocus();
            return false;
        } else if (!Util.isValidMail(etEmail.getText().toString())) {
            etEmail.setError(getActivity().getResources().getString(R.string.please_enter_valid_email));
            etEmail.requestFocus();
            return false;
        } else if (etTransporterName.getText().toString().length() == 0 && etTransporterName.getVisibility() == View.VISIBLE) {//||etConfirmPassword.getText().toString().length() == 0
            etTransporterName.setError(getActivity().getResources().getString(R.string.transporter_required));
            etTransporterName.requestFocus();
            return false;
        } else if (etPanNumber.getText().toString().length() == 0) {//||etConfirmPassword.getText().toString().length() == 0
            etPanNumber.setError(getActivity().getResources().getString(R.string.pan_number_required));
            etPanNumber.requestFocus();
            return false;
        } else if (etAddressLineOne.getText().toString().length() <= 3) {//||etConfirmPassword.getText().toString().length() == 0
            etAddressLineOne.setError(getActivity().getResources().getString(R.string.address_line_one));
            etAddressLineOne.requestFocus();
            return false;
        } else if (etAddressLineTwo.getText().toString().length() == 0) {//||etConfirmPassword.getText().toString().length() == 0
            etAddressLineTwo.setError(getActivity().getResources().getString(R.string.address_line_one));
            etAddressLineTwo.requestFocus();
            return false;
        }
        else if (etPinCode.getText().toString().length() < 6) {//||etConfirmPassword.getText().toString().length() == 0
            etPinCode.setError(getActivity().getResources().getString(R.string.please_enter_valid_pincode));
            etPinCode.requestFocus();
            return false;
        }

        user.setFirstName(etFirstName.getText().toString());
        user.setLastName(etLastName.getText().toString());
        user.setEmail(etEmail.getText().toString());
        user.setTransportName(etTransporterName.getText().toString());
        user.setPanNo(etPanNumber.getText().toString());
        user.setAddress1(etAddressLineOne.getText().toString());
        user.setAddress2(etAddressLineTwo.getText().toString());
        user.setPincode(etPinCode.getText().toString());
        user.setArea(etArea.getText().toString());
        user.setCity(etCity.getText().toString());
        user.setDistric(etDistrict.getText().toString());
        user.setState(etState.getText().toString());
        user.setPhone(phone);
        user.setGroup(userType);
        user.setPrivacy("false");

        return true;
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

                        etArea.setText(responsePost.getPostOffice().get(0).getName());
                        etCity.setText(responsePost.getPostOffice().get(0).getTaluk());
                        etDistrict.setText(responsePost.getPostOffice().get(0).getDistrict());
                        etState.setText(responsePost.getPostOffice().get(0).getState());

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
        }, Request.Method.GET, C.API_GET_ADDRESS+pincode, Util.getHeader(getActivity()));


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
                        intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_OTP);
                        startActivity(intent);
                        getActivity().finish();

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
