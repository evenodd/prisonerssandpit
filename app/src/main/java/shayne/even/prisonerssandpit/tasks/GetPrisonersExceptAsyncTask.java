package shayne.even.prisonerssandpit.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.tasks.prisoner.OnGetPrisonersFinishedListener;
import shayne.even.prisonerssandpit.ui.presenters.SelectPrisonerListPresenterImpl;

/**
 * Queries the database for a list of prisoners excluding the specified prisoner
 */

public class GetPrisonersExceptAsyncTask extends BaseAsyncTask<Void, Void, ArrayList<Prisoner>>  {
    private final OnGetPrisonersFinishedListener mListener;
    private final long mExcludedPrisoner;

    /**
     * Creates a GetPrisonersExceptAsyncTask
     * @param context context of the application
     * @param listener the entity that provides the callback function
     * @param excludedPrisoner the id of the prisoner to exclude from the list
     */
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
