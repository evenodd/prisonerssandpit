package shayne.even.prisonerssandpit.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import shayne.even.prisonerssandpit.PrisonersSandpitApp;
import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.di.component.DaggerTaskComponent;
import shayne.even.prisonerssandpit.di.component.TaskComponent;

/**
 * Base class for Async Tasks that query the database
 */

public abstract class BaseAsyncTask<Params, Progress, Results>
        extends AsyncTask<Params, Progress, Results>{
    @Inject
    AppDatabase mAppDatabase;

    private TaskComponent mTaskComponent;

    protected WeakReference<Context> mContext;

    public BaseAsyncTask(WeakReference<Context >context) {
        mContext = context;
    }

    /**
     * Provides the component that contains async task dependencies
     * @return the Dagger component for async tasks
     */
    protected TaskComponent getTaskComponent() {
        if (mTaskComponent == null) {
            mTaskComponent = DaggerTaskComponent.builder()
                    .applicationComponent(PrisonersSandpitApp.get(mContext.get()).getComponent())
                    .build();
        }
        return mTaskComponent;
    }
}
