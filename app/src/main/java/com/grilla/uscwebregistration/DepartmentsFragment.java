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
import com.grilla.uscwebregistration.organization.Department;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DepartmentsFragment extends Fragment {
    public static final String ARG_SCHOOL_CODE = "com.grilla.uscwebregistration.ARG_SCHOOL_CODE";

    private Department[] departments = {};
    private String[] departmentsNames = {};
    private String school;

    ArrayAdapter<String> arrayAdpt;

    public DepartmentsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) savedInstanceState = getArguments();
        if (savedInstanceState != null) school = savedInstanceState.getString(ARG_SCHOOL_CODE);

        Context c = getActivity().getApplicationContext();
        View rootView = inflater.inflate(R.layout.fragment_departments, container, false);

        ListView schoolsList = (ListView)rootView.findViewById(R.id.departments_list);
        arrayAdpt = new ArrayAdapter<>(c, R.layout.schools_list_item);
        schoolsList.setAdapter(arrayAdpt);
        schoolsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ViewSchoolClassesFragment viewSchoolClassesFragment = new ViewSchoolClassesFragment();

                Bundle args = new Bundle();
                args.putString(ViewSchoolClassesFragment.ARG_SCHOOL_NAME, departments[position].getSocDepartmentCode());
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
                        Log.d("DepartmentsFragment", "Departments data loaded"); // optional log message

                        try {
                            JSONArray jo = new JSONArray(response);
                            JSONArray ja = jo.getJSONObject(0).getJSONArray("SOC_DEPARTMENT_CODE");
                            loadDepartmentsData(ja);

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

    private void loadDepartmentsData(JSONArray jo) throws JSONException {
        departments = new Department[jo.length()];
        departmentsNames = new String[jo.length()];

        arrayAdpt.clear();

        for (int i = 0; i < jo.length(); i++) {
            JSONObject dept = jo.getJSONObject(i);

            String departmentCode = dept.getString("SOC_DEPARTMENT_CODE");
            String departmentDescription = dept.getString("SOC_DEPARTMENT_DESCRIPTION");

            Department department = new Department(departmentCode, departmentDescription, school);
            departments[i] = department;
            departmentsNames[i] = departmentDescription;
            arrayAdpt.add(departmentDescription);
        }

        arrayAdpt.notifyDataSetChanged();
    }
}
