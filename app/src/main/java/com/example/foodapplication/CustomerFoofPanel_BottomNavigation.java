package com.example.foodapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodapplication.chefFoodPanel.ChefHomeFragment;
import com.example.foodapplication.chefFoodPanel.ChefOrderFragment;
import com.example.foodapplication.chefFoodPanel.ChefPendingOrderFragment;
import com.example.foodapplication.chefFoodPanel.ChefProfileFragment;
import com.example.foodapplication.customerFoodPanel.CustomerCartFragmnet;
import com.example.foodapplication.customerFoodPanel.CustomerHomeFragment;
import com.example.foodapplication.customerFoodPanel.CustomerOrdersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerFoofPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_foof_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.customerHome) {
            fragment = new CustomerHomeFragment();
        } else if (itemId == R.id.PendingOrders) {
            fragment = new CustomerOrdersFragment();
        } else if (itemId == R.id.Orders) {
            fragment = new CustomerCartFragmnet();
        } else if (itemId == R.id.customerProfile) {
            fragment = new ChefProfileFragment();
        }
        return loadcustomerfragment(fragment);
    }
    private boolean loadcustomerfragment(Fragment fragment) {

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;
    }



}