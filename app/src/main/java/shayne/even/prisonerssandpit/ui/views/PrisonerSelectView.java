package shayne.even.prisonerssandpit.ui.views;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public interface PrisonerSelectView {

    long getExcludePrisonerExtra();

    void populatePrisonersList(long excludePrisonerExtra);

    void closeDialog();
}
