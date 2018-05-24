package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Intent;

import shayne.even.prisonerssandpit.ui.views.TrainerSettingsView;

/**
 * Created by Shayne Even on 21/05/2018.
 */

public class TrainerSettingsPresenterImpl implements TrainerSettingsPresenter {

    private TrainerSettingsView mView;

    public TrainerSettingsPresenterImpl(TrainerSettingsView view) {
        mView = view;
    }

    @Override
    public void startTrainerService() {
        TrainerOption trainerOption = mView.getSelectedTrainer();
        String episodeOption = mView.getSelectedEpisodeOption();
        boolean shouldPushNotification = mView.isTrainerNotificationChecked();

        if (trainerOption == null) {
            mView.displayTrainerErrorMessage();
        }
        mView.startTrainerService(mView.getPrisonerId());
    }
}
