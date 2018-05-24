package shayne.even.prisonerssandpit.tasks.prisoner;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.tasks.BaseAsyncTask;

/**
 * Created by Shayne Even on 13/05/2018.
 */

public class GetPrisonerAsyncTask extends BaseAsyncTask<Void, Void, Prisoner> {

    private OnGetPrisonerFinishedListener mListener;
    private long mId;

    public GetPrisonerAsyncTask(Context context, OnGetPrisonerFinishedListener listener, long id) {
        super(new WeakReference<>(context));
        mListener = listener;
        mId = id;
    }

    @Override
    protected Prisoner doInBackground(Void... voids) {
        Log.v("seven", "started get prisoner");
        return getTaskComponent().getAppDatabase().prisonerDao().getPrisoner(mId);
    }

    @Override
    protected void onPostExecute(Prisoner prisoner) {
        Log.v("seven", "end of get prisoner");
        super.onPostExecute(prisoner);
        mListener.onSuccess(prisoner);
    }
}
