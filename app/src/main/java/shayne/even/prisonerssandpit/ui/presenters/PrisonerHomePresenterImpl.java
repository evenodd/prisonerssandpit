package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.models.PrisonerPerformanceScore;
import shayne.even.prisonerssandpit.models.PrisonerStatus;
import shayne.even.prisonerssandpit.tasks.GetPrisonerStatusAsyncTask;
import shayne.even.prisonerssandpit.tasks.OnGetStatusListener;
import shayne.even.prisonerssandpit.tasks.performanceScore.GetPerformanceScoreAsyncTask;
import shayne.even.prisonerssandpit.tasks.performanceScore.OnGetPerformanceScoreListener;
import shayne.even.prisonerssandpit.tasks.prisoner.GetPrisonerAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.OnGetPrisonerFinishedListener;
import shayne.even.prisonerssandpit.ui.views.PrisonerHomeView;

/**
 * Created by Shayne Even on 13/05/2018.
 */

public class PrisonerHomePresenterImpl implements PrisonerHomePresenter,
        OnGetPrisonerFinishedListener,
        OnGetPerformanceScoreListener,
        OnGetStatusListener{

    private PrisonerHomeView mView;
    private Context mContext;
    private Prisoner mPrisoner;

    public PrisonerHomePresenterImpl(PrisonerHomeView view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void onSuccess(PrisonerStatus prisonerStatus) {
        mView.setStatus(prisonerStatus.getStatus());
    }

    @Override
    public void onSuccess(Prisoner prisoner) {
        mPrisoner = prisoner;
        mView.setName(prisoner.getName());
        getPerformanceScore(mPrisoner.getUid());
        getStatus(mPrisoner.getUid());
    }

    @Override
    public void onSuccess(PrisonerPerformanceScore prisonerPerformanceScore) {
        mView.setBetrayScore(Integer.toString(prisonerPerformanceScore.getBetrayScore()));
        mView.setCoopScore(Integer.toString(prisonerPerformanceScore.getCoopScore()));
        mView.setTitFirTatScore(Integer.toString(prisonerPerformanceScore.getTitForTatScore()));
    }

    @Override
    public void navigateToTrainerOptions() {
        mView.navigateToTrainerOptions();
    }

    @Override
    public void getPrisoner() {
        new GetPrisonerAsyncTask(mContext, this, mView.getPrisonerId()).execute();
    }

    private void getStatus(long prisonerId) {
        new GetPrisonerStatusAsyncTask(mContext, this, prisonerId).execute();
    }

    @Override
    public void getPerformanceScore(long prisonerId) {
        new GetPerformanceScoreAsyncTask(mContext, this, prisonerId).execute();
    }

    @Override
    public void navigateToPrisonerTester() {
        mView.startTesterSelectDialog(mView.getPrisonerId());
    }

    @Override
    public void handleTesterSelected(Prisoner tester) {
        mView.startTestingActivity(mView.getPrisonerId(), tester.getUid());
    }

    @Override
    public String toString() {
        return "PrisonerHomePresenterImpl{" +
                "mView=" + mView +
                ", mContext=" + mContext +
                ", mPrisoner=" + mPrisoner +
                '}';
    }
}
