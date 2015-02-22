package com.grilla.uscwebregistration;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.grilla.uscwebregistration.organization.School;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.actions.TextSupplementalAction;
import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.view.CardViewNative;

public class SchoolListFragment extends Fragment {
    School[] schools;
    View rootView;
    LinearLayout cardsLayout;
    Map<String, String> schoolImages;


    public SchoolListFragment(){
        schoolImages = new HashMap<String, String>();
        schoolImages.put("ACAD", "@drawable/innovation");
        schoolImages.put("ACCT", "accounting");
        schoolImages.put("ANSC", "communication__journalism");
        schoolImages.put("ARCH", "architecture");
        schoolImages.put("BUAD", "business");
        schoolImages.put("CNTV", "cinema");
        schoolImages.put("DANC", "dance");
        schoolImages.put("DENT", "dentistry");
        schoolImages.put("DHRP", "physical_therapy");
        schoolImages.put("EDUC", "teaching");
        schoolImages.put("ENGR", "engineering");
        schoolImages.put("FA", "fine_art");
        schoolImages.put("GE", "ge");
        schoolImages.put("GERO", "gerontology");
        schoolImages.put("GRAD", "grad");
        schoolImages.put("LAS", "dornsife");
        schoolImages.put("LAW", "law");
        schoolImages.put("MED", "medicine");
        schoolImages.put("MUS", "music");
        schoolImages.put("OT", "occupational");
        schoolImages.put("PHAR", "pharmacy");
        schoolImages.put("PPD", "public_policy");
        schoolImages.put("SOWK", "social_work");
        schoolImages.put("THTR", "drama");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_school_list, container, false);
        Context c = getActivity().getApplicationContext();

        TypedArray schools = getResources().obtainTypedArray(R.array.schools);
        TypedArray schoolsImages = getResources().obtainTypedArray(R.array.schools_images);

        cardsLayout = (LinearLayout)rootView.findViewById(R.id.cardLayout);

        /*for (int i = 0; i < schools.length(); i++) {
            ArrayList<BaseSupplementalAction> actions = new ArrayList<BaseSupplementalAction>();
            TextSupplementalAction dt1 = new TextSupplementalAction(getActivity(), R.id.text1);
            dt1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Toast.makeText(getActivity()," SHARE ", Toast.LENGTH_SHORT).show();
                }
            });
            actions.add(dt1);

            TextSupplementalAction dt2 = new TextSupplementalAction(getActivity(), R.id.text2);
            dt2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Toast.makeText(getActivity()," LEARN ",Toast.LENGTH_SHORT).show();
                }
            });

            MaterialLargeImageCard card =
                    MaterialLargeImageCard.with(getActivity())
                            .setTextOverImage(schools.getText(i))
                            .useDrawableId(schoolsImages.getResourceId(i, -1))
                            .setupSupplementalActions(R.layout.horiz_text, actions)
                            .build();

            card.setOnClickListener(new SchoolCardClickListener((String)schools.getText(i), i, this));

            LinearLayout holder = (LinearLayout)getActivity().getLayoutInflater().inflate(R.layout.school_card, null);
            CardViewNative layoutCard = (CardViewNative)holder.getChildAt(0);
            layoutCard.setCard(card);
            holder.removeAllViews();
            cardsLayout.addView(layoutCard);
        }

        schools.recycle();
        schoolsImages.recycle();*/

        String request = JSONHelper.SCHOOLS_URL;
        RequestQueue queue = JSONSingleton.getInstance(c).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, request,
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        Log.d("SchoolList", "School list data loaded");

                        try {
                            JSONArray jo = new JSONArray(response);
                            loadSchoolsData(jo);
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

    public int getResourceId(String pVariableName, String pResourcename, String pPackageName)
    {
        try {
            return getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void loadSchoolsData(JSONArray jo) throws JSONException {
        schools = new School[jo.length()];
        TypedArray schoolsImages = getResources().obtainTypedArray(R.array.schools_images);

        for (int i = 0; i < jo.length(); i++) {
            JSONObject school = jo.getJSONObject(i);
            String schoolCode = school.getString("SOC_SCHOOL_CODE");
            String schoolDescription = school.getString("SOC_SCHOOL_DESCRIPTION");
            schools[i] = new School(schoolCode, schoolDescription, getActivity());

            ArrayList<BaseSupplementalAction> actions = new ArrayList<BaseSupplementalAction>();
            TextSupplementalAction dt1 = new TextSupplementalAction(getActivity(), R.id.text1);
            dt1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Toast.makeText(getActivity()," SHARE ", Toast.LENGTH_SHORT).show();
                }
            });
            actions.add(dt1);

            TextSupplementalAction dt2 = new TextSupplementalAction(getActivity(), R.id.text2);
            dt2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Toast.makeText(getActivity()," LEARN ",Toast.LENGTH_SHORT).show();
                }
            });



            MaterialLargeImageCard card =
                    MaterialLargeImageCard.with(getActivity())
                            .setTextOverImage(schoolDescription)
                            .useDrawableId(getResourceId(schoolImages.get(schoolCode), "drawable", getActivity().getPackageName()))
                            .setupSupplementalActions(R.layout.horiz_text, actions)
                            .build();

            card.setOnClickListener(new SchoolCardClickListener(schoolDescription, i, this));

            LinearLayout holder = (LinearLayout)getActivity().getLayoutInflater().inflate(R.layout.school_card, null);
            CardViewNative layoutCard = (CardViewNative)holder.getChildAt(0);
            layoutCard.setCard(card);
            holder.removeAllViews();
            cardsLayout.addView(layoutCard);
        }

        schoolsImages.recycle();
    }

    public void showDepartments(int college) {
        String schoolCode = "";
        String[] schoolCodes = getResources().getStringArray(R.array.schools_codes);

        // get departments based on selected college
        /*switch(college) {
            case 0:
                // Dornsife
                //schools = getResources().getStringArray(R.array.dornsife_schools);
                schoolCode =
                break;
            case 1:
                // Leventhal
                schools = getResources().getStringArray(R.array.leventhal_schools);
                break;
            case 2:
                // Viterbi
                schools = getResources().getStringArray(R.array.viterbi_schools);
                break;
            default:
                schools = null;
                break;
        }*/

        schoolCode = schools[college].getSocSchoolCode();

        Intent intent = new Intent(getActivity(), DepartmentsActivity.class);
        intent.putExtra(DepartmentsFragment.ARG_SCHOOL_CODE, schoolCode);
        startActivity(intent);

    }
}