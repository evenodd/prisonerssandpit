package shayne.even.prisonerssandpit.ui.presenters;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.trainers.Trainer;

/**
 * Created by Shayne Even on 21/05/2018.
 */

public interface TrainerSettingsPresenter {
    void startTrainerService();

    void getPrisonerFromUser();

    void handleSelectPrisoner(Prisoner prisoner);

    enum TrainerOption {
        COOP(1), BETRAYER(2), TIT_FOR_TAT(3), PRISONER_AGENT(4);
        private int mValue;

        TrainerOption(int value) {
            mValue = value;
        }

        public int getValue() {
            return mValue;
        }

        @SuppressLint("UseSparseArrays")
        private static Map mMap = new HashMap<Integer, TrainerOption>();

        static {
            for (TrainerOption trainerOption: TrainerOption.values()) {
                mMap.put(trainerOption.getValue(), trainerOption);
            }
        }

        public static TrainerOption valueOf(int trainerOption) {
            return (TrainerOption) mMap.get(trainerOption);
        }
    }
}
