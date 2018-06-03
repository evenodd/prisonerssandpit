package shayne.even.prisonerssandpit.rl.environments;

/**
 * An interface for the prisoner's dilemma that can give listeners a 'read-only' access to the
 * environment
 */

public interface EnvironmentState {
    /**
     * Determines if the environment is in a final state of the episode.
     * @return true if all iterations have taken place
     */
    boolean reachedEnd();

    int getState();
}
