package com.truck.airlines.airlines.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.adapter.AdapterLoadsSummary;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.LoadSummaryReq;
import com.truck.airlines.airlines.pojos.LoadSummaryResponse;
import com.truck.airlines.airlines.pojos.LoginUser;
import com.truck.airlines.airlines.pojos.UserProfileReq;
import com.truck.airlines.airlines.pojos.UserProfileResponse;
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
public class FragmentProfile extends Fragment {


    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.tvFirstName)
    TextView tvFirstName;
    @BindView(R.id.tvLastName)
    TextView tvLastName;
    @BindView(R.id.tvMobile)
    TextView tvMobile;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvPanNumber)
    TextView tvPanNumber;
    @BindView(R.id.tvAddressLine1)
    TextView tvAddressLine1;
    @BindView(R.id.tvAddressLine2)
    TextView tvAddressLine2;
    @BindView(R.id.tvPincode)
    TextView tvPincode;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.tvDistrict)
    TextView tvDistrict;
    @BindView(R.id.tvCity)
    TextView tvCity;
    private Dialog dialog;
    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        UserProfileReq userProfileReq = new UserProfileReq();
        userProfileReq.setId(SharedPreference.getInstance(getActivity()).getLoginUser(C.USER).getUId());
        getProfile(userProfileReq);

    }

    private void getProfile(UserProfileReq userProfileReq) {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();

        HashMap<String, UserProfileReq> stringUserHashMap = new HashMap<>();
        stringUserHashMap.put("vmsdata", userProfileReq);
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
                    UserProfileResponse responsePost = gson.fromJson(response.toString(), UserProfileResponse.class);
                    if (responsePost.getStatus()) {
                        //  showDialog(responsePost.getMessage());
                        if(responsePost.getVmsdata().getFirstname()!=null) {
                            tvName.setText(responsePost.getVmsdata().getFirstname());
                        }
                        else {
                            tvName.setText("");

                        }
                        if(responsePost.getVmsdata().getPhone1()!=null) {
                            tvNumber.setText(responsePost.getVmsdata().getPhone1());
                        }
                        else {
                            tvNumber.setText("");

                        }

                        if(responsePost.getVmsdata().getFirstname()!=null) {
                            tvFirstName.setText(responsePost.getVmsdata().getFirstname());
                        }
                        else {
                            tvFirstName.setText("");

                        }
                        if(responsePost.getVmsdata().getLastname()!=null) {
                            tvLastName.setText(responsePost.getVmsdata().getLastname());
                        }
                        else {
                            tvLastName.setText("");

                        }
                        if(responsePost.getVmsdata().getPhone1()!=null) {
                            tvMobile.setText(responsePost.getVmsdata().getPhone1());

                        }
                        else {
                            tvMobile.setText("");

                        }
                        if(responsePost.getVmsdata().getEmail()!=null) {
                            tvEmail.setText(responsePost.getVmsdata().getEmail());

                        }
                        else {
                            tvEmail.setText("");

                        }
                        if(responsePost.getVmsdata().getPan()!=null) {
                            tvPanNumber.setText(responsePost.getVmsdata().getPan());

                        }
                        else {
                            tvPanNumber.setText("");

                        }
                        if(responsePost.getVmsdata().getPincode()!=null) {
                            tvPincode.setText(responsePost.getVmsdata().getPincode());

                        }
                        else {
                            tvPincode.setText("");

                        }
                        if(responsePost.getVmsdata().getAddress()!=null) {
                            tvAddressLine1.setText(responsePost.getVmsdata().getAddress());

                        }
                        else {
                            tvAddressLine1.setText("");

                        }
                      //  tvName.setText(responsePost.getVmsdata().getFirstname());

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
        }, "loadList", C.API_USER_PROFILE, Util.getHeader(getActivity()), obj);

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

                        getActivity().onBackPressed();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
