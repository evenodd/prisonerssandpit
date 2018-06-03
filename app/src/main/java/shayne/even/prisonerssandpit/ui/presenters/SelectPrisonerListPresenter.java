package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.views.PrisonerRowView;

/**
 * Presenter for the Select Prisoner View
 */

public interface SelectPrisonerListPresenter extends PrisonerListPresenter{
    /**
     * Populates the list with prisoners except for the specified prisoner
     * @param excludedPrisoner the id of the prisoner to exclude from the list
     */
    void getAllPrisonersExcept(long excludedPrisoner);

    /**
     * Handler for when a prisoner has been selected. Notifies the OnSelectListener that the
     * prisoner for the passed view has been selected
     * @param viewHolder the view for the selected prisoner
     */
    void handlePrisonerSelected(PrisonerRowView viewHolder);
}
