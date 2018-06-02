package shayne.even.prisonerssandpit.rl.episodes;

import android.os.AsyncTask;

/**
 * Created by Shayne Even on 2/06/2018.
 */

public class AwaitingPrisonersDilemma extends PrisonersDilemma {

    private final Agent mOpponent;

    public AwaitingPrisonersDilemma(Agent opponent) {
        mOpponent = opponent;
        mOpponent.onPreEpisode(this);
    }

    public void setNextAction(final int action, final IterationListener listener) {
        mOpponent.onPreIteration(this);
        new GetOpponentActionAsyncTask(mOpponent, getState(), new GetActionListener() {
            @Override
            public void onSuccess(Integer opponentAction) {
                updateState(action, opponentAction);
                mOpponent.onPostIteration(
                        AwaitingPrisonersDilemma.this,
                        getRewards().second
                );
                listener.onPostIteration(
                        AwaitingPrisonersDilemma.this,
                        getRewards().first,
                        opponentAction
                );
            }
        }).execute();
    }


    private static class GetOpponentActionAsyncTask
            extends AsyncTask<Void, Void, Integer> {

        private final Agent mOpponent;

        private final int mState;
        private final GetActionListener mListener;
        GetOpponentActionAsyncTask(Agent opponent, int state, GetActionListener listener) {
            mOpponent = opponent;
            mState = state;
            mListener =listener;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return mOpponent.getAction(mState);
        }

        @Override
        protected void onPostExecute(Integer integer){
            mListener.onSuccess(integer);
        }

    }

    public interface IterationListener {
        void onPostIteration(EnvironmentState environmentState, int reward, Integer opponentAction);
    }

    private interface GetActionListener {
        void onSuccess(Integer integer);
    }
}
