package com.grilla.uscwebregistration;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;


public class ViewCourseActivity extends ActionBarActivity {
    public static final String ARG_COURSE_ID = "com.grilla.uscwebregistration.ARG_COURSE_ID";

    DrawerLayout drawerLayout;
    CalendarView calendarDrawer;
    ActionBarDrawerToggle drawerToggle;

    String mTitle, mDrawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        // Set up navigation drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        calendarDrawer = (CalendarView)findViewById(R.id.calendar_drawer);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mTitle = mDrawerTitle = (String)getTitle();
        setTitle("Course View");

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.text));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar,
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                //getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                //getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);*/

        Intent incoming = getIntent();
        String courseID = String.format("%d",(long)incoming.getDoubleExtra(ARG_COURSE_ID, -1));

        CourseViewFragment courseViewFragment = new CourseViewFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Bundle args = new Bundle();
        args.putString(ViewCourseActivity.ARG_COURSE_ID, courseID);
        courseViewFragment.setArguments(args);

        ft.add(R.id.course_container, courseViewFragment);
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_course, menu);
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
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
