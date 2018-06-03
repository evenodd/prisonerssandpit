package shayne.even.prisonerssandpit.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.ui.adapters.TesterResultsAdapter;
import shayne.even.prisonerssandpit.ui.presenters.TestingPresenter;
import shayne.even.prisonerssandpit.ui.presenters.TestingPresenterImpl;
import shayne.even.prisonerssandpit.ui.views.TestingView;

/**
 * Activity that tests the performance when two prisoners are opposing each other and displays the
 * results
 */

public class TestingActivity extends AppCompatActivity implements TestingView {

    @BindView(R.id.prisoner_name_title)
    TextView mPrisonerNameTitleTextView;

    @BindView(R.id.tester_name_title)
    TextView mTesterNameTitleTextView;

    @BindView(R.id.prisoner_score_heading)
    TextView mPrisonerScoreHeadingTextView;

    @BindView(R.id.tester_score_heading)
    TextView mTesterScoreHeadingTitle;

    @BindView(R.id.prisoner_score)
    TextView mPrisonerScoreTextView;

    @BindView(R.id.tester_score)
    TextView mTesterScoreTextView;

    @BindView(R.id.results_list_recycler_view)
    RecyclerView mResultsListRecyclerView;

    private TestingPresenter mPresenter;

    public static final String
            PRISONER_ID_EXTRA = "prisoner_id_extra",
            PRISONER_TESTER_ID_EXTRA = "prisoner_tester_id_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        ButterKnife.bind(this);
        mPresenter = new TestingPresenterImpl(this, this);
        mResultsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mResultsListRecyclerView.setAdapter(new TesterResultsAdapter(
                this,
                getPrisonerId(),
                getTesterId(),
                mPresenter.getScoreUpdaterListener()
        ));
    }

    @Override
    public long getPrisonerId() {
        return getIntent().getLongExtra(PRISONER_ID_EXTRA, -1);
    }

    @Override
    public long getTesterId() {
        return getIntent().getLongExtra(PRISONER_TESTER_ID_EXTRA, -1);
    }

    @Override
    public void setPrisonerNameTitle(String name) {
        mPrisonerNameTitleTextView.setText(name);
    }

    @Override
    public void setPrisonerScoreHeading(String name) {
        mPrisonerScoreHeadingTextView.setText(String.format(
                getString(R.string.testing_activity_score_heading_format),
                name
        ));
    }

    @Override
    public void setTesterNameTitle(String name) {
        mTesterNameTitleTextView.setText(name);
    }

    @Override
    public void setTesterScoreHeading(String name) {
        mTesterScoreHeadingTitle.setText(String.format(
                getString(R.string.testing_activity_score_heading_format),
                name
        ));
    }

    @Override
    public void setPrisonerScore(String s) {
        mPrisonerScoreTextView.setText(s);
    }

    @Override
    public void setTesterScore(String s) {
        mTesterScoreTextView.setText(s);
    }

}
