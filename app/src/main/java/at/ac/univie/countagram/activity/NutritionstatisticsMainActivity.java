package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.model.CaloryIntake;

/**
 * NutritionstatisticsMainActivity provides the main menu for the nutrition statistics area including
 * the buttons "View My Statistics", "General Nutrition Info" and "My Nutrition Advice".
 */

public class NutritionstatisticsMainActivity extends AppCompatActivity {
    /**
     * onCreate sets the corresponding ContentView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritionstatistics_main);
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
     * homeOnClick back to CaloryIntakeActivity upon click of the homeButton.
     * @param view
     */
    protected  void homeOnClick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));

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

    // STATISTICS MAIN MENU
    /**
     * viewMyStatisticsOnClick forwards the User to NutritionstatisticsMystatisticsActivity upon click of the "View
     * My Statistics" button.
     * @param view
     */
    protected void viewMyStatisticsOnClick(View view){
        startActivity(new Intent(this, NutritionstatisticsMystatisticsActivity.class));
        finish();
    }

    /**
     * generalNutritionInfoOnClick forwards the User to NutritionstatisticsGeneralInfoActivity upon click of the "General
     * Nutrition Info" button.
     * @param view
     */
    protected void generalNutritionInfoOnClick(View view){
        startActivity(new Intent(this, NutritionstatisticsGeneralInfoActivity.class));
        finish();
    }

    /**
     * myNutritionAdviceOnClick forwards the User to NutritionstatisticsAdviceActivity upon click of the "My Nutrition
     * Advice" button.
     * @param view
     */
    protected void myNutritionAdviceOnClick(View view){
        startActivity(new Intent(this, NutritionstatisticsAdviceActivity.class));
        finish();
    }
}
