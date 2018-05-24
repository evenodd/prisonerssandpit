package shayne.even.prisonerssandpit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.ui.activities.PrisonerHomeActivity;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerListPresenter;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerListPresenterImpl;
import shayne.even.prisonerssandpit.ui.views.PrisonerListView;
import shayne.even.prisonerssandpit.ui.views.PrisonerRowView;

/**
 * Created by Shayne Even on 23/04/2018.
 */
public class PrisonerAdapter extends RecyclerView.Adapter<PrisonerAdapter.ViewHolder>
        implements PrisonerListView {

    private final Context mContext;
    private final PrisonerListPresenter mPresenter;

    public PrisonerAdapter(Context context) {
        mContext = context;
        mPresenter = new PrisonerListPresenterImpl(this, mContext);
        mPresenter.getAllPrisoners();
    }

    @Override
    public void notifyPrisonerDataChanged() {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements PrisonerRowView {
        @BindView(R.id.prisoner_name)
        TextView mName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setName(String name) {
            mName.setText(name);
        }

        @Override
        public int getPositionInAdapter() {
            return getAdapterPosition();
        }

        @Override
        public Context getContext() {
            return mContext;
        }

        @OnClick(R.id.prisoner_name)
        public void startHomeActivity(View view) {
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
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.prisoner_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
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
