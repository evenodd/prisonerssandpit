package shayne.even.prisonerssandpit.tasks.performanceScore;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.models.PrisonerPerformanceScore;
import shayne.even.prisonerssandpit.tasks.BaseAsyncTask;

/**
 * Created by Shayne Even on 13/05/2018.
 */

public class GetPerformanceScoreAsyncTask extends BaseAsyncTask<Void, Void, PrisonerPerformanceScore>{

    private final OnGetPerformanceScoreListener mListener;
    private final long mPrisonerId;

    public GetPerformanceScoreAsyncTask(Context context, OnGetPerformanceScoreListener listener,
                                        long prisonerId) {
        super(new WeakReference<>(context));
        mListener = listener;
        mPrisonerId = prisonerId;
    }

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
