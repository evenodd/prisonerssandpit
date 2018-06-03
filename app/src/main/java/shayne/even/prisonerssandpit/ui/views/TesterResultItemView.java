package shayne.even.prisonerssandpit.ui.views;

/**
 * View for an item in a test results list
 */

public interface TesterResultItemView {
    /**
     * Displays the progress bars
     */
    void displayProgress();

    /**
     * Hides the progress bars
     */
    void hideProgress();

    /**
     * Displays the passed action as the prisoner's action
     * @param action the action to display
     */
    void setPrisonerAction(String action);

    /**
     * Displays the passed action as the tester's action
     * @param action the action to display
     */
    void setTesterAction(String action);
}
