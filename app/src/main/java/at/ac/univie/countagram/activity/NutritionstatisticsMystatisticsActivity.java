package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.CaloryIntakeManager;
import at.ac.univie.countagram.logic.ProductManager;
import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.model.CaloryIntake;
import at.ac.univie.countagram.model.Product;

/**
 * NutritionstatisticsMystatisticsActivity shows the User's nutrition statistics (i.e. calory intake sum) over
 * the past week by the means of a line chart.
 * The class uses the external library com.github.mikephil.charting to create the chart.
 */

public class NutritionstatisticsMystatisticsActivity extends AppCompatActivity {

    /**
     * Instance variables
     */
    private LineChart lineChart;
    private CaloryIntakeManager caloryIntakeManager;
    private SettingsSingleTon setting;

    /**
     * onCreate sets the corresponding ContentView and the settings.
     * It also sets the line chart to be ready to take data.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritionstatistics_mystatistics);
        setting = SettingsSingleTon.getInstance();
        caloryIntakeManager = new CaloryIntakeManager();
        lineChart = (LineChart) findViewById(R.id.plot);
        setData();

    }

    /**
     * logoOnClick forwards the User to CaloryIntakeActivity upon click of the countagram logo.
     * @param view
     */
    protected  void logoOnlick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));
        finish();
    }

    /**
     * statisticsOnclick forwards the User to NutritionstatisticsMainActivity upon click of the statistics button.
     * @param view
     */
    protected void statisticsOnclick(View view){
        startActivity(new Intent(this, NutritionstatisticsMainActivity.class));
        finish();
    }

    /**
     * recipesOnclick forwards the User to SearchRecipeActivity upon click of the recipes button.
     * @param view
     */
    protected void recipesOnclick(View view){
        startActivity(new Intent(this, SearchRecipeActivity.class));
        finish();
    }

    /**
     * homeOnClick back to CaloryIntakeActivity upon click of the homeButton.
     * @param view
     */
    protected  void homeOnClick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));

    }

    /**
     * addIntakeOnclick forwards the User to AddcaloryintakeActivty upon click of the plus symbol button.
     * @param view
     */
    protected void addIntakeOnclick(View view){
        startActivity(new Intent(this, AddcaloryintakeActivty.class));
        finish();
    }

    /**
     * competitionOnclick forwards the User to CompetitionMainActivity upon click of the gamepad symbol button.
     * @param view
     */
    protected void competitionOnclick(View view){
        startActivity(new Intent(this, CompetitionMainActivity.class));
        finish();
    }

    /**
     * settingsOnclick forwards the User to MyProfileActivity upon click of the settings symbol button.
     * @param view
     */
    protected void settingsOnclick(View view){
        startActivity(new Intent(this, MyProfileActivity.class));
        finish();
    }

    /**
     * backToStatisticsMainMenuOnClick forwards the User to NutritionstatisticsMainActivity upon click of the return button.
     * @param view
     */
    protected void backToStatisticsMainMenuOnClick(View view){
        startActivity(new Intent(this, NutritionstatisticsMainActivity.class));
        finish();
    }

    /**
     * setData sets the data of the line chart.
     * It takes the calory intake data of the User for the past week via the CaloryIntakeManager.
     * Then it plots the data as a line chart.
     */
    private void setData(){
        Description des = lineChart.getDescription();
        des.setEnabled(false);
        lineChart.getLegend().setEnabled(false);

        ArrayList<Entry> values = caloryIntakeManager.getValues(setting.getUser().getId());
        LineDataSet set1;

        set1 = new LineDataSet(values, "DataSet 1");

        set1.setDrawIcons(false);

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleRadius(4f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);
        set1.setFormLineWidth(1f);
        set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        set1.setFormSize(15.f);

        if (Utils.getSDKInt() >= 18) {
            // fill drawable only supported on api level 18 and above
            //Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.fruit);
            set1.setFillColor(Color.GREEN);
        }
        else {
            set1.setFillColor(Color.BLACK);
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        final ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("7 ago");
        xLabel.add("6 ago");
        xLabel.add("5 ago");
        xLabel.add("4 ago");
        xLabel.add("3 ago");
        xLabel.add("2 ago");
        xLabel.add("1 ago");
        xLabel.add("Today");


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return xLabel.get((int)value);
            }
        });


        // create a data object with the datasets
        LineData data = new LineData(dataSets);

        lineChart.setData(data);
        lineChart.animateX(2000);
        lineChart.invalidate(); // refresh
    }
}
