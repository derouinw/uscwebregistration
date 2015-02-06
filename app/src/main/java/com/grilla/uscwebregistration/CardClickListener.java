package com.grilla.uscwebregistration;

import android.view.View;

import it.gmariotti.cardslib.library.internal.Card;

// Listen for the click event on a card
public class CardClickListener implements Card.OnCardClickListener {
    private String name;
    private int college;
    private DepartmentFragment df;

    public CardClickListener(String name, int college, DepartmentFragment df) {
        this.name = name;
        this.college = college;
        this.df = df;
    }

    @Override
    public void onClick(Card card, View view) {
        df.showSchools(college);
    }
}
