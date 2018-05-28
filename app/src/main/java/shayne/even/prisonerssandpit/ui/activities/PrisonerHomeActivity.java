package shayne.even.prisonerssandpit.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.ui.dialogs.PrisonerSelectDialog;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerHomePresenter;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerHomePresenterImpl;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerSelectPresenter;
import shayne.even.prisonerssandpit.ui.views.PrisonerHomeView;

public class PrisonerHomeActivity extends AppCompatActivity implements PrisonerHomeView,
        PrisonerSelectPresenter.OnSelectListener {

    public static final String PRISONER_ID = "prisoner_id_extra";
    @BindView(R.id.prisoner_home_status_text_view)
    TextView mStatusTextView;

    @BindView(R.id.prisoner_home_activity_prisoner_name)
    TextView mNameTextView;

    @BindView(R.id.prisoner_home_betray_score)
    TextView mBetrayScoreTextView;

    @BindView(R.id.prisoner_home_coop_score)
    TextView mCoopScoreTextView;

    @BindView(R.id.prisoner_home_titfortat_score)
    TextView mTitForTatScoreTextView;

    @BindView(R.id.navigation)
    BottomNavigationView mBottomNavigationView;

    PrisonerHomePresenter mPresenter;

    @Override
    public void setName(String name) {
        mNameTextView.setText(name);
    }

    @Override
    public void setBetrayScore(String score) {
        mBetrayScoreTextView.setText(score);
    }

    @Override
    public void setCoopScore(String score) {
        mCoopScoreTextView.setText(score);
    }

    @Override
    public void setTitFirTatScore(String score) {
        mTitForTatScoreTextView.setText(score);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisoner_home);
        ButterKnife.bind(this);

        mBottomNavigationView
                .setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mPresenter = new PrisonerHomePresenterImpl(this, this);
        mPresenter.getPrisoner();
    }

    @Override
    public long getPrisonerId() {
        return getIntent().getLongExtra(PRISONER_ID, -1);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_train :
                    mPresenter.navigateToTrainerOptions();
                    return true;
                case R.id.navigation_test :
                    mPresenter.navigateToPrisonerTester();
                    return true;
            }
            return false;
        }
    };

    @Override
    public void startTesterSelectDialog(long excludedPrisoner) {
        PrisonerSelectDialog prisonerSelectDialog = new PrisonerSelectDialog(
                this,
                excludedPrisoner,
                this
        );
        prisonerSelectDialog.setTitle(R.string.select_tester_dialog_title);
        prisonerSelectDialog.show();
    }

    @Override
    public void navigateToTrainerOptions() {
        Intent intent = new Intent(this, TrainerSettingsActivity.class);
        intent.putExtra(TrainerSettingsActivity.PRISONER_ID_EXTRA, getPrisonerId());
        startActivity(intent);
    }

    @Override
    public void startTestingActivity(long prisonerId, long testerId) {
        startActivity(new Intent(this, TestingActivity.class)
                .putExtra(TestingActivity.PRISONER_ID_EXTRA, prisonerId)
                .putExtra(TestingActivity.PRISONER_TESTER_ID_EXTRA, testerId)
        );
    }

    @Override
    public void setStatus(String status) {
        mStatusTextView.setText(status);
    }

    @Override
    public void onSelect(Prisoner tester) {
        mPresenter.handleTesterSelected(tester);
    }
}
