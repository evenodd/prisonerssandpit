package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.views.TesterResultsListView;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public interface TestingPresenter {

    TesterResultsListView.OnScoreUpdateListener getScoreUpdaterListener();
}
