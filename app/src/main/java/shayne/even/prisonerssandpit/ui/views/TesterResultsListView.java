package shayne.even.prisonerssandpit.ui.views;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public interface TesterResultsListView {
    void notifyDataChanged();

    interface OnScoreUpdateListener {

        void onPrisonerScoreUpdate(Integer prisonerScore);

        void onTesterScoreUpdate(Integer testerScore);
    }
}
