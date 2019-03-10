package com.course.navigationdrawerlearn;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.transition.Transition;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class CustomNavigationDrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_custom_navigation_drawer);

        mDrawerLayout = findViewById(R.id.drawer_custom_layout);
        mNavigationView = findViewById(R.id.navigation_custom_view);

        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeAsUpIndicator(R.drawable.ic_menu);
        setHandlers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack("Add Trip Fragment Transaction");
        transaction.commit();transaction.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);


    }
    private void setHandlers(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               int id =menuItem.getItemId();
               if(id == R.id.item_view_trips)
               {
                   addFragment(new AddTripFragment());
                   Toast.makeText(CustomNavigationDrawerActivity.this,"View Trips",Toast.LENGTH_SHORT).show();
               }
               if(id ==R.id.item_view_favorites)
               {
                   getSupportFragmentManager().popBackStack();
                   Toast.makeText(CustomNavigationDrawerActivity.this,"View Favorites",Toast.LENGTH_SHORT);

               }



               mNavigationView.setCheckedItem(id);
               mDrawerLayout.closeDrawer(Gravity.START);

                //TODO replace fragments here

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(Gravity.START))
            mDrawerLayout.closeDrawer(Gravity.START);
        super.onBackPressed();
    }
}
