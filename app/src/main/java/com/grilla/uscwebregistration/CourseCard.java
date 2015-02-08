package com.grilla.uscwebregistration;

import android.content.Context;
import android.support.annotation.LayoutRes;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.base.BaseMaterialCard;

public class CourseCard extends BaseMaterialCard {

    public CourseCard(Context context) {
        this(context, R.layout.native_material_largeimage_inner_base_main);
    }

    public CourseCard(Context context, @LayoutRes int innerLayout) {
        super(context, innerLayout);
    }

    public static SetupWizard with(Context context) {
        return new SetupWizard(context);
    }

    public static final class SetupWizard {
        private final Context mContext;
        private ArrayList<BaseSupplementalAction> mActions;
        private int mSupplementalActionLayoutId;

        private SetupWizard(Context context) {
            mContext = context;
        }

        public SetupWizard setupSupplementalActions(@LayoutRes int layoutId,ArrayList<BaseSupplementalAction> actions){
            mSupplementalActionLayoutId = layoutId;
            mActions = actions;
            return this;
        }

        public CourseCard build() {
            CourseCard card = new CourseCard(mContext);
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
}
