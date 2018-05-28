package shayne.even.prisonerssandpit.tasks.prisoner;

import android.content.Context;
import android.util.Log;

import java.lang.ref.WeakReference;

import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.models.PrisonerStatus;
import shayne.even.prisonerssandpit.models.QTable;
import shayne.even.prisonerssandpit.rl.testers.PrisonerTester;
import shayne.even.prisonerssandpit.tasks.BaseAsyncTask;

/**
 * Created by Shayne Even on 14/05/2018.
 */

public class AddPrisonerAsyncTask extends BaseAsyncTask<Void, Void, Long> {

    private final String mName;
    private final double mAlpha;
    private final double mGamma;
    private OnAddPrisonerFinishedListener mListener;

    public AddPrisonerAsyncTask(String name, double alpha, double gamma,
                                OnAddPrisonerFinishedListener listener,
                                WeakReference<Context> context) {
        super(context);
        mName = name;
        mAlpha = alpha;
        mGamma = gamma;
        mListener = listener;
        mContext = context;
    }

    @Override
    protected Long doInBackground(Void... voids) {
        Log.v("seven", "add prisoner started");
        long id = getTaskComponent().getAppDatabase().prisonerDao().insertPrisoner(
                new Prisoner(
                        mName,
                        QTable.createQTableWithRows(getTaskComponent().getAppDatabase()),
                        mAlpha,
                        mGamma
                )
        );

        getTaskComponent().getAppDatabase().prisonerStatusDao().insert(
                        new PrisonerStatus(id, mContext.get().getString(R.string.idle_status))
        );

        getTaskComponent().getAppDatabase().prisonerPerformanceScoreDao()
                .insertPrisonerPerformanceScore(
                        new PrisonerTester().generatePerformanceScores(
                                getTaskComponent().getAppDatabase().prisonerDao().getPrisoner(id),
                                mContext.get()
                        )
                );

        return id;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        Log.v("seven", "add prisoner done");
        super.onPostExecute(aLong);
        mListener.onSuccess(aLong);
    }
}
