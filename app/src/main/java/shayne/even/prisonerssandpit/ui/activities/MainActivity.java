package shayne.even.prisonerssandpit.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.ui.adapters.MainPrisonerAdapter;
import shayne.even.prisonerssandpit.ui.presenters.MainPresenter;
import shayne.even.prisonerssandpit.ui.presenters.MainPresenterImpl;
import shayne.even.prisonerssandpit.ui.views.MainView;

/**
 * Main activity lists all the prisoner agents and lets users start the AddPrisoner Activity
 */

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.main_activity_prisoner_recycler_view)
    RecyclerView mPrisonerRecyclerView;

    @BindView(R.id.fab)
    FloatingActionButton mAddPrisonerFab;

    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        mPresenter = new MainPresenterImpl(this);

        mPrisonerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPrisonerRecyclerView.setAdapter(new MainPrisonerAdapter(
                this,
                mPresenter.getNoEntriesListener()
        ));
    }

    /**
     * Notifies the presenter that result from another activity are available
     * @param requestCode the request code to start the activity
     * @param resultCode the result code from the other activity
     * @param data the intent data from the other activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AddPrisonerActivity.ADD_PRISONER_REQUEST :
                if (data != null) {
                    mPresenter.handleAddPrisonerResult(
                            resultCode,
                            data.getLongExtra(AddPrisonerActivity.NEW_PRISONER_ID, -1)
                    );
                }
                else {
                    mPresenter.handleInvalidResultFromAddPrisonerView();
                }
                break;
        }
    }

    /**
     * Notifies the presenter the floating action button has been clicked
     * @param view the view of the fab
     */
    @OnClick(R.id.fab)
    public void handleFloatingActionButtonClick(View view) {
        mPresenter.navigateToAddPrisonerView();
    }

    @Override
    public void showAddPrisonerTapTarget() {
        TapTargetView.showFor(this,
                TapTarget.forView(
                        mAddPrisonerFab,
                        getString(R.string.add_prisoner_fab_tap_target_title),
                        getString(R.string.add_prisoner_fab_tap_target_desc)
                )
                        .titleTextColor(R.color.textPrimary)
                        .descriptionTextColor(R.color.textSecondary)
                        .targetCircleColor(R.color.colorAccent)
                        .outerCircleColor(R.color.colorPrimary)
                        .targetRadius(60)
                        .icon(ContextCompat
                                .getDrawable(this,R.drawable.ic_action_add_person))
        );
    }

    @Override
    public boolean okResultCode(int resultCode) {
        return resultCode == Activity.RESULT_OK;
    }

    @Override
    public void navigateToAddPrisonerView() {
        startActivityForResult(
                new Intent(this, AddPrisonerActivity.class),
                AddPrisonerActivity.ADD_PRISONER_REQUEST
        );
    }

    @Override
    public void addPrisonerToList(long id) {
        ((MainPrisonerAdapter) mPrisonerRecyclerView.getAdapter()).appendPrisoner(id);
    }

    @Override
    public void displayAddPrisonerError() {
        Toast.makeText(this, R.string.failed_add_prisoner_msg, Toast.LENGTH_SHORT)
                .show();
    }
}
