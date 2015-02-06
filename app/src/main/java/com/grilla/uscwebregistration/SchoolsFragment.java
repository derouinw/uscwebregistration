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

public class SchoolsFragment extends Fragment {
    public final static String ARG_SCHOOLS = "com.grilla.uscwebregistration.SCHOOLS";

    private String[] schools;

    public SchoolsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (savedInstanceState == null) savedInstanceState = getArguments();
        //if (savedInstanceState != null) schools = savedInstanceState.getStringArray(ARG_SCHOOLS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) savedInstanceState = getArguments();
        if (savedInstanceState != null) schools = savedInstanceState.getStringArray(ARG_SCHOOLS);

        Context c = getActivity().getApplicationContext();
        View rootView = inflater.inflate(R.layout.fragment_schools, container, false);

        if (savedInstanceState == null) savedInstanceState = getArguments();
        if (savedInstanceState != null) schools = savedInstanceState.getStringArray(ARG_SCHOOLS);

        ListView schoolsList = (ListView)rootView.findViewById(R.id.schools_list);
        ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(c, R.layout.schools_list_item, schools);
        schoolsList.setAdapter(arrayAdpt);
        schoolsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ViewSchoolClassesFragment viewSchoolClassesFragment = new ViewSchoolClassesFragment();

                Bundle args = new Bundle();
                args.putString(ViewSchoolClassesFragment.ARG_SCHOOL_NAME, schools[position]);
                viewSchoolClassesFragment.setArguments(args);

                ft.replace(R.id.schools_frame, viewSchoolClassesFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return rootView;
    }
}
