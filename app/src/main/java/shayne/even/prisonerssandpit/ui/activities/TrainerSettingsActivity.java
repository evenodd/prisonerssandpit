package shayne.even.prisonerssandpit.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.models.Prisoner;
import shayne.even.prisonerssandpit.ui.dialogs.PrisonerSelectDialog;
import shayne.even.prisonerssandpit.service.AgentTrainerService;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerSelectPresenter;
import shayne.even.prisonerssandpit.ui.presenters.TrainerSettingsPresenter;
import shayne.even.prisonerssandpit.ui.presenters.TrainerSettingsPresenterImpl;
import shayne.even.prisonerssandpit.ui.views.TrainerSettingsView;

public class TrainerSettingsActivity extends AppCompatActivity implements TrainerSettingsView,
        PrisonerSelectPresenter.OnSelectListener {

    public static final String PRISONER_ID_EXTRA = "prisoner_id_extra";

    @BindView(R.id.select_trainer_radio_group)
    RadioGroup mTrainerRadioGroup;

    @BindView(R.id.trainer_settings_episodes_spinner)
    Spinner mEpisodesSpinner;

    @BindView(R.id.training_notification_checkbox)
    CheckBox mNotificationCheckbox;

    @BindView(R.id.trainer_settings_train_button)
    Button mTrainButton;

    @BindView(R.id.other_prisoner_trainer_btn)
    RadioButton mOtherPrisonerTrainerButton;

    private TrainerSettingsPresenter mPresenter;

    @OnClick(R.id.trainer_settings_train_button)
    void onTrainButtonClick(View view) {
        mPresenter.startTrainerService();
    }

    @OnClick(R.id.other_prisoner_trainer_btn)
    void onOtherPrisonerRadioButtonClicked (View view) {
        if (mOtherPrisonerTrainerButton.isChecked()) {
            mPresenter.getPrisonerFromUser();
        }
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
    public void startTrainerService(long prisoner, TrainerSettingsPresenter.TrainerOption trainer,
                                    String episodeOption, boolean shouldPushNotification,
                                    Long prisonerTrainer) {
        Intent intent = new Intent(this, AgentTrainerService.class)
                .putExtra(AgentTrainerService.PRISONER_ID_EXTRA, getPrisonerId())
                .putExtra(AgentTrainerService.TRAINER_EXTRA, trainer.getValue())
                .putExtra(AgentTrainerService.EPISODE_OPTION_EXTRA, episodeOption)
                .putExtra(AgentTrainerService.PUSH_NOTIFICATION_EXTRA, shouldPushNotification);
        if (prisonerTrainer != null) {
            intent.putExtra(AgentTrainerService.PRISONER_TRAINER_EXTRA, prisonerTrainer);
        }

        startService(intent);
    }

    @Override
    public void startPrisonerSelectDialog(long excludedPrisoner) {
        PrisonerSelectDialog prisonerSelectDialog = new PrisonerSelectDialog(
                this,
                excludedPrisoner,
                this
        );
        prisonerSelectDialog.setTitle(R.string.select_a_prisoner_recycler_view_text);
        prisonerSelectDialog.show();
    }

    @Override
    public void setSelectedAgent(String name) {
        mOtherPrisonerTrainerButton.setText(String.format(
                getString(R.string.other_prisoner_agent_radio_button_txt),
                name
        ));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_settings);
        ButterKnife.bind(this);

        mPresenter = new TrainerSettingsPresenterImpl(this);
    }

    @Override
    public void onSelect(Prisoner prisoner) {
        mPresenter.handleSelectPrisoner(prisoner);
    }
}
