package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.models.Prisoner;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public interface PrisonerSelectPresenter {

    void populateList();

    OnSelectListener getListener();

    interface OnSelectListener{
        void onSelect(Prisoner prisoner);
    }
}
