package shayne.even.prisonerssandpit.ui.views;

/**
 * User interface that displays all the prisoner agents and lets users navigate to the Add Prisoner
 * View or each prisoner's Home View
 */

public interface MainView {
    /**
     * Displays a prompt explaining the functionality of the add prisoner button
     */
    void showAddPrisonerTapTarget();

    /**
     * Determines if the passed result code is ok
     * @param resultCode the code to check
     * @return true if the result code is ok
     */
    boolean okResultCode(int resultCode);

    /**
     * Opens the Add Prisoner View
     */
    void navigateToAddPrisonerView();

    /**
     * Appends the specified prisoner to list
     * @param id the id of the prisoner to append
     */
    void addPrisonerToList(long id);

    /**
     * Display add prisoner error message
     */
    void displayAddPrisonerError();
}
