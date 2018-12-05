package com.truck.airlines.airlines.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.truck.airlines.airlines.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddTruck extends Fragment {


    @BindView(R.id.etVehiclePart1)
    EditText etVehiclePart1;
    @BindView(R.id.etVehiclePart2)
    EditText etVehiclePart2;
    @BindView(R.id.etVehiclePart3)
    EditText etVehiclePart3;
    @BindView(R.id.etRegistrationNum)
    EditText etRegistrationNum;
    @BindView(R.id.etRegistrationDate)
    EditText etRegistrationDate;
    @BindView(R.id.BtnRegistrationCert)
    Button BtnRegistrationCert;
    @BindView(R.id.etChassisNum)
    EditText etChassisNum;
    @BindView(R.id.BtnChahiss_img)
    Button BtnChahissImg;
    @BindView(R.id.etEngineNum)
    EditText etEngineNum;
    @BindView(R.id.BtnEngineImg)
    Button BtnEngineImg;
    @BindView(R.id.etVehicalClass)
    EditText etVehicalClass;
    @BindView(R.id.rbpetrol)
    RadioButton rbpetrol;
    @BindView(R.id.rbdiesel)
    RadioButton rbdiesel;
    @BindView(R.id.rbcng)
    RadioButton rbcng;
    @BindView(R.id.etModel)
    EditText etModel;
    @BindView(R.id.btnFitnessupto)
    Button btnFitnessupto;
    @BindView(R.id.btnInsuranse)
    Button btnInsuranse;
    @BindView(R.id.etFuelNorms)
    EditText etFuelNorms;
    @BindView(R.id.btnRoadTaxPaidUpto)
    Button btnRoadTaxPaidUpto;
    @BindView(R.id.BtnFrontImage)
    Button BtnFrontImage;
    @BindView(R.id.BtnBackImage)
    Button BtnBackImage;
    @BindView(R.id.btnSubmitf)
    Button btnSubmitf;
    Unbinder unbinder;

    public FragmentAddTruck() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_truck, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.BtnRegistrationCert, R.id.BtnChahiss_img, R.id.BtnEngineImg, R.id.btnFitnessupto, R.id.btnInsuranse, R.id.btnRoadTaxPaidUpto, R.id.BtnFrontImage, R.id.BtnBackImage, R.id.btnSubmitf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BtnRegistrationCert:
                break;
            case R.id.BtnChahiss_img:
                break;
            case R.id.BtnEngineImg:
                break;
            case R.id.btnFitnessupto:
                break;
            case R.id.btnInsuranse:
                break;
            case R.id.btnRoadTaxPaidUpto:
                break;
            case R.id.BtnFrontImage:
                break;
            case R.id.BtnBackImage:
                break;
            case R.id.btnSubmitf:
                break;
        }
    }
}
