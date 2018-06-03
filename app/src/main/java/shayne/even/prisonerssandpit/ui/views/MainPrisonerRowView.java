package shayne.even.prisonerssandpit.ui.views;

/**
 * View for items in the main prisoner list
 */

public interface MainPrisonerRowView extends PrisonerRowView{
    /**
     * Opens the Home View for the specified prisoner
     * @param prisoner the id of the prisoner
     */
    void navigateToPrisonerHome(long prisoner);
}
