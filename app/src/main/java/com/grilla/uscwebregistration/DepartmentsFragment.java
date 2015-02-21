package com.grilla.uscwebregistration;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DepartmentsFragment extends Fragment {
    public final static String ARG_DEPARTMENTS = "com.grilla.uscwebregistration.ARG_DEPARTMENTS";

    private String[] departments;

    public DepartmentsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) savedInstanceState = getArguments();
        if (savedInstanceState != null) departments = savedInstanceState.getStringArray(ARG_DEPARTMENTS);

        Context c = getActivity().getApplicationContext();
        View rootView = inflater.inflate(R.layout.fragment_departments, container, false);

        if (savedInstanceState == null) savedInstanceState = getArguments();
        if (savedInstanceState != null) departments = savedInstanceState.getStringArray(ARG_DEPARTMENTS);

        ListView schoolsList = (ListView)rootView.findViewById(R.id.departments_list);
        ArrayAdapter<String> arrayAdpt = new ArrayAdapter<>(c, R.layout.schools_list_item, departments);
        schoolsList.setAdapter(arrayAdpt);
        schoolsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ViewSchoolClassesFragment viewSchoolClassesFragment = new ViewSchoolClassesFragment();

                Bundle args = new Bundle();
                args.putString(ViewSchoolClassesFragment.ARG_SCHOOL_NAME, departments[position]);
                viewSchoolClassesFragment.setArguments(args);

                ft.replace(R.id.departments_frame, viewSchoolClassesFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return rootView;
    }
}
