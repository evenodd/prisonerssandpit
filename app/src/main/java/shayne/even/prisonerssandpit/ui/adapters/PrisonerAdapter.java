package shayne.even.prisonerssandpit.ui.adapters;

import android.content.Context;
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
import shayne.even.prisonerssandpit.ui.views.PrisonerListView;
import shayne.even.prisonerssandpit.ui.views.PrisonerRowView;

/**
 * Created by Shayne Even on 27/05/2018.
 */

public abstract class PrisonerAdapter extends RecyclerView.Adapter<PrisonerAdapter.ViewHolder>
        implements PrisonerListView {

    protected Context mContext;

    public PrisonerAdapter(Context context) {
        mContext = context;
    }


    public abstract class ViewHolder extends RecyclerView.ViewHolder implements PrisonerRowView {
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
        public abstract void onPrisonerClicked(View view);
    }

    public View inflateViewGroup(@NonNull ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prisoner_item, parent, false);
    }

    @Override
    public void notifyPrisonerDataChanged() {
        notifyDataSetChanged();
    }
}
