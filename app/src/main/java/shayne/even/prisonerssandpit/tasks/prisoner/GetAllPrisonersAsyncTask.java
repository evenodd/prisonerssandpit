package shayne.even.prisonerssandpit.tasks.prisoner;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.tasks.BaseAsyncTask;

/**
 * Queries the database for all prisoners within an async task
 */

public class GetAllPrisonersAsyncTask extends BaseAsyncTask<Void, Void, ArrayList<Prisoner>> {

    private OnGetPrisonersFinishedListener mListener;

    public GetAllPrisonersAsyncTask(Context context,
                             OnGetPrisonersFinishedListener listener) {
        super(new WeakReference<>(context));
        mListener = listener;
    }

    @Override
    protected ArrayList<Prisoner> doInBackground(Void... voids) {
        return (ArrayList<Prisoner>) getTaskComponent().getAppDatabase().prisonerDao().getAll();
    }

    @Override
    protected void onPostExecute(ArrayList<Prisoner> prisoners) {
        super.onPostExecute(prisoners);
        mListener.onSuccess(prisoners);
    }
}
