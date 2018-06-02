package shayne.even.prisonerssandpit.rl.episodes;

import android.arch.persistence.room.Entity;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

/**
 * Created by Shayne Even on 2/05/2018.
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

    protected void updateState(int action, int othersAction) {
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

    protected Pair<Integer, Integer> getRewards() {
        if (mState == 0) return new Pair<>(0, 0);
        return new Pair<>(
                REWARD_MATRIX[(mState - 1) % 4][0],
                REWARD_MATRIX[(mState - 1) % 4][1]
        );
    }

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

    public void resetEpisode() {
        mState = 0;
    }

    public interface Agent {
        int getAction(int state);

        void onPreEpisode(EnvironmentState environmentState);
        void onPostEpisode(EnvironmentState environmentState);

        void onPreIteration(EnvironmentState environmentState);
        void onPostIteration(EnvironmentState environmentState, int reward);
    }

    public interface EpisodeListener {
        void onPreEpisode(EnvironmentState environmentState);
        void onPostEpisode(EnvironmentState environmentState);

        void onPreIteration(EnvironmentState environmentState);
        void onPostIteration(EnvironmentState environmentState);
    }
}
