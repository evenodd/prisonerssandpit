package shayne.even.prisonerssandpit.tasks.prisoner;

/**
 * Listener for an OnAddPrisonerAsyncTask
 * provides callback function upon errors and the completion of the task
 */

public interface OnAddPrisonerFinishedListener {
    /**
     * Callback function to handle an invalid name
     */
    void onNameError();

    /**
     * Callback function to handle an invalid alpha value
     */
    void onAlphaError();

    /**
     * Callback function to handle an invalid gamma value
     */
    void onGammaError();

    /**
     * Callback function for the completion of the async task
     * @param id the results of the async task
     */
    void onSuccess(long id);
}
