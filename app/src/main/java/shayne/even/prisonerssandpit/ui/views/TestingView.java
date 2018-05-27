package shayne.even.prisonerssandpit.ui.views;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public interface TestingView {
    long getPrisonerExtra();

    long getTesterExtra();

    void setPrisonerNameTitle(String name);

    void setPrisonerScoreHeading(String name);

    void setTesterNameTitle(String name);

    void setTesterScoreHeading(String name);

    void setPrisonerScore(String s);

    void setTesterScore(String s);
}
