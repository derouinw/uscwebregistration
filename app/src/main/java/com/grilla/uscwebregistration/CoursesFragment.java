package com.grilla.uscwebregistration;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.grilla.uscwebregistration.organization.Course;
import com.grilla.uscwebregistration.organization.Section;
import com.grilla.uscwebregistration.views.SectionCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.actions.TextSupplementalAction;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

public class CoursesFragment extends Fragment {
    public String ARG_SCHOOL = "com.grilla.uscwebregistration.ARG_SCHOOL";
    private String school;
    private ArrayList<Card> cards;

    private ArrayList<Course> courses;

    public CoursesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);
        Context c = getActivity().getApplicationContext();

        /*school = savedInstanceState.getString(ARG_SCHOOL);
        String request = "http://petri.usc.edu/socAPI/Courses/" + JSONHelper.term + "/" + school;

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, request,
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        System.out.println("Response is: " + response);

                        try {
                            JSONObject jo = new JSONObject(response);

                            // Load cards with courses

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
        queue.add(stringRequest);*/

        /*ArrayList<BaseSupplementalAction> actions = new ArrayList<>();
        TextSupplementalAction dt1 = new TextSupplementalAction(getActivity(), R.id.course_register);
        dt1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity(), " REGISTER ", Toast.LENGTH_SHORT).show();
            }
        });
        actions.add(dt1);

        TextSupplementalAction dt2 = new TextSupplementalAction(getActivity(), R.id.course_save);
        dt2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," SAVE ",Toast.LENGTH_SHORT).show();
            }
        });*/

        CharSequence loc1 = "SAL 101";
        CharSequence time1 = "10:00-10:50";
        CharSequence date1 = "MWF";
        CharSequence teacher1 = "Redekopp, Mark";

        CharSequence loc2 = "SGM 124";
        CharSequence time2 = "1:00-2:50";
        CharSequence date2 = "TH";
        CharSequence teacher2 = "Crowley, Michael";

        /*CourseCard card1 = CourseCard.with(getActivity())
                .setLocationText(loc1)
                .setTimeText(time1)
                .setDateText(date1)
                .setTeacherText(teacher1)
                .setupSupplementalActions(R.layout.course_actions, actions)
                .build();
        //card1.setupInnerViewElements(container, inflater.inflate(R.layout.course_card, container));

        CourseCard card2 = CourseCard.with(getActivity())
                .setLocationText(loc2)
                .setTimeText(time2)
                .setDateText(date2)
                .setTeacherText(teacher2)
                .build();
        //card2.setupInnerViewElements(container, inflater.inflate(R.layout.course_card, container));

        SectionCard card3 = new SectionCard(getActivity().getApplicationContext());
        card3.setmLocationText(loc2);
        card3.setmTimeText(time2);
        card3.setmDateText(date2);
        card3.setmInstructorText(teacher2);

        Card card4 = new Card(getActivity(), R.layout.drawer_list_item);

        //((CardViewNative) rootView.findViewById(R.id.card)).setCard(card3);
        CardViewNative cardView = (CardViewNative)rootView.findViewById(R.id.card);
        cardView.setCard(card4);*/

        cards = new ArrayList<>();
        courses = new ArrayList<>();
        /*Card card = new Card(getActivity());
        card.setInnerLayout(R.layout.section_card_inner);
        cards.add(card);

        SectionCard card3 = new SectionCard(getActivity());
        card3.setmLocationText(loc2);
        card3.setmTimeText(time2);
        card3.setmDateText(date2);
        card3.setmInstructorText(teacher2);
        cards.add(card3);*/

        CardArrayAdapter adapter = new CardArrayAdapter(getActivity(), cards);
        CardListView listView = (CardListView)rootView.findViewById(R.id.card_list);
        listView.setAdapter(adapter);

        String request = JSONHelper.COURSES_URL + "CSCI";
        RequestQueue queue = JSONSingleton.getInstance(c).getRequestQueue();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, request,
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        System.out.println("Response is: " + response);

                        try {
                            JSONArray ja = new JSONArray(response);
                            populateCards(ja);
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

    private void populateCards(JSONArray response) {
        /*try {
            for (int i = 0; i < response.length(); i++) {
                Double courseID = response.getJSONObject(i).getDouble("COURSE_ID");

                Course course = new Course(courseID, getActivity());
                courses.add(course);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            Section[] sections = course.getSections();
            for (int j = 0; j < sections.length; j++) {
                Section section = sections[j];
                SectionCard card = new SectionCard(getActivity());
                card.setSection(section);
                cards.add(card);
            }
        }*/
    }
}
