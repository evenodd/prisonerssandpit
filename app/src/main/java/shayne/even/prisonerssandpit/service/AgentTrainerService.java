package shayne.even.prisonerssandpit.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import shayne.even.prisonerssandpit.PrisonersSandpitApp;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.di.component.ServiceComponent;
import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.agents.BetrayingAgent;
import shayne.even.prisonerssandpit.rl.agents.CooperativeAgent;
import shayne.even.prisonerssandpit.rl.agents.PrisonerAgentHolder;
import shayne.even.prisonerssandpit.rl.agents.TitForTatAgent;
import shayne.even.prisonerssandpit.rl.trainers.Trainer;
import shayne.even.prisonerssandpit.ui.presenters.TrainerSettingsPresenter.TrainerOption;

import static shayne.even.prisonerssandpit.PrisonersSandpitApp.CHANNEL_ID;

public class AgentTrainerService extends IntentService {

    public static final String PRISONER_ID_EXTRA = "prisoner_id_extra";
    public static final String TRAINER_EXTRA = "trainer_extra";
    public static final String EPISODE_OPTION_EXTRA = "episode_option_extra";
    public static final String PUSH_NOTIFICATION_EXTRA = "push_notification_extra";
    public static final String PRISONER_TRAINER_EXTRA = "prisoner_trainer_extra";
    private static final String TAG = "seven.agentTrainer";
    private ServiceComponent mServiceComponent;


    public AgentTrainerService() {
        super("AgentTrainerService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        assert intent != null;
        Prisoner prisoner = getServiceComponent().getAppDatabase().prisonerDao().getPrisoner(
                intent.getLongExtra(PRISONER_ID_EXTRA, -1)
        );
        Trainer trainer;
        switch (TrainerOption.valueOf(intent.getIntExtra(TRAINER_EXTRA, -1))) {
            case COOP :
                trainer = new Trainer(new CooperativeAgent());
                break;
            case BETRAYER:
                trainer = new Trainer(new BetrayingAgent());
                break;
            case TIT_FOR_TAT:
                trainer = new Trainer(new TitForTatAgent());
                break;
            case PRISONER_AGENT:
                trainer = new Trainer(new PrisonerAgentHolder(
                        getServiceComponent()
                                .getAppDatabase()
                                .prisonerDao()
                                .getPrisoner(
                                        intent.getLongExtra(PRISONER_TRAINER_EXTRA, -1)
                                ),
                        this
                ));
                break;
            default :
                Log.e(TAG, "Invalid trainer option given to Training service");
                return;
        }
        trainer.trainPrisoner(
                prisoner,
                this,
                intent.getIntExtra(EPISODE_OPTION_EXTRA, 0)
        );
        Log.i(TAG, "Training done :)");

    }

    private void displayNotification(Prisoner prisoner) {
        new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_action_fitness)
                .setContentTitle(getString(R.string.completed_training_notification_title))
                .setContentText(String.format(
                        getString(R.string.completed_training_notification_content),
                        prisoner.getName()
                ))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build();
    }

    public ServiceComponent getServiceComponent() {
        if (mServiceComponent == null) {
            mServiceComponent = DaggerServiceComponent.builder()
                    .applicationComponent(
                            PrisonersSandpitApp.get(this.getApplicationContext()).getComponent())
                    .build();
        }
        return mServiceComponent;
    }
}
