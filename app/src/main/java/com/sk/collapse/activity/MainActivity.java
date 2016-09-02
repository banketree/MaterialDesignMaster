package com.sk.collapse.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar mToolBar = null;

    private DrawerLayout mDrawlayout;
    private ActionBarDrawerToggle mDrawToggle;
    private NavigationView mNavigationView;

    private int mCurrentTabIndex = 0;
    private Fragment []mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = (Toolbar) findViewById(R.id.id_toolbar);
        mDrawlayout = (DrawerLayout) findViewById(R.id.id_drawlayout);
        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);




        //mToolBar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(mToolBar);
        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayUseLogoEnabled(true);
            ab.setDisplayShowTitleEnabled(true);
        }

        mDrawToggle = new ActionBarDrawerToggle(this, mDrawlayout, mToolBar, R.string.app_name, R.string.app_name);
        mDrawlayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawToggle.syncState();
            }
        });

        mDrawlayout.addDrawerListener(mDrawToggle);


        mNavigationView.getChildAt(0).setVerticalScrollBarEnabled(false);
        mNavigationView.setNavigationItemSelectedListener(this);

        //init fragments
        HomeFragment homeFragment = HomeFragment.newInstance();
        DownloadFragment downloadFragment = DownloadFragment.newInstance();
        mFragments = new Fragment[] {

                homeFragment,
                downloadFragment,

        };
        mCurrentTabIndex = 0;
        getSupportFragmentManager().beginTransaction().add(R.id.id_container, homeFragment).show(homeFragment).commit();
    }

    private void setNavigationSelected() {
        Menu menu = mNavigationView.getMenu();
        int len = menu.size();
        for(int i=0;i<len;i++) {

        }
    }

    private void switchFragment(int index) {
        if(index < 0 || index >= mFragments.length)
            return;

        Fragment willfragment = mFragments[index % mFragments.length];
        Fragment currfragment = mFragments[mCurrentTabIndex % mFragments.length];
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(!willfragment.isAdded()) {
            ft.add(R.id.id_container, willfragment);
        }

        ft.hide(currfragment).show(willfragment).commit();
        mCurrentTabIndex = index;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int index = 0;
        switch (item.getItemId()) {
            case R.id.nav_home:
                index = 0;
                break;

            case R.id.nav_download:
                index = 1;
                break;

            case R.id.nav_favourite:
                index = 2;
                break;

            case R.id.nav_history:
                index = 3;
                break;

            case R.id.nav_group:
                index = 4;
                break;

            case R.id.nav_tracker:
                index = 5;
                break;

            case R.id.nav_theme:
                index = 6;
                break;

            case R.id.nav_app:
                index = 7;
                break;

            case R.id.nav_setting:
                index = 8;
                break;
        }
        item.setChecked(true);
        switchFragment(index);
        mDrawlayout.closeDrawers();

        return true;
    }


}
