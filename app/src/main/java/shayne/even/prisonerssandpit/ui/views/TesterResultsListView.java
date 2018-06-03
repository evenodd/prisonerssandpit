package shayne.even.prisonerssandpit.ui.views;

/**
 * View for the list of tester results
 */

public interface TesterResultsListView {
    /**
     * Notifies the view that the list data has changed
     */
    void notifyDataChanged();

    /**
     * Listener that handles updating scores in the view
     */
    interface OnScoreUpdateListener {

        /**
         * Handler for when the prisoner's score changes
         * @param prisonerScore the new score
         */
        void onPrisonerScoreUpdate(Integer prisonerScore);

        /**
         * Handler for when the tester's score changes
         * @param testerScore the new score
         */
        void onTesterScoreUpdate(Integer testerScore);
    }
}
