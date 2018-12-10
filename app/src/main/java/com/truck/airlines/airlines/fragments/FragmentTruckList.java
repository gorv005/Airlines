package com.truck.airlines.airlines.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.adapter.AdapterTruckListing;
import com.truck.airlines.airlines.adapter.AdapterTrucklist;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.LoadSummaryReq;
import com.truck.airlines.airlines.pojos.ResponseTruckList;
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
public class FragmentTruckList extends Fragment {

    @BindView(R.id.tvTotalTruck)
    TextView tvTotalTruck;
    @BindView(R.id.lvTruckList)
    RecyclerView lvTruckList;
    private Dialog dialog;

    public FragmentTruckList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_truck_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);


        getTruckList();
    }

    private void getTruckList() {
        LoadSummaryReq loadSummaryReq = new LoadSummaryReq();
        loadSummaryReq.setUId(SharedPreference.getInstance(getActivity()).getLoginUser(C.USER).getUId());
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
                    ResponseTruckList responsePost = gson.fromJson(response.toString(), ResponseTruckList.class);
                    if (responsePost.getStatus()) {

                        AdapterTruckListing adapterTrucklist = new AdapterTruckListing(getActivity(), responsePost.getVmsdata());
                        lvTruckList.setAdapter(adapterTrucklist);

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
        }, "truck_list", C.API_TRUCK_LIST, Util.getHeader(getActivity()), obj);


    }

    private void showDialog(String msg) {

        AlertDialog.Builder builder;
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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

}
