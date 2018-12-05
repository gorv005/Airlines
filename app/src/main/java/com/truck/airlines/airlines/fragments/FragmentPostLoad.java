package com.truck.airlines.airlines.fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.truck.airlines.airlines.ActivitySearchAddress;
import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.adapter.AdapterMaterialType;
import com.truck.airlines.airlines.adapter.AdapterNoOfTruck;
import com.truck.airlines.airlines.adapter.AdapterTruckType;
import com.truck.airlines.airlines.adapter.AdapterWeight;
import com.truck.airlines.airlines.interfaces.IResult;
import com.truck.airlines.airlines.pojos.MaterialType;
import com.truck.airlines.airlines.pojos.MaterialTypeResponse;
import com.truck.airlines.airlines.pojos.PostLoad;
import com.truck.airlines.airlines.pojos.Response;
import com.truck.airlines.airlines.pojos.TruckType;
import com.truck.airlines.airlines.pojos.WeightResponse;
import com.truck.airlines.airlines.pojos.WeightType;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.Util;
import com.truck.airlines.airlines.webservice.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPostLoad extends Fragment {
    @BindView(R.id.etSourceCity)
    TextView etSourceCity;
    @BindView(R.id.etDestinationCity)
    TextView etDestinationCity;
    @BindView(R.id.etMaterialtype)
    TextView etMaterialtype;
    @BindView(R.id.etMaterialName)


    TextView etMaterialName;
    @BindView(R.id.etWeight)
    TextView etWeight;
    @BindView(R.id.etTruckType)
    TextView etTruckType;
    @BindView(R.id.etDateOfLoad)
    TextView etDateOfLoad;
    @BindView(R.id.etNumberOfTruck)
    TextView etNoOfTruck;
    @BindView(R.id.etFrightType)
    TextView etFrightType;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    private ArrayList truckList;
    private Dialog dialog;
    ArrayList<TruckType> truckTypesList;
    ArrayList<WeightType> weightTypesList;
    ArrayList<MaterialType> materialTypesList;
    String weightId, truckTypeId, materialId, mNoOfTruck, dateOFLoad;
    ArrayList<String> noOfTruck = new ArrayList<>();
    Calendar myCalendar = Calendar.getInstance(Locale.US);
    boolean isSource = false;

    public FragmentPostLoad() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_load, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initialize();
    }

    void initialize() {

        noOfTruck.add("1");
        noOfTruck.add("2");
        noOfTruck.add("3");
        noOfTruck.add("4");
        noOfTruck.add("5");
        noOfTruck.add("6");
        noOfTruck.add("7");
        noOfTruck.add("8");
        noOfTruck.add("9");
        noOfTruck.add("10");
        noOfTruck.add("11");
        noOfTruck.add("12");

        etDateOfLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalender();
            }
        });


        etMaterialtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMatirialType();

            }
        });
        etWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeightList();

            }
        });

        etTruckType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTruckType();
            }
        });
        etNoOfTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setspinnerItemForNoOfTruck(noOfTruck);
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllValid()) {

                    PostLoad postLoad = new PostLoad();
                    postLoad.setWeightId(weightId);
                    postLoad.setTruckTypeId(truckTypeId);
                    postLoad.setMaterialTypeId(materialId);
                    postLoad.setMaterialName(etMaterialName.getText().toString());
                    postLoad.setSourceCity(etSourceCity.getText().toString());
                    postLoad.setDestinationCity(etDestinationCity.getText().toString());
                    postLoad.setNoOfTruck(mNoOfTruck);
                    postLoad.setDate(dateOFLoad);
                    postLoad.setSourcePincode("110011");
                    postLoad.setDestinationPincode("210011");
                    doLoad(postLoad);
                }
            }
        });

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

        etMaterialName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopUpforMaterialName();
            }
        });
        etFrightType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopUpForFrightType();
            }
        });
    }

    private void openPopUpforMaterialName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.material_name);
        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.layout_material_name, (ViewGroup) getView(), false);
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                etMaterialName.setText(input.getText().toString());
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


    void openPopUpForFrightType() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.fright_type);
        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.layout_frieght_type, (ViewGroup) getView(), false);
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
        final RadioButton btnPerMt = (RadioButton) viewInflated.findViewById(R.id.rbPerMt);
        final RadioButton btnFixed = (RadioButton) viewInflated.findViewById(R.id.rbFixed);

        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (btnFixed.isChecked())
                    etFrightType.setText("Fixed . " + input.getText().toString());
                else if (btnPerMt.isChecked()) {
                    etFrightType.setText("Per Mt . " + input.getText().toString());
                }
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

    private void doLoad(PostLoad postLoad) {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();

        final Gson gson = new Gson();
        String json = gson.toJson(postLoad);
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
                        emptyFields();
                        showDialog(responsePost.getMessage());


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
                dialog.dismiss();

                Log.e("Response :", error.toString());
                showDialog("ServerError :" + error.toString());

//                Intent intent = new Intent(getActivity(), ActivityContainer.class);
//                intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_USER_TYPE);
//                startActivity(intent);
//                dialog.dismiss();
            }
        }, "otp", C.API_TRUCK_LOAD, Util.getHeader(getActivity()), obj);

    }

    void emptyFields() {
        etWeight.setText("");
        etTruckType.setText("");
        etMaterialtype.setText("");
        etMaterialName.setText("");
        etNoOfTruck.setText("");
        etSourceCity.setText("");
        etDestinationCity.setText("");
        etDateOfLoad.setText("");
    }


    private void showDialog(String msg) {

        AlertDialog.Builder builder;
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }*/

        builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Alert")
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void openCalender() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        //  myCalendar.set(Calendar.YEAR, myCalendar.get(Calendar.MONTH) +1);
        // myCalendar.setTimeInMillis(System.currentTimeMillis());
        //   datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        //datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis()+1000*60*60*24*30);
        datePickerDialog.show();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {

        String myFormat = C.DATE_FORMAT;
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        etDateOfLoad.setText(sdf.format(myCalendar.getTime()));
        dateOFLoad = "" + myCalendar.getTimeInMillis();

    }

    public boolean isAllValid() {

        if (etSourceCity.getText().toString().length() == 0) {
            etSourceCity.setError(this.getResources().getString(R.string.required_field));
            etSourceCity.requestFocus();
            return false;
        } else if (etDestinationCity.getText().toString().length() == 0) {
            etDestinationCity.setError(this.getResources().getString(R.string.required_field));
            etDestinationCity.requestFocus();
            return false;
        } else if (etMaterialtype.getText().toString().length() == 0) {
            etMaterialtype.setError(this.getResources().getString(R.string.required_field));
            etMaterialtype.requestFocus();
            return false;
        } else if (etMaterialName.getText().toString().length() == 0) {
            etMaterialName.setError(this.getResources().getString(R.string.required_field));
            etMaterialName.requestFocus();
            return false;
        } else if (etWeight.getText().toString().length() == 0) {
            etWeight.setError(this.getResources().getString(R.string.required_field));
            etWeight.requestFocus();
            return false;
        } else if (etTruckType.getText().toString().length() == 0) {
            etTruckType.setError(this.getResources().getString(R.string.required_field));
            etTruckType.requestFocus();
            return false;
        } else if (etDateOfLoad.getText().toString().length() == 0) {
            etDateOfLoad.setError(this.getResources().getString(R.string.required_field));
            etDateOfLoad.requestFocus();
            return false;
        } else if (etNoOfTruck.getText().toString().length() == 0) {
            etNoOfTruck.setError(this.getResources().getString(R.string.required_field));
            etNoOfTruck.requestFocus();
            return false;
        } else if (etFrightType.getText().toString().length() == 0) {
            etFrightType.setError(this.getResources().getString(R.string.required_field));
            etFrightType.requestFocus();
            return false;
        }


        return true;
    }

    public void getMatirialType() {


        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();


        JSONObject obj = null;
        try {
            obj = new JSONObject("{\"ff\":\"ff\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyService volleyService = new VolleyService(getActivity());
        volleyService.postDataVolley(new IResult() {
            @Override
            public void notifySuccess(String requestType, String response) {
                Log.e("Response :", response.toString());
                dialog.dismiss();

                Gson gson = new Gson();
                try {
                    MaterialTypeResponse alArrayList = gson.fromJson(response, MaterialTypeResponse.class);

                    if (alArrayList.getStatusCode().equals(C.STATUS_SUCCESS)) {

                        materialTypesList = alArrayList.getData();
                        setspinnerItemForMaterial(materialTypesList);
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, String error) {

                Log.e("Response :", error.toString());
                dialog.dismiss();


            }
        }, "truckType", C.API_MATERIAL_TYPE, Util.getHeader(getActivity()), obj);

    }

    void setspinnerItemForMaterial(final ArrayList<MaterialType> list) {
        final AdapterMaterialType spinnerDisabilityArrayAdapter = new AdapterMaterialType(getActivity(), list);
        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(
                R.layout.layout_material_type, null);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(deleteDialogView);
        dialog.setCancelable(true);

        TextView tvTitle = (TextView) deleteDialogView.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.select_material_type);

        GridView gvMaterialType = (GridView) deleteDialogView.findViewById(R.id.gvMaterialType);
        gvMaterialType.setAdapter(spinnerDisabilityArrayAdapter);
        gvMaterialType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                materialId = "" + materialTypesList.get(position).getId();
                String data = materialTypesList.get(position).getMaterialName();
                etMaterialtype.setText(data);
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    public void getTruckType() {


        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(
                R.layout.layout_select_truck_type, null);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(deleteDialogView);
        dialog.setCancelable(true);

        TextView tvTitle = (TextView) deleteDialogView.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.truck_type);

        LinearLayout llOpen = (LinearLayout) deleteDialogView.findViewById(R.id.llOpen);
        LinearLayout llContainer = (LinearLayout) deleteDialogView.findViewById(R.id.llContainer);
        LinearLayout llTrailer = (LinearLayout) deleteDialogView.findViewById(R.id.llTrailer);

        llOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                truckTypeId = "Open";
                selectTruckSize(Util.getTruckSize());
                dialog.dismiss();

            }
        });

        llTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                truckTypeId = "Trailer";
                selectTruckSize(Util.getTruckSize());
                dialog.dismiss();

            }
        });

        llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                truckTypeId = "Container";
                selectTruckSize(Util.getTruckSize());
                dialog.dismiss();

            }
        });


        dialog.show();
    }


    void selectTruckSize(ArrayList<TruckType> list) {


        final AdapterTruckType spinnerDisabilityArrayAdapter = new AdapterTruckType(getActivity(), list);
        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(
                R.layout.layout_material_type, null);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(deleteDialogView);
        dialog.setCancelable(true);

        TextView tvTitle = (TextView) deleteDialogView.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.truck_type);

        GridView gvMaterialType = (GridView) deleteDialogView.findViewById(R.id.gvMaterialType);
        gvMaterialType.setAdapter(spinnerDisabilityArrayAdapter);
        gvMaterialType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                truckTypeId = truckTypeId + " . " + spinnerDisabilityArrayAdapter.getItem(position).getTruckType();
                selectTruckCapacity(Util.getTruckCapacity());
                dialog.dismiss();
            }
        });

        dialog.show();

//        spinnerTruckType.setAdapter(spinnerDisabilityArrayAdapter);
    }


    void selectTruckCapacity(ArrayList<TruckType> list) {


        final AdapterTruckType spinnerDisabilityArrayAdapter = new AdapterTruckType(getActivity(), list);
        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(
                R.layout.layout_material_type, null);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(deleteDialogView);
        dialog.setCancelable(true);

        TextView tvTitle = (TextView) deleteDialogView.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.truck_type);

        GridView gvMaterialType = (GridView) deleteDialogView.findViewById(R.id.gvMaterialType);
        gvMaterialType.setAdapter(spinnerDisabilityArrayAdapter);
        gvMaterialType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                truckTypeId = truckTypeId + " . " + spinnerDisabilityArrayAdapter.getItem(position).getTruckType();
                etTruckType.setText(truckTypeId);
                dialog.dismiss();
            }
        });
        dialog.show();
//        spinnerTruckType.setAdapter(spinnerDisabilityArrayAdapter);
    }


    void setspinnerItemForNoOfTruck(ArrayList<String> list) {

        final AdapterNoOfTruck spinnerDisabilityArrayAdapter = new AdapterNoOfTruck(getActivity(), list);
        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(
                R.layout.layout_material_type, null);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(deleteDialogView);
        dialog.setCancelable(true);

        TextView tvTitle = (TextView) deleteDialogView.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.no_of_trucks);

        GridView gvMaterialType = (GridView) deleteDialogView.findViewById(R.id.gvMaterialType);
        gvMaterialType.setAdapter(spinnerDisabilityArrayAdapter);
        gvMaterialType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mNoOfTruck = "" + noOfTruck.get(position);

                etNoOfTruck.setText(mNoOfTruck + " Trucks");
                dialog.dismiss();
            }
        });

        dialog.show();

//        spinnerTruckType.setAdapter(spinnerDisabilityArrayAdapter);
    }


    public void getWeightList() {


        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.setCancelable(false);
        dialog.show();


        JSONObject obj = null;
        try {
            obj = new JSONObject("{\"ff\":\"ff\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyService volleyService = new VolleyService(getActivity());
        volleyService.postDataVolley(new IResult() {
            @Override
            public void notifySuccess(String requestType, String response) {
                Log.e("Response :", response.toString());
                dialog.dismiss();

                Gson gson = new Gson();
                try {
                    WeightResponse alArrayList = gson.fromJson(response, WeightResponse.class);

                    if (alArrayList.getStatusCode().equals(C.STATUS_SUCCESS)) {

                        weightTypesList = alArrayList.getData();
                        setspinnerItemForWeight(weightTypesList);
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, String error) {

                Log.e("Response :", error.toString());
                dialog.dismiss();


            }
        }, "truckType", C.API_WEIGHT, Util.getHeader(getActivity()), obj);

    }

    void setspinnerItemForWeight(ArrayList<WeightType> list) {


        final AdapterWeight spinnerDisabilityArrayAdapter = new AdapterWeight(getActivity(), list);
        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(
                R.layout.layout_material_type, null);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(deleteDialogView);
        dialog.setCancelable(true);

        TextView tvTitle = (TextView) deleteDialogView.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.select_weight);

        GridView gvMaterialType = (GridView) deleteDialogView.findViewById(R.id.gvMaterialType);
        gvMaterialType.setAdapter(spinnerDisabilityArrayAdapter);
        gvMaterialType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                weightId = "" + weightTypesList.get(position).getId();
                String data = weightTypesList.get(position).getWeight();
                etWeight.setText(data);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void setSource(String stringExtra) {
        etSourceCity.setText(stringExtra);
    }

    public void setDestination(String stringExtra) {
        etDestinationCity.setText(stringExtra);
    }
}
