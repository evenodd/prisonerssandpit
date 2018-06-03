package shayne.even.prisonerssandpit.ui.views;

/**
 * View that lets the user vs a prisoner agent directly in a Prisoner's Dilemma
 */

public interface VsView {
    /**
     * Starts the ripple animation on the round circle
     */
    void startRippleAnimation();

    /**
     * Provides the id of the prisoner the user is versing
     * @return the id of the prisoner
     */
    long getPrisonerId();

    /**
     * Displays the passed name as the name of the prisoner
     * @param name the string to display
     */
    void setPrisonerName(String name);

    /**
     * Displays the passed string as the user's score
     * @param s the user's score
     */
    void setYourScore(String s);

    /**
     * Displays the passed string as the opponent's score
     * @param s the opponent's score
     */
    void setOpponentsScore(String s);

    /**
     * Displays the passed string as the opponents action
     * @param s the action to display
     */
    void setOpponentAction(String s);

    /**
     * Displays the passed string as the current round number
     * @param s the round number to display
     */
    void setRound(String s);

    /**
     * Displays the progress bar for the opponent's action
     */
    void setOpponentActionProgressBar();

    /**
     * Hides the progress bar for the opponent's action
     */
    void hideOpponentActionProgressBar();

    /**
     * Displays the next round button
     */
    void showNextRoundButton();

    /**
     * Hides the next round button
     */
    void hideNextRoundButton();

    /**
     * Disables the Cooperate and Betray buttons
     */
    void disableActionButtons();

    /**
     * Enables the Cooperate and betray buttons
     */
    void enableActionButtons();
}
