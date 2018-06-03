package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.presenters.listeners.NoEntriesListener;

/**
 * Created by Shayne Even on 14/05/2018.
 */

public interface MainPresenter {
    void startAddAddPrisonerActivity();

    void handleAddPrisonerResult(int resultCode, long prisonerId);

    void navigateToAddPrisonerView();

    NoEntriesListener getNoEntriesListener();

    void handleInvalidResultFromAddPrisonerView();
}
