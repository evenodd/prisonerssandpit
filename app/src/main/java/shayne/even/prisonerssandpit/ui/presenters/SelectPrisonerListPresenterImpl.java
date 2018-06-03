package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;

import shayne.even.prisonerssandpit.tasks.GetPrisonersExceptAsyncTask;
import shayne.even.prisonerssandpit.ui.views.PrisonerListView;
import shayne.even.prisonerssandpit.ui.views.PrisonerRowView;

/**
 * Implements SelectPrisonerListPresenter
 */

public class SelectPrisonerListPresenterImpl extends PrisonerListPresenterImpl
        implements SelectPrisonerListPresenter {

    private final PrisonerSelectPresenter.OnSelectListener mListener;

    /**
     * Creates a SelectPrisonerListPresenterImpl
     * @param view the view the presenter is for
     * @param context the context of the view
     * @param listener the handler for when a prisoner is selected
     */
    public SelectPrisonerListPresenterImpl(PrisonerListView view, Context context,
                                           PrisonerSelectPresenter.OnSelectListener listener) {
        super(view, context, null);
        mListener = listener;
    }

    @Override
    public void getAllPrisonersExcept(long excludedPrisoner) {
        new GetPrisonersExceptAsyncTask(mContext, this, excludedPrisoner).execute();
    }

    @Override
    public void handlePrisonerSelected(PrisonerRowView viewHolder) {
        mListener.onSelect(mPrisoners.get(viewHolder.getPositionInAdapter()));
    }
}
