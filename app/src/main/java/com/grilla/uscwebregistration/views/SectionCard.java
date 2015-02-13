package com.grilla.uscwebregistration.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import it.gmariotti.cardslib.library.internal.Card;
import com.grilla.uscwebregistration.R;

/**
 * @author Bill Derouin <bill@billderouin.com>
 */
public class SectionCard extends Card {
    protected CharSequence mLocationText;
    protected CharSequence mTimeText;
    protected CharSequence mDateText;
    protected CharSequence mTeacherText;

    /**
     * Constructor with default section card layout
     * @param context
     */
    public SectionCard(Context context) {
        this(context, R.layout.section_card_inner);
    }

    /**
     * Constructor specifying alternate inner layout
     * @param context
     * @param innerLayout
     */
    public SectionCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }

    /**
     * Init
     */
    private void init(){

        //No Header

        //Set a OnClickListener listener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getContext(), "Click Listener card=", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        System.out.println("Setting up");


        if (view != null) {
            TextView locationText = (TextView)parent.findViewById(R.id.location_text);
            if (mLocationText != null) locationText.setText(mLocationText);

            TextView timeText = (TextView)view.findViewById(R.id.time_text);
            if (mTimeText != null) timeText.setText(mTimeText);

            TextView dateText = (TextView)parent.findViewById(R.id.date_text);
            if (mDateText != null) dateText.setText(mDateText);

            TextView teacherText = (TextView)parent.findViewById(R.id.teacher_text);
            if (mTeacherText != null) teacherText.setText(mTeacherText);
        }
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

    public CharSequence getmTeacherText() {
        return mTeacherText;
    }

    public void setmTeacherText(CharSequence mTeacherText) {
        this.mTeacherText = mTeacherText;
    }
}
