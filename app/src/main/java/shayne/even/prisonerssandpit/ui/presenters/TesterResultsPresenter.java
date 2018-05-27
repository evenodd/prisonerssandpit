package shayne.even.prisonerssandpit.ui.presenters;

import shayne.even.prisonerssandpit.ui.views.TesterResultItemView;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public interface TesterResultsPresenter {
    void bindViewHolder(TesterResultItemView view, int position);

    int getItemCount();
}
