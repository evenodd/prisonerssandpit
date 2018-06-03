package shayne.even.prisonerssandpit.rl.agents;

import android.content.Context;

import java.util.ArrayList;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.environments.EnvironmentState;
import shayne.even.prisonerssandpit.rl.environments.PrisonersDilemma;

/**
 * A wrapper for a Prisoner data model that lets it act as an Agent for an episode of a Prisoner's
 * Dilemma
 */

public class PrisonerAgentHolder implements PrisonersDilemma.Agent {

    private final Prisoner mPrisoner;
    private final Context mContext;
    private ArrayList<Integer> rewards;
    private int mLastAction;

    /**
     * Creates a PrisonerAgentHolder
     * @param prisoner the prisoner agent that supplies actions
     * @param context the application context to use to get actions from the database
     */
    public PrisonerAgentHolder(Prisoner prisoner, Context context) {
        mPrisoner = prisoner;
        mContext = context;
    }

    /**
     * {@inheritDoc}
     * Gets an action based on the model within the prisoner's Q Table
     * @param state the state the PrisonersDilemma environment is in
     * @return
     */
    @Override
    public int getAction(int state) {
        mLastAction = mPrisoner.getAction(mContext, state);
        return mLastAction;
    }

    protected int getLastAction() {
        return mLastAction;
    }

    @Override
    public void onPreEpisode(EnvironmentState environmentState) {
        rewards = new ArrayList<>();
    }

    @Override
    public void onPostEpisode(EnvironmentState environmentState) {

    }

    @Override
    public void onPreIteration(EnvironmentState prisonerDilemmaEpisodeState) {

    }

    @Override
    public void onPostIteration(EnvironmentState environmentState) {

    }

    @Override
    public void onPostIteration(EnvironmentState environmentState,
                                int reward) {
        rewards.add(reward);
    }

    public int getScore() {
        int sum = 0;
        for (int reward : rewards) {
            sum += reward;
        }
        return sum;
    }
}
