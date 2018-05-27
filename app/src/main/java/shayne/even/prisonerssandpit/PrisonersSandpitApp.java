package shayne.even.prisonerssandpit;

import android.app.Application;
import android.content.Context;

import shayne.even.prisonerssandpit.di.component.ApplicationComponent;
import shayne.even.prisonerssandpit.di.component.DaggerApplicationComponent;
import shayne.even.prisonerssandpit.di.modules.ApplicationModule;
import shayne.even.prisonerssandpit.di.modules.DatabaseModule;

/**
 * Created by Shayne Even on 14/05/2018.
 */

public class PrisonersSandpitApp extends Application {

    protected ApplicationComponent mApplicationComponent;

    public static PrisonersSandpitApp get(Context context) {
        return (PrisonersSandpitApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .databaseModule(new DatabaseModule(this))
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}
