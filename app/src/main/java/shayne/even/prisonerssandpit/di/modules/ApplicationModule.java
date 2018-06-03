package shayne.even.prisonerssandpit.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application Module that provides the app's context
 */

@Module
public class ApplicationModule {
    private final Context mContext;

    /**
     * Creates an Application Module
     * @param context the app's context
     */
    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }
}
