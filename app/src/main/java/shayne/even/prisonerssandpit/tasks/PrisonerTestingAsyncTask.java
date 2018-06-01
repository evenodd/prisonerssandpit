package shayne.even.prisonerssandpit.tasks;

import android.content.Context;
import android.util.Pair;

import java.lang.ref.WeakReference;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.agents.PrisonerAgentHolder;
import shayne.even.prisonerssandpit.rl.episodes.EnvironmentState;
import shayne.even.prisonerssandpit.rl.episodes.PrisonersDilemma;
import shayne.even.prisonerssandpit.rl.testers.TesterResult;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public class PrisonerTestingAsyncTask extends BaseAsyncTask<Void, TesterResult, Void>{

    private final Prisoner mPrisoner, mTester;
    private OnResultsUpdatedListener mListener;

    public PrisonerTestingAsyncTask(Context context, Prisoner prisoner, Prisoner tester,
                                    OnResultsUpdatedListener listener) {
        super(new WeakReference<>(context));
        mPrisoner = prisoner;
        mTester = tester;
        mListener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        PrisonersDilemma prisonersDilemma = new PrisonersDilemma();
        final TesterResult[] testerResult = {new TesterResult()};

        prisonersDilemma.runEpisode(
                new PrisonerAgentHolder(mPrisoner, mContext.get()) {
                    @Override
                    public void onPostIteration(EnvironmentState environmentState, int reward) {
                        super.onPostIteration(environmentState, reward);
                        testerResult[0].setPrisonerAction(getLastAction());
                        testerResult[0].addPrisonerScore(reward);
                    }
                },
                new PrisonerAgentHolder(mTester, mContext.get()) {
                    @Override
                    public void onPostIteration(EnvironmentState environmentState, int reward) {
                        super.onPostIteration(environmentState, reward);
                        testerResult[0].setTesterAction(getLastAction());
                        testerResult[0].addTesterScore(reward);
                        publishProgress(testerResult[0]);
                        testerResult[0] = new TesterResult();
                    }
                },
                null
        );
        return null;
    }

    @Override
    protected void onProgressUpdate(TesterResult... values) {
        super.onProgressUpdate(values);
        mListener.onNextResultsUpdate(values[0]);

    }
}
