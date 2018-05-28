package shayne.even.prisonerssandpit.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import shayne.even.prisonerssandpit.models.PrisonerStatus;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerHomePresenterImpl;

/**
 * Created by Shayne Even on 28/05/2018.
 */

public class GetPrisonerStatusAsyncTask extends BaseAsyncTask<Void, Void, PrisonerStatus> {

    private final OnGetStatusListener mListener;
    private final long mPrisoner;

    public GetPrisonerStatusAsyncTask(Context context, OnGetStatusListener listener,
                                      long prisonerId) {
        super(new WeakReference<>(context));
        mListener = listener;
        mPrisoner = prisonerId;
    }

    @Override
    protected PrisonerStatus doInBackground(Void... voids) {
        return getTaskComponent().getAppDatabase().prisonerStatusDao().getPrisonerStatus(mPrisoner);
    }

    @Override
    protected void onPostExecute(PrisonerStatus prisonerStatus) {
        super.onPostExecute(prisonerStatus);
        mListener.onSuccess(prisonerStatus);
    }
}
