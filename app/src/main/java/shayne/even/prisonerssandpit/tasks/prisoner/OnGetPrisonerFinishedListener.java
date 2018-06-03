package shayne.even.prisonerssandpit.tasks.prisoner;

import shayne.even.prisonerssandpit.models.Prisoner;

/**
 * Listener for an OnGetPrisonerAsyncTask
 */

public interface OnGetPrisonerFinishedListener {
    /**
     * Callback function for the completion of the async task
     * @param prisoner the results of the async task
     */
    void onSuccess(Prisoner prisoner);
}
