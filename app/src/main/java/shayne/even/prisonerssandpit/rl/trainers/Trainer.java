package shayne.even.prisonerssandpit.rl.trainers;

import android.content.Context;
import android.util.Log;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.agents.QLearningPrisoner;
import shayne.even.prisonerssandpit.rl.environments.PrisonersDilemma;

/**
 * Trainer class trains a Prisoner agent against another agent
 */

public class Trainer {

    private static final String TAG = "seven";
    private PrisonersDilemma.Agent mTrainerAgent;

    /**
     * Creates a Trainer
     * @param trainerAgent the agent to train the prisoner against
     */
    public Trainer(PrisonersDilemma.Agent trainerAgent) {
        mTrainerAgent = trainerAgent;
    }

    /**
     * Trains a prisoner for the specified number of episodes
     * @param prisoner the agent to train
     * @param context the application's context
     * @param episodes the number of episodes to train the prisoner for
     */
    public void trainPrisoner(Prisoner prisoner, Context context, int episodes) {
        PrisonersDilemma prisonersDilemma = new PrisonersDilemma();
        QLearningPrisoner learningPrisoner = new QLearningPrisoner(context, prisoner);

        for (int i = 0; i < episodes; i++) {
            prisonersDilemma.runEpisode(learningPrisoner, mTrainerAgent, null);
            prisonersDilemma.resetEpisode();
            Log.v(TAG, "Completed episode: " + i);
        }
        if (episodes > 0) learningPrisoner.save();
    }

}
