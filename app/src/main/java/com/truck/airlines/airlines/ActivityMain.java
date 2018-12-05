package com.truck.airlines.airlines;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.truck.airlines.airlines.adapter.AdapterHomeMenu;
import com.truck.airlines.airlines.adapter.AdapterSideMenu;
import com.truck.airlines.airlines.pojos.SideMenuItem;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.SharedPreference;
import com.truck.airlines.airlines.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lvMenuItem)
    ListView listView;
    @BindView(R.id.gvHome)
    GridView gvHome;

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvMobile)
    TextView tvMobile;

    private AdapterSideMenu adapterSideMenu;
    private AdapterHomeMenu adapterHomeMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.menu);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        adapterSideMenu = new AdapterSideMenu(this, Util.getSideMenuList(ActivityMain.this));
        listView.setAdapter(adapterSideMenu);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                SideMenuItem sideMenuItem = adapterSideMenu.getItem(position);

                if (sideMenuItem.getNameResourse() == R.string.post_a_load) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_POST_LOAD);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.post_truck) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_POST_TRUCK);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.profile) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_PROFILE);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.search_truck) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_TOTAL_TRUCK);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.my_loads) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_LOADS_SUMMARY);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.kyc_document) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_KYC_DOCUMENT);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.kyc_document) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_KYC_DOCUMENT);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.about_us) {
                    Intent intent = new Intent(ActivityMain.this, ActivitySearchAddress.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_ABOUT_US);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.logout) {
                    SharedPreference.getInstance(ActivityMain.this).clearData();
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_OTP);
                    startActivity(intent);
                }else if (sideMenuItem.getNameResourse() == R.string.my_trucks) {

                } else if (sideMenuItem.getNameResourse() == R.string.add_truck) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_ADD_TRUCK);
                    startActivity(intent);
                }
            }
        });


        tvName.setText(SharedPreference.getInstance(this).getLoginUser(C.USER).getName());
        tvMobile.setText(SharedPreference.getInstance(this).getLoginUser(C.USER).getPan());


        adapterHomeMenu = new AdapterHomeMenu(this, Util.getmainMenuList(ActivityMain.this));
        gvHome.setAdapter(adapterHomeMenu);

        gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                SideMenuItem sideMenuItem = adapterSideMenu.getItem(position);

                if (sideMenuItem.getNameResourse() == R.string.post_a_load) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_POST_LOAD);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.post_truck) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_POST_TRUCK);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.profile) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_PROFILE);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.search_truck) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_TOTAL_TRUCK);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.my_loads) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_TOTAL_LOADS);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.search_load) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_SEARCH_LOAD);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.kyc_document) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_KYC_DOCUMENT);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.about_us) {
                    Intent intent = new Intent(ActivityMain.this, ActivitySearchAddress.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_ABOUT_US);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.logout) {
                    SharedPreference.getInstance(ActivityMain.this).clearData();
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_OTP);
                    startActivity(intent);
                } else if (sideMenuItem.getNameResourse() == R.string.my_trucks) {

                } else if (sideMenuItem.getNameResourse() == R.string.add_truck) {
                    Intent intent = new Intent(ActivityMain.this, ActivityContainer.class);
                    intent.putExtra(C.FRAGMENT_ACTION, C.FRAGMENT_ADD_TRUCK);
                    startActivity(intent);
                }
            }
        });


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


}
