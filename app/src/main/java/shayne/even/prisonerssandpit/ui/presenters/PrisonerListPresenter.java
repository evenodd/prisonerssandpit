package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.views.PrisonerRowView;

/**
 * Created by Shayne Even on 21/05/2018.
 */

public interface PrisonerListPresenter {
    void getAllPrisoners();

    void onBindPrisonerView(PrisonerRowView holder, int position);

    int getPrisonerRowCount();

    void appendPrisoner(long id);
}
