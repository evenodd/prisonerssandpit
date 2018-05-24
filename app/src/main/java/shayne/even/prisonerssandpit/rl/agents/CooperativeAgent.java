package shayne.even.prisonerssandpit.rl.agents;

import shayne.even.prisonerssandpit.rl.episodes.PrisonerDilemmaEnvironmentState;
import shayne.even.prisonerssandpit.rl.episodes.PrisonersDilemma;

/**
 * Created by Shayne Even on 20/05/2018.
 */

public class CooperativeAgent implements PrisonersDilemma.Agent {
    @Override
    public int getAction(int state) {
        return PrisonersDilemma.STAY;
    }

    @Override
    public void onPreEpisode(PrisonerDilemmaEnvironmentState prisonerDilemmaEnvironmentState) {

    }

    @Override
    public void onPostEpisode(PrisonerDilemmaEnvironmentState prisonerDilemmaEnvironmentState) {

    }

    @Override
    public void onPreIteration(PrisonerDilemmaEnvironmentState prisonerDilemmaEnvironmentState) {

    }

    @Override
    public void onPostIteration(PrisonerDilemmaEnvironmentState prisonerDilemmaEnvironmentState,
                                int reward) {

    }
}
