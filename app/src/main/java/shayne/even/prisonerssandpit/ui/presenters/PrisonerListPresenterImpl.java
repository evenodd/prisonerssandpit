package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;

import java.util.ArrayList;

import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.tasks.prisoner.GetAllPrisonersAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.GetPrisonerAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.OnGetPrisonersFinishedListener;
import shayne.even.prisonerssandpit.tasks.prisoner.OnGetPrisonerFinishedListener;
import shayne.even.prisonerssandpit.ui.presenters.listeners.NoEntriesListener;
import shayne.even.prisonerssandpit.ui.views.PrisonerListView;
import shayne.even.prisonerssandpit.ui.views.PrisonerRowView;

/**
 * Implementation of the Base PrisonerListPresenter
 */

abstract class PrisonerListPresenterImpl implements PrisonerListPresenter,
        OnGetPrisonersFinishedListener,
        OnGetPrisonerFinishedListener {
    ArrayList<Prisoner> mPrisoners;
    private PrisonerListView mView;
    protected Context mContext;
    private NoEntriesListener mListener;


    PrisonerListPresenterImpl(PrisonerListView view, Context context,
                              NoEntriesListener listener) {
        mPrisoners = new ArrayList<>();
        mView = view;
        mContext = context;
        mListener = listener;
    }

    /**
     * {@inheritDoc}
     * notifies the view the prisoners list has changed and checks if the listener should be
     * notified that no results were returned
     * @param prisoners the results of the async task
     */
    @Override
    public void onSuccess(ArrayList<Prisoner> prisoners) {
        mPrisoners = prisoners;
        mView.notifyPrisonerDataChanged();
        if (getPrisonerRowCount() == 0 && mListener != null) mListener.onNoEntries();
    }

    /**
     * {@inheritDoc}
     * Adds the prisoner to the list and notifies the view that the list has changed
     * @param prisoner the results of the async task
     */
    @Override
    public void onSuccess(Prisoner prisoner) {
        mPrisoners.add(prisoner);
        mView.notifyPrisonerDataChanged();
    }


    void getAllPrisoners() {
        new GetAllPrisonersAsyncTask(mContext, this).execute();
    }

    @Override
    public void onBindPrisonerView(PrisonerRowView holder, int position) {
        holder.setName(mPrisoners.get(position).getName());
    }

    @Override
    public int getPrisonerRowCount() {
        return mPrisoners.size();
    }

    @Override
    public void appendPrisoner(long id) {
        new GetPrisonerAsyncTask(mContext, this, id).execute();
    }
}
