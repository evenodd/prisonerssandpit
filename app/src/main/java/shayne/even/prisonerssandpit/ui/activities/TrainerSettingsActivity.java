package shayne.even.prisonerssandpit.ui.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.ui.presenters.AgentTrainerService;
import shayne.even.prisonerssandpit.ui.presenters.TrainerSettingsPresenter;
import shayne.even.prisonerssandpit.ui.presenters.TrainerSettingsPresenterImpl;
import shayne.even.prisonerssandpit.ui.views.TrainerSettingsView;

public class TrainerSettingsActivity extends AppCompatActivity implements TrainerSettingsView {

    public static final String PRISONER_ID_EXTRA = "prisoner_id_extra";

    @BindView(R.id.select_trainer_radio_group)
    RadioGroup mTrainerRadioGroup;

    @BindView(R.id.trainer_settings_episodes_spinner_label)
    Spinner mEpisodesSpinner;

    @BindView(R.id.training_notification_checkbox)
    CheckBox mNotificationCheckbox;

    @BindView(R.id.trainer_settings_train_button)
    Button mTrainButton;

    TrainerSettingsPresenter mPresenter;

    @OnClick(R.id.trainer_settings_train_button)
    void onTrainButtonClick(View view) {
        mPresenter.startTrainerService();
    }

    @Override
    public TrainerSettingsPresenter.TrainerOption getSelectedTrainer() {
        switch(mTrainerRadioGroup.getCheckedRadioButtonId()) {
            case R.id.coop_trainer_radio_btn :
                return TrainerSettingsPresenter.TrainerOption.COOP;
            case R.id.betray_trainer_radio_btn:
                return TrainerSettingsPresenter.TrainerOption.BETRAYER;
            case R.id.tit_for_tat_trainer_radio_btn :
                return TrainerSettingsPresenter.TrainerOption.TIT_FOR_TAT;
            case R.id.other_prisoner_trainer_btn :
                return TrainerSettingsPresenter.TrainerOption.PRISONER_AGENT;
        }
        return null;
    }

    @Override
    public String getSelectedEpisodeOption() {
        return mEpisodesSpinner.getSelectedItem().toString();
    }

    @Override
    public boolean isTrainerNotificationChecked() {
        return mNotificationCheckbox.isChecked();
    }

    @Override
    public void displayTrainerErrorMessage() {
        Toast.makeText(this, R.string.trainer_settings_error_msg, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public long getPrisonerId() {
        return getIntent().getLongExtra(PRISONER_ID_EXTRA, -1);
    }

    @Override
    public void startTrainerService(long prisonerId) {
        startService(
                new Intent(this, AgentTrainerService.class)
                        .putExtra(PRISONER_ID_EXTRA, getPrisonerId())
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_settings);
        ButterKnife.bind(this);

        mPresenter = new TrainerSettingsPresenterImpl(this);
    }
}
