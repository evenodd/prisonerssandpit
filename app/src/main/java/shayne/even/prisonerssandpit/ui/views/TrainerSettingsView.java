package shayne.even.prisonerssandpit.ui.views;

import shayne.even.prisonerssandpit.ui.presenters.TrainerSettingsPresenter;

/**
 * View that lets the user configure and start the Training Service
 */

public interface TrainerSettingsView {
    /**
     * Provides the trainer option selected by the user
     * @return the selected trainer type
     */
    TrainerSettingsPresenter.TrainerOption getSelectedTrainer();

    /**
     * Provides the episodes option selected by the user
     * @return the selected episode option
     */
    String getSelectedEpisodeOption();

    /**
     * Determines if the user has checked the notification checkbox
     * @return true if ticked
     */
    boolean isTrainerNotificationChecked();

    /**
     * Displays the invalid trainer error message
     */
    void displayTrainerErrorMessage();

    /**
     * Provides the prisoner the view is for
     * @return the id of the prisoner
     */
    long getPrisonerId();

    /**
     * Creates a background service that trains the prisoner
     * @param prisoner the id of the prisoner to train
     * @param trainer the trainer type to use
     * @param episodeOption the episode option used for training
     * @param shouldPushNotification flag to notify the user upon completion
     * @param prisonerTrainer the prisoner to use if using a prisoner agent trainer
     */
    void startTrainerService(long prisoner, TrainerSettingsPresenter.TrainerOption trainer,
                             String episodeOption, boolean shouldPushNotification,
                             Long prisonerTrainer);

    /**
     * Displays a Prisoner select dialog
     * @param excludedPrisoner the prisoner to exclude from the selection list
     */
    void startPrisonerSelectDialog(long excludedPrisoner);

    /**
     * Displays the passed string as the name of the selected prisoner agent trainer
     * @param name the value to display
     */
    void setSelectedAgent(String name);
}

