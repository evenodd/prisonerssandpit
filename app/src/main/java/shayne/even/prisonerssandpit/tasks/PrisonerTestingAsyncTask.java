package shayne.even.prisonerssandpit.tasks;

import android.content.Context;

import java.lang.ref.WeakReference;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.agents.PrisonerAgentHolder;
import shayne.even.prisonerssandpit.rl.environments.EnvironmentState;
import shayne.even.prisonerssandpit.rl.environments.PrisonersDilemma;
import shayne.even.prisonerssandpit.rl.testers.TesterResult;

/**
 * Tests a prisoner against another prisoner agent providing updates for every iteration
 */

public class PrisonerTestingAsyncTask extends BaseAsyncTask<Void, TesterResult, Void>{

    private final Prisoner mPrisoner, mTester;
    private OnResultsUpdatedListener mListener;

    /**
     * Creates a PrisonerTestingAsyncTask
     * @param context the context of the application
     * @param prisoner the prisoner to be tested
     * @param tester the prisoner to oppose the tested prisoner
     * @param listener the listener to provide callback function for each iteration's results
     */
    public PrisonerTestingAsyncTask(Context context, Prisoner prisoner, Prisoner tester,
                                    OnResultsUpdatedListener listener) {
        super(new WeakReference<>(context));
        mPrisoner = prisoner;
        mTester = tester;
        mListener = listener;
    }

    /**
     * Performs an episode of a prisoner's dilemma creating a Tester Result for each iteration that
     * id passed over on progress updates
     * @param voids
     * @return null
     */
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

    /**
     * Notifies the listener that the last iteration's results are available
     * @param values
     */
    @Override
    protected void onProgressUpdate(TesterResult... values) {
        super.onProgressUpdate(values);
        mListener.onNextResultsUpdate(values[0]);

    }
}
