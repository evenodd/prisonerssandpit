package shayne.even.prisonerssandpit.ui.presenters;

/**
 * Created by Shayne Even on 14/05/2018.
 */

public interface MainPresenter {
    public void startAddAddPrisonerActivity();

    public void handleAddPrisonerResult(int resultCode, long prisonerId);

    public void navigateToAddPrisonerView();
}
