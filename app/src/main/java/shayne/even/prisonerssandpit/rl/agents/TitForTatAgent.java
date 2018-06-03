package shayne.even.prisonerssandpit.rl.agents;

import java.util.Locale;

import shayne.even.prisonerssandpit.rl.environments.EnvironmentState;
import shayne.even.prisonerssandpit.rl.environments.PrisonersDilemma;

/**
 * Prisoner Agent that performs a static tit for tat strategy for any given state. This agent should
 * always be the second agent in the environment.
 */

public class TitForTatAgent implements PrisonersDilemma.Agent {
    /**
     * {@inheritDoc}
     * On the first iteration this method will always return a STAY action. For every other
     * iteration it will return the last action it's opponent performed
     * @param state the state the PrisonersDilemma environment is in
     * @return
     */
    @Override
    public int getAction(int state) {
        switch (state % 4) {
            case 0 : return PrisonersDilemma.BETRAY;
            case 1 : return PrisonersDilemma.STAY;
            case 2 : return PrisonersDilemma.STAY;
            case 3 : return  PrisonersDilemma.BETRAY;
        }
        throw new IllegalArgumentException(
                String.format(Locale.ENGLISH, "Invalid state %d", state)
        );
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
