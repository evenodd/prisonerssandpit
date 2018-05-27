package shayne.even.prisonerssandpit.ui.views;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public interface TesterResultItemView {
    void displayProgress();

    void hideProgress();

    void setPrisonerAction(String action);

    void setTesterAction(String action);
}
