package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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
 * NutritionstatisticsAdviceActivity shows individual advice for the User logged in based on the User's calory intake info.
 */

public class NutritionstatisticsAdviceActivity extends AppCompatActivity {
    /**
     * Instance variables
     */
    private TextView adviceContent2;
    private SettingsSingleTon settings;
    private ProductManager productManager;
    private CaloryIntakeManager caloryIntakeManager;

    /**
     * onCreate sets the corresponding ContentView and settings.
     * It also sets the TextView adviceContent2 to be ready for adaptation.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritionstatistics_advice);
        adviceContent2 = (TextView) findViewById(R.id.adviceContent2);
        caloryIntakeManager = new CaloryIntakeManager();
        productManager = new ProductManager();
        settings = SettingsSingleTon.getInstance();

        /*double allowance = 0;
        int year = new GregorianCalendar().get(Calendar.YEAR);
        if(settings.getUser().getGender() == null && settings.getUser().getTargetweight() == null && settings.getUser().getHeight() == null &&
                settings.getUser().getBirthday() == null )
        {
            allowance = 2000;
        }else if (settings.getUser().getGender().equals("male")){
            //http://bmi-calories.com/calorie-intake-calculator.html
            allowance = (88.362 + (13.397 * settings.getUser().getTargetweight()) + (4.799*settings.getUser().getHeight()) - (5.677 * (year - Integer.parseInt(settings.getUser().getBirthday())))) * 1.375;
        }else if (settings.getUser().getGender().equals("female")){
            allowance = (447.593 + (9.247 * settings.getUser().getTargetweight()) + (3.098*settings.getUser().getHeight()) - (4.330 * (year - Integer.parseInt(settings.getUser().getBirthday())))) * 1.375;
        }
        */
        //TODO: individual advice has to be checke and/or could be based on longer calory intake history
        List<CaloryIntake> caloryIntakeList = caloryIntakeManager.getCaloryIntakeByUserId(settings.getUser().getId());
        //List<Product> productList = new ArrayList<>();
        int dailyCalories = 0;
        try {
            for (CaloryIntake caloryIntake : caloryIntakeList) {
                dailyCalories += (productManager.findProductById(caloryIntake.getProduct()).getCalories());
            }
            if (dailyCalories > settings.getUser().getDailyCaloryIntakeAllowance() * 0.9) {
                adviceContent2.setText("Today you should eat fewer calories to achieve your goal.");
            } else {
                adviceContent2.setText("Today you should eat more calories to achieve your goal.");
            }
        }catch(Exception e)
        {
            adviceContent2.setText("Today you did not eat anything.You should eat something.");
        }


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
     * addIntakeOnclick forwards the User to AddcaloryintakeActivty upon click of the plus symbol button.
     * @param view
     */
    protected void addIntakeOnclick(View view){
        startActivity(new Intent(this, AddcaloryintakeActivty.class));
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
}
