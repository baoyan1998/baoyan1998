package com.github.baoyan1998.zhihu.ui.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.renderscript.ScriptGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.baoyan1998.zhihu.R;
import com.github.baoyan1998.zhihu.ui.adapter.ZhihuMainAdapter;
import com.github.baoyan1998.zhihu.ui.fragment.DailyFragment;
import com.github.baoyan1998.zhihu.ui.fragment.HotFragment;
import com.github.baoyan1998.zhihu.ui.fragment.ThemeFragment;
import com.github.baoyan1998.zhihu.ui.fragment.ZhuanlanFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {


    private TabLayout mTabLayout;


    private ViewPager mViewPager;

    private ZhihuMainAdapter zhihuMainAdapter;

    private List<Fragment> fragments = new ArrayList<>();

    String[] tabTitle = new String[]{"日报","主题","专栏","热门"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTabLayout = findViewById(R.id.tab_zhihu_main);
        mViewPager = findViewById(R.id.vp_zhihu_main);


        fragments.add(new DailyFragment());
        fragments.add(new HotFragment());
        fragments.add(new ThemeFragment());
        fragments.add(new ZhuanlanFragment());

        zhihuMainAdapter = new ZhihuMainAdapter(getSupportFragmentManager(),fragments);

        for(int i=0;i<tabTitle.length;i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        }


        mViewPager.setAdapter(zhihuMainAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


       for (int i = 0;i<tabTitle.length;i++){
           mTabLayout.getTabAt(i).setText(tabTitle[i]);
       }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            showExitDialog();
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
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_notification){
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),PersonalActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent intent =  new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS);
            startActivity(intent);
        }
          else if(id == R.id.nav_haoping){
            Intent intent =  new Intent(Settings.ACTION_PRIVACY_SETTINGS);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("提示");

        builder.setMessage("确定退出知乎日报吗？");
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

            }
        });

        AlertDialog dialog = builder.show();
    }




}
