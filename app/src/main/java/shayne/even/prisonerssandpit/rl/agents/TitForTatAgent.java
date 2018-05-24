package shayne.even.prisonerssandpit.rl.agents;

import java.util.Locale;

import shayne.even.prisonerssandpit.rl.episodes.PrisonerDilemmaEnvironmentState;
import shayne.even.prisonerssandpit.rl.episodes.PrisonersDilemma;

/**
 * Created by Shayne Even on 20/05/2018.
 */

public class TitForTatAgent implements PrisonersDilemma.Agent {
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
