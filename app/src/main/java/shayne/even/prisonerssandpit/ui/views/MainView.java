package shayne.even.prisonerssandpit.ui.views;

/**
 * Created by Shayne Even on 14/05/2018.
 */

public interface MainView {
    void showAddPrisonerTapTarget();

    boolean okResultCode(int resultCode);

    void navigateToAddPrisonerView();

    void addPrisonerToList(long id);

    void displayAddPrisonerError();
}
