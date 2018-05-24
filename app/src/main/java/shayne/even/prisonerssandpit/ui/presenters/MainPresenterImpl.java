package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.views.MainView;

/**
 * Created by Shayne Even on 14/05/2018.
 */

public class MainPresenterImpl implements MainPresenter {
    private final MainView mView;

    public MainPresenterImpl(MainView view) {
        mView = view;
    }

    @Override
    public void startAddAddPrisonerActivity() {

    }

    @Override
    public void handleAddPrisonerResult(int resultCode, long prisonerId) {
        if (mView.okResultCode(resultCode) && prisonerId != -1) {
            mView.addPrisonerToList(prisonerId);
        }
        else {
            mView.displayAddPrisonerError();
        }
    }

    @Override
    public void navigateToAddPrisonerView() {
        mView.navigateToAddPrisonerView();
    }
}
