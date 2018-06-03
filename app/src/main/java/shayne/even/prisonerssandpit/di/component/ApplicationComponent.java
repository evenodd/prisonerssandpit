package shayne.even.prisonerssandpit.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import shayne.even.prisonerssandpit.PrisonersSandpitApp;
import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.di.modules.ApplicationModule;
import shayne.even.prisonerssandpit.di.modules.DatabaseModule;

/**
 * The Application component that contains the apps database dependency.
 */

@Singleton
@Component(modules = {ApplicationModule.class, DatabaseModule.class})
public interface ApplicationComponent {
    /**
     * Provides the application's context
     * @return the Context of the application
     */
    Context getContext();

    /**
     * Provides the database from the DatabaseModule
     * @return the instance of the AppDatabase
     */
    AppDatabase getAppDatabase();

    /**
     * Injects members and methods to the passed Application instance
     * @param app the app to inject
     */
    void inject(PrisonersSandpitApp app);
}
