package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;

import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.agents.PrisonerAgentHolder;
import shayne.even.prisonerssandpit.rl.episodes.AwaitingPrisonersDilemma;
import shayne.even.prisonerssandpit.rl.episodes.EnvironmentState;
import shayne.even.prisonerssandpit.rl.episodes.PrisonersDilemma;
import shayne.even.prisonerssandpit.tasks.prisoner.GetPrisonerAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.OnGetPrisonerFinishedListener;
import shayne.even.prisonerssandpit.ui.views.VsView;

/**
 * Created by Shayne Even on 2/06/2018.
 */

public class VsPresenterImpl implements VsPresenter, OnGetPrisonerFinishedListener, AwaitingPrisonersDilemma.IterationListener {

    private final Context mContext;
    private final VsView mView;
    private AwaitingPrisonersDilemma mPrisonersDilemma;
    private int mYourScore = 0, mOpponentsScore = 0, mRound = 1;

    public VsPresenterImpl(Context context, VsView view) {
        mContext = context;
        mView = view;
        mView.startRippleAnimation();
        getPrisoner();
    }

    private void getPrisoner() {
        new GetPrisonerAsyncTask(mContext, this, mView.getPrisonerId()).execute();
    }

    @Override
    public void onSuccess(Prisoner prisoner) {
        mView.setPrisonerName(prisoner.getName());
        mPrisonersDilemma = new AwaitingPrisonersDilemma(new PrisonerAgentHolder(prisoner, mContext){
            @Override
            public void onPostIteration(EnvironmentState environmentState, int reward) {
                super.onPostIteration(environmentState, reward);
                mOpponentsScore += reward;
                mView.setOpponentsScore(Integer.toString(mOpponentsScore));
            }
        });
    }

    @Override
    public void onCooperateAction() {
        onAction(PrisonersDilemma.STAY);
    }

    @Override
    public void onBetrayAction() {
        onAction(PrisonersDilemma.BETRAY);
    }

    @Override
    public void handleNextRoundButtonClicked() {
        mView.setRound(Integer.toString(++mRound));
        mView.setOpponentAction(mContext.getString(R.string.default_opponents_action_label));
        mView.hideNextRoundButton();
        mView.enableActionButtons();
    }

    private void onAction(int action) {
        if (mPrisonersDilemma != null && !mPrisonersDilemma.reachedEnd()) {
            mView.setOpponentAction("");
            mView.disableActionButtons();
            mView.setOpponentActionProgressBar();
            mPrisonersDilemma.setNextAction(action, this);
        }
    }

    @Override
    public void onPostIteration(EnvironmentState environmentState, int yourReward,
                                Integer opponentAction) {
        mYourScore += yourReward;
        mView.setYourScore(Integer.toString(mYourScore));
        String prisonerAction;
        if (opponentAction == PrisonersDilemma.BETRAY) {
            prisonerAction = mContext.getString(R.string.betray_result_action);
        }
        else {
            prisonerAction = mContext.getString(R.string.stay_result_action);
        }
        mView.hideOpponentActionProgressBar();
        mView.setOpponentAction(prisonerAction);

        if (!mPrisonersDilemma.reachedEnd()) mView.showNextRoundButton();
    }
}
