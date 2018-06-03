package shayne.even.prisonerssandpit.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skyfishjy.library.RippleBackground;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.ui.presenters.VsPresenter;
import shayne.even.prisonerssandpit.ui.presenters.VsPresenterImpl;
import shayne.even.prisonerssandpit.ui.views.VsView;

/**
 * Activity that lets the user vs a prisoner agent directly in a Prisoner's Dilemma
 */

public class VsActivity extends AppCompatActivity implements VsView {

    public static final String PRISONER_ID_EXTRA = "prisoner_id_extra";

    @BindView(R.id.round_circle)
    RippleBackground mRoundCircle;

    @BindView(R.id.vs_activity_heading)
    TextView mHeadingTextView;

    @BindView(R.id.vs_activity_round_number)
    TextView mRoundNumberTextView;

    @BindView(R.id.next_round_text_view)
    TextView mNextRoundTextView;

    @BindView(R.id.cooperate_button)
    Button mCooperateButton;

    @BindView(R.id.betray_button)
    Button mBetrayButton;

    @BindView(R.id.opponents_move_text_view)
    TextView mOpponentsMoveTextView;

    @BindView(R.id.your_score)
    TextView mYourScoreTextView;

    @BindView(R.id.opponent_score)
    TextView mOpponentsScoreTextView;

    @BindView(R.id.opponent_move_progress_bar)
    ProgressBar mOpponentActionProgressBar;

    VsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs);
        ButterKnife.bind(this);
        mPresenter = new VsPresenterImpl(this, this);
    }

    /**
     * Notifies presenter the betray button has been clicked
     * @param view the view of the clicked button
     */
    @OnClick(R.id.betray_button)
    public void onBetrayButtonClicked(View view) {
        mPresenter.onBetrayAction();
    }

    /**
     * Notifies the presenter the cooperate button has been clicked
     * @param view the view of the clicked button
     */
    @OnClick(R.id.cooperate_button)
    public void onCooperateButtonClicked(View view) {
        mPresenter.onCooperateAction();
    }

    /**
     * Notifies the presenter the next round button has been clicked
     * @param view the view of the clicked button
     */
    @OnClick(R.id.next_round_text_view)
    public void onNextRoundClick(View view) {
        mPresenter.handleNextRoundButtonClicked();
    }

    @Override
    public void startRippleAnimation() {
        mRoundCircle.startRippleAnimation();
    }

    @Override
    public long getPrisonerId() {
        return getIntent().getLongExtra(PRISONER_ID_EXTRA, -1);
    }

    @Override
    public void setPrisonerName(String name) {
        mHeadingTextView.setText(
                String.format(getString(R.string.vs_activity_header_format), name)
        );
    }

    @Override
    public void setYourScore(String s) {
        mYourScoreTextView.setText(s);
    }

    @Override
    public void setOpponentsScore(String s) {
        mOpponentsScoreTextView.setText(s);
    }

    @Override
    public void setOpponentAction(String s) {
        mOpponentsMoveTextView.setText(s);
    }

    @Override
    public void setRound(String s) {
        mRoundNumberTextView.setText(s);
    }

    @Override
    public void setOpponentActionProgressBar() {
        mOpponentActionProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideOpponentActionProgressBar() {
        mOpponentActionProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNextRoundButton() {
        mNextRoundTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNextRoundButton() {
        mNextRoundTextView.setVisibility(View.GONE);
    }

    @Override
    public void disableActionButtons() {
        mCooperateButton.setEnabled(false);
        mBetrayButton.setEnabled(false);
    }

    @Override
    public void enableActionButtons() {
        mCooperateButton.setEnabled(true);
        mBetrayButton.setEnabled(true);
    }
}
