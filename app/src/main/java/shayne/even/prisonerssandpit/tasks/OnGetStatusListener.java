package shayne.even.prisonerssandpit.tasks;

import android.arch.lifecycle.LiveData;

import shayne.even.prisonerssandpit.models.PrisonerStatus;

/**
 * Created by Shayne Even on 28/05/2018.
 */

public interface OnGetStatusListener {
    void onSuccess(LiveData<PrisonerStatus> prisonerStatus);
}
