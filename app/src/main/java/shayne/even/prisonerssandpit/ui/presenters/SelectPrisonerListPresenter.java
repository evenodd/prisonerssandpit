package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.views.PrisonerRowView;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public interface SelectPrisonerListPresenter extends PrisonerListPresenter{
    void getAllPrisonersExcept(long excludedPrisoner);

    void handlePrisonerSelected(PrisonerRowView viewHolder);
}
