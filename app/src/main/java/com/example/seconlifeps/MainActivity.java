package com.example.seconlifeps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.seconlifeps.Fragments.MainFragment;
import com.example.seconlifeps.Fragments.PaymentFragment;
import com.example.seconlifeps.Fragments.ProfileFragment;
import com.example.seconlifeps.Fragments.ReviewsFragment;
import com.example.seconlifeps.Fragments.ShelterDetailFragment;
import com.example.seconlifeps.Fragments.SheltersFragment;
import com.example.seconlifeps.interfaces.iShelters;
import com.example.seconlifeps.model.Business;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iShelters {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    // Fragment Manager
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //Reference to destination Fragment (detailed Shelter)
    ReviewsFragment reviewsFragment;
    ProfileFragment profileFragment;
    //Session variables

    public String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        userId = getIntent().getStringExtra("USER_ID");
        Log.d("User in Main:",userId.toString());


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout =  findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        //set onclick event to NavigationView
        navigationView.setNavigationItemSelectedListener(this);


        actionBarDrawerToggle = new ActionBarDrawerToggle( this, drawerLayout, toolbar, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //load main fragmennt

       fragmentManager = getSupportFragmentManager();
       fragmentTransaction = fragmentManager.beginTransaction();
       MainFragment mainFragment = new MainFragment();
       // arguments
       Bundle arguments = new Bundle();
       arguments.putString("userId", userId);
       mainFragment.setArguments(arguments);
       fragmentTransaction.add(R.id.container, mainFragment);
       fragmentTransaction.commit();



       //    Intent i = new Intent(MainActivity.this, MainActivity.class);
      //  startActivity(i);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);

        if (item.getItemId() == R.id.home) {

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            MainFragment mainFragment = new MainFragment();
            Bundle arguments = new Bundle();
            arguments.putString("userId", userId);
            mainFragment.setArguments(arguments);
            fragmentTransaction.replace(R.id.container, mainFragment);
            fragmentTransaction.commit();
        }
        if (item.getItemId() == R.id.profile) {

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            profileFragment = new ProfileFragment();
            // argument
            Bundle arguments = new Bundle();
            arguments.putString("userId", userId);
            profileFragment.setArguments(arguments);
            fragmentTransaction.replace(R.id.container, profileFragment);
            fragmentTransaction.commit();
        }

        if (item.getItemId() == R.id.payment) {

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            PaymentFragment paymentFragment = new PaymentFragment();
            // argument
            Bundle arguments = new Bundle();
            arguments.putString("userId", userId);
            paymentFragment.setArguments(arguments);
            fragmentTransaction.replace(R.id.container, paymentFragment);
            fragmentTransaction.commit();
        }

        if (item.getItemId() == R.id.shelters) {

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            SheltersFragment sheltersFragment = new SheltersFragment();
            // argument
            Bundle arguments = new Bundle();
            arguments.putString("userId", userId);
            sheltersFragment.setArguments(arguments);
            fragmentTransaction.replace(R.id.container, sheltersFragment);
            fragmentTransaction.commit();
        }

        if (item.getItemId() == R.id.users) {

         //   fragmentManager = getSupportFragmentManager();
         //   fragmentTransaction = fragmentManager.beginTransaction();
         //   fragmentTransaction.replace(R.id.container, new ShelterDetailFragment());
         //   fragmentTransaction.commit();


            Intent i = new Intent(MainActivity.this, ListNotesActivity.class);
            //Intent i = new Intent(LoginActivity.this, PaymentActivity.class);
            startActivity(i);
        }

        return false;
    }

    @Override
    public void sendShelter(Business business) {
        //logic here to send the object
        reviewsFragment = new ReviewsFragment();
        // bundle object to transport data
        Bundle bundle = new Bundle();
        bundle.putSerializable("objecto",business);
        reviewsFragment.setArguments(bundle);

        //open fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, reviewsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(profileFragment!=null)
            profileFragment.onActivityResult(requestCode,resultCode,data);
    }
}