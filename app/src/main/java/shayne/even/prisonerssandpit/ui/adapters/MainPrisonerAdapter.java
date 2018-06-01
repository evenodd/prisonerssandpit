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
 * Created by Shayne Even on 23/04/2018.
 */
public class MainPrisonerAdapter extends PrisonerAdapter {

    private final MainPrisonerListPresenter mPresenter;

    public MainPrisonerAdapter(Context context, NoEntriesListener listener) {
        super(context);
        mPresenter = new MainPrisonerListPresenterImpl(this, mContext, listener);
        mPresenter.getAllPrisoners();
    }

    public class ViewHolder extends PrisonerAdapter.ViewHolder implements MainPrisonerRowView {
        @BindView(R.id.prisoner_name)
        TextView mName;

        ViewHolder(View itemView) {
            super(itemView);
        }


        @Override
        public void onPrisonerClicked(View view) {
            mPresenter.navigateToPrisonersHome(this);
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

    @Override
    public void onBindViewHolder(@NonNull PrisonerAdapter.ViewHolder holder, int position) {
        mPresenter.onBindPrisonerView(holder, position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getPrisonerRowCount();
    }

    public void appendPrisoner(final long id) {
        mPresenter.appendPrisoner(id);
    }
}
