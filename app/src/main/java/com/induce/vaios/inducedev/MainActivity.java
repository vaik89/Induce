package com.induce.vaios.inducedev;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.appevents.AppEventsLogger;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;

    private ResideMenuItem itemSettings;

    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMenu();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


// Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);


        Button buttoncheckin= (Button) findViewById(R.id.checkinbutton);
        buttoncheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Checkin is pressed!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, MapDraw.class);

                MainActivity.this.startActivity(myIntent);
            }
        });

        Button buttonlist= (Button) findViewById(R.id.mylistbutton);
        buttonlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button is pressed!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, listandsearchactivity.class);

                MainActivity.this.startActivity(myIntent);
            }
        });

        Button buttoncard= (Button) findViewById(R.id.mycardbutton);
        buttoncard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button card pressed!", Toast.LENGTH_SHORT).show();
            }
        });



        //test FAB button
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent myIntent = new Intent(MainActivity.this, MapDraw.class);

                MainActivity.this.startActivity(myIntent);


            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }


    private void setUpMenu() {
        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(false);
        resideMenu.setBackground(R.drawable.background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.7f);

        // create menu items;
        itemHome = new ResideMenuItem(this, R.drawable.ic_action_user, "Home");
        itemProfile = new ResideMenuItem(this, R.drawable.ic_collections, "Profile");
        itemSettings = new ResideMenuItem(this, R.drawable.ic_settings, "Settings");

        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);

        itemSettings.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);

        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Menu is opened!", Toast.LENGTH_SHORT).show();
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });


        ViewPager ignored_view = (ViewPager) findViewById(R.id.pager);
        resideMenu.addIgnoredView(ignored_view);

    }

    @Override
    public void onClick(View view) {

        if (view == itemHome) {

        } else if (view == itemProfile) {


        } else if (view == itemSettings) {

        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            // Toast.makeText(MainActivity.this, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            //Toast.makeText(MainActivity.this, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);

    }


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {

            return new firstpageimagefragment();


        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
