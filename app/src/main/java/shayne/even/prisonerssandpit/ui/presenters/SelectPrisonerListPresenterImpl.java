package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;

import shayne.even.prisonerssandpit.tasks.GetPrisonersExceptAsyncTask;
import shayne.even.prisonerssandpit.ui.views.PrisonerListView;
import shayne.even.prisonerssandpit.ui.views.PrisonerRowView;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public class SelectPrisonerListPresenterImpl extends PrisonerListPresenterImpl
        implements SelectPrisonerListPresenter {

    private final PrisonerSelectPresenter.OnSelectListener mListener;

    public SelectPrisonerListPresenterImpl(PrisonerListView view, Context context, PrisonerSelectPresenter.OnSelectListener listener) {
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
