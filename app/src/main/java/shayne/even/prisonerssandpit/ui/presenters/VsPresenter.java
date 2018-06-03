package shayne.even.prisonerssandpit.ui.presenters;

/**
 * Presenter for the Vs View
 */

public interface VsPresenter {
    /**
     * Uses STAY as the user's action in the current iteration.
     */
    void onCooperateAction();

    /**
     * Uses BETRAY as the user's action in the current iteration.
     */
    void onBetrayAction();

    /**
     * Advances to the next iteration in the episode
     */
    void handleNextRoundButtonClicked();
}
