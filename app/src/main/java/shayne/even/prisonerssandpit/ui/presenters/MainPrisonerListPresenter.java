package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.views.MainPrisonerRowView;

/**
 * Presenter for the Main Prisoner List View
 */

public interface MainPrisonerListPresenter extends PrisonerListPresenter {
    /**
     * Handles when a prisoner item in the list is selected.
     * @param view the selected view
     */
    void handleOnPrisonerRowSelected(MainPrisonerRowView view);
}
