package shayne.even.prisonerssandpit.rl.testers;

/**
 * Holds the scores and actions between two prisoner agents
 */

public class TesterResult {
    private Integer mPrisonerAction;
    private Integer mTesterAction;
    private int mPrisonerScore = 0;
    private int mTesterScore = 0;

    public void setPrisonerAction(int prisonerAction) {
        mPrisonerAction = prisonerAction;
    }

    public void addPrisonerScore(int score) {
        mPrisonerScore += score;
    }

    public Integer getPrisonerAction() {
        return mPrisonerAction;
    }

    public void setTesterAction(int testerAction) {
        mTesterAction = testerAction;
    }

    public void addTesterScore(int score) {
        mTesterScore += score;
    }

    public Integer getTesterAction() {
        return mTesterAction;
    }

    public int getPrisonerScore() {
        return mPrisonerScore;
    }

    public int getTesterScore() {
        return mTesterScore;
    }
}
