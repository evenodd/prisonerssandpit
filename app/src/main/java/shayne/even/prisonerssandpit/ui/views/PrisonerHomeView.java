package shayne.even.prisonerssandpit.ui.views;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

/**
 * User interface displays information about a Prisoner agent and lets the user navigate to the
 * prisoner's Test Vs and Train views
 */

public interface PrisonerHomeView {
    /**
     * Displays the specified name as the prisoner's name
     * @param name the string to display
     */
    void setName(String name);

    /**
     * Returns the id of the prisoner this view is for
     * @return
     */
    long getPrisonerId();

    /**
     * Opens the Tester Select Dialog
     * @param excludedPrisoner the prisoner not to include in the list
     */
    void startTesterSelectDialog(long excludedPrisoner);

    /**
     * Opens the Trainer Options View
     */
    void navigateToTrainerOptions();

    /**
     * Opens the testing view
     * @param prisonerId the prisoner to test
     * @param testerId the prisoner to be tested against
     */
    void startTestingView(long prisonerId, long testerId);

    /**
     * Displays the passed string as the status
     * @param status the string to display
     */
    void setStatus(String status);

    /**
     * Displays the performance chart with the specified data
     * @param xAxisLabels the labels for each bar in the chart
     * @param scores the values for each bar
     */
    void setPerformanceChartData(List<String> xAxisLabels, BarDataSet... scores);

    /**
     * Displays the specified string as the alpha value
     * @param s the value to display
     */
    void setAlphaText(String s);

    /**
     * Displays the specified string as the gamma value
     * @param s the value to display
     */
    void setGammaText(String s);

    /**
     * Starts the Vs View
     * @param prisonerId the id of the prisoner to vs
     */
    void navigateToVs(long prisonerId);
}
