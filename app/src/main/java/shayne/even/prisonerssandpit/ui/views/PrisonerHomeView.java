package shayne.even.prisonerssandpit.ui.views;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

/**
 * Created by Shayne Even on 13/05/2018.
 */

public interface PrisonerHomeView {
    void setName(String name);

    long getPrisonerId();

    void startTesterSelectDialog(long excludedPrisoner);

    void navigateToTrainerOptions();

    void startTestingActivity(long prisonerId, long uid);

    void setStatus(String status);

    void setPerformanceChartData(List<String> xAxisLabels, BarDataSet... scores);

    void setAlphaText(String s);

    void setGammaText(String s);

    void navigateToVs(long prisonerId);
}
