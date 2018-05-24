package shayne.even.prisonerssandpit.ui.presenters;

/**
 * Created by Shayne Even on 21/05/2018.
 */

public interface TrainerSettingsPresenter {
    void startTrainerService();

    enum TrainerOption {
        COOP, BETRAYER, TIT_FOR_TAT, PRISONER_AGENT;
    }
}
