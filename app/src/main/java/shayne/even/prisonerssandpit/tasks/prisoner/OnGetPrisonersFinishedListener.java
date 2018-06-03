package shayne.even.prisonerssandpit.tasks.prisoner;

import java.util.ArrayList;

import shayne.even.prisonerssandpit.models.Prisoner;

/**
 * Listener for a GetPrisonerAsyncTask
 */

public interface OnGetPrisonersFinishedListener {

    /**
     * Callback function for the completion of the async task
     * @param prisoners the results of the async task
     */
    void onSuccess(ArrayList<Prisoner> prisoners);

}
