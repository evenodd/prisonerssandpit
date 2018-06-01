package shayne.even.prisonerssandpit.tasks;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import shayne.even.prisonerssandpit.models.PrisonerStatus;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerHomePresenterImpl;

/**
 * Created by Shayne Even on 28/05/2018.
 */

public class GetPrisonerStatusAsyncTask
        extends BaseAsyncTask<Void, Void, LiveData<PrisonerStatus>> {

    private final OnGetStatusListener mListener;
    private final long mPrisoner;

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
