package shayne.even.prisonerssandpit.di.component;

import android.content.Context;

import dagger.Component;
import shayne.even.prisonerssandpit.database.AppDatabase;
import shayne.even.prisonerssandpit.di.ModelContext;
import shayne.even.prisonerssandpit.di.modules.ModelModule;
import shayne.even.prisonerssandpit.models.Prisoner;

/**
 * Created by Shayne Even on 20/05/2018.
 */

@Component(
        dependencies = ApplicationComponent.class,
        modules = {ModelModule.class}
)
@ModelContext
public interface ModelComponent {
    Context getContext();
    AppDatabase getAppDatabase();

    void inject(Prisoner prisoner);

}
