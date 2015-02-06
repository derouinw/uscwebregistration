package com.grilla.uscwebregistration;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewSchoolClassesFragment extends Fragment {
    public static final String ARG_SCHOOL_NAME = "com.grilla.uscwebregistration.ARG_SCHOOL_NAME";
    private String schoolName;

    public ViewSchoolClassesFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) savedInstanceState = getArguments();
        if (savedInstanceState != null) schoolName = savedInstanceState.getString(ARG_SCHOOL_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_school_classes, container, false);

        TextView schoolNameView = (TextView)rootView.findViewById(R.id.school_name);
        schoolNameView.setText(schoolName);

        return rootView;
    }
}
