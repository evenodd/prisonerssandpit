package shayne.even.prisonerssandpit.ui.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.ui.adapters.SelectPrisonerAdapter;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerSelectPresenter;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerSelectPresenterImpl;
import shayne.even.prisonerssandpit.ui.views.PrisonerSelectView;

/**
 * Dialog that lets users select from a list of prisoners
 */

public class PrisonerSelectDialog extends AppCompatDialog implements PrisonerSelectView {

    private final long mExcludedPrisoner;

    @BindView(R.id.prisoner_select_recycler_view)
    RecyclerView mPrisonerSelectRecyclerView;

    private PrisonerSelectPresenter mPresenter;

    /**
     * Creates a PrisonerSelectDialog
     * @param context the context of the activity to display the dialog
     * @param excludedPrisoner the prisoner to exclude in the list
     * @param listener handles the selection of a prisoner
     */
    public PrisonerSelectDialog(Context context, long excludedPrisoner,
                                PrisonerSelectPresenter.OnSelectListener listener) {
        super(context);
        mPresenter = new PrisonerSelectPresenterImpl(this, listener);
        mExcludedPrisoner = excludedPrisoner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisoner_select);
        ButterKnife.bind(this);
        mPrisonerSelectRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPrisonerSelectRecyclerView.setAdapter(
                new SelectPrisonerAdapter(getContext(), mPresenter.getListener())
        );
        mPresenter.populateList();
    }

    @Override
    public long getExcludePrisonerId() {
        return mExcludedPrisoner;
    }

    @Override
    public void populatePrisonersList(long excludedPrisoner) {
        ((SelectPrisonerAdapter) mPrisonerSelectRecyclerView.getAdapter()).addAllPrisoners(excludedPrisoner);
    }

    @Override
    public void closeDialog() {
        dismiss();
    }
}
