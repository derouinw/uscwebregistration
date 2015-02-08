package com.grilla.uscwebregistration;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.actions.TextSupplementalAction;
import it.gmariotti.cardslib.library.cards.base.BaseMaterialCard;
import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardViewNative;

public class CoursesFragment extends Fragment {
    public String ARG_SCHOOL = "com.grilla.uscwebregistration.ARG_SCHOOL";
    private String school;

    public CoursesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);

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

        ArrayList<BaseSupplementalAction> actions = new ArrayList<>();
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
        });

       CourseCard card = CourseCard.with(getActivity()).setupSupplementalActions(R.layout.course_actions, actions).build();
        ((CardViewNative)rootView.findViewById(R.id.card)).setCard(card);
        return rootView;
    }
}
