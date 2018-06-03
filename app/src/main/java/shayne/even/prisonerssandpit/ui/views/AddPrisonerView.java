package shayne.even.prisonerssandpit.ui.views;

/**
 * User interface containing a form that lets users create new Prisoner agents
 */

public interface AddPrisonerView {
    /**
     * Displays the progress bar
     */
    void showProgress();

    /**
     * Hides the progress bar
     */
    void hideProgress();

    /**
     * Returns the passed id to the Main View and ends the view
     * @param newPrisonerId
     */
    void returnResults(long newPrisonerId);

    /**
     * Displays the invalid name error message
     */
    void setNameErrorMessage();

    /**
     * Displays the invalid alpha value error message
     */
    void setAlphaErrorMessage();

    /**
     * Displays the invalid gamma value error message
     */
    void setGammaErrorMessage();
}
