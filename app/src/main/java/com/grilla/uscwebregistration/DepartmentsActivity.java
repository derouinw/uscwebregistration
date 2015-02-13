package com.grilla.uscwebregistration;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class DepartmentsActivity extends ActionBarActivity {
    public static final String ARG_SCHOOL = "com.grilla.uscwebregistration.ARG_SCHOOL";
    String[] departments;
    String school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.text));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent incoming = getIntent();
        departments = incoming.getStringArrayExtra(DepartmentsFragment.ARG_DEPARTMENTS);
        school = incoming.getStringExtra(ARG_SCHOOL);

        DepartmentsFragment departmentsFragment = new DepartmentsFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Bundle args = new Bundle();
        args.putStringArray(DepartmentsFragment.ARG_DEPARTMENTS, departments);
        departmentsFragment.setArguments(args);

        ft.add(R.id.departments_frame, departmentsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schools, menu);
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
}
