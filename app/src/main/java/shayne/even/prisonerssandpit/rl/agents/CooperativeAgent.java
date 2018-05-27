package shayne.even.prisonerssandpit.rl.agents;

import shayne.even.prisonerssandpit.rl.episodes.EnvironmentState;
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
    public void onPreEpisode(EnvironmentState environmentState) {

    }

    @Override
    public void onPostEpisode(EnvironmentState environmentState) {

    }

    @Override
    public void onPreIteration(EnvironmentState environmentState) {

    }

    @Override
    public void onPostIteration(EnvironmentState environmentState,
                                int reward) {

    }
}
