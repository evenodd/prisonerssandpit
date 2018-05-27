package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.ui.views.TrainerSettingsView;

/**
 * Created by Shayne Even on 21/05/2018.
 */

public class TrainerSettingsPresenterImpl implements TrainerSettingsPresenter {

    private TrainerSettingsView mView;

    private long mSelectedTrainerAgent;

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

    @Override
    public void getPrisonerFromUser() {
        mView.startPrisonerSelectDialog(mView.getPrisonerId());
    }

    @Override
    public void handleSelectPrisoner(Prisoner prisoner) {
        mSelectedTrainerAgent = prisoner.getUid();
        mView.setSelectedAgent(prisoner.getName());
    }
}
