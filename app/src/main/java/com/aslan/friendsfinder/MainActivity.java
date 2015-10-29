package com.aslan.friendsfinder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aslan.friendsfinder.model.User;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set the home fragment
        Fragment fragment = HomeFragment.newInstance(null, null);
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.content, fragment).commit();
        // Highlight the menu
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment;

        if (id == R.id.nav_friends) {
            // Show the list of nearby friends
            User[] friends = new User[3];
            friends[0] = new User();
            friends[0].setId("A001");
            friends[0].setName("Vishnu");
            friends[0].setPhoneNumber("0770780210");
            friends[0].setStatus("Free");

            friends[1] = new User();
            friends[1].setId("A002");
            friends[1].setName("Annet");
            friends[1].setPhoneNumber("0770780210");
            friends[1].setStatus("Busy");

            friends[2] = new User();
            friends[2].setId("A003");
            friends[2].setName("Raveen");
            friends[2].setPhoneNumber("0770780220");
            friends[2].setStatus("Free");

            fragment = FriendsFragment.newInstance(friends);
        } else if (id == R.id.nav_profile) {
            // Show the profile
            fragment = HomeFragment.newInstance(null, null);
        } else if (id == R.id.nav_settings) {
            // Show the settings
            fragment = HomeFragment.newInstance(null, null);
        } else {
            // Show the home
            fragment = HomeFragment.newInstance(null, null);
        }

        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.content, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
