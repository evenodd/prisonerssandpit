package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.environments.PrisonersDilemma;
import shayne.even.prisonerssandpit.rl.testers.TesterResult;
import shayne.even.prisonerssandpit.tasks.OnResultsUpdatedListener;
import shayne.even.prisonerssandpit.tasks.PrisonerTestingAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.GetPrisonerAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.OnGetPrisonerFinishedListener;
import shayne.even.prisonerssandpit.ui.views.TesterResultItemView;
import shayne.even.prisonerssandpit.ui.views.TesterResultsListView;

/**
 * Implementation of the TesterResultsPresenter
 */

public class TesterResultsPresenterImpl implements TesterResultsPresenter,
        OnResultsUpdatedListener {
    private final Context mContext;
    private final TesterResultsListView mView;
    private ArrayList<TesterResult> mTesterResults;
    private Prisoner mPrisoner, mTester;
    private AsyncTask mGetPrisonerTask, mGetTesterTask;
    private final TesterResultsListView.OnScoreUpdateListener mListener;

    private int prisonerScore = 0, testerScore= 0;

    /**
     * Creates a TesterResultsPresenterImpl
     * @param context the context of the view
     * @param view the view the presenter is for
     * @param prisoner the prisoner to be tested
     * @param tester the prisoner to be tested against
     * @param listener handles updates in the scores within the test results
     */
    public TesterResultsPresenterImpl(Context context, TesterResultsListView view, long prisoner,
                                      long tester,
                                      TesterResultsListView.OnScoreUpdateListener listener) {
        mContext = context;
        mView = view;
        mTesterResults = new ArrayList<>();
        mListener = listener;
        getPrisoner(prisoner);
        getTester(tester);
    }

    @Override
    public void bindViewHolder(TesterResultItemView view, int position) {
        TesterResult testerResult = mTesterResults.get(position);
        String prisonerAction = "", testerAction = "";
        if(testerResult.getPrisonerAction() == null || testerResult.getTesterAction() == null) {
            view.displayProgress();
        }
        else {
            view.hideProgress();

            if (testerResult.getPrisonerAction() == PrisonersDilemma.STAY) {
                prisonerAction = mContext.getString(R.string.stay_result_action);
            }
            else {
                prisonerAction = mContext.getString(R.string.betray_result_action);
            }

            if (testerResult.getTesterAction() == PrisonersDilemma.STAY) {
                testerAction = mContext.getString(R.string.stay_result_action);
            }
            else {
                testerAction = mContext.getString(R.string.betray_result_action);
            }
        }
        view.setPrisonerAction(prisonerAction);
        view.setTesterAction(testerAction);
    }

    @Override
    public int getItemCount() {
        return mTesterResults.size();
    }

    private void getPrisoner(long prisoner) {
        mGetPrisonerTask = new GetPrisonerAsyncTask(
                mContext,
                new OnGetPrisonerFinishedListener() {
                    @Override
                    public void onSuccess(Prisoner prisoner) {
                        mPrisoner = prisoner;
                        if (mGetTesterTask != null &&
                                mGetTesterTask.getStatus() == AsyncTask.Status.FINISHED) {
                            testPrisoner();
                        }
                    }
                },
                prisoner
        ).execute();
    }

    private void getTester(long tester) {
        mGetTesterTask = new GetPrisonerAsyncTask(
                mContext,
                new OnGetPrisonerFinishedListener() {
                    @Override
                    public void onSuccess(Prisoner prisoner) {
                        mTester = prisoner;
                        if (mGetPrisonerTask != null &&
                                mGetPrisonerTask.getStatus() == AsyncTask.Status.FINISHED) {
                            testPrisoner();
                        }
                    }
                },
                tester
        ).execute();
    }

    private void testPrisoner() {
        mTesterResults.add(new TesterResult());
        mView.notifyDataChanged();
        new PrisonerTestingAsyncTask(mContext, mPrisoner, mTester, this).execute();
    }

    /**
     * {@inheritDoc}
     * Adds the results to the list, notifies the view the data has changes and notifies the
     * listener that the scores have changed
     * @param testerResult the iteration's results
     */
    @Override
    public void onNextResultsUpdate(TesterResult testerResult) {
        mTesterResults.set(getItemCount() - 1, testerResult);
        if (getItemCount() < PrisonersDilemma.ITERATIONS) mTesterResults.add(new TesterResult());
        mView.notifyDataChanged();

        prisonerScore += testerResult.getPrisonerScore();
        testerScore += testerResult.getTesterScore();

        mListener.onPrisonerScoreUpdate(prisonerScore);
        mListener.onTesterScoreUpdate(testerScore);
    }
}
