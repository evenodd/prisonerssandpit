package shayne.even.prisonerssandpit.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import shayne.even.prisonerssandpit.ui.presenters.PrisonerSelectPresenter.OnSelectListener;
import shayne.even.prisonerssandpit.ui.presenters.SelectPrisonerListPresenter;
import shayne.even.prisonerssandpit.ui.presenters.SelectPrisonerListPresenterImpl;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public class SelectPrisonerAdapter extends PrisonerAdapter {

    private final SelectPrisonerListPresenter mPresenter;

    public SelectPrisonerAdapter(Context context, OnSelectListener listener) {
        super(context);
        mPresenter = new SelectPrisonerListPresenterImpl(this, mContext, listener);
    }

    public void addAllPrisoners(long excludedPrisoner) {
        if (excludedPrisoner == -1) {
            mPresenter.getAllPrisoners();
        }
        else {
            mPresenter.getAllPrisonersExcept(excludedPrisoner);
        }
    }

    public class ViewHolder extends PrisonerAdapter.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onPrisonerClicked(View view) {
            mPresenter.handlePrisonerSelected(this);
            //TODO return result as Intent with extra
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflateViewGroup(parent));

    }

    @Override
    public void onBindViewHolder(@NonNull PrisonerAdapter.ViewHolder holder, int position) {
      mPresenter.onBindPrisonerView(holder, position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getPrisonerRowCount();
    }
}
