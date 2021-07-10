package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout mCategoryLayout;
    TabItem mGeneral, mHealth, mBusiness, mEntertainment, mScience, mSports, mTechnology;
    ViewPager mViewPager;
    NewsPagesAdapter mNewsPageAdapter;
    TextView mNetConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCategoryLayout = findViewById(R.id.tab_layout);
        mGeneral = findViewById(R.id.general);
        mHealth = findViewById(R.id.health);
        mBusiness = findViewById(R.id.business);
        mEntertainment = findViewById(R.id.entertainment);
        mScience = findViewById(R.id.science);
        mSports = findViewById(R.id.sports);
        mTechnology = findViewById(R.id.technology);
        mViewPager = findViewById(R.id.viewPager);
        mNetConnect = findViewById(R.id.netconnect);

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        work : if(networkInfo != null && networkInfo.isConnected()) {
            mNewsPageAdapter = new NewsPagesAdapter(getSupportFragmentManager(), mCategoryLayout.getTabCount());
            mViewPager.setAdapter(mNewsPageAdapter);

            mCategoryLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    mViewPager.setCurrentItem(tab.getPosition());
                    if(tab.getPosition()==0 || tab.getPosition()==1 || tab.getPosition()==2 || tab.getPosition()==3 || tab.getPosition()==4 || tab.getPosition()==5
                            || tab.getPosition()==6)
                        mNewsPageAdapter.notifyDataSetChanged();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mCategoryLayout));
        } else {
            mNetConnect.setVisibility(View.VISIBLE);
            networkInfo = manager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isConnected()) {
                break work;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.setting:
                // Do nothing for now
                Settings(item);
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.app_bar_search:
                // Do nothing for now
                Log.d(MainActivity.class.toString(), "Setting Clicked");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Settings(MenuItem item) {
        Log.d(MainActivity.class.toString(), "Setting Clicked");
        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
    }
}