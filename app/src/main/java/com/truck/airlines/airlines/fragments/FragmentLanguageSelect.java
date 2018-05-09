package com.truck.airlines.airlines.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.truck.airlines.airlines.ActivityContainer;
import com.truck.airlines.airlines.ActivityMain;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.SharedPreference;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLanguageSelect extends Fragment {

    @BindView(R.id.loopView)
    LoopView loopView;
    @BindView(R.id.btnContinue)
    Button btnContinue;
    private int langSelected;

    public FragmentLanguageSelect() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_language_select, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        final ArrayList<String> strings = new ArrayList<>();
        strings.add(getString(R.string.english));
        strings.add(getString(R.string.hindi));
        strings.add(getString(R.string.telegu));
        strings.add(getString(R.string.tamil));
        strings.add(getString(R.string.gujrati));
        strings.add(getString(R.string.punjabi));
        loopView.setItems(strings);
        loopView.setCenterTextColor(Color.RED);
        loopView.setDividerColor(Color.RED);
        loopView.setNotLoop();
        loopView.setOuterTextColor(R.color.colortext);
        loopView.setTextSize(15);
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

                langSelected = index;
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreference.getInstance(getActivity()).setString(C.LANGUAGE, strings.get(langSelected));
                SharedPreference.getInstance(getActivity()).setBoolean(C.IS_FIRST_TIME_NOT_APP_OPEN, true);
                if (SharedPreference.getInstance(getActivity()).getBoolean(C.IS_LOGIN)) {
                    Intent intent = new Intent(getActivity(), ActivityMain.class);
                    startActivity(intent);

                    getActivity().finish();

                } else {

                    Intent intent = new Intent(getActivity(), ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_OTP);
                    startActivity(intent);
                }

            }
        });
    }
}
