package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.tasks.prisoner.GetPrisonerAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.OnGetPrisonerFinishedListener;
import shayne.even.prisonerssandpit.ui.views.TesterResultsListView;
import shayne.even.prisonerssandpit.ui.views.TestingView;

/**
 * Implementation of the TestingPresenter
 */

public class TestingPresenterImpl implements TestingPresenter,
         TesterResultsListView.OnScoreUpdateListener {
    private final TestingView mView;
    private final Context mContext;

    /**
     * Creates a Testing Presenter, starting two async tasks to retrieve the models of the prisoner
     * and tester
     * @param context the context of the view
     * @param view the view the presenter is presenting
     */
    public TestingPresenterImpl(Context context, TestingView view) {
        mContext = context;
        mView = view;

        new GetPrisonerAsyncTask(
                context,
                new OnGetPrisonerFinishedListener() {
                    /**
                     * {@inheritDoc}
                     * Displays the name of the prisoner in the view
                     * @param prisoner the results of the async task
                     */
                    @Override
                    public void onSuccess(Prisoner prisoner) {
                        mView.setPrisonerNameTitle(prisoner.getName());
                        mView.setPrisonerScoreHeading(prisoner.getName());
                    }
                },
                mView.getPrisonerId()
        ).execute();

        new GetPrisonerAsyncTask(
                context,
                new OnGetPrisonerFinishedListener() {
                    /**
                     * {@inheritDoc}
                     * Displays the name of the tester in the view
                     * @param prisoner the results of the async task
                     */
                    @Override
                    public void onSuccess(Prisoner prisoner) {
                        mView.setTesterNameTitle(prisoner.getName());
                        mView.setTesterScoreHeading(prisoner.getName());
                    }
                },
                mView.getTesterId()
        ).execute();
    }

    @Override
    public TesterResultsListView.OnScoreUpdateListener getScoreUpdaterListener() {
        return this;
    }

    /**
     * {@inheritDoc}
     * Updates the new prisoner score on the view
     * @param prisonerScore
     */
    @Override
    public void onPrisonerScoreUpdate(Integer prisonerScore) {
        mView.setPrisonerScore(prisonerScore.toString());
    }

    /**
     * {@inheritDoc}
     * Updates the new tester score in the view
     * @param testerScore
     */
    @Override
    public void onTesterScoreUpdate(Integer testerScore) {
        mView.setTesterScore(testerScore.toString());
    }
}
