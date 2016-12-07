package com.gmyboy.review;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.gmyboy.review.fragment.FlowFragment;
import com.gmyboy.review.fragment.activities.ActivitiesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.container)
    FrameLayout container;
    private Fragment fragment;
    private FragmentTransaction transaction;

    @OnClick(R.id.fab)
    void showSnack(View v) {
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //定义drawLayout开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        //菜单点击监听
        navView.setNavigationItemSelectedListener(this);
        //设置默认显示第一个frag
        navView.setCheckedItem(R.id.nav_camera);
        transaction = getSupportFragmentManager().beginTransaction();
        fragment = ActivitiesFragment.newInstance();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_camera) {
            fragment = ActivitiesFragment.newInstance();
            transaction.replace(R.id.container, fragment);
        } else if (id == R.id.nav_gallery) {
            fragment = ActivitiesFragment.newInstance();
            transaction.replace(R.id.container, fragment);
        } else if (id == R.id.nav_slideshow) {
            fragment = ActivitiesFragment.newInstance();
            transaction.replace(R.id.container, fragment);
        } else if (id == R.id.nav_manage) {
            fragment = ActivitiesFragment.newInstance();
            transaction.replace(R.id.container, fragment);
        } else if (id == R.id.nav_share) {
            fragment = ActivitiesFragment.newInstance();
            transaction.replace(R.id.container, fragment);
        } else if (id == R.id.nav_send) {//流式布局
            fragment = FlowFragment.newInstance();
            transaction.replace(R.id.container, fragment);
        }

        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
