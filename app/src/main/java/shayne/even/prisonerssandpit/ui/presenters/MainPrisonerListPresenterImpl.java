package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;

import shayne.even.prisonerssandpit.ui.views.MainPrisonerRowView;
import shayne.even.prisonerssandpit.ui.views.PrisonerListView;

/**
 * Created by Shayne Even on 13/05/2018.
 */

public class MainPrisonerListPresenterImpl extends PrisonerListPresenterImpl implements MainPrisonerListPresenter {


    public MainPrisonerListPresenterImpl(PrisonerListView view, Context context) {
        super(view, context);
    }

    @Override
    public void navigateToPrisonersHome(MainPrisonerRowView view) {
        view.navigateToPrisonerHome(mPrisoners.get(view.getPositionInAdapter()).getUid());
    }
}
