package shayne.even.prisonerssandpit.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import shayne.even.prisonerssandpit.PrisonersSandpitApp;
import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.di.modules.ApplicationModule;
import shayne.even.prisonerssandpit.di.modules.DatabaseModule;

/**
 * Created by Shayne Even on 14/05/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, DatabaseModule.class})
public interface ApplicationComponent {
    Context getContext();
    AppDatabase getAppDatabase();

    void inject(PrisonersSandpitApp app);
}
