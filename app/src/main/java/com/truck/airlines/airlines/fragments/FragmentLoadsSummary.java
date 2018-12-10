package com.truck.airlines.airlines.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.truck.airlines.airlines.ActivitySearchAddress;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.adapter.AdapterLoadsSummary;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.LoadSummaryReq;
import com.truck.airlines.airlines.pojos.LoadSummaryResponse;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.SharedPreference;
import com.truck.airlines.airlines.utils.Util;
import com.truck.airlines.airlines.webservice.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLoadsSummary extends Fragment implements View.OnClickListener{

    @BindView(R.id.tvNoLoad)
    TextView tvNoLoad;
    private Dialog dialog;

    @BindView(R.id.rvLoads)
    RecyclerView rvLoads;
    @BindView(R.id.fabFilter)
    FloatingActionButton fabFilter;
    Unbinder unbinder;

    LinearLayoutManager layoutManager;
     TextView etSourceCity;
     TextView etDestinationCity;
     String mSource,mDestination;
    public FragmentLoadsSummary() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loads_summary, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvLoads.setLayoutManager(layoutManager);
        rvLoads.setHasFixedSize(true);
        fabFilter.setOnClickListener(this);
        LoadSummaryReq loadSummaryReq = new LoadSummaryReq();
        loadSummaryReq.setUId(SharedPreference.getInstance(getActivity()).getLoginUser(C.USER).getUId());
        getLoadList(loadSummaryReq);
    }

    private void getLoadList(LoadSummaryReq loadSummaryReq) {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();

        HashMap<String, LoadSummaryReq> stringUserHashMap = new HashMap<>();
        stringUserHashMap.put("vmsdata", loadSummaryReq);
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
                    LoadSummaryResponse responsePost = gson.fromJson(response.toString(), LoadSummaryResponse.class);
                    if (responsePost.getStatus()) {
                        //  showDialog(responsePost.getMessage());
                        if(responsePost.getVmsdata().size()>0) {
                            tvNoLoad.setVisibility(View.GONE);
                            AdapterLoadsSummary  adapterLoadsSummary = new AdapterLoadsSummary(getActivity(), responsePost.getVmsdata());
                            rvLoads.setAdapter(adapterLoadsSummary);
                        }
                        else {
                            tvNoLoad.setVisibility(View.VISIBLE);
                        }
                    } else {
//                        Util.showAlert(getActivity(), getString(R.string.alert), responsePost.getMessage(), getString(R.string.ok), R.drawable.warning);
                        tvNoLoad.setVisibility(View.VISIBLE);

                       // showDialog(responsePost.getMessage());
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
        }, "loadList", C.API_LOAD_SUMMARY, Util.getHeader(getActivity()), obj);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void openPopUpforFilter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.search_load);
        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.layout_search_source_destination, (ViewGroup) getView(), false);
          etSourceCity = (TextView) viewInflated.findViewById(R.id.etSourceCity);
          etDestinationCity = (TextView) viewInflated.findViewById(R.id.etDestinationCity);

        builder.setView(viewInflated);

        etSourceCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivitySearchAddress.class);
                intent.putExtra(C.ADDRESS_FOR, C.ADDRESS_SOURCE);
                startActivityForResult(intent, C.REQUEST_ADDRESS);
            }
        });
        etDestinationCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivitySearchAddress.class);
                intent.putExtra(C.ADDRESS_FOR, C.ADDRESS_DESTINATION);
                startActivityForResult(intent, C.REQUEST_ADDRESS);
            }
        });
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoadSummaryReq loadSummaryReq = new LoadSummaryReq();
                loadSummaryReq.setUId(SharedPreference.getInstance(getActivity()).getLoginUser(C.USER).getUId());
                loadSummaryReq.setSourceAddress(etSourceCity.getText().toString());
                loadSummaryReq.setDestinationAddress(etDestinationCity.getText().toString());
                getLoadList(loadSummaryReq);
                dialog.dismiss();

            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    public void setSource(String stringExtra,String slat,String sLong) {
        etSourceCity.setText(stringExtra);
        mSource=stringExtra;

    }

    public void setDestination(String stringExtra,String dlat,String dLong) {
        etDestinationCity.setText(stringExtra);
        mDestination=stringExtra;

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


                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabFilter:
                openPopUpforFilter();
            break;
        }
    }
}
