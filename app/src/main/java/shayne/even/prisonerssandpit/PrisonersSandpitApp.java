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
 * The Prisoner's Sandpit Application
 */

public class PrisonersSandpitApp extends Application {

    public static final String CHANNEL_ID = "prisoner_channel";
    protected ApplicationComponent mApplicationComponent;

    /**
     * Converts an instance of the application's context into this class
     * @param context the applications context
     * @return The instance of the application
     */
    public static PrisonersSandpitApp get(Context context) {
        return (PrisonersSandpitApp) context.getApplicationContext();
    }

    /**
     * Creates the application's channel and injects the app with it's dependencies
     */
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

    /**
     * Provides he dagger component containing the application's dependencies
     * @return the applications dependency component
     */
    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

}
