package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.model.CaloryIntake;

/**
 * MyProfileActivity shows the main menu for the user settings area.
 * The User can view either "Nutrition Goal", "Personal Details" or "Account Info" upon click
 * of the respective button.
 */

public class MyProfileActivity extends AppCompatActivity {
    /**
     * onCreate sets the corresponding ContentView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
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

    // MY PROFILE MAIN MENU
    /**
     * nutritionGoalOnClick forwards the User to ResetGoalActivity upon click of the "Nutrition Goal" button.
     * @param view
     */
    protected void nutritionGoalOnClick(View view){
        startActivity(new Intent(this, ResetGoalActivity.class));
        finish();
    }

    /**
     * personalDetailsOnClick forwards the User to ResetPersonalDetailsActivity upon click of the "Personal Details" button.
     * @param view
     */
    protected void personalDetailsOnClick(View view){
        startActivity(new Intent(this, ResetPersonalDetailsActivity.class));
        finish();
    }

    /**
     * nutritionDetailsOnClick forwards the User to ResetPersonalDetails2Activity upon click of the "Nutrition Details" button.
     * @param view
     */
    protected void nutritionDetailsOnClick(View view){
        startActivity(new Intent(this, ResetPersonalDetails2Activity.class));
        finish();
    }

    /**
     * accountInfoOnClick forwards the User to ResetAccountInfoActivity upon click of the "Account Info" button.
     * @param view
     */
    protected void accountInfoOnClick(View view){
        startActivity(new Intent(this, ResetAccountInfoActivity.class));
        finish();
    }
}
