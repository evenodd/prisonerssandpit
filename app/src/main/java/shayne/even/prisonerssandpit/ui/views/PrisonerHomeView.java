package shayne.even.prisonerssandpit.ui.views;

/**
 * Created by Shayne Even on 13/05/2018.
 */

public interface PrisonerHomeView {
    void setName(String name);

    void setBetrayScore(String score);

    void setCoopScore(String score);

    void setTitFirTatScore(String score);

    long getPrisonerId();

    void navigateToTrainerOptions();
}
