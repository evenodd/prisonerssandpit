package shayne.even.prisonerssandpit.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shayne.even.prisonerssandpit.R;
import shayne.even.prisonerssandpit.ui.dialogs.PrisonerSelectDialog;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerHomePresenter;
import shayne.even.prisonerssandpit.ui.presenters.PrisonerHomePresenterImpl;
import shayne.even.prisonerssandpit.ui.views.PrisonerHomeView;

/**
 * Displays information on a Prisoner Agent and lets the user navigate to the Vs test And Train
 * views for that prisoner
 */

public class PrisonerHomeActivity extends AppCompatActivity implements PrisonerHomeView {

    public static final String PRISONER_ID = "prisoner_id_extra";
    @BindView(R.id.prisoner_home_status_text_view)
    TextView mStatusTextView;

    @BindView(R.id.prisoner_home_activity_prisoner_name)
    TextView mNameTextView;

    @BindView(R.id.navigation)
    BottomNavigationView mBottomNavigationView;

    @BindView(R.id.performance_bar_chart)
    HorizontalBarChart mPerformanceBarChart;

    @BindView(R.id.prisoner_home_alpha_value)
    TextView mAlphaTextView;

    @BindView(R.id.prisoner_home_gamma_value)
    TextView mGammaTextView;

    PrisonerHomePresenter mPresenter;

    @Override
    public void setName(String name) {
        mNameTextView.setText(name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisoner_home);
        ButterKnife.bind(this);

        mBottomNavigationView
                .setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mPresenter = new PrisonerHomePresenterImpl(this, this);
    }

    @Override
    public long getPrisonerId() {
        return getIntent().getLongExtra(PRISONER_ID, -1);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        /**
         * {@inheritDoc}
         * Notifies the presenter a navigation item has been selected
         * @param item
         * @return
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_train :
                    mPresenter.navigateToTrainerOptions();
                    return true;
                case R.id.navigation_test :
                    mPresenter.navigateToPrisonerTester();
                    return true;
                case R.id.navigation_vs:
                    mPresenter.navigateToVs();
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
                mPresenter.getOnSelectListener()
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
    public void startTestingView(long prisonerId, long testerId) {
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
    public void setPerformanceChartData(final List<String> xAxisLabels, BarDataSet... scores) {
        mPerformanceBarChart.animateY(1000);
        mPerformanceBarChart.getDescription().setEnabled(false);

        mPerformanceBarChart.setMaxVisibleValueCount(4);
        mPerformanceBarChart.setPinchZoom(false);
        mPerformanceBarChart.setDrawGridBackground(false);

        XAxis xAxis = mPerformanceBarChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setAxisLineColor(ContextCompat.getColor(this, R.color.textSecondary));
        xAxis.setGridColor(ContextCompat.getColor(this, R.color.textSecondary));
        xAxis.setTextColor(ContextCompat.getColor(this, R.color.textSecondary));
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                try {
                    return xAxisLabels.get((int) value);
                } catch (IndexOutOfBoundsException e) {
                    return "";
                }
            }
        });

        mPerformanceBarChart.getAxisLeft().setDrawAxisLine(true);
        mPerformanceBarChart.getAxisLeft().setDrawGridLines(false);
        mPerformanceBarChart.getAxisLeft().setAxisMinimum(0f);
        mPerformanceBarChart.getAxisLeft().setAxisMaximum(70f);

        mPerformanceBarChart.getLegend().setEnabled(false);
        mPerformanceBarChart.setDoubleTapToZoomEnabled(false);
        mPerformanceBarChart.setPinchZoom(false);


        int[] colours = {Color.GREEN, Color.RED, Color.BLUE};
        int colourCounter = 0;
        for (BarDataSet score : scores) {
            score.setColor(colours[colourCounter]);
            colourCounter = (colourCounter + 1) % colours.length;
            score.setDrawValues(true);
        }

        BarData data = new BarData(scores);
        data.setValueTextSize(10);
        data.setValueTextColor(ContextCompat.getColor(this, R.color.textSecondary));
        data.setDrawValues(true);
        data.setValueFormatter(new DefaultValueFormatter(0));

        mPerformanceBarChart.setData(data);
        mPerformanceBarChart.invalidate();
    }

    @Override
    public void setAlphaText(String s) {
        mAlphaTextView.setText(s);
    }

    @Override
    public void setGammaText(String s) {
        mGammaTextView.setText(s);
    }

    @Override
    public void navigateToVs(long prisonerId) {
        startActivity(
                new Intent(this, VsActivity.class)
                        .putExtra(VsActivity.PRISONER_ID_EXTRA, prisonerId)
        );
    }
}
