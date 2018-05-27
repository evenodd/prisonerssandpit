package shayne.even.prisonerssandpit.rl.agents;

import android.content.Context;

import java.util.Random;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.episodes.EnvironmentState;
import shayne.even.prisonerssandpit.rl.episodes.PrisonersDilemma;

/**
 * Created by Shayne Even on 20/05/2018.
 */

public class QLearningPrisoner implements PrisonersDilemma.Agent {

    private final Context mContext;
    private final Prisoner mPrisoner;
    private final Random mRandom;
    private int currentState, currentAction;

    public QLearningPrisoner(Context context, Prisoner prisoner) {
        mContext = context;
        mPrisoner = prisoner;
        mRandom = new Random();
    }

    @Override
    public int getAction(int state) {
        return currentAction;
    }

    @Override
    public void onPreEpisode(EnvironmentState environmentState) {

    }

    @Override
    public void onPostEpisode(EnvironmentState environmentState) {
    }

    @Override
    public void onPreIteration(EnvironmentState environmentState) {
        currentState = environmentState.getState();
        currentAction = mRandom.nextInt(2);
    }

    @Override
    public void onPostIteration(EnvironmentState environmentState,
                                int reward) {
        mPrisoner.learn(
                currentState,
                environmentState.getState(),
                reward,
                currentAction,
                mContext
        );
    }
}
