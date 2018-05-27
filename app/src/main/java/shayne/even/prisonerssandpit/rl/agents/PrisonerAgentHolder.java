package shayne.even.prisonerssandpit.rl.agents;

import android.content.Context;

import java.util.ArrayList;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.episodes.EnvironmentState;
import shayne.even.prisonerssandpit.rl.episodes.PrisonersDilemma;

/**
 * Created by Shayne Even on 20/05/2018.
 */

public class PrisonerAgentHolder implements PrisonersDilemma.Agent {

    private final Prisoner mPrisoner;
    private final Context mContext;
    private ArrayList<Integer> rewards;

    public PrisonerAgentHolder(Prisoner prisoner, Context context) {
        mPrisoner = prisoner;
        mContext = context;
    }

    @Override
    public int getAction(int state) {
        return mPrisoner.getAction(mContext, state);
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
