package com.grilla.uscwebregistration;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.grilla.uscwebregistration.organization.Course;
import com.grilla.uscwebregistration.organization.Section;
import com.grilla.uscwebregistration.views.SectionCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

public class CourseViewFragment extends Fragment {
    private String courseId;
    private Course course;

    private TextView titleText;
    private TextView unitsText;
    private TextView descriptionText;

    private Section[] sections;
    private ArrayList<Card> cards;
    CardArrayAdapter adapter;

    public CourseViewFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course_view, container, false);
        Context c = getActivity().getApplicationContext();

        if (savedInstanceState == null) savedInstanceState = getArguments();
        if (savedInstanceState != null) courseId = savedInstanceState.getString(ViewCourseActivity.ARG_COURSE_ID);
        course = new Course(Double.parseDouble(courseId), getActivity());

        cards = new ArrayList<>();
        adapter = new CardArrayAdapter(getActivity(), cards);
        CardListView listView = (CardListView)rootView.findViewById(R.id.section_list);
        listView.setAdapter(adapter);

        View header = inflater.inflate(R.layout.course_header, null);
        listView.addHeaderView(header);

        titleText = (TextView)header.findViewById(R.id.text_title);
        unitsText = (TextView)header.findViewById(R.id.text_units);
        descriptionText = (TextView)header.findViewById(R.id.text_description);

        RequestQueue queue = JSONSingleton.getInstance(c).getRequestQueue();
        String request = JSONHelper.COURSES_URL + courseId;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, request,
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        Log.d("CourseViewFragment", "Loaded course data"); // optional log message

                        try {
                            JSONObject jo = new JSONObject(response);
                            loadCourseData(jo);
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

    private void loadCourseData(JSONObject jo) throws JSONException {
        String sisCourseId = jo.getString("SIS_COURSE_ID");
        String title = jo.getString("TITLE");
        double units = jo.getDouble("MAX_UNITS");
        String description = jo.getString("DESCRIPTION");

        titleText.setText(title);
        unitsText.setText(String.valueOf(units) + " units");
        descriptionText.setText(description);
        getActivity().setTitle(sisCourseId);

        course.setSisCourseID(sisCourseId);
        course.setTitle(title);
        course.setMaxUnits(units);
        course.setDescription(description);

        JSONArray sectionsArr = jo.getJSONArray("V_SOC_SECTION");
        sections = new Section[sectionsArr.length()];
        for (int i = 0; i < sectionsArr.length(); i++) {
            JSONObject s = sectionsArr.getJSONObject(i);
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
            //double registered = s.getDouble("REGISTERED");
            double registered = 0; // TODO: registered is always null?
            String instructor = s.getString("INSTRUCTOR");
            double seats = s.getDouble("SEATS");
            String addDate = s.getString("ADD_DATE");
            String cancelDate = s.getString("CANCEL_DATE");
            String publishFlag = s.getString("PUBLISH_FLAG");
            String publishSectionFlag = s.getString("PUBLISH_SECTION_FLAG");
            // s.getJSONArray("V_SOC_COURSE");

            Section temp = new Section(termCode, courseID, sisCourseID, name, section, session, maxUnits /* or minUnits? */, type, beginTime, endTime, day, registered, seats, instructor, location);
            sections[i] = temp;

            SectionCard card = new SectionCard(getActivity().getApplicationContext());
            card.setSection(temp);
            cards.add(card);
            adapter.add(card);
        }

        adapter.notifyDataSetChanged();
    }
}