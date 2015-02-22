package com.grilla.uscwebregistration;

import com.grilla.uscwebregistration.organization.Section;
import com.grilla.uscwebregistration.views.SectionCard;

/**
 * This interface allows the fragments in
 * course view to communicate.
 * I.e. when you click a lecture it keeps
 * the section data when going to discussions/labs.
 * @author Bill Derouin <bill@billderouin.com>
 */
public interface LectureCardClickListener {
    void onLectureClick(Section[] sections, SectionCard card);
}
