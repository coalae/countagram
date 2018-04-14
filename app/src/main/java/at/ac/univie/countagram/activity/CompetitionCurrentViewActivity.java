package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.CaloryIntakeManager;
import at.ac.univie.countagram.logic.CompetitionManager;
import at.ac.univie.countagram.logic.ProductManager;
import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.logic.UserManager;
import at.ac.univie.countagram.model.CaloryIntake;
import at.ac.univie.countagram.model.Competition;
import at.ac.univie.countagram.model.Product;

/**
 * CompetitionCurrentViewActivity shows the current Competition (by the means of a line chart)
 * if the User has a running Competition. It uses the external libary com.github.mikephil.charting
 * to create the chart.
 * If there is no running Competition, the User obtains a message saying that there is no current
 * Competition.
 */

public class CompetitionCurrentViewActivity extends AppCompatActivity{

    /**
     * Instance variables
     */
    private LineChart lineChart;
    private CaloryIntakeManager caloryIntakeManager;
    private SettingsSingleTon settings;
    private CompetitionManager competitionManager;

    /**
     * onCreate sets the corresponding ContentView and settings.
     * It also sets the line chart ready for data intake.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_currentview);
        settings = SettingsSingleTon.getInstance();
        competitionManager = new CompetitionManager();
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
     * homeOnClick back to CaloryIntakeActivity upon click of the homeButton.
     * @param view
     */
    protected  void homeOnClick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));

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
     * setData sets the data of the line chart. It gets the Competition list in order to extract the
     * data of the current (active) to enter it into the line chart and plot it.
     * The line chart plots both Users' daily calory intake over the time period of the Competition.
     */
    private void setData(){
        Description des = lineChart.getDescription();
        des.setEnabled(false);
        //lineChart.getLegend().setEnabled(false);
        List<Competition> competitionList = competitionManager.findCompetitionByUserId(settings.getUser().getId());
        Competition competition = null;
        // if competitionList available, then it can be plotted
        if (competitionList != null && competitionList.size() != 0) {
            for (Competition comp : competitionList) {
                if (comp.getActive() == 1) {
                    competition = comp;
                    break;
                }
            }
            ArrayList<Entry> valuesUser1 = null;
            ArrayList<Entry> valuesUser2 = null;
            valuesUser1 = caloryIntakeManager.getValues(competition.getUserId1());
            valuesUser2 = caloryIntakeManager.getValues(competition.getUserId2());
/*
        for (int i = 0; i < 10; i++) {

            float val = (float) (Math.random() * 5) + 3;
            valuesUser1.add(new Entry(i, val, R.mipmap.game));
            float val2 = (float) (Math.random() * 5) + 3;
            values2.add(new Entry(i, val2, R.mipmap.game));
        }
        */
            LineDataSet set1 = null;
            if (competition.getUserId1() == settings.getUser().getId())
                set1 = new LineDataSet(valuesUser1, "You");
            else
                set1 = new LineDataSet(valuesUser2, "You");

            set1.setDrawIcons(false);

            // set the line to be drawn like this "- - - - - -"
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.GREEN);
            set1.setCircleColor(Color.GREEN);
            set1.setLineWidth(1f);
            set1.setCircleRadius(4f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(false);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            LineDataSet set2;
            UserManager userManager = new UserManager();
            if (competition.getUserId1() == settings.getUser().getId())
                set2 = new LineDataSet(valuesUser2, userManager.getUserById(competition.getUserId2()).getUsername());
            else
                set2 = new LineDataSet(valuesUser1, userManager.getUserById(competition.getUserId1()).getUsername());

            set2.setDrawIcons(false);

            // set the line to be drawn like this "- - - - - -"
            set2.enableDashedLine(10f, 5f, 0f);
            set2.enableDashedHighlightLine(10f, 5f, 0f);
            set2.setColor(Color.BLUE);
            set2.setCircleColor(Color.BLUE);
            set2.setLineWidth(1f);
            set2.setCircleRadius(4f);
            set2.setDrawCircleHole(false);
            set2.setValueTextSize(9f);
            set2.setDrawFilled(false);
            set2.setFormLineWidth(1f);
            set2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set2.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                //Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.fruit);
                set1.setFillColor(Color.GREEN);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets
            dataSets.add(set2);
            LimitLine ll = new LimitLine(5, "Critical Blood Pressure");
            ll.setLineColor(Color.RED);
            ll.setLineWidth(2f);
            ll.setTextColor(Color.BLACK);
            ll.setTextSize(12f);


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

                    return xLabel.get((int) value);
                }
            });


            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            lineChart.setData(data);
            lineChart.animateX(2000);
            lineChart.invalidate(); // refresh
        }else{
            lineChart.setNoDataText("You have no Competition");
            Paint p = lineChart.getPaint(Chart.PAINT_INFO);
            p.setTextSize(40);
            p.setColor(Color.RED);
            p.setTypeface(Typeface.DEFAULT);
        }
    }


}
