package shayne.even.prisonerssandpit.ui.presenters;

/**
 * Created by Shayne Even on 21/05/2018.
 */

public interface PrisonerHomePresenter {
    void navigateToTrainerOptions();

    void getPrisoner();

    void getPerformanceScore(long prisonerId);
}
