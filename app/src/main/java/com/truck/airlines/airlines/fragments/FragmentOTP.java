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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.truck.airlines.airlines.ActivityContainer;
import com.truck.airlines.airlines.ActivityMain;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.ResponseLogin;
import com.truck.airlines.airlines.pojos.ResponseOTP;
import com.truck.airlines.airlines.pojos.Vmsdata;
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
        etPhoneNumber.setText("9411008787");

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
        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.alert)).setMessage(msg).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
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
        Vmsdata vmsdata = new Vmsdata();
        vmsdata.setAction("login");
        vmsdata.setMobile(mobile);
        HashMap<String, Vmsdata> hashMap = new HashMap<>();
        hashMap.put("vmsdata", vmsdata);

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
                    ResponseOTP responsePost = gson.fromJson(response.toString(), ResponseOTP.class);
                    if (responsePost.getStatus()) {
                        showScreenOTP();
                    } else {

                        Intent intent = new Intent(getActivity(), ActivityContainer.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(C.MOBILE_NUMBER, etPhoneNumber.getText().toString());
                        intent.putExtra(C.BUNDLE, bundle);
                        intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
                        startActivity(intent);
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                    showDialog(getActivity().getString(R.string.something_went_wrong));
                }

            }

            @Override
            public void notifyError(String requestType, String error) {
                dialog.dismiss();

                Log.e("Response :", error.toString());
                showDialog("ServerError :" + error.toString());

//                showDialog(getActivity().getString(R.string.something_went_wrong));

                dialog.dismiss();

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
        hashMap.put("mobile", phone);
        hashMap.put("otp", password);

        HashMap<String, HashMap<String,String>> map =new HashMap<>();
        map.put("vmsdata",hashMap);
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
                    ResponseLogin responsePost = gson.fromJson(response.toString(), ResponseLogin.class);
                    if (responsePost.getStatus()) {

                        SharedPreference.getInstance(getActivity()).setLoginUser(C.USER, responsePost.getLoginUser());
                        SharedPreference.getInstance(getActivity()).setBoolean(C.IS_LOGIN, true);
                        SharedPreference.getInstance(getActivity()).setString(C.MOBILE_NUMBER, etPhoneNumber.getText().toString());

                        Intent intent = new Intent(getActivity(), ActivityMain.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(C.MOBILE_NUMBER, etPhoneNumber.getText().toString());
                        intent.putExtra(C.BUNDLE, bundle);
                        intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
                        startActivity(intent);
                        getActivity().finish();


                    } else {
                        SharedPreference.getInstance(getActivity()).setBoolean(C.IS_LOGIN, false);

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
//                showDialog("ServerError :" + error.toString());

//
//                Intent intent = new Intent(getActivity(), ActivityContainer.class);
//                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
//                startActivity(intent);
//
                dialog.dismiss();

            }
        }, "otp", C.API_USER_LOGIN, Util.getHeader(getActivity()), obj);


    }

}
