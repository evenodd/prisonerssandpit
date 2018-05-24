package shayne.even.prisonerssandpit.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.ui.presenters.AddPrisonerPresenter;
import shayne.even.prisonerssandpit.ui.presenters.AddPrisonerPresenterImpl;
import shayne.even.prisonerssandpit.ui.views.AddPrisonerView;

/**
 * Created by Shayne Even on 23/04/2018.
 */

public class AddPrisonerActivity extends AppCompatActivity implements AddPrisonerView{

    public static final int ADD_PRISONER_REQUEST = 1;
    public static final String NEW_PRISONER_ID = "new_prisoner_id";

    @BindView(R.id.add_prisoner_activity_submit_button)
    Button mSubmitButton;

    @BindView(R.id.add_prisoner_activity_progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.add_prisoner_activity_name_input)
    EditText mNameEditText;

    @BindView(R.id.add_prisoner_activity_alpha_input)
    EditText mAlphaEditText;

    @BindView(R.id.add_prisoner_activity_gamma_input)
    EditText mGammaEditText;

    private AddPrisonerPresenter mAddPrisonerPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prisoner);

        ButterKnife.bind(this);
        mAddPrisonerPresenter = new AddPrisonerPresenterImpl(this);
    }

    @OnClick(R.id.add_prisoner_activity_submit_button)
    public void addPrisoner(View view) {
        mAddPrisonerPresenter.addPrisoner(
                mNameEditText.getText().toString(),
                mAlphaEditText.getText().toString(),
                mGammaEditText.getText().toString(),
                this
        );
    }

    @Override
    public void showProgress() {
        mSubmitButton.setEnabled(false);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mSubmitButton.setEnabled(true);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void returnResults(long newPrisonerId) {
        Intent result = new Intent();
        result.putExtra(NEW_PRISONER_ID, newPrisonerId);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    public void setNameErrorMessage() {
        Toast.makeText(this, getString(R.string.invalid_name_msg), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setAlphaErrorMessage() {
        Toast.makeText(this, getString(R.string.invalid_alpha_msg), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setGammaErrorMessage() {
        Toast.makeText(this, getString(R.string.invalid_gamma_msg), Toast.LENGTH_SHORT)
                .show();
    }
}
