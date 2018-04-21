package com.truck.airlines.airlines.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.Util;

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
    @BindView(R.id.etCity)
    EditText etCity;
    @BindView(R.id.etDistrict)
    EditText etDistrict;
    @BindView(R.id.etState)
    EditText etState;
    private Bundle bundle;
    private String phone;
    private String userType;

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
        if (isAllValid()) {
            doRegister();
        }
    }

    private void doRegister() {



    }

    public boolean isAllValid() {

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

        return true;
    }
}
