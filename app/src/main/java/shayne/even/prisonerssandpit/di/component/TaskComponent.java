package shayne.even.prisonerssandpit.di.component;

import android.content.Context;

import dagger.Component;
import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.di.TaskContext;
import shayne.even.prisonerssandpit.di.modules.TaskModule;
import shayne.even.prisonerssandpit.tasks.GetPrisonerStatusAsyncTask;
import shayne.even.prisonerssandpit.tasks.GetPrisonersExceptAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.AddPrisonerAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.GetAllPrisonersAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.GetPrisonerAsyncTask;

/**
 * The Task Component that contains tasks' database dependency
 */

@Component(
        dependencies = ApplicationComponent.class,
        modules = {TaskModule.class}
)
@TaskContext
public interface TaskComponent {
    /** Provides the application context from the ApplicationComponent*/
    Context getContext();
    /** Provides the AppDatabase instance from the Application Component*/
    AppDatabase getAppDatabase();

    /**
     * Injects the methods and members into the passed AddPrisonerAsyncTask
     * @param addPrisonerAsyncTask the instance to inject
     */
    void inject(AddPrisonerAsyncTask addPrisonerAsyncTask);

    /**
     * Injects the methods and members into the passed GetAllPrisonersAsyncTask
     * @param getAllPrisonersAsyncTask the instance to inject
     */
    void inject(GetAllPrisonersAsyncTask getAllPrisonersAsyncTask);

    /**
     * Injects the methods and members into the passed GetPrisonerAsyncTask
     * @param getPrisonerAsyncTask the instance to inject
     */
    void inject(GetPrisonerAsyncTask getPrisonerAsyncTask);

    /**
     * Injects the methods and members into the passed GetPrisonersExceptAsyncTask
     * @param getPrisonersExceptAsyncTask the instance to inject
     */
    void inject(GetPrisonersExceptAsyncTask getPrisonersExceptAsyncTask);

    /**
     * Injects the methods and members into the passed GetPrisonerStatusAsyncTask
     * @param getPrisonerStatusAsyncTask the instance to inject
     */
    void inject(GetPrisonerStatusAsyncTask getPrisonerStatusAsyncTask);
}
