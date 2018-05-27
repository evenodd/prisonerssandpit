package shayne.even.prisonerssandpit.ui.views;

import shayne.even.prisonerssandpit.ui.presenters.TrainerSettingsPresenter;

/**
 * Created by Shayne Even on 21/05/2018.
 */

public interface TrainerSettingsView {
    TrainerSettingsPresenter.TrainerOption getSelectedTrainer();

    String getSelectedEpisodeOption();

    boolean isTrainerNotificationChecked();

    void displayTrainerErrorMessage();

    long getPrisonerId();

    void startTrainerService(long prisonerId);

    void startPrisonerSelectDialog(long excludedPrisoner);

    void setSelectedAgent(String name);
}

