package shayne.even.prisonerssandpit.rl.trainers;

import android.content.Context;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.agents.QLearningPrisoner;
import shayne.even.prisonerssandpit.rl.episodes.PrisonersDilemma;

/**
 * Created by Shayne Even on 2/05/2018.
 */

public class Trainer {

    private PrisonersDilemma.Agent mTrainerAgent;

    public Trainer(PrisonersDilemma.Agent trainerAgent) {
        mTrainerAgent = trainerAgent;
    }

    public void trainPrisoner(Prisoner prisoner, Context context, int episodes) {
        PrisonersDilemma prisonersDilemma = new PrisonersDilemma();
        QLearningPrisoner learningPrisoner = new QLearningPrisoner(context, prisoner);

        for (int i = 0; i < episodes; i++) {
            prisonersDilemma.runEpisode(learningPrisoner,mTrainerAgent, null);
            prisonersDilemma.resetEpisode();
        }

        prisoner.saveQTable(context);
    }

}
