package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;

import shayne.even.prisonerssandpit.ui.presenters.listeners.NoEntriesListener;
import shayne.even.prisonerssandpit.ui.views.MainPrisonerRowView;
import shayne.even.prisonerssandpit.ui.views.PrisonerListView;

/**
 * Implementation of the MainPrisonerListPresenter
 */

public class MainPrisonerListPresenterImpl extends PrisonerListPresenterImpl implements MainPrisonerListPresenter {

    /**
     * Creates a MainPrisonerListPresenterImpl
     * @param view the view the presenter is for
     * @param context teh context of the view
     * @param listener handler for when the list doesn't have any entries
     */
    public MainPrisonerListPresenterImpl(PrisonerListView view, Context context,
                                         NoEntriesListener listener) {
        super(view, context, listener);
        getAllPrisoners();
    }

    /**
     * {@inheritDoc}
     * Navigates to the Home View of the prisoner the passed view is for
     * @param view the selected view
     */
    @Override
    public void handleOnPrisonerRowSelected(MainPrisonerRowView view) {
        view.navigateToPrisonerHome(mPrisoners.get(view.getPositionInAdapter()).getUid());
    }
}
