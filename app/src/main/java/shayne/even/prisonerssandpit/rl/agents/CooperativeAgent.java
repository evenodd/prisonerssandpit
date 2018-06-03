package shayne.even.prisonerssandpit.rl.agents;

import shayne.even.prisonerssandpit.rl.environments.EnvironmentState;
import shayne.even.prisonerssandpit.rl.environments.PrisonersDilemma;

/**
 * Prisoner Agent that performs a static strategy of staying for any given state
 */

public class CooperativeAgent implements PrisonersDilemma.Agent {
    /**
     * {@inheritDoc}
     * Always returns the action STAY
     * @param state the state the PrisonersDilemma environment is in
     * @return
     */
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
    public void onPostIteration(EnvironmentState environmentState) {

    }

    @Override
    public void onPostIteration(EnvironmentState environmentState,
                                int reward) {

    }
}
