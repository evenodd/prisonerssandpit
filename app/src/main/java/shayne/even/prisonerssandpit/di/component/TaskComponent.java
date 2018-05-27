package shayne.even.prisonerssandpit.di.component;

import android.content.Context;

import dagger.Component;
import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.di.TaskContext;
import shayne.even.prisonerssandpit.di.modules.TaskModule;
import shayne.even.prisonerssandpit.tasks.GetPrisonersExceptAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.AddPrisonerAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.GetAllPrisonersAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.GetPrisonerAsyncTask;

/**
 * Created by Shayne Even on 20/05/2018.
 */

@Component(
        dependencies = ApplicationComponent.class,
        modules = {TaskModule.class}
)
@TaskContext
public interface TaskComponent {
    Context getContext();
    AppDatabase getAppDatabase();

    void inject(AddPrisonerAsyncTask addPrisonerAsyncTask);
    void inject(GetAllPrisonersAsyncTask getAllPrisonersAsyncTask);
    void inject(GetPrisonerAsyncTask getPrisonerAsyncTask);
    void inject(GetPrisonersExceptAsyncTask getPrisonersExceptAsyncTask);
}
