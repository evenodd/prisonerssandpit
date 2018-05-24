package shayne.even.prisonerssandpit.rl.episodes;

import android.util.Pair;

/**
 * Created by Shayne Even on 20/05/2018.
 */

public interface PrisonerDilemmaEnvironmentState {


    boolean reachedEnd();

    int getState();
}
