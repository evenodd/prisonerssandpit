package shayne.even.prisonerssandpit.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import shayne.even.prisonerssandpit.database.AppDatabase;

/**
 * Created by Shayne Even on 20/05/2018.
 */

@Module
public class DatabaseModule {
    private AppDatabase mAppDatabase;

    public DatabaseModule(Context context) {
        mAppDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                AppDatabase.DB_NAME
        ).build();
    }

    @Singleton
    @Provides
    AppDatabase providesAppDatabase() {
        return mAppDatabase;
    }
}
