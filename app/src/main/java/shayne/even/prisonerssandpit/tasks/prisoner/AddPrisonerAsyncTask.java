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
 * Creates a prisoner model it's QTable models and status model and inserts them into the database
 */

public class AddPrisonerAsyncTask extends BaseAsyncTask<Void, Void, Long> {

    private final String mName;
    private final double mAlpha;
    private final double mGamma;
    private OnAddPrisonerFinishedListener mListener;

    /**
     * Creates an AddPrisonerAsyncTask
     * @param name the name of prisoner to create
     * @param alpha the alpha constant of the prisoner to create
     * @param gamma the gamma constant of the prisoner to create
     * @param listener the listener of the async task
     * @param context the application's context
     */
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

    /**
     * Creates the Prisoner Model, its Q Table Models, status model and inserts them into the
     * database. It will also generate the newly created prisoner's performance scores
     * @param voids
     * @return the id of the new Prisoner
     */
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
