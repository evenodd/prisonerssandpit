package shayne.even.prisonerssandpit.tasks.prisoner;

import shayne.even.prisonerssandpit.models.Prisoner;

/**
 * Created by Shayne Even on 13/05/2018.
 */

public interface OnGetPrisonerFinishedListener {
    void onSuccess(Prisoner prisoner);
}
