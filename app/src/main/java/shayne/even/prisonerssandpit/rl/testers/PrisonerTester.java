package shayne.even.prisonerssandpit.rl.testers;

import android.content.Context;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.models.PrisonerPerformanceScore;
import shayne.even.prisonerssandpit.rl.agents.BetrayingAgent;
import shayne.even.prisonerssandpit.rl.agents.CooperativeAgent;
import shayne.even.prisonerssandpit.rl.agents.PrisonerAgentHolder;
import shayne.even.prisonerssandpit.rl.agents.TitForTatAgent;
import shayne.even.prisonerssandpit.rl.environments.PrisonersDilemma;

/**
 * Class that tests and records a Prisoner model's performance
 */

public class PrisonerTester {

    /**
     * Tests the passed prisoner against the static strategies and returns the results.
     * @param prisoner the prisoner to test
     * @param context the app context
     * @return the performance of the prisoner agent
     */
    public PrisonerPerformanceScore generatePerformanceScores(Prisoner prisoner, Context context) {
        PrisonersDilemma prisonersDilemma = new PrisonersDilemma();
        PrisonerAgentHolder prisonerAgentHolder = new PrisonerAgentHolder(prisoner, context);

        prisonersDilemma.runEpisode(prisonerAgentHolder, new CooperativeAgent(), null);
        int coopScore = prisonerAgentHolder.getScore();
        prisonersDilemma.resetEpisode();

        prisonersDilemma.runEpisode(prisonerAgentHolder, new BetrayingAgent(), null);
        int betrayScore = prisonerAgentHolder.getScore();
        prisonersDilemma.resetEpisode();

        prisonersDilemma.runEpisode(prisonerAgentHolder, new TitForTatAgent(), null);
        int titForTatScore = prisonerAgentHolder.getScore();
        prisonersDilemma.resetEpisode();

        return new PrisonerPerformanceScore(prisoner.getUid(), coopScore, betrayScore,
                titForTatScore);
    }
}
