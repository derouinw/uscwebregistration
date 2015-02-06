package com.grilla.uscwebregistration;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.actions.TextSupplementalAction;
import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.view.CardViewNative;

public class DepartmentFragment extends Fragment {

    public DepartmentFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_department, container, false);

        // Total number of actions for all cards
        /*ArrayList<ArrayList<BaseSupplementalAction>> actionses = new ArrayList<ArrayList<BaseSupplementalAction> >();

        // Dornsife
        ArrayList<BaseSupplementalAction> actions = new ArrayList<BaseSupplementalAction>();
        TextSupplementalAction dt1 = new TextSupplementalAction(getActivity(), R.id.text1);
        dt1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity(), " Dornsife SHARE ", Toast.LENGTH_SHORT).show();
            }
        });
        actions.add(dt1);

        TextSupplementalAction dt2 = new TextSupplementalAction(getActivity(), R.id.text2);
        dt2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Dornsife LEARN ",Toast.LENGTH_SHORT).show();
            }
        });
        actions.add(dt2);
        actionses.add(actions);

        // Leventhal
        ArrayList<BaseSupplementalAction> actions2 = new ArrayList<BaseSupplementalAction>();
        TextSupplementalAction lt1 = new TextSupplementalAction(getActivity(), R.id.text1);
        lt1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity(), " Leventhal SHARE ", Toast.LENGTH_SHORT).show();
            }
        });
        actions2.add(lt1);

        TextSupplementalAction lt2 = new TextSupplementalAction(getActivity(), R.id.text2);
        lt2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Leventhal LEARN ",Toast.LENGTH_SHORT).show();
            }
        });
        actions2.add(lt2);
        actionses.add(actions2);

        // Viterbi
        ArrayList<BaseSupplementalAction> actions3 = new ArrayList<BaseSupplementalAction>();
        TextSupplementalAction vt1 = new TextSupplementalAction(getActivity(), R.id.text1);
        vt1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity(), " Viterbi SHARE ", Toast.LENGTH_SHORT).show();
            }
        });
        actions3.add(vt1);

        TextSupplementalAction vt2 = new TextSupplementalAction(getActivity(), R.id.text2);
        vt2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Viterbi LEARN ",Toast.LENGTH_SHORT).show();
            }
        });
        actions3.add(vt2);
        actionses.add(actions3);*/

        TypedArray schools = getResources().obtainTypedArray(R.array.schools);
        TypedArray schoolsImages = getResources().obtainTypedArray(R.array.schools_images);

        LinearLayout cardsLayout = (LinearLayout)rootView.findViewById(R.id.cardLayout);

        for (int i = 0; i < schools.length(); i++) {
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

            card.setOnClickListener(new CardClickListener((String)schools.getText(i), i, this));

            LinearLayout holder = (LinearLayout)getActivity().getLayoutInflater().inflate(R.layout.dept_card, null);
            CardViewNative layoutCard = (CardViewNative)holder.getChildAt(0);
            layoutCard.setCard(card);
            holder.removeAllViews();
            cardsLayout.addView(layoutCard);
        }

        schools.recycle();
        schoolsImages.recycle();

        return rootView;
    }

    public void showSchools(int college) {
        String[] schools;

        // get schools based on selected college
        switch(college) {
            case 0:
                // Dornsife
                schools = getResources().getStringArray(R.array.dornsife_schools);
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
        }

        Intent intent = new Intent(getActivity(), SchoolsActivity.class);
        intent.putExtra(SchoolsFragment.ARG_SCHOOLS, schools);
        startActivity(intent);

    }
}