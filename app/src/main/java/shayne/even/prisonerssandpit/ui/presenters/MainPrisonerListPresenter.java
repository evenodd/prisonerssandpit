package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.views.MainPrisonerRowView;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public interface MainPrisonerListPresenter extends PrisonerListPresenter {
    void navigateToPrisonersHome(MainPrisonerRowView view);
}
