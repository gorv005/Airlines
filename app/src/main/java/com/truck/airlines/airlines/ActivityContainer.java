package com.truck.airlines.airlines;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.truck.airlines.airlines.fragments.FragmentAddressSearch;
import com.truck.airlines.airlines.fragments.FragmentKYCDocuments;
import com.truck.airlines.airlines.fragments.FragmentLanguageSelect;
import com.truck.airlines.airlines.fragments.FragmentLoadList;
import com.truck.airlines.airlines.fragments.FragmentLoadsSummary;
import com.truck.airlines.airlines.fragments.FragmentOTP;
import com.truck.airlines.airlines.fragments.FragmentOfflineOTP;
import com.truck.airlines.airlines.fragments.FragmentPostLoad;
import com.truck.airlines.airlines.fragments.FragmentPostTruck;
import com.truck.airlines.airlines.fragments.FragmentProfile;
import com.truck.airlines.airlines.fragments.FragmentSplash;
import com.truck.airlines.airlines.fragments.FragmentTruckList;
import com.truck.airlines.airlines.fragments.FragmentUserRegistration;
import com.truck.airlines.airlines.fragments.FragmentUserType;
import com.truck.airlines.airlines.utils.C;

import java.util.List;

public class ActivityContainer extends AppCompatActivity {

    public static TextView tvTitle;
    private Fragment fragment;
    private Bundle bundle;
    private int fragmentAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
//        final Drawable upArrow = getResources().getDrawable(R.drawable.back);
//        upArrow.setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        tvTitle = (TextView) findViewById(R.id.tvTitle);

        bundle = getIntent().getBundleExtra(C.BUNDLE);
        fragmentAction = getIntent().getIntExtra(C.FRAGMENT_ACTION, C.FRAGMENT_SPLASH);
        fragmnetLoader(fragmentAction, bundle);

    }

    public void fragmnetLoader(int fragmentType, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (fragmentType) {
            case C.FRAGMENT_SPLASH:
                getSupportActionBar().hide();
                fragment = new FragmentSplash();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_SPLASH);
                break;
            case C.FRAGMENT_OTP:
                getSupportActionBar().hide();
                fragment = new FragmentOTP();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_OTP);
                break;

            case C.FRAGMENT_USER_TYPE:
                fragment = new FragmentUserType();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_USER_TYPE);
                break;
            case C.FRAGMENT_REGISTER:
                fragment = new FragmentUserRegistration();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_REGISTER);
                break;
            case C.FRAGMENT_POST_TRUCK:
                fragment = new FragmentPostTruck();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_POST_TRUCK);
                break;
            case C.FRAGMENT_POST_LOAD:
                fragment = new FragmentPostLoad();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_POST_LOAD);
                break;
            case C.FRAGMENT_PROFILE:
                fragment = new FragmentProfile();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_PROFILE);
                break;
            case C.FRAGMENT_TOTAL_TRUCK:
                fragment = new FragmentTruckList();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_TOTAL_TRUCK);
                break;
            case C.FRAGMENT_LOADS_SUMMARY:
                fragment = new FragmentLoadsSummary();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_TOTAL_LOAD);
                break;
            case C.FRAGMENT_TOTAL_LOADS:
                fragment = new FragmentLoadList();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_TOTAL_LOAD);
                break;
            case C.FRAGMENT_KYC_DOCUMENT:
                fragment = new FragmentKYCDocuments();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_KYC_DOCUMENT);
                break;
            case C.FRAGMENT_ABOUT_US:
                fragment = new FragmentAddressSearch();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_ABOUT_US);
                break;
            case C.FRAGMENT_OFFFLINE_REGISTER:
                getSupportActionBar().hide();
                fragment = new FragmentOfflineOTP();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_OFFLINE_REGISTRATION);
                break;
            case C.FRAGMENT_LANGUAGE_SELECT:
                getSupportActionBar().hide();
                fragment = new FragmentLanguageSelect();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_LANGUAGE_SELECT);
                break;

        }
        fragment.setArguments(bundle);
        fragmentTransaction.commit();
        getSupportFragmentManager().executePendingTransactions();


    }


    private Fragment getVisibleFragment() {
        FragmentManager fragmentManager = ActivityContainer.this.getSupportFragmentManager();
        @SuppressLint("RestrictedApi") List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getVisibleFragment();

        getSupportFragmentManager().executePendingTransactions();
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {

//            Log.e("CountPop", getSupportFragmentManager().getBackStackEntryCount() + "");

            int fragmentCount = getSupportFragmentManager().getBackStackEntryCount();
            FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(fragmentCount - 2);
            String fragmentTag = backEntry.getName();

            getSupportFragmentManager().popBackStack();

            getSupportFragmentManager().executePendingTransactions();
            if (fragmentTag != null) {
                switch (fragmentTag) {

//                    case C.TAG_FRAGMENT_PATIENT_PROFILE:
//                        tvTitle.setText(R.string.patient_profile);
//                        break;
//                    case C.TAG_FRAGMENT_ADD_INQUEU:
//                        tvTitle.setText(R.string.select_patient);
//                        break;

                }
            }


        } else {
            finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fragment fragment = getVisibleFragment();

        if (fragment instanceof FragmentPostLoad) {

            if (resultCode == C.RESULT_ADDRESS) {
                if (data.getStringExtra(C.ADDRESS_SOURCE) != null) {

                    ((FragmentPostLoad) fragment).setSource(data.getStringExtra(C.ADDRESS_SOURCE));


                } else if (data.getStringExtra(C.ADDRESS_DESTINATION) != null) {
                    ((FragmentPostLoad) fragment).setDestination(data.getStringExtra(C.ADDRESS_DESTINATION));

                }
            }

        }
    }


}
