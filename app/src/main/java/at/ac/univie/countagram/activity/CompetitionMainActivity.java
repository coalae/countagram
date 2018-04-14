package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.model.CaloryIntake;

/**
 * CompetitionMainActivity presents the main menu for selection of adding a new competition,
 * viewing an existing competition or viewing the competition score.
 */

public class CompetitionMainActivity extends AppCompatActivity {
    /**
     * onCreate sets the respective ContentView for the CompetitionMainActivity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_main);
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
     * viewCompetitionOnClick forwards the User to CompetitionCurrentViewActivity upon click of the "View Competition" button.
     * @param view
     */
    protected void viewCompetitionOnClick(View view){
        startActivity(new Intent(this, CompetitionCurrentViewActivity.class));
        finish();
    }

    /**
     * viewCompetitionScoreOnClick forwards the User to CompetitionScoreActivity upon click of the "View Competition Score" button.
     * @param view
     */
    protected void viewCompetitionScoreOnClick(View view){
        startActivity(new Intent(this, CompetitionScoreActivity.class));
        finish();
    }

    /**
     * addCompetitionOnclick forwards the User to CompetitionAddNewActivity upon click of the "Add New Competition" button.
     * @param view
     */
    protected void addCompetitionOnclick(View view){
        startActivity(new Intent(this, CompetitionAddNewActivity.class));
        finish();
    }
}
