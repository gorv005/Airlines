package com.truck.airlines.airlines.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.truck.airlines.airlines.ActivityContainer;
import com.truck.airlines.airlines.ActivityMain;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.LoginUser;
import com.truck.airlines.airlines.pojos.Response;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.SharedPreference;
import com.truck.airlines.airlines.utils.Util;
import com.truck.airlines.airlines.webservice.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOTP extends Fragment {

    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @BindView(R.id.etOTP)
    EditText etOTP;
    @BindView(R.id.tvMsg)
    TextView tvMsg;

    private Dialog dialog;
    private String phone;

    public FragmentOTP() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            phone = getArguments().getString(C.MOBILE_NUMBER, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPhoneNumber.getVisibility() == View.VISIBLE) {
                    if (Util.isValidMobile(etPhoneNumber.getText().toString())) {
                        otpRequest(etPhoneNumber.getText().toString());
                    } else {
                        showDialog("Please enter valid number");
                    }
                } else {
                    if (etOTP.getText().toString().length() > 0) {
                        login(etPhoneNumber.getText().toString(), etOTP.getText().toString());
                    } else {
                        showDialog("Please enter password");
                    }
                }
            }
        });


        if (phone != null) {
            etPhoneNumber.setText(phone + "");
        }
    }

    private void showDialog(String msg) {

        AlertDialog.Builder builder;
    /*    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    private void otpRequest(String mobile) {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("phone", mobile);

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
                    Response responsePost = gson.fromJson(response.toString(), Response.class);
                    if (responsePost.getStatus().equals(C.STATUS_SUCCESS)) {

                        if (responsePost.getIsRegister()) {
//                            showDialog(responsePost.getMessage());
                            showScreenOTP();
                        } else {

                            Intent intent = new Intent(getActivity(), ActivityContainer.class);
                            Bundle bundle = new Bundle();
                            bundle.putString(C.MOBILE_NUMBER, etPhoneNumber.getText().toString());
                            intent.putExtra(C.BUNDLE, bundle);
                            intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
                            startActivity(intent);
                        }

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
                showDialog("ServerError :" + error.toString());

//
//                Intent intent = new Intent(getActivity(), ActivityContainer.class);
//                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
//                startActivity(intent);
//
//                dialog.dismiss();

            }
        }, "otp", C.API_CHECK_NUMBER, Util.getHeader(getActivity()), obj);


    }

    private void showScreenOTP() {
        etOTP.setVisibility(View.VISIBLE);
        etPhoneNumber.setVisibility(View.GONE);
        tvMsg.setText(R.string.please_enter_password);


    }

    private void login(String phone, String password) {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        hashMap.put("password", password);
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
                    LoginUser responsePost = gson.fromJson(response.toString(), LoginUser.class);
                    if (responsePost.getStatus().equals(C.STATUS_SUCCESS)) {

                        SharedPreference.getInstance(getActivity()).setLoginUser(C.USER,responsePost);
                        Intent intent = new Intent(getActivity(), ActivityMain.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(C.MOBILE_NUMBER, etPhoneNumber.getText().toString());
                        intent.putExtra(C.BUNDLE, bundle);
                        intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
                        startActivity(intent);
                        getActivity().finish();


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
                showDialog("ServerError :" + error.toString());

//
//                Intent intent = new Intent(getActivity(), ActivityContainer.class);
//                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
//                startActivity(intent);
//
//                dialog.dismiss();

            }
        }, "otp", C.API_USER_LOGIN, Util.getHeader(getActivity()), obj);


    }

}
