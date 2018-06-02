package shayne.even.prisonerssandpit.ui.views;

/**
 * Created by Shayne Even on 2/06/2018.
 */

public interface VsView {
    void startRippleAnimation();

    long getPrisonerId();

    void setPrisonerName(String name);

    void setYourScore(String s);

    void setOpponentsScore(String s);

    void setOpponentAction(String s);

    void setRound(String s);

    void setOpponentActionProgressBar();

    void hideOpponentActionProgressBar();

    void showNextRoundButton();

    void hideNextRoundButton();

    void disableActionButtons();

    void enableActionButtons();
}
