package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.models.Prisoner;

/**
 * Presenter for the Prisoner Select Dialog
 */

public interface PrisonerSelectPresenter {

    void populateList();

    OnSelectListener getListener();

    /**
     * Listener to handle the selection of an item in the list
     */
    interface OnSelectListener{
        /**
         * Handler for when an item is seleted
         * @param prisoner the selected prisoner
         */
        void onSelect(Prisoner prisoner);
    }
}
