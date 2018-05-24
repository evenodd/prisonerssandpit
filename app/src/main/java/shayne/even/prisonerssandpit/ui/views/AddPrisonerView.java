package shayne.even.prisonerssandpit.ui.views;

/**
 * Created by Shayne Even on 13/05/2018.
 */

public interface AddPrisonerView {
    void showProgress();

    void hideProgress();
    
    void returnResults(long newPrisonerId);

    void setNameErrorMessage();

    void setAlphaErrorMessage();

    void setGammaErrorMessage();
}
