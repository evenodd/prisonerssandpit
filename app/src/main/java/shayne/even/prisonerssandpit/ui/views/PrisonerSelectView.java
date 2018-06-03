package shayne.even.prisonerssandpit.ui.views;

/**
 * View for prisoner select dialog
 */

public interface PrisonerSelectView {

    /**
     * Provides the prisoner to be excluded from the list
     * @return the id of the excluded prisoner
     */
    long getExcludePrisonerId();

    /**
     * Populates the list with prisoners excluding the specified prisoner
     * @param excludedPrisoner teh id of the prisoner to exclude
     */
    void populatePrisonersList(long excludedPrisoner);

    /**
     * Closes the dialog
     */
    void closeDialog();
}
