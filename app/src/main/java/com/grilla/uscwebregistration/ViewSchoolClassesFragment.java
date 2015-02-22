package com.grilla.uscwebregistration;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.grilla.uscwebregistration.organization.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

public class ViewSchoolClassesFragment extends Fragment {
    public static final String ARG_SCHOOL_NAME = "com.grilla.uscwebregistration.ARG_SCHOOL_NAME";
    private String schoolName;

    private ArrayAdapter<String> courseAdapter;
    Course[] courses;

    public ViewSchoolClassesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) savedInstanceState = getArguments();
        if (savedInstanceState != null) schoolName = savedInstanceState.getString(ARG_SCHOOL_NAME);

        View rootView = inflater.inflate(R.layout.fragment_view_school_classes, container, false);
        Context c = getActivity().getApplicationContext();

        courseAdapter = new ArrayAdapter<>(c, R.layout.schools_list_item);

        ListView listView = (ListView)rootView.findViewById(R.id.courses_list);
        listView.setAdapter(courseAdapter);

        String request = JSONHelper.COURSES_URL + schoolName;
        Log.d("ViewSchoolClasses", request);
        RequestQueue queue = Volley.newRequestQueue(c);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, request,
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        Log.d("ViewSchoolClasses", "Loaded course data");

                        try {
                            JSONArray jo = new JSONArray(response);
                            loadCourses(jo);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        return rootView;
    }

    private void loadCourses(JSONArray response) throws JSONException {
        courses = new Course[response.length()];
        courseAdapter.clear();

        for (int i = 0; i < response.length(); i++) {
            JSONObject course = response.getJSONObject(i);

            double courseId = course.getDouble("COURSE_ID");
            String sisCourseId = course.getString("SIS_COURSE_ID");
            String title = course.getString("TITLE");

            courses[i] = new Course(courseId, getActivity());
            courseAdapter.add(sisCourseId + ": " + title);
        }

        courseAdapter.notifyDataSetChanged();
    }
}
