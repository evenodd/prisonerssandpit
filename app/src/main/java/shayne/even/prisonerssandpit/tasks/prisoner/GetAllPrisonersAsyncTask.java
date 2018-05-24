package shayne.even.prisonerssandpit.tasks.prisoner;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.tasks.BaseAsyncTask;

/**
 * Created by Shayne Even on 14/05/2018.
 */

public class GetAllPrisonersAsyncTask extends BaseAsyncTask<Void, Void, ArrayList<Prisoner>> {

    private OnGetAllPrisonerFinishedListener mListener;

    public GetAllPrisonersAsyncTask(Context context,
                             OnGetAllPrisonerFinishedListener listener) {
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
