package com.grilla.uscwebregistration;

import android.view.View;

import com.grilla.uscwebregistration.views.SectionCard;

/**
 * This interface is implemented in CourseViewFragment
 * and SectionAddlFragment to give SectionCard a
 * click listener.
 * @author Bill Derouin <bill@billderouin.com>
 */
public interface SectionCardClickCommunicator {
    void clickCard(SectionCard card);
}
