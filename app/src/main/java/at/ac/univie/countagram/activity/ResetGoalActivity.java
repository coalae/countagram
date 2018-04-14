package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.logic.UserManager;

/**
 * ResetGoalActivity enables the reset of the current nutrition goal of the User.
 */

public class ResetGoalActivity extends AppCompatActivity {

    /**
     * Instance variables
     */
    private TextView showCurrentGoalTxt;
    private SettingsSingleTon settings;
    private UserManager userManager;

    /**
     * onCreate sets the corresponding ContentView and the settings.
     * It also makes the TextView showCurrentGoalTxt available to be
     * shown on the screen.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile_resetgoal);
        settings = SettingsSingleTon.getInstance();
        userManager = new UserManager();
        showCurrentGoalTxt = (TextView) findViewById(R.id.showCurrentGoalTxt);

        if (settings.getUser().getGoal() != null)
            showCurrentGoalTxt.setText("Your current goal is: " + settings.getUser().getGoal());
        else
            showCurrentGoalTxt.setText("You don't have selected a goal");
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

    // RESET GOAL MENU
    /**
     * loseWeightOnClick resets the goal and forwards the User to CaloryIntakeActivity upon click
     * of the Lose Weight button.
     * @param view
     */
    protected void loseWeightOnClick(View view){
        settings.getUser().setGoal("LoseWeight");
        userManager.updateUser(settings.getUser());
        startActivity(new Intent(this, CaloryIntakeActivity.class));
        finish();
    }

    /**
     * GainWeightOnClick resets the goal and forwards the User to CaloryIntakeActivity upon click
     * of the Gain Weight button.
     * @param view
     */
    protected void GainWeightOnClick(View view){
        settings.getUser().setGoal("GainWeight");
        userManager.updateUser(settings.getUser());
        startActivity(new Intent(this, CaloryIntakeActivity.class));
        finish();
    }

    /**
     * stayHealthyOnClick resets the goal and forwards the User to CaloryIntakeActivity upon click
     * of the Stay Healthy button.
     * @param view
     */
    protected void stayHealthyOnClick(View view){
        settings.getUser().setGoal("StayHealthy");
        userManager.updateUser(settings.getUser());
        startActivity(new Intent(this, CaloryIntakeActivity.class));
        finish();
    }
}
