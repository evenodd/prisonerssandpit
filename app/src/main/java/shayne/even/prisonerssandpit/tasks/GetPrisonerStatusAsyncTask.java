package shayne.even.prisonerssandpit.tasks;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import shayne.even.prisonerssandpit.models.PrisonerStatus;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerHomePresenterImpl;

/**
 * Queries the database for the status of the specified prisoner and returns the model within a
 * LiveData holder
 */

public class GetPrisonerStatusAsyncTask
        extends BaseAsyncTask<Void, Void, LiveData<PrisonerStatus>> {

    private final OnGetStatusListener mListener;
    private final long mPrisoner;

    /**
     * Creates a GetPrisonerStatusAsyncTask
     * @param context the context of the application
     * @param listener the entity that provides the callback function
     * @param prisonerId the id of the prisoner
     */
    public GetPrisonerStatusAsyncTask(Context context, OnGetStatusListener listener,
                                      long prisonerId) {
        super(new WeakReference<>(context));
        mListener = listener;
        mPrisoner = prisonerId;
    }

    @Override
    protected LiveData<PrisonerStatus> doInBackground(Void... voids) {
        return getTaskComponent().getAppDatabase().prisonerStatusDao().getPrisonerStatus(mPrisoner);
    }

    @Override
    protected void onPostExecute(LiveData<PrisonerStatus> prisonerStatus) {
        super.onPostExecute(prisonerStatus);
        mListener.onSuccess(prisonerStatus);
    }
}
