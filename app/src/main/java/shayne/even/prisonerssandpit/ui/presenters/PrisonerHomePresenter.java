package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.models.Prisoner;

/**
 * Created by Shayne Even on 21/05/2018.
 */

public interface PrisonerHomePresenter {
    void navigateToTrainerOptions();

    void getPrisoner();

    void getPerformanceScore(long prisonerId);

    void navigateToPrisonerTester();

    PrisonerSelectPresenter.OnSelectListener getOnSelectListener();
}
