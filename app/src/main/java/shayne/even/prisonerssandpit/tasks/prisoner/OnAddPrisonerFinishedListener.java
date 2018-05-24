package shayne.even.prisonerssandpit.tasks.prisoner;

/**
 * Created by Shayne Even on 14/05/2018.
 */

public interface OnAddPrisonerFinishedListener {
    void onNameError();

    void onAlphaError();

    void onGammaError();

    void onSuccess(long id);
}
