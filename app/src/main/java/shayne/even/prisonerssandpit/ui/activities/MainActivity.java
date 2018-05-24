package shayne.even.prisonerssandpit.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.adapters.PrisonerAdapter;
import shayne.even.prisonerssandpit.ui.presenters.MainPresenter;
import shayne.even.prisonerssandpit.ui.presenters.MainPresenterImpl;
import shayne.even.prisonerssandpit.ui.views.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.main_activity_prisoner_recycler_view)
    RecyclerView mPrisonerRecyclerView;

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
        mPrisonerRecyclerView.setAdapter(new PrisonerAdapter(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AddPrisonerActivity.ADD_PRISONER_REQUEST :
                mPresenter.handleAddPrisonerResult(
                        resultCode,
                        data.getLongExtra(AddPrisonerActivity.NEW_PRISONER_ID, -1)
                );
                break;
        }
    }

    @Override
    public boolean okResultCode(int resultCode) {
        return resultCode == Activity.RESULT_OK;
    }

    @OnClick(R.id.fab)
    public void handleFloatingActionButtonClick(View view) {
        mPresenter.navigateToAddPrisonerView();
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
        ((PrisonerAdapter) mPrisonerRecyclerView.getAdapter()).appendPrisoner(id);
    }

    @Override
    public void displayAddPrisonerError() {
        Toast.makeText(this, R.string.failed_add_prisoner_msg, Toast.LENGTH_SHORT)
                .show();
    }
}
