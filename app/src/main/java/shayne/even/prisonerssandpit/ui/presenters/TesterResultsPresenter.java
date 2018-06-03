package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.views.TesterResultItemView;

/**
 * Presenter for the Tester Results View
 */

public interface TesterResultsPresenter {
    /**
     * Binds the specified view the the tester results in the passed position in the prisoner list
     * @param view the view to bind
     * @param position the position of the tester result to bind to the view to
     */
    void bindViewHolder(TesterResultItemView view, int position);

    /**
     * Provides the number of tester results in the list
     * @return the count of tester results
     */
    int getItemCount();
}
