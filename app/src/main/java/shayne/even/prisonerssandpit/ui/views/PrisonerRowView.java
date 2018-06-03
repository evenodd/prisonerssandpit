package shayne.even.prisonerssandpit.ui.views;

import android.content.Context;

/**
 * User interface for a single Prisoner item within a list
 */

public interface PrisonerRowView {

    /**
     * Displays the passed name as the name of the prisoner
     * @param name the string to display
     */
    void setName(String name);

    /**
     * Provides the position the View is in the list
     * @return the index of this view in the list
     */
    int getPositionInAdapter();
}
