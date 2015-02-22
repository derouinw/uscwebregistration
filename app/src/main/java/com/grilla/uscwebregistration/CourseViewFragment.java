package com.grilla.uscwebregistration;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CourseViewFragment extends Fragment {
    private String courseId;

    public CourseViewFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course_view, container, false);
        Context c = getActivity().getApplicationContext();

        if (savedInstanceState == null) savedInstanceState = getArguments();
        if (savedInstanceState != null) courseId = savedInstanceState.getString(ViewCourseActivity.ARG_COURSE_ID);

        TextView text = (TextView)rootView.findViewById(R.id.text);
        text.setText(courseId);

        return rootView;
    }
}