package shayne.even.prisonerssandpit.tasks.performanceScore;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.models.PrisonerPerformanceScore;
import shayne.even.prisonerssandpit.tasks.BaseAsyncTask;

/**
 * Retrieves the performance score of a prisoner from the database as an async task
 */

public class GetPerformanceScoreAsyncTask extends BaseAsyncTask<Void, Void, PrisonerPerformanceScore>{

    private final OnGetPerformanceScoreListener mListener;
    private final long mPrisonerId;

    /**
     * Creates a GetPerformanceScoreAsyncTask
     * @param context context of the application
     * @param listener listener to handle callback on post execution
     * @param prisonerId the id of the prisoner model that the score is of
     */
    public GetPerformanceScoreAsyncTask(Context context, OnGetPerformanceScoreListener listener,
                                        long prisonerId) {
        super(new WeakReference<>(context));
        mListener = listener;
        mPrisonerId = prisonerId;
    }

    /**
     * Queries the database for the prisoner's performance scores
     * @param voids
     * @return the performance scores for the prisoner
     */
    @Override
    protected PrisonerPerformanceScore doInBackground(Void... voids) {
        Log.i("seven", this.toString());
        return getTaskComponent()
                .getAppDatabase()
                .prisonerPerformanceScoreDao()
                .getPrisonersScore(mPrisonerId);
    }

    @Override
    protected void onPostExecute(PrisonerPerformanceScore prisonerPerformanceScore) {
        super.onPostExecute(prisonerPerformanceScore);
        mListener.onSuccess(prisonerPerformanceScore);
    }

    @Override
    public String toString() {
        return "GetPerformanceScoreAsyncTask{" +
                "mContext=" + mContext +
                ", mListener=" + mListener +
                ", mPrisonerId=" + mPrisonerId +
                '}';
    }
}
