package shayne.even.prisonerssandpit.ui.presenters;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shayne.even.prisonerssandpit.R;
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
        OnGetStatusListener, PrisonerSelectPresenter.OnSelectListener {

    private PrisonerHomeView mView;
    private Context mContext;
    private Prisoner mPrisoner;


    public PrisonerHomePresenterImpl(PrisonerHomeView view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void onSuccess(LiveData<PrisonerStatus> prisonerStatus) {
        prisonerStatus.observeForever( new Observer<PrisonerStatus>() {
            @Override
            public void onChanged(@Nullable PrisonerStatus prisonerStatus) {
                if (prisonerStatus != null) mView.setStatus(prisonerStatus.getStatus());
            }
        });
    }

    @Override
    public void onSuccess(Prisoner prisoner) {
        mPrisoner = prisoner;
        mView.setName(prisoner.getName());
        mView.setAlphaText(Double.toString(prisoner.getAlpha()));
        mView.setGammaText(Double.toString(prisoner.getGamma()));
        getPerformanceScore(mPrisoner.getUid());
        getStatus(mPrisoner.getUid());
    }

    @Override
    public void onSuccess(PrisonerPerformanceScore prisonerPerformanceScore) {
        List<BarEntry>
                coopEntry  = new ArrayList<>(),
                betrayEntry  = new ArrayList<>(),
                titForTatEntry = new ArrayList<>();

        coopEntry.add(new BarEntry(2, prisonerPerformanceScore.getCoopScore()));
        betrayEntry.add(new BarEntry(1, prisonerPerformanceScore.getBetrayScore()));
        titForTatEntry.add(new BarEntry(0, prisonerPerformanceScore.getTitForTatScore()));


        mView.setPerformanceChartData(Arrays.asList("Tit for Tat", "Betray", "Coop"),
                new BarDataSet(coopEntry, "Coop"),
                new BarDataSet(betrayEntry, "Betray"),
                new BarDataSet(titForTatEntry, "Tit for Tat")
        );
    }

    @Override
    public void navigateToTrainerOptions() {
        mView.navigateToTrainerOptions();
    }

    @Override
    public void getPrisoner() {
        new GetPrisonerAsyncTask(mContext, this, mView.getPrisonerId()).execute();
    }

    public void getStatus(long prisonerId) {
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
    public PrisonerSelectPresenter.OnSelectListener getOnSelectListener() {
        return this;
    }

    @Override
    public String toString() {
        return "PrisonerHomePresenterImpl{" +
                "mView=" + mView +
                ", mContext=" + mContext +
                ", mPrisoner=" + mPrisoner +
                '}';
    }

    @Override
    public void onSelect(Prisoner prisoner) {
        mView.startTestingActivity(mView.getPrisonerId(), prisoner.getUid());
    }
}
