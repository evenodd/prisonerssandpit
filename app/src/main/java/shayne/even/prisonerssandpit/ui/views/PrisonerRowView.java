package shayne.even.prisonerssandpit.ui.views;

import android.content.Context;

/**
 * Created by Shayne Even on 13/05/2018.
 */

public interface PrisonerRowView {

    void setName(String name);

    int getPositionInAdapter();

    Context getContext();

    void navigateToPrisonerHome(long prisoner);
}
