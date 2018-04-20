package com.truck.airlines.airlines;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.truck.airlines.airlines.fragments.FragmentOTP;
import com.truck.airlines.airlines.fragments.FragmentSplash;
import com.truck.airlines.airlines.fragments.FragmentUserRegistration;
import com.truck.airlines.airlines.utils.C;

import java.util.List;

public class ActivityContainer extends AppCompatActivity {

    public static TextView tvTitle;
    private Fragment fragment;
    private Bundle bundle;
    private int fragmentAction;
    private Button btnAddDependent;
    private ImageView ivQuestionMark;

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

            case C.FRAGMENT_REGISTER:
                getSupportActionBar().hide();
                fragment = new FragmentUserRegistration();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(C.TAG_FRAGMENT_REGISTER);
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
        Fragment fragment=getVisibleFragment();

        btnAddDependent.setVisibility(View.GONE);
        ivQuestionMark.setVisibility(View.GONE);
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
}
