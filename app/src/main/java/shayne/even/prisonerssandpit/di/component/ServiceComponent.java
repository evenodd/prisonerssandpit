package shayne.even.prisonerssandpit.di.component;

import android.content.Context;

import dagger.Component;
import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.di.ServiceContext;
import shayne.even.prisonerssandpit.di.modules.ServiceModule;
import shayne.even.prisonerssandpit.service.AgentTrainerService;

/**
 * Created by Shayne Even on 28/05/2018.
 */

@Component(
        dependencies = ApplicationComponent.class,
        modules = {ServiceModule.class}
)
@ServiceContext
public interface ServiceComponent {
    Context getContext();
    AppDatabase getAppDatabase();

    void inject(AgentTrainerService agentTrainerService);

}
