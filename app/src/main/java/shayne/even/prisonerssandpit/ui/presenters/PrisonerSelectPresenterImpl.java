package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.ui.views.PrisonerSelectView;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public class PrisonerSelectPresenterImpl implements PrisonerSelectPresenter,
        PrisonerSelectPresenter.OnSelectListener{
    private final PrisonerSelectView mView;
    private final OnSelectListener mListener;

    public PrisonerSelectPresenterImpl(PrisonerSelectView view, OnSelectListener listener) {
        mView = view;
        mListener = listener;
    }

    @Override
    public void populateList() {
        mView.populatePrisonersList(mView.getExcludePrisonerExtra());
    }

    @Override
    public OnSelectListener getListener() {
        return this;
    }

    @Override
    public void onSelect(Prisoner prisoner) {
        mListener.onSelect(prisoner);
        mView.closeDialog();
    }
}
