package shayne.even.prisonerssandpit.rl.agents;

import shayne.even.prisonerssandpit.rl.environments.EnvironmentState;
import shayne.even.prisonerssandpit.rl.environments.PrisonersDilemma;

/**
 * Prisoner Agent that performs a static strategy of betraying for any given state
 */

public class BetrayingAgent implements PrisonersDilemma.Agent {
    /**
     * {@inheritDoc}
     * Always return a BETRAY action
     * @param state the state the PrisonersDilemma environment is in
     * @return
     */
    @Override
    public int getAction(int state) {
        return PrisonersDilemma.BETRAY;
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
