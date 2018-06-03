package shayne.even.prisonerssandpit.ui.presenters;

import android.annotation.SuppressLint;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.rl.trainers.Trainer;

/**
 * Presenter for the Training Settings View
 */

public interface TrainerSettingsPresenter {
    /**
     * Starts a training service to run in the background using the user's configurations
     */
    void startTrainerService();

    /**
     * Opens a select prisoner dialog
     */
    void getPrisonerFromUser();

    /**
     * Sets the prisoner as the selected prisoner agent and displays the name of the prisoner in the
     * view
     * @param prisoner the selected prisoner
     */
    void handleSelectPrisoner(Prisoner prisoner);

    /**
     * An Enum of the different options of trainer types available
     */
    enum TrainerOption {
        COOP(1), BETRAYER(2), TIT_FOR_TAT(3), PRISONER_AGENT(4);
        private int mValue;

        private static SparseArray<TrainerOption> mMap = new SparseArray<>();

        TrainerOption(int value) {
            mValue = value;
        }

        /**
         * returns the integer value of an option
         * @return the value of the TrainerOption
         */
        public int getValue() {
            return mValue;
        }

        static {
            for (TrainerOption trainerOption: TrainerOption.values()) {
                mMap.put(trainerOption.getValue(), trainerOption);
            }
        }

        /**
         * Returns the TrainOption with the passed value
         * @param trainerOption the value of the trainer option to get
         * @return the TrainerOpinion with the specified value
         */
        public static TrainerOption valueOf(int trainerOption) {
            return mMap.get(trainerOption);
        }
    }
}
