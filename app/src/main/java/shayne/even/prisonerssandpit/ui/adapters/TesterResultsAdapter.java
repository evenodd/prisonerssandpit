package shayne.even.prisonerssandpit.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.ui.activities.TestingActivity;
import shayne.even.prisonerssandpit.ui.presenters.TesterResultsPresenter;
import shayne.even.prisonerssandpit.ui.presenters.TesterResultsPresenterImpl;
import shayne.even.prisonerssandpit.ui.views.TesterResultItemView;
import shayne.even.prisonerssandpit.ui.views.TesterResultsListView;

/**
 * Adapter for a list of test results between two prisoners
 */

public class TesterResultsAdapter extends RecyclerView.Adapter<TesterResultsAdapter.ViewHolder>
        implements TesterResultsListView {
    protected Context mContext;
    private final TesterResultsPresenter mPresenter;

    /**
     * Creates a TesterResultsAdapter
     * @param context the context of the list
     * @param prisoner the prisoner to be tested
     * @param tester the prisoner to be tested against
     * @param listener handles updates to the test results
     */
    public TesterResultsAdapter(Context context, long prisoner, long tester,
                                OnScoreUpdateListener listener) {
        mContext = context;
        mPresenter = new TesterResultsPresenterImpl(mContext, this, prisoner, tester, listener);
    }

    /**
     * View Holder class manages the view of each individual list item
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements TesterResultItemView {

        @BindView(R.id.prisoner_action_result)
        TextView mPrisonerAction;

        @BindView(R.id.tester_action_result)
        TextView mTesterAction;

        @BindView(R.id.prisoner_result_progress_bar)
        ProgressBar mPrisonerProgress;

        @BindView(R.id.tester_result_progress_bar)
        ProgressBar mTesterProgress;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void displayProgress() {
            mPrisonerProgress.setVisibility(View.VISIBLE);
            mTesterProgress.setVisibility(View.VISIBLE);
        }

        @Override
        public void hideProgress() {
            mPrisonerProgress.setVisibility(View.INVISIBLE);
            mTesterProgress.setVisibility(View.INVISIBLE);
        }

        @Override
        public void setPrisonerAction(String action) {
            mPrisonerAction.setText(action);
        }

        @Override
        public void setTesterAction(String action) {
            mTesterAction.setText(action);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.testing_results_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mPresenter.bindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getItemCount();
    }

    @Override
    public void notifyDataChanged() {
        notifyDataSetChanged();
    }
}
