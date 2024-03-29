package com.grilla.uscwebregistration.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.gmariotti.cardslib.library.internal.Card;

import com.grilla.uscwebregistration.R;
import com.grilla.uscwebregistration.SectionCardClickCommunicator;
import com.grilla.uscwebregistration.organization.Section;

/**
 * @author Bill Derouin <bill@billderouin.com>
 */
public class SectionCard extends Card {
    protected CharSequence mLocationText;
    protected CharSequence mTimeText;
    protected CharSequence mDateText;
    protected CharSequence mInstructorText;

    private Section section;
    private SectionCardClickCommunicator listener;

    /**
     * Constructor with default section card layout
     * @param context
     */
    public SectionCard(Context context,SectionCardClickCommunicator listener) {
        this(context, R.layout.section_card_inner, listener);
    }

    /**
     * Constructor specifying alternate inner layout
     * @param context
     * @param innerLayout
     */
    public SectionCard(Context context, int innerLayout, SectionCardClickCommunicator listener) {
        super(context, innerLayout);
        this.listener = listener;
        init();
    }

    /**
     * Init
     */
    private void init(){

        //No Header

        //Set a OnClickListener listener
        setOnClickListener(new SectionCardClickListener(this));
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        if (view != null) {
            TextView locationText = (TextView)parent.findViewById(R.id.location_text);
            if (mLocationText != null) locationText.setText(mLocationText);

            TextView timeText = (TextView)view.findViewById(R.id.time_text);
            if (mTimeText != null) timeText.setText(mTimeText);

            TextView dateText = (TextView)parent.findViewById(R.id.date_text);
            if (mDateText != null) dateText.setText(mDateText);

            TextView teacherText = (TextView)parent.findViewById(R.id.teacher_text);
            if (mInstructorText != null) teacherText.setText(mInstructorText);
        }
    }

    private void loadSectionData() {
        mLocationText = section.getLocation();
        mTimeText = section.getBeginTime() + " - " + section.getEndTime();
        mDateText = section.getDay();
        mInstructorText = section.getInstructor();
    }


    public CharSequence getmLocationText() {
        return mLocationText;
    }

    public void setmLocationText(CharSequence mLocationText) {
        this.mLocationText = mLocationText;
    }

    public CharSequence getmTimeText() {
        return mTimeText;
    }

    public void setmTimeText(CharSequence mTimeText) {
        this.mTimeText = mTimeText;
    }

    public CharSequence getmDateText() {
        return mDateText;
    }

    public void setmDateText(CharSequence mDateText) {
        this.mDateText = mDateText;
    }

    public CharSequence getmInstructorText() {
        return mInstructorText;
    }

    public void setmInstructorText(CharSequence mInstructorText) {
        this.mInstructorText = mInstructorText;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
        loadSectionData();
    }

    private class SectionCardClickListener implements Card.OnCardClickListener {
        private SectionCard card;

        public SectionCardClickListener(SectionCard card) {
            super();
            this.card = card;
        }

        @Override
        public void onClick(Card card, View view) {
            Log.d("SectionCardClick", "Clicked " + this.card.getmTimeText() + ": " + this.card.getmInstructorText());

            listener.clickCard(this.card);
        }
    }
}
