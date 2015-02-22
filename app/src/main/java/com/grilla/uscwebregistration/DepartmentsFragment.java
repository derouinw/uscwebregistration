package com.grilla.uscwebregistration;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class DepartmentsFragment extends Fragment {
    public static final String ARG_SCHOOL_CODE = "com.grilla.uscwebregistration.ARG_SCHOOL_CODE";

    private String[] departments;
    private String school;

    public DepartmentsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) savedInstanceState = getArguments();
        if (savedInstanceState != null) school = savedInstanceState.getString(ARG_SCHOOL_CODE);

        Context c = getActivity().getApplicationContext();
        View rootView = inflater.inflate(R.layout.fragment_departments, container, false);

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

        RequestQueue queue = JSONSingleton.getInstance(c).getRequestQueue();
        String request = JSONHelper.SCHOOLS_URL + school;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, request,
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        Log.d("", ""); // optional log message

                        try {
                            JSONObject jo = new JSONObject(response);

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

    private void loadDepartmentsData(JSONObject jo) {

    }
}
