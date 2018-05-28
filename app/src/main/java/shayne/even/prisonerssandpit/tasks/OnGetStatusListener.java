package shayne.even.prisonerssandpit.tasks;

import shayne.even.prisonerssandpit.models.PrisonerStatus;

/**
 * Created by Shayne Even on 28/05/2018.
 */

public interface OnGetStatusListener {
    void onSuccess(PrisonerStatus prisonerStatus);
}
