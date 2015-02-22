package com.grilla.uscwebregistration;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

public class ViewSchoolClassesFragment extends Fragment {
    public static final String ARG_SCHOOL_NAME = "com.grilla.uscwebregistration.ARG_SCHOOL_NAME";
    private String schoolName;

    private ArrayList<Card> cards;

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
        Context c = getActivity().getApplicationContext();

        TextView schoolNameView = (TextView)rootView.findViewById(R.id.school_name);
        schoolNameView.setText(schoolName);

        CardArrayAdapter adapter = new CardArrayAdapter(getActivity(), cards);
        CardListView listView = (CardListView)rootView.findViewById(R.id.card_list);
        listView.setAdapter(adapter);

        String request = JSONHelper.COURSES_URL + schoolName;
        RequestQueue queue = Volley.newRequestQueue(c);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, request,
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        System.out.println("Response is: " + response);

                        try {
                            JSONObject jo = new JSONObject(response);
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

    private void loadCourses(JSONObject response) {

    }
}
