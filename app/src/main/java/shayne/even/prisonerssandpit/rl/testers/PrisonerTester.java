package shayne.even.prisonerssandpit.rl.testers;

import android.content.Context;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.models.PrisonerPerformanceScore;
import shayne.even.prisonerssandpit.rl.agents.BetrayingAgent;
import shayne.even.prisonerssandpit.rl.agents.CooperativeAgent;
import shayne.even.prisonerssandpit.rl.agents.PrisonerAgentHolder;
import shayne.even.prisonerssandpit.rl.agents.TitForTatAgent;
import shayne.even.prisonerssandpit.rl.episodes.PrisonersDilemma;

/**
 * Created by Shayne Even on 20/05/2018.
 */

public class PrisonerTester {

    public PrisonerPerformanceScore generatePerformanceScores(Prisoner prisoner, Context context) {
        PrisonersDilemma prisonersDilemma = new PrisonersDilemma();
        PrisonerAgentHolder prisonerAgentHolder = new PrisonerAgentHolder(prisoner, context);

        prisonersDilemma.runEpisode(prisonerAgentHolder, new CooperativeAgent());
        int coopScore = prisonerAgentHolder.getScore();
        prisonersDilemma.resetEpisode();

        prisonersDilemma.runEpisode(prisonerAgentHolder, new BetrayingAgent());
        int betrayScore = prisonerAgentHolder.getScore();
        prisonersDilemma.resetEpisode();

        prisonersDilemma.runEpisode(prisonerAgentHolder, new TitForTatAgent());
        int titForTatScore = prisonerAgentHolder.getScore();
        prisonersDilemma.resetEpisode();

        return new PrisonerPerformanceScore(prisoner.getUid(), coopScore, betrayScore,
                titForTatScore);
    }
}
