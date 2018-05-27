package shayne.even.prisonerssandpit.tasks.prisoner;

import java.util.ArrayList;

import shayne.even.prisonerssandpit.models.Prisoner;

/**
 * Created by Shayne Even on 14/05/2018.
 */

public interface OnGetPrisonersFinishedListener {

    void onSuccess(ArrayList<Prisoner> prisoners);

}
