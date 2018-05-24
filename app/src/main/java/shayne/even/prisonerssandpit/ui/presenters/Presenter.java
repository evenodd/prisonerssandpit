package shayne.even.prisonerssandpit.ui.presenters;

/**
 * Created by Shayne Even on 14/05/2018.
 */

public abstract class Presenter<V> {
    protected final V mView;

    protected Presenter(V view) {
        mView = view;
    }
}
