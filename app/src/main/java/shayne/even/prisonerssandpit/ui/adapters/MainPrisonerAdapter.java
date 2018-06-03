package shayne.even.prisonerssandpit.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.ui.activities.PrisonerHomeActivity;
import shayne.even.prisonerssandpit.ui.presenters.MainPrisonerListPresenter;
import shayne.even.prisonerssandpit.ui.presenters.MainPrisonerListPresenterImpl;
import shayne.even.prisonerssandpit.ui.presenters.listeners.NoEntriesListener;
import shayne.even.prisonerssandpit.ui.views.MainPrisonerRowView;

/**
 * Adapter used for the prisoner list in the main activity
 */
public class MainPrisonerAdapter extends PrisonerAdapter {

    private final MainPrisonerListPresenter mPresenter;

    /**
     * Creates a MainPrisonerAdapter
     * @param context the context of the activity the list is in
     * @param listener the listener that provides a handler for an empty list
     */
    public MainPrisonerAdapter(Context context, NoEntriesListener listener) {
        super(context);
        mPresenter = new MainPrisonerListPresenterImpl(this, mContext, listener);
    }

    /**
     * View Holder class manages the view of each individual list item
     */
    public class ViewHolder extends PrisonerAdapter.ViewHolder implements MainPrisonerRowView {
        @BindView(R.id.prisoner_name)
        TextView mName;

        ViewHolder(View itemView) {
            super(itemView);
        }


        /**
         * Notifies the presenter that the view has been clicked
         * @param view the view of the clicked item
         */
        @Override
        public void onPrisonerClicked(View view) {
            mPresenter.handleOnPrisonerRowSelected(this);
        }

        @Override
        public void navigateToPrisonerHome(long prisoner) {
            mContext.startActivity(
                    new Intent(mContext, PrisonerHomeActivity.class)
                            .putExtra(PrisonerHomeActivity.PRISONER_ID, prisoner)
            );
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflateViewGroup(parent));
    }

    /**
     * {@inheritDoc}
     * Notify the presenter a view holder need to bind to its data
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull PrisonerAdapter.ViewHolder holder, int position) {
        mPresenter.onBindPrisonerView(holder, position);
    }

    /**
     * {@inheritDoc}
     * Requests the item count from the presenter
     * @return
     */
    @Override
    public int getItemCount() {
        return mPresenter.getPrisonerRowCount();
    }

    /**
     * Notifies the presenter a prisoner needs to be appended to the list
     * @param id the id of the prisoner to append
     */
    public void appendPrisoner(final long id) {
        mPresenter.appendPrisoner(id);
    }
}
