package com.grilla.uscwebregistration;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.base.BaseMaterialCard;

public class CourseCard extends BaseMaterialCard {
    protected CharSequence mLocationText;
    protected CharSequence mTimeText;
    protected CharSequence mDateText;
    protected CharSequence mTeacherText;

    public CourseCard(Context context) {
        this(context, R.layout.course_card_inner);
    }

    public CourseCard(Context context, @LayoutRes int innerLayout) {
        super(context, innerLayout);
    }

    public static SetupWizard with(Context context) {
        return new SetupWizard(context);
    }

    public static final class SetupWizard {
        private final Context mContext;

        private CharSequence mLocationText;
        private CharSequence mTimeText;
        private CharSequence mDateText;
        private CharSequence mTeacherText;

        private ArrayList<BaseSupplementalAction> mActions;
        private int mSupplementalActionLayoutId;

        private SetupWizard(Context context) {
            mContext = context;
        }

        public SetupWizard setLocationText(CharSequence locationText) {
            mLocationText = locationText;
            return this;
        }

        public SetupWizard setTimeText(CharSequence timeText) {
            mTimeText = timeText;
            return this;
        }

        public SetupWizard setDateText(CharSequence dateText) {
            mDateText = dateText;
            return this;
        }

        public SetupWizard setTeacherText(CharSequence teacherText) {
            mTeacherText = teacherText;
            return this;
        }

        public SetupWizard setupSupplementalActions(@LayoutRes int layoutId,ArrayList<BaseSupplementalAction> actions){
            mSupplementalActionLayoutId = layoutId;
            mActions = actions;
            return this;
        }

        public CourseCard build() {
            CourseCard card = new CourseCard(mContext);

            if (mLocationText != null)
                card.setLocationText(mLocationText);

            if (mTimeText != null)
                card.setTimeText(mTimeText);

            if (mDateText != null)
                card.setDateText(mDateText);

            if (mTeacherText != null)
                card.setTeacherText(mTeacherText);

            if (mActions != null){
                for (BaseSupplementalAction ac:mActions)
                    card.addSupplementalAction(ac);
            }
            card.setLayout_supplemental_actions_id(mSupplementalActionLayoutId);
            card.build();
            return card;
        }
    }

    @Override
    public void build() {

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        System.out.println("Setting up");

        if (view != null) {
            TextView locationText = (TextView)parent.findViewById(R.id.location_text);
            if (mLocationText != null) locationText.setText(mLocationText);

            TextView timeText = (TextView)parent.findViewById(R.id.time_text);
            if (mTimeText != null) timeText.setText(mTimeText);

            TextView dateText = (TextView)parent.findViewById(R.id.date_text);
            if (mDateText != null) dateText.setText(mDateText);

            TextView teacherText = (TextView)parent.findViewById(R.id.teacher_text);
            if (mTeacherText != null) teacherText.setText(mTeacherText);
        }
    }

    public CharSequence getLocationText() { return mLocationText; }
    public void setLocationText(CharSequence locationText) { mLocationText = locationText; }

    public CharSequence getTimeText() { return mTimeText; }
    public void setTimeText(CharSequence timeText) { mTimeText = timeText; }

    public CharSequence getDateText() { return mDateText; }
    public void setDateText(CharSequence dateText) { mDateText = dateText; }

    public CharSequence getTeacherText() { return mTeacherText; }
    public void setTeacherText(CharSequence teacherText) { mTeacherText = teacherText; }
}
