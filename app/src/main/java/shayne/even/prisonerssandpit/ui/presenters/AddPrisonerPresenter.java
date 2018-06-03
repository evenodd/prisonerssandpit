package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;

/**
 * Presenter for teh Add Prisoner View
 */

public interface AddPrisonerPresenter {

    /**
     * Creates a new prisoner
     * @param name the name of the prisoner
     * @param alpha the alpha constant for the prisoner
     * @param gamma the gamma constant for the prisoner
     * @param context the context of the application
     */
    void addPrisoner(String name, String alpha, String gamma, Context context);

}
