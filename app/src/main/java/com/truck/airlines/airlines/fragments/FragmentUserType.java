package com.truck.airlines.airlines.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.truck.airlines.airlines.ActivityContainer;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.utils.C;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUserType extends Fragment {

    @BindView(R.id.btnTransporter)
    Button btnTransporter;
    @BindView(R.id.btnDrive)
    Button btnDriver;
    @BindView(R.id.btnCustomer)
    Button btnSupplier;
    @BindView(R.id.btnOfflineLogin)
    Button btnOfflineLogin;
    @BindView(R.id.btnTruckOwner)
    Button btnTruckOwner;
    Unbinder unbinder;
    private String phone;

    public FragmentUserType() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        phone = bundle.getString(C.MOBILE_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_user_type, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        btnTransporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivityContainer.class);
                Bundle bundle = new Bundle();
                bundle.putString(C.MOBILE_NUMBER, phone);
                bundle.putString(C.USER_TYPE, C.TRANSPORTER);
                intent.putExtra(C.BUNDLE, bundle);
                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_REGISTER);
                startActivity(intent);

            }
        });

        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivityContainer.class);
                Bundle bundle = new Bundle();
                bundle.putString(C.MOBILE_NUMBER, phone);
                bundle.putString(C.USER_TYPE, C.DRIVER);
                intent.putExtra(C.BUNDLE, bundle);
                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_REGISTER);
                startActivity(intent);
            }
        });
        btnSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivityContainer.class);
                Bundle bundle = new Bundle();
                bundle.putString(C.MOBILE_NUMBER, phone);
                bundle.putString(C.USER_TYPE, C.SUPPLIER);
                intent.putExtra(C.BUNDLE, bundle);
                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_REGISTER);
                startActivity(intent);
            }
        });
        btnTruckOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivityContainer.class);
                Bundle bundle = new Bundle();
                bundle.putString(C.MOBILE_NUMBER, phone);
                bundle.putString(C.USER_TYPE, C.TRUCK_OWENER);
                intent.putExtra(C.BUNDLE, bundle);
                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_REGISTER);
                startActivity(intent);
            }
        });
        btnOfflineLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivityContainer.class);
                Bundle bundle = new Bundle();
                bundle.putString(C.MOBILE_NUMBER, phone);
                intent.putExtra(C.BUNDLE, bundle);
                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_OFFFLINE_REGISTER);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
