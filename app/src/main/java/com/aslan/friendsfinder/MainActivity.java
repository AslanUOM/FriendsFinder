package com.aslan.friendsfinder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.aslan.friendsfinder.model.User;
import com.aslan.friendsfinder.services.RemoteMessagingService;
import com.aslan.friendsfinder.utility.Constants;
import com.aslan.friendsfinder.utility.Utility;

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

        Intent messagingService = new Intent(this, RemoteMessagingService.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_TYPE, Constants.Type.NEARBY_FRIENDS);
        messagingService.putExtras(bundle);
        Log.d("Main", messagingService.toString());
        Log.d("Main", bundle.toString());
        startService(messagingService);

        // Highlight the menu
        navigationView.getMenu().getItem(1).setChecked(true); //load friends view
//        navigationView.setCheckedItem(R.id.nav_friends);
        onNavigationItemSelected(navigationView.getMenu().getItem(1));
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
            String nearbyFriends = Utility.getNearbyFriends(getApplicationContext());
            if (nearbyFriends != null && nearbyFriends.length() > 2) {
                nearbyFriends = nearbyFriends.substring(1, nearbyFriends.length() - 1);
                String[] list = nearbyFriends.split(",");
                User[] friends = new User[list.length];
                for (int i = 0; i < friends.length; i++) {
                    friends[i] = new User();
                    friends[i].setId("A00" + i);
                    friends[i].setName(list[i].substring(1, list[i].length() - 1));
                    friends[i].setPhoneNumber("Number");
                    friends[i].setStatus("Free");
                }
                fragment = FriendsFragment.newInstance(friends);
            } else {
                User[] friends = new User[1];
                friends[0] = new User();
                friends[0].setId("A000");
                friends[0].setName("Only you");
                friends[0].setPhoneNumber("0777123456");
                friends[0].setStatus("Free");
                fragment = FriendsFragment.newInstance(friends);
            }
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
