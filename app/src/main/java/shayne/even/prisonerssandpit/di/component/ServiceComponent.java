package shayne.even.prisonerssandpit.di.component;

import android.content.Context;

import dagger.Component;
import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.di.ServiceContext;
import shayne.even.prisonerssandpit.di.modules.ServiceModule;
import shayne.even.prisonerssandpit.service.AgentTrainerService;

/**
 * Service component that contains the Services' database dependency
 */

@Component(
        dependencies = ApplicationComponent.class,
        modules = {ServiceModule.class}
)
@ServiceContext
public interface ServiceComponent {
    /** Provides the context of the application from the ApplicationComponent */
    Context getContext();

    /** Provides the instance of the AppDatabase from the Application Component*/
    AppDatabase getAppDatabase();

    /**
     * Injects modules and methods into the passed Service
     * @param agentTrainerService th instance to inject
     */
    void inject(AgentTrainerService agentTrainerService);

}
