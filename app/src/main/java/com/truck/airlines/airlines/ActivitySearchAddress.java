package com.truck.airlines.airlines;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.truck.airlines.airlines.adapter.AdapterAddress;
import com.truck.airlines.airlines.interfaces.RecyclerItemClickListener;
import com.truck.airlines.airlines.utils.C;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivitySearchAddress extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {
    private static final String LOG_TAG = "MainActivity";
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private EditText mAutocompleteTextView;

    private GoogleApiClient mGoogleApiClient;
    private AdapterAddress mAdapterAddress;

    @BindView(R.id.lvAddress)
    RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private String addressFor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_address);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addressFor = getIntent().getStringExtra(C.ADDRESS_FOR);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();
        mAutocompleteTextView = (EditText) findViewById(R.id
                .autoCompleteTextView);
        mAutocompleteTextView.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.toString().equals("") && mGoogleApiClient.isConnected()) {
                    mAdapterAddress.getFilter().filter(s.toString());
                } else if (!mGoogleApiClient.isConnected()) {
                    Toast.makeText(getApplicationContext(), "Please connect to internet", Toast.LENGTH_SHORT).show();

                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });

//        mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(Place.TYPE_COUNTRY)
                .setCountry("IND")
                .build();
        mAdapterAddress = new AdapterAddress(this, R.layout.layout_item_address,
                mGoogleApiClient, null, autocompleteFilter);
//        mAutocompleteTextView.setAdapter(mAdapterAddress);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapterAddress);


        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        try {
                            final AdapterAddress.PlaceAutocomplete item = mAdapterAddress.getItem(position);
                            final String placeId = String.valueOf(item.placeId);
                            Log.d("TAG", "Autocomplete item selected: " + item.description);
                        /*
                             Issue a request to the Places Geo Data API to retrieve a Place object with additional details about the place.
                         */
                            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                                    .getPlaceById(mGoogleApiClient, placeId);
                            placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                                @Override
                                public void onResult(PlaceBuffer places) {
                                    if (places.getCount() == 1) {
                                        //Do the things here on Click.....
                                        Toast.makeText(getApplicationContext(), String.valueOf(places.get(0).getLatLng()), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "SOMETHING_WENT_WRONG", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            Log.d("TAG", "Clicked: " + item.description);
                            Log.d("TAG", "Called getPlaceById to get Place details for " + item.placeId);
                            Intent intent = new Intent();
                            intent.putExtra(addressFor, item.description);
                            setResult(C.RESULT_ADDRESS, intent);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
        );

    }

//    private AdapterView.OnItemClickListener mAutocompleteClickListener
//            = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            final AdapterAddress.PlaceAutocomplete item = mAdapterAddress.getItem(position);
//            final String placeId = String.valueOf(item.placeId);
//            Log.i(LOG_TAG, "Selected: " + item.description);
//            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
//                    .getPlaceById(mGoogleApiClient, placeId);
//            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
//            Log.i(LOG_TAG, "Fetching details for ID: " + item.placeId);
//        }
//    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e(LOG_TAG, "Place query did not complete. Error: " +
                        places.getStatus().toString());
                return;
            }
            // Selecting the first object buffer.
            final Place place = places.get(0);
            CharSequence attributions = places.getAttributions();

//            mAddressTextView.setText(Html.fromHtml(place.getAddress() + ""));

        }
    };


    @Override
    public void onConnected(Bundle bundle) {
        Log.v("Google API Callback", "Connection Done");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.v("Google API Callback", "Connection Suspended");
        Log.v("Code", String.valueOf(i));
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.v("Google API Callback", "Connection Failed");
        Log.v("Error Code", String.valueOf(connectionResult.getErrorCode()));
        Toast.makeText(this, "API_NOT_CONNECTED", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onClick(View v) {
////        if(v==delete){
////            mAutocompleteView.setText("");
////        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()) {
            Log.v("Google API", "Connecting");
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            Log.v("Google API", "Dis-Connecting");
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}