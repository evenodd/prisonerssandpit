package shayne.even.prisonerssandpit.di.component;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import shayne.even.prisonerssandpit.PrisonersSandpitApp;

/**
 * Created by Shayne Even on 14/05/2018.
 */

@Component
public interface DaggerAppComponent {

    void inject(PrisonersSandpitApp app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        DaggerAppComponent build();
    }
}
