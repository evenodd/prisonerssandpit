package shayne.even.prisonerssandpit.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import shayne.even.prisonerssandpit.ui.presenters.PrisonerSelectPresenter.OnSelectListener;
import shayne.even.prisonerssandpit.ui.presenters.SelectPrisonerListPresenter;
import shayne.even.prisonerssandpit.ui.presenters.SelectPrisonerListPresenterImpl;

/**
 * Adapter used for the prisoner list inside the select prisoner dialog
 */

public class SelectPrisonerAdapter extends PrisonerAdapter {

    private final SelectPrisonerListPresenter mPresenter;

    /**
     * Creates a SelectPrisonerAdapter
     * @param context the context of the dialog the list is in
     * @param listener the handler for when an item is selected
     */
    public SelectPrisonerAdapter(Context context, OnSelectListener listener) {
        super(context);
        mPresenter = new SelectPrisonerListPresenterImpl(this, mContext, listener);
    }

    /**
     * Notifies the presenter to populates the list with all prisoners except the specified one
     * @param excludedPrisoner the prisoner to exclude form the list
     */
    public void addAllPrisoners(long excludedPrisoner) {
        mPresenter.getAllPrisonersExcept(excludedPrisoner);
    }

    /**
     * View Holder class manages the view of each individual list item
     */
    public class ViewHolder extends PrisonerAdapter.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }

        /**
         * Notifies the presenter a prisoner has been selected
         * @param view the view of the clicked item
         */
        @Override
        public void onPrisonerClicked(View view) {
            mPresenter.handlePrisonerSelected(this);
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
