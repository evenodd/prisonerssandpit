package shayne.even.prisonerssandpit.di.component;

import android.content.Context;

import dagger.Component;
import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.di.ModelContext;
import shayne.even.prisonerssandpit.di.modules.ModelModule;
import shayne.even.prisonerssandpit.models.Prisoner;

/**
 * Model Component that contains the Models' database dependency
 */

@Component(
        dependencies = ApplicationComponent.class,
        modules = {ModelModule.class}
)
@ModelContext
public interface ModelComponent {
    /**
     * Provides the applications context from the Application Component
     * @return the apps context
     */
    Context getContext();

    /**
     * Provides the AppDatabase instance
     * @return the AppDatabase instance
     */
    AppDatabase getAppDatabase();

    /**
     * Inject members and methods to the passed Prisoner Model
     * @param prisoner the instance to inject
     */
    void inject(Prisoner prisoner);

}
