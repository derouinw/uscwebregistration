package com.grilla.uscwebregistration.organization;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.grilla.uscwebregistration.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Bill Derouin <bill@billderouin.com>
 */
public class School {
    // API data
    private String socSchoolCode;
    private String socSchoolDescription;
    private Department[] socDepartments;

    // other data
    private Activity activity;

    /**
     * Default constructor, created when a total list of schools
     * is loaded from the API. From there, departments can be
     * loaded by using the school code.
     * @param socSchoolCode
     * @param socSchoolDescription
     */
    public School(String socSchoolCode, String socSchoolDescription, Activity activity) {
        this.socSchoolCode = socSchoolCode;
        this.socSchoolDescription = socSchoolDescription;
        this.activity = activity;
    }

    /**
     * Loads all data for the schools departments
     * @see Department
     */
    public void loadDepartments() {
        String request = JSONHelper.SCHOOLS_URL + socSchoolCode;
        RequestQueue queue = Volley.newRequestQueue(activity);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, request,
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        System.out.println("Response is: " + response);

                        try {
                            JSONArray ja = new JSONArray(response);
                            JSONArray depts = ja.getJSONObject(0).getJSONArray("SOC_DEPARTMENT_CODE");

                            socDepartments = new Department[depts.length()];
                            for (int i = 0; i < depts.length(); i++) {
                                JSONObject d = depts.getJSONObject(i);

                                String socDepartmentCode = d.getString("SOC_DEPARTMENT_CODE");
                                String socDepartmentDescription = d.getString("SOC_DEPARTMENT_DESCRIPTION");
                                String socSchoolCode = d.getString("SOC_SCHOOL_CODE");

                                Department dep = new Department(socDepartmentCode, socDepartmentDescription, socSchoolCode);
                                socDepartments[i] = dep;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public String getSocSchoolCode() {
        return socSchoolCode;
    }

    public void setSocSchoolCode(String socSchoolCode) {
        this.socSchoolCode = socSchoolCode;
    }

    public String getSocSchoolDescription() {
        return socSchoolDescription;
    }

    public void setSocSchoolDescription(String socSchoolDescription) {
        this.socSchoolDescription = socSchoolDescription;
    }

    public Department[] getSocDepartments() {
        return socDepartments;
    }

    public void setSocDepartments(Department[] socDepartments) {
        this.socDepartments = socDepartments;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
