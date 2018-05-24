package shayne.even.prisonerssandpit.ui.presenters;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import shayne.even.prisonerssandpit.tasks.prisoner.GetAllPrisonersAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.GetPrisonerAsyncTask;
import shayne.even.prisonerssandpit.tasks.prisoner.OnGetAllPrisonerFinishedListener;
import shayne.even.prisonerssandpit.ui.activities.PrisonerHomeActivity;
import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.tasks.prisoner.OnGetPrisonerFinishedListener;
import shayne.even.prisonerssandpit.ui.views.PrisonerListView;
import shayne.even.prisonerssandpit.ui.views.PrisonerRowView;

/**
 * Created by Shayne Even on 13/05/2018.
 */

public class PrisonerListPresenterImpl implements PrisonerListPresenter,
        OnGetAllPrisonerFinishedListener,
        OnGetPrisonerFinishedListener {

    private ArrayList<Prisoner> mPrisoners;
    private PrisonerListView mView;
    private Context mContext;

    public PrisonerListPresenterImpl(PrisonerListView view, Context context) {
        mPrisoners = new ArrayList<>();
        mView = view;
        mContext = context;
    }

    @Override
    public void onSuccess(ArrayList<Prisoner> prisoners) {
        mPrisoners = prisoners;
        mView.notifyPrisonerDataChanged();
    }

    @Override
    public void onSuccess(Prisoner prisoner) {
        mPrisoners.add(prisoner);
        mView.notifyPrisonerDataChanged();
    }

    @Override
    public void getAllPrisoners() {
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

    @Override
    public void navigateToPrisonersHome(PrisonerRowView view) {
        view.navigateToPrisonerHome(mPrisoners.get(view.getPositionInAdapter()).getUid());
    }
}
