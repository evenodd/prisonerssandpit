package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.models.Prisoner;

/**
 * Created by Shayne Even on 21/05/2018.
 */

public interface TrainerSettingsPresenter {
    void startTrainerService();

    void getPrisonerFromUser();

    void handleSelectPrisoner(Prisoner prisoner);

    enum TrainerOption {
        COOP, BETRAYER, TIT_FOR_TAT, PRISONER_AGENT;
    }
}
