package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.views.PrisonerRowView;

/**
 * Presenter for the Prisoner List View
 */

public interface PrisonerListPresenter {

    /**
     * Binds the specified view the the prisoner in the passed position in the prisoner list
     * @param holder the view to bind
     * @param position the position of the prisoner to bind to the view to
     */
    void onBindPrisonerView(PrisonerRowView holder, int position);

    /**
     * Provides the number of prisoners in the list
     * @return the count of prisoners in the list
     */
    int getPrisonerRowCount();

    /**
     * Adds the specified prisoner to the list
     * @param id the id of the prisoner to append
     */
    void appendPrisoner(long id);
}
