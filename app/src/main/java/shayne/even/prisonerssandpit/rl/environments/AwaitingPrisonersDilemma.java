package shayne.even.prisonerssandpit.rl.environments;

import android.os.AsyncTask;

/**
 * A Prisoner Dilemma Environment that requires on of the agent's actions to be passed into the
 * object one by one.
 */

public class AwaitingPrisonersDilemma extends PrisonersDilemma {

    private final Agent mOpponent;

    /**
     * Creates an AwaitingPrisonersDilemma
     * @param opponent the opponent agent to compete against
     */
    public AwaitingPrisonersDilemma(Agent opponent) {
        mOpponent = opponent;
        mOpponent.onPreEpisode(this);
    }

    /**
     * Sets the agent's next action in the episode
     * @param action the agent's action Should either be PrisonersDilemma.STAY or
     *               PrisonersDilemma.BETRAY
     * @param listener to be notified of the end of the current iteration
     */
    public void setNextAction(final int action, final IterationListener listener) {
        mOpponent.onPreIteration(this);
        new GetOpponentActionAsyncTask(mOpponent, getState(), new GetActionListener() {
            /**
             * {@inheritDoc}
             * Updates the environment to the next state and notifies the listener the iteration
             * has completed
             * @param opponentAction
             */
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


    /**
     * Queries for the opponent's action within an async task
     */
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

    /**
     * A listener for the environment's iterations.
     */
    public interface IterationListener {
        /**
         * Handler for the end of an iteration
         * @param environmentState the current state of the environment
         * @param reward the reward the agent received for it's last action
         * @param opponentAction the action the agent's opponent performed
         */
        void onPostIteration(EnvironmentState environmentState, int reward, Integer opponentAction);
    }

    /**
     * A listener for the opponents actions
     */
    private interface GetActionListener {
        /**
         * Handler for when the opponent's action is received from the database
         * @param action the opponent's action
         */
        void onSuccess(Integer action);
    }
}
