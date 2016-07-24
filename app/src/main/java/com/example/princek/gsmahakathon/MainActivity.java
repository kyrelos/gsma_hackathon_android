package com.example.princek.gsmahakathon;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.princek.gsmahakathon.MyUtils.ViewPagerAdapter;
import com.example.princek.gsmahakathon.customUi.SlidingTabLayout;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ViewPagerAdapter.DrawerAdapter drawerAdapter;
    private ArrayList<ViewPagerAdapter.DrawerItemObject> menuItems = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private SharedPreferences prefs;
    private ListView mDrawerList;
    private TextView drawerUserName;
    private TextView drawerUserPhone;

    ViewPager pager;
    private String titles[] = new String[]{"Balance", "Send Money", "Withdraw", "Deposit","History"};
    public Toolbar toolbar;

    SlidingTabLayout slidingTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("my_user_prefs", MODE_PRIVATE);
        drawerUserName = (TextView) findViewById(R.id.drawerNametextView);
        drawerUserPhone = (TextView) findViewById(R.id.drawerPhoneTextView);
        drawerUserName.setText(prefs.getString("full_name", "GsmaUser1"));
        drawerUserPhone.setText(prefs.getString("phoneNumber", "2557123456"));
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) mDrawerLayout.findViewById(R.id.navlist);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//            toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
//        }
        pager = (ViewPager) findViewById(R.id.viewpager);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titles));

        slidingTabLayout.setViewPager(pager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(drawerToggle);

        menuItems.add(new ViewPagerAdapter.DrawerItemObject("Home", R.drawable.ic_menu_white_24dp));
        menuItems.add(new ViewPagerAdapter.DrawerItemObject("Send Money", R.drawable.ic_menu_white_24dp));
        menuItems.add(new ViewPagerAdapter.DrawerItemObject("Deposit", R.drawable.ic_menu_white_24dp));
        menuItems.add(new ViewPagerAdapter.DrawerItemObject("Withdraw", R.drawable.ic_menu_white_24dp));
        menuItems.add(new ViewPagerAdapter.DrawerItemObject("History", R.drawable.ic_menu_white_24dp));

        drawerAdapter = new ViewPagerAdapter.DrawerAdapter(this);
        drawerAdapter.loadItems(menuItems);
        mDrawerList.setAdapter(drawerAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                System.out.println(position);
                switch (position) {
                    case 0:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:

                        mDrawerLayout.closeDrawer(GravityCompat.START);

                        break;
                    case 2:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:

                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            System.out.println(position);

        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}



