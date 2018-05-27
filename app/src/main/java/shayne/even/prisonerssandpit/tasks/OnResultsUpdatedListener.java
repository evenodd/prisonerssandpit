package shayne.even.prisonerssandpit.tasks;

import shayne.even.prisonerssandpit.rl.testers.TesterResult;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public interface OnResultsUpdatedListener {
    void onNextResultsUpdate(TesterResult testerResult);
}
