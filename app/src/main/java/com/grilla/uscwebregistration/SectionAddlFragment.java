package com.grilla.uscwebregistration;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grilla.uscwebregistration.organization.Section;
import com.grilla.uscwebregistration.views.SectionCard;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.prototypes.CardSection;
import it.gmariotti.cardslib.library.prototypes.SectionedCardAdapter;
import it.gmariotti.cardslib.library.view.CardListView;


/**
 * @author Bill Derouin <bill@billderouin.com>
 */
public class SectionAddlFragment extends Fragment implements SectionCardClickCommunicator {
    private Section[] sections;
    private ArrayList<Card> cards;
    private SectionCard card;

    private CardArrayAdapter adapter;
    private ArrayList<CardSection> cardSections;

    public SectionAddlFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_section_addl, container, false);
        Context c = getActivity().getApplicationContext();

        // Load section data passed from activity
        if (savedInstanceState == null) savedInstanceState = getArguments();
        if (savedInstanceState != null) {
            sections = (Section[])savedInstanceState.getParcelableArray(ViewCourseActivity.ARG_SECTIONS);
        }

        // construct section cards out of section data
        cards = new ArrayList<>();
        for (int i = 0; i < sections.length; i++) {
            Section s = sections[i];
            Log.d("SectionAddlFragment", s.toString());
            SectionCard sc = new SectionCard(c, this);
            sc.setSection(s);
            cards.add(sc);
        }

        // load section cards into adapter
        // its split up into sections (i.e.
        // discussion, lab, etc)
        adapter = new CardArrayAdapter(c, cards);
        cardSections = new ArrayList<>();
        cardSections.add(new CardSection(0, "Discussions"));
        cardSections.add(new CardSection(3, "Labs"));
        CardSection[] dummy = new CardSection[cardSections.size()];

        SectionedCardAdapter sectionAdapter = new SectionedCardAdapter(c, adapter);
        sectionAdapter.setCardSections(cardSections.toArray(dummy));

        CardListView listView = (CardListView)rootView.findViewById(R.id.other_section_list);
        //listView.setExternalAdapter(sectionAdapter, adapter);
        listView.setAdapter(adapter);

        return rootView;
    }


    @Override
    public void clickCard(SectionCard card) {

    }
}
