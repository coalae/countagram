package at.ac.univie.countagram.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.CaloryIntakeManager;
import at.ac.univie.countagram.logic.ProductManager;
import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.logic.UserManager;
import at.ac.univie.countagram.model.CaloryIntake;
import at.ac.univie.countagram.model.Product;
import at.ac.univie.countagram.model.User;

/**
 * CaloryIntakeActivity shows the current day's calory intake for the User who is logged in.
 * It uses the external library com.github.mikephil.charting to draw a pie chart to represent
 * the User's calory intake data.
 */

public class CaloryIntakeActivity extends AppCompatActivity {
    /**
     * Instance variables
     */
    private UserManager userManager;
    private Bundle bundle;
    private User user;
    private PieChart pieChart;
    private SettingsSingleTon settings;
    private CaloryIntakeManager caloryIntakeManager;
    private ProductManager productManager;
    //private PieChart plot;

    /**
     * onCreate sets the respective ContentView and the settings.
     * It sets the pie chart (plot) showing the User's used calories and open calories on the current day.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caloryintake);
        settings = SettingsSingleTon.getInstance();
        settings.startLoading(this);
        caloryIntakeManager = new CaloryIntakeManager();
        productManager = new ProductManager();
        pieChart = (PieChart) findViewById(R.id.plot);
        userManager = new UserManager();
        bundle = getIntent().getExtras();

        if (bundle != null) {
            if (bundle.getString("startedActivityName") != null &&
                    bundle.getString("startedActivityName").toString().equals("Login")) {
                user = userManager.getUserByUsername(bundle.getString("username"));
                settings.setUser(user);
            } else {
                setUser();
                user = userManager.getUserByUsername(user.getUsername());
                settings.setUser(user);
            }
        }

        setPlot();
        settings.stopLoading();
    }

    /**
     * getCaloryIntake counts the calory consumption for the User who is logged in
     * @return
     */
    private int getCaloryIntake(){
        int caloryIntakeCount = 0;

        List<CaloryIntake> caloryIntakeList = caloryIntakeManager.getCaloryIntakeByUserId(settings.getUser().getId());
        GregorianCalendar today = new GregorianCalendar();
        if (caloryIntakeList != null)
        for (CaloryIntake caloryIntake : caloryIntakeList){
            if (caloryIntake.getDate().get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                    caloryIntake.getDate().get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
                    caloryIntake.getDate().get(Calendar.DAY_OF_MONTH) ==  today.get(Calendar.DAY_OF_MONTH)) {
                Product product = productManager.findProductById(caloryIntake.getProduct());
                caloryIntakeCount += product.getCalories();
            }
        }

        return caloryIntakeCount;
    }

    /**
     * setPlot creates the pie chart representing the User's calory consumption vs. open calories on the current day.
     */
    private void setPlot(){
        //configuration
        pieChart.setCenterText("Count-A-Gram");
        pieChart.setDrawCenterText(true);
        Description des = pieChart.getDescription();
        des.setEnabled(false);
        pieChart.getLegend().setEnabled(false);

        int caloryIntake = getCaloryIntake(); // sum of calories consumed on current day
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(caloryIntake, "Used Calories Today"));
        Log.e("USer: " , settings.getUser().getUsername());
        entries.add(new PieEntry(settings.getUser().getDailyCaloryIntakeAllowance() - caloryIntake, "Open Calories Today"));
        //entries.add(new PieEntry(24.0f, "Red"));
        //entries.add(new PieEntry(30.8f, "Blue"));

        PieDataSet set = new PieDataSet(entries, "Count A Diagram");

        int colors[] = new int[2];
        colors[0] = Color.RED;
        colors[1] = Color.GREEN;
        //colors[2] = Color.GRAY;
        //colors[3] = Color.MAGENTA;
        //colors[4] = Color.RED;
        set.setColors(colors);
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.animateX(2000);
        pieChart.invalidate(); // refresh
    }
/*
    private void setPlot(){
        Segment segment = new Segment("my segment", 15);
        SegmentFormatter formatter = new SegmentFormatter(Color.RED);
        Segment segment2 = new Segment("my segment", 5);
        SegmentFormatter formatter2 = new SegmentFormatter(Color.BLUE);
        plot.addSegment(segment, formatter);
        plot.addSegment(segment2, formatter2);
    }
*/

    /**
     * logoOnClick forwards the User to CaloryIntakeActivity upon click of the countagram logo.
     * @param view
     */
    protected  void logoOnlick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));

    }

    /**
     * statisticsOnclick forwards the User to NutritionstatisticsMainActivity upon click of the statistics button.
     * @param view
     */
    protected void statisticsOnclick(View view){
        startActivity(new Intent(this, NutritionstatisticsMainActivity.class));

    }


    /**
     * helpOnclick forwards the User to CaloryIntakeHelpActivity upon click of the help button.
     * @param view
     */
    protected void helpOnClick(View view){
        startActivity(new Intent(this, CaloryIntakeHelpActivity.class));

    }

    /**
     * recipesOnclick forwards the User to SearchRecipeActivity upon click of the recipes button.
     * @param view
     */
    protected void recipesOnclick(View view){
        startActivity(new Intent(this, SearchRecipeActivity.class));

    }

    /**
     * addIntakeOnclick forwards the User to AddcaloryintakeActivty upon click of the plus symbol button.
     * @param view
     */
    protected void addIntakeOnclick(View view){
        startActivity(new Intent(this, AddcaloryintakeActivty.class));

    }

    /**
     * competitionOnclick forwards the User to CompetitionMainActivity upon click of the gamepad symbol button.
     * @param view
     */
    protected void competitionOnclick(View view){
        startActivity(new Intent(this, CompetitionMainActivity.class));

    }

    /**
     * settingsOnclick forwards the User to MyProfileActivity upon click of the settings symbol button.
     * @param view
     */
    protected void settingsOnclick(View view){
        startActivity(new Intent(this, MyProfileActivity.class));

    }

    /**
     * setUser sets the User's data
     */
    private void setUser(){
        String username = bundle.getString("username");
        String password = bundle.getString("password");
        String email = bundle.getString("email");
        String goal = bundle.getString("goal");
        String firstName = bundle.getString("firstName");
        String lastName = bundle.getString("lastName");
        String gender = bundle.getString("gender");
        String dateOfBirth = bundle.getString("dateOfBirth");
        String height = bundle.getString("height");
        String weight = bundle.getString("weight");
        String targetWeight = bundle.getString("targetWeight");
        String allergies = bundle.getString("allergies");
        String like = bundle.getString("like");
        String dislike = bundle.getString("dislike");
        user = new User(username, password, email);
        double allowance = 0;
        int year = new GregorianCalendar().get(Calendar.YEAR);
        if (goal != null && !goal.isEmpty())
            user.setGoal(goal);
        if (firstName != null && !firstName.isEmpty())
            user.setFirstname(firstName);
        if (lastName != null && !lastName.isEmpty())
            user.setLastname(lastName);
        if (gender != null &&!gender.isEmpty())
            user.setGender(gender);
        if (dateOfBirth != null && !dateOfBirth.isEmpty()){
            user.setBirthday(dateOfBirth);
        }
        if (height != null && !height.isEmpty())
            user.setHeight(Integer.parseInt(height));
        if (weight != null && !weight.isEmpty())
            user.setWeight(Integer.parseInt(weight));
        if (targetWeight != null && !targetWeight.isEmpty())
            user.setTargetweight(Integer.parseInt(targetWeight));
        if (allergies != null && !allergies.isEmpty())
            user.putAllergy(allergies);
        if (like != null && !like.isEmpty())
            user.putLike(like);
        if (dislike != null &&!dislike.isEmpty())
            user.putDislike(dislike);
        if(user.getGender() == null && user.getTargetweight() == null && user.getHeight() == null &&
                user.getBirthday() == null )
        {
            allowance = 2000;
        }else if (user.getGender().equals("male")){
            //http://bmi-calories.com/calorie-intake-calculator.html
            allowance = (88.362 + (13.397 * user.getTargetweight()) + (4.799*user.getHeight()) - (5.677 * (year - Integer.parseInt(user.getBirthday())))) * 1.375;
        }else if (user.getGender().equals("female")){
            allowance = (447.593 + (9.247 * user.getTargetweight()) + (3.098*user.getHeight()) - (4.330 * (year - Integer.parseInt(user.getBirthday())))) * 1.375;
        }
        user.setDailyCaloryIntakeAllowance((int)allowance);

        if (!userManager.addUser(user)) {
            createDialog(R.string.FalscheEingabe, R.string.userCannotAdd);
        }
    }

    private void createDialog(int titel, int message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(titel);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
