package shayne.even.prisonerssandpit.tasks;

import android.arch.lifecycle.LiveData;

import shayne.even.prisonerssandpit.models.PrisonerStatus;

/**
 * Listener for a GetPrisonerStatusAsyncTask
 */

public interface OnGetStatusListener {
    /**
     * Callback function for the completion of the async task
     * @param prisonerStatus the results of the async task
     */
    void onSuccess(LiveData<PrisonerStatus> prisonerStatus);
}
