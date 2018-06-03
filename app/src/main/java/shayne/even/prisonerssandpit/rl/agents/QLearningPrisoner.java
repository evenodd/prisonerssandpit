package shayne.even.prisonerssandpit.rl.agents;

import android.content.Context;

import java.util.Random;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.environments.EnvironmentState;
import shayne.even.prisonerssandpit.rl.environments.PrisonersDilemma;

/**
 * A wrapper for the Prison Model that can be used as an Agent in a prisoner Dilemma. The Agent
 * learn from the actions it will perform during episodes
 */

public class QLearningPrisoner implements PrisonersDilemma.Agent {

    private final Context mContext;
    private final Prisoner mPrisoner;
    private final Random mRandom;
    private int mCurrentState, mCurrentAction;

    public QLearningPrisoner(Context context, Prisoner prisoner) {
        mContext = context;
        mPrisoner = prisoner;
        mRandom = new Random();
    }

    /**
     * {@inheritDoc}
     * Returns a random action
     * @param state the state the PrisonersDilemma environment is in
     * @return
     */
    @Override
    public int getAction(int state) {
        return mCurrentAction;
    }

    @Override
    public void onPreEpisode(EnvironmentState environmentState) {

    }

    @Override
    public void onPostEpisode(EnvironmentState environmentState) {
    }

    @Override
    public void onPreIteration(EnvironmentState environmentState) {
        mCurrentState = environmentState.getState();
        mCurrentAction = mRandom.nextInt(2);
    }

    @Override
    public void onPostIteration(EnvironmentState environmentState) {

    }

    @Override
    public void onPostIteration(EnvironmentState environmentState,
                                int reward) {
        mPrisoner.learn(
                mCurrentState,
                environmentState.getState(),
                reward,
                mCurrentAction,
                mContext,
                environmentState.reachedEnd()
        );
    }

    public void save() {
        mPrisoner.saveQTable(mContext);
    }
}
