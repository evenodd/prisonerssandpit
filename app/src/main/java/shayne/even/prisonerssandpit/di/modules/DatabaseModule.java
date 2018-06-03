package shayne.even.prisonerssandpit.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import shayne.even.prisonerssandpit.database.AppDatabase;

/**
 * Database module that provides the app's AppDatabase instance
 */

@Module
public class DatabaseModule {
    private AppDatabase mAppDatabase;

    /**
     * Creates a DatabaseModule
     * @param context the apps context
     */
    public DatabaseModule(Context context) {
        mAppDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                AppDatabase.DB_NAME
        ).build();
    }

    /** Provides the apps AppDatabase as a singleton pattern */
    @Singleton
    @Provides
    AppDatabase providesAppDatabase() {
        return mAppDatabase;
    }
}
