package com.grilla.uscwebregistration.organization;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.grilla.uscwebregistration.JSONHelper;
import com.grilla.uscwebregistration.JSONSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Bill Derouin <bill @ billderouin.com>
 */
public class Course {
    // API data
    private double courseID;
    private String sisCourseID;
    private String title;
    private double minUnits;
    private double maxUnits;
    private double totalMaxUnits;
    private String description;
    private String diversityFlag;
    private String effectiveTermCode;
    private Section[] sections;

    // add'l data
    private Activity activity;
    public boolean isLoaded = false;

    /**
     * Constructor, takes the course ID and activity
     * to create an HTML request to load the JSON data
     * for the course from the webreg API.
     * @param courseID
     * @param activity
     */
    public Course(double courseID, Activity activity) {
        this.courseID = courseID;
        this.activity = activity;
        //loadCourseAndSectionData();
    }

    private void loadCourseData() {

    }

    /**
     * Takes the course ID given in the constructor
     * and creates and processes an HTML request
     * from the web registration API.
     * Then parses this data into the course itself
     * and the sections it contains.
     * @see Section
     */
    private void loadCourseAndSectionData() {
        String request = JSONHelper.COURSES_URL + String.format("%d",(long)courseID);

        RequestQueue queue = JSONSingleton.getInstance(activity).getRequestQueue();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, request,
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        System.out.println("Response is: " + response);

                        try {
                            JSONObject jo = new JSONObject(response);

                            setCourseID(jo.getDouble("COURSE_ID"));
                            setSisCourseID(jo.getString("SIS_COURSE_ID"));
                            setTitle(jo.getString("TITLE"));
                            setMinUnits(jo.getDouble("MIN_UNTIS"));
                            setMaxUnits(jo.getDouble("MAX_UNITS"));
                            setTotalMaxUnits(jo.getDouble("TOTAL_MAX_UNITS"));
                            setDescription(jo.getString("DESCRIPTION"));
                            setDiversityFlag(jo.getString("DIVERSITY_FLAG"));
                            setEffectiveTermCode(jo.getString("EFFECTIVE_TERM_CODE"));

                            JSONArray jsonSections = jo.getJSONArray("V_SOC_SECTIONS");
                            sections = new Section[jsonSections.length()];
                            for (int i = 0; i < jsonSections.length(); i++) {
                                JSONObject s = jsonSections.getJSONObject(i);
                                double sectionID = s.getDouble("SECTION_ID");
                                String termCode = s.getString("TERM_CODE");
                                double courseID = s.getDouble("COURSE_ID");
                                String sisCourseID = s.getString("SIS_COURSE_ID");
                                double minUnits = s.getDouble("MIN_UNITS");
                                double maxUnits = s.getDouble("MAX_UNITS");
                                String name = s.getString("NAME");
                                String section = s.getString("SECTION");
                                String session = s.getString("SESSION");
                                String type = s.getString("TYPE");
                                String beginTime = s.getString("BEGIN_TIME");
                                String endTime = s.getString("END_TIME");
                                String day = s.getString("DAY");
                                String location = s.getString("LOCATION");
                                double registered = s.getDouble("REGISTERED");
                                String instructor = s.getString("INSTRUCTOR");
                                double seats = s.getDouble("SEATS");
                                String addDate = s.getString("ADD_DATE");
                                String cancelDate = s.getString("CANCEL_DATE");
                                String publishFlag = s.getString("PUBLISH_FLAG");
                                String publishSectionFlag = s.getString("PUBLISH_SECTION_FLAG");
                                // s.getJSONArray("V_SOC_COURSE");

                                Section temp = new Section(termCode, courseID, sisCourseID, name, section, session, maxUnits /* or minUnits? */, type, beginTime, endTime, day, registered, seats, instructor, location);
                                sections[i] = temp;
                            }

                            isLoaded = true;

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

    public double getCourseID() {
        return courseID;
    }

    public void setCourseID(double courseID) {
        this.courseID = courseID;
    }

    public String getSisCourseID() {
        return sisCourseID;
    }

    public void setSisCourseID(String sisCourseID) {
        this.sisCourseID = sisCourseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMinUnits() {
        return minUnits;
    }

    public void setMinUnits(double minUnits) {
        this.minUnits = minUnits;
    }

    public double getMaxUnits() {
        return maxUnits;
    }

    public void setMaxUnits(double maxUnits) {
        this.maxUnits = maxUnits;
    }

    public double getTotalMaxUnits() {
        return totalMaxUnits;
    }

    public void setTotalMaxUnits(double totalMaxUnits) {
        this.totalMaxUnits = totalMaxUnits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiversityFlag() {
        return diversityFlag;
    }

    public void setDiversityFlag(String diversityFlag) {
        this.diversityFlag = diversityFlag;
    }

    public String getEffectiveTermCode() {
        return effectiveTermCode;
    }

    public void setEffectiveTermCode(String effectiveTermCode) {
        this.effectiveTermCode = effectiveTermCode;
    }

    public Section[] getSections() {
        return sections;
    }

    public void setSections(Section[] sections) {
        this.sections = sections;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
