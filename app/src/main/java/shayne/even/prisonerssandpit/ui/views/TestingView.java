package shayne.even.prisonerssandpit.ui.views;

/**
 * Activity that tests the performance when two prisoners are opposing each other and displays the
 * results
 */

public interface TestingView {
    /**
     * Provides the prisoner being tested in this view
     * @return the id of the prisoner
     */
    long getPrisonerId();

    /**
     * Provides the prisoner that is being tested against in this view
     * @return the tester's id
     */
    long getTesterId();

    /**
     * Displays the passed name as the Prisoner's name
     * @param name the string to display
     */
    void setPrisonerNameTitle(String name);

    /**
     * Displays the passed name as the prisoner's name in the score board
     * @param name the string to display
     */
    void setPrisonerScoreHeading(String name);

    /**
     * Displays the passed name as the Tester's name
     * @param name the string to display
     */
    void setTesterNameTitle(String name);

    /**
     * Displays the passed name as the tester's name in the score board
     * @param name the string to display
     */
    void setTesterScoreHeading(String name);

    /**
     * Displays the passed string as the prisoner's current score
     * @param s the value to display
     */
    void setPrisonerScore(String s);

    /**
     * Displays the passed string as the tester's current score
     * @param s the value to display
     */
    void setTesterScore(String s);
}
