package shayne.even.prisonerssandpit.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.tasks.prisoner.OnGetPrisonersFinishedListener;
import shayne.even.prisonerssandpit.ui.presenters.SelectPrisonerListPresenterImpl;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public class GetPrisonersExceptAsyncTask extends BaseAsyncTask<Void, Void, ArrayList<Prisoner>>  {
    private final OnGetPrisonersFinishedListener mListener;
    private final long mExcludedPrisoner;

    public GetPrisonersExceptAsyncTask(Context context, OnGetPrisonersFinishedListener listener,
                                       long excludedPrisoner) {
        super(new WeakReference<>(context));
        mListener = listener;
        mExcludedPrisoner = excludedPrisoner;
    }

    @Override
    protected ArrayList<Prisoner> doInBackground(Void... voids) {
        return (ArrayList<Prisoner>) getTaskComponent()
                .getAppDatabase()
                .prisonerDao()
                .getWherePrisonerIsNot(mExcludedPrisoner);
    }

    @Override
    protected void onPostExecute(ArrayList<Prisoner> prisoners) {
        super.onPostExecute(prisoners);
        mListener.onSuccess(prisoners);
    }
}
