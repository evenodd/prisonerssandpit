package shayne.even.prisonerssandpit;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import shayne.even.prisonerssandpit.di.component.ApplicationComponent;
import shayne.even.prisonerssandpit.di.component.DaggerApplicationComponent;
import shayne.even.prisonerssandpit.di.modules.ApplicationModule;
import shayne.even.prisonerssandpit.di.modules.DatabaseModule;

/**
 * Created by Shayne Even on 14/05/2018.
 */

public class PrisonersSandpitApp extends Application {

    public static final String CHANNEL_ID = "prisoner_channel";
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
        createNotificationChannel();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

}
