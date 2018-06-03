package shayne.even.prisonerssandpit.ui.presenters;

/**
 * Presenter for the Prison Home View
 */

public interface PrisonerHomePresenter {
    /**
     * Opens the Trainer Options View
     */
    void navigateToTrainerOptions();

    /**
     * Opens the Prisoner Tester view
     */
    void navigateToPrisonerTester();

    /**
     * Provides a listener for selected items for the Prisoner Select Dialog
     * @return a handler for selected items
     */
    PrisonerSelectPresenter.OnSelectListener getOnSelectListener();

    /**
     * Opens the Vs View
     */
    void navigateToVs();
}
