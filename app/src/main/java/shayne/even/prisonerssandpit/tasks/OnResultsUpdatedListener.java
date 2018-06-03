package shayne.even.prisonerssandpit.tasks;

import shayne.even.prisonerssandpit.rl.testers.TesterResult;

/**
 * Listener for a PrisonerTestingAsyncTask
 */

public interface OnResultsUpdatedListener {
    /**
     * Callback function that provides the results for each iteration
     * @param testerResult the iteration's results
     */
    void onNextResultsUpdate(TesterResult testerResult);
}
