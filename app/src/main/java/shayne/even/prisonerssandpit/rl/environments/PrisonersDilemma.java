package shayne.even.prisonerssandpit.rl.environments;

import android.util.Pair;

/**
 * An environment that performs episodes of iterative Prisoner Dilemmas.
 */

public class PrisonersDilemma implements EnvironmentState {
    public static final int ITERATIONS = 10;
    public static final int STAY = 0;
    public static final int BETRAY = 1;
    private static final int[][] REWARD_MATRIX = {
            {2,2},
            {0,5},
            {5,0},
            {1,1}
    };
    private int mState;
    private final int mStateCount;

    public PrisonersDilemma() {
        mStateCount = calculateNumberOfStates();
        mState = 0;
    }

    /**
     * Updates the environment's state based on the passed actions of its two agents
     * @param action the action of the environment's first agent
     * @param othersAction the action of the second agent
     */
    void updateState(int action, int othersAction) {
        mState = (mState * 4) + (action << 1) + othersAction + 1;
    }

    @Override
    public boolean reachedEnd() {
        return mState >= mStateCount;
    }

    private static int calculateNumberOfStates() {
        int stateCount = 0;

        for (int i = 0; i < ITERATIONS ; i++) {
            stateCount += Math.pow(4, i);
        }
        return stateCount;
    }

    @Override
    public int getState() {
        return mState;
    }

    Pair<Integer, Integer> getRewards() {
        if (mState == 0) return new Pair<>(0, 0);
        return new Pair<>(
                REWARD_MATRIX[(mState - 1) % 4][0],
                REWARD_MATRIX[(mState - 1) % 4][1]
        );
    }

    /**
     * Performs an episode of the prisoner's dilemma
     * @param firstAgent the first prisoner agent in the dilemma
     * @param secondAgent the second prisoner agent in the dilemma
     * @param listener a listener that gets called at the start and end of the episode and every
     *                 iteration
     */
    public void runEpisode(Agent firstAgent, Agent secondAgent, EpisodeListener listener) {
        firstAgent.onPreEpisode(this);
        secondAgent.onPreEpisode(this);
        if (listener != null) listener.onPreEpisode(this);
        while(!reachedEnd()) {
            firstAgent.onPreIteration( this);
            secondAgent.onPreIteration( this);
            if (listener != null) listener.onPreIteration( this);
            updateState(firstAgent.getAction(mState), secondAgent.getAction(mState));
            firstAgent.onPostIteration(this, getRewards().first);
            secondAgent.onPostIteration(this, getRewards().second);
            if (listener != null) listener.onPostIteration(this);
        }
        firstAgent.onPostEpisode(this);
        secondAgent.onPostEpisode(this);
        if (listener != null) listener.onPostEpisode(this);
    }

    /**
     * Returns the environment to its starting state
     */
    public void resetEpisode() {
        mState = 0;
    }

    /**
     * An agent that can participate and interact within a Prisoner Dilemma environment
     */
    public interface Agent extends EpisodeListener{
        /**
         * returns the action the agent would perform given the passed state
         * @param state the state the PrisonersDilemma environment is in
         * @return the action the agent would perform
         */
        int getAction(int state);

        /**
         * Callback function called at the end of an iteration of a dilemma
         * @param environmentState the state the Prisoner's Dilemma environment is in
         * @param reward the reward the agent received for it's action
         */
        void onPostIteration(EnvironmentState environmentState, int reward);
    }

    /**
     * An entity that that can listen to milestones during episodes of Prisoner Dilemmas
     */
    public interface EpisodeListener {
        /**
         * Callback function called at the start of an episode
         * @param environmentState the state the Prisoner's Dilemma environment is in
         */
        void onPreEpisode(EnvironmentState environmentState);

        /**
         * Callback function called at the end of an episode
         * @param environmentState the state the Prisoner's Dilemma environment is in
         */
        void onPostEpisode(EnvironmentState environmentState);

        /**
         * Callback function called at the start of an iteration of a dilemma
         * @param environmentState the state the Prisoner's Dilemma environment is in
         */
        void onPreIteration(EnvironmentState environmentState);

        /**
         * Callback function called at the end of an iteration of a dilemma
         * @param environmentState the state the Prisoner's Dilemma environment is in
         */
        void onPostIteration(EnvironmentState environmentState);

    }
}
