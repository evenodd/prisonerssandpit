package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.tasks.prisoner.GetPrisonerAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.OnGetPrisonerFinishedListener;
import shayne.even.prisonerssandpit.ui.views.TesterResultsListView;
import shayne.even.prisonerssandpit.ui.views.TestingView;

/**
 * Created by Shayne Even on 28/05/2018.
 */

public class TestingPresenterImpl implements TestingPresenter,
         TesterResultsListView.OnScoreUpdateListener {
    private final TestingView mView;
    private final Context mContext;

    public TestingPresenterImpl(Context context, TestingView view) {
        mContext = context;
        mView = view;

        new GetPrisonerAsyncTask(
                context,
                new OnGetPrisonerFinishedListener() {
                    @Override
                    public void onSuccess(Prisoner prisoner) {
                        mView.setPrisonerNameTitle(prisoner.getName());
                        mView.setPrisonerScoreHeading(prisoner.getName());
                    }
                },
                mView.getPrisonerExtra()
        ).execute();

        new GetPrisonerAsyncTask(
                context,
                new OnGetPrisonerFinishedListener() {
                    @Override
                    public void onSuccess(Prisoner prisoner) {
                        mView.setTesterNameTitle(prisoner.getName());
                        mView.setTesterScoreHeading(prisoner.getName());
                    }
                },
                mView.getTesterExtra()
        ).execute();
    }

    @Override
    public TesterResultsListView.OnScoreUpdateListener getScoreUpdaterListener() {
        return this;
    }

    @Override
    public void onPrisonerScoreUpdate(Integer prisonerScore) {
        mView.setPrisonerScore(prisonerScore.toString());
    }

    @Override
    public void onTesterScoreUpdate(Integer testerScore) {
        mView.setTesterScore(testerScore.toString());
    }
}
