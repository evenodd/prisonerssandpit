package shayne.even.prisonerssandpit.tasks.performanceScore;

import shayne.even.prisonerssandpit.models.PrisonerPerformanceScore;

/**
 * Listener for OnGetPerformanceScoreAsyncTask
 */

public interface OnGetPerformanceScoreListener {
    /**
     * Callback function for when the async task has completed
     * @param prisonerPerformanceScore results of the async task
     */
    void onSuccess(PrisonerPerformanceScore prisonerPerformanceScore);
}
