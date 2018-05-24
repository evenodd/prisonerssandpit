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
 * Created by Shayne Even on 20/05/2018.
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

    public TaskComponent getTaskComponent() {
        if (mTaskComponent == null) {
            mTaskComponent = DaggerTaskComponent.builder()
                    .applicationComponent(PrisonersSandpitApp.get(mContext.get()).getComponent())
                    .build();
        }
        return mTaskComponent;
    }

    public  Context getContext() {
        return mContext.get();
    }

}
