package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.views.TesterResultsListView;

/**
 * Presenter for the Testing View
 */

public interface TestingPresenter {

    /**
     * Provides a listener for updates in scores
     */
    TesterResultsListView.OnScoreUpdateListener getScoreUpdaterListener();
}
