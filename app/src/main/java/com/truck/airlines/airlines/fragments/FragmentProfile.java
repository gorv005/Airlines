package com.truck.airlines.airlines.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.pojos.LoginUser;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.SharedPreference;

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

        LoginUser loginUser = SharedPreference.getInstance(getActivity()).getLoginUser(C.USER);
//        tvName.setText(loginUser.getData().getFirstName()+" "+loginUser.getData().getLastName());
//        tvNumber.setText(loginUser.getData().getPhone());
//        tvFirstName.setText(loginUser.getData().getFirstName());
//        tvLastName.setText(loginUser.getData().getLastName());
//        tvMobile.setText(loginUser.getData().getPhone());
//        tvEmail.setText(loginUser.getData().getEmail());
//        tvPanNumber.setText(loginUser.getData().getPanNumber());
//        tvAddressLine1.setText(loginUser.getData().getAddressLine1());
//        tvAddressLine2.setText(loginUser.getData().getAddressLine2());
//        tvPincode.setText(loginUser.getData().getPincode());
//        tvState.setText(loginUser.getData().getState());
//        tvDistrict.setText(loginUser.getData().getDistric());
//        tvCity.setText(loginUser.getData().getCity());


    }
}
