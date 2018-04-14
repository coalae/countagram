package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.CompetitionManager;
import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.model.CaloryIntake;
import at.ac.univie.countagram.model.Competition;

/**
 * CompetitionScoreActivity shows the Competition score of the User.
 */

public class CompetitionScoreActivity extends AppCompatActivity {
    /**
     * Instance variables
     */
    private ListView competitionList;
    private CompetitionManager competitionManager;
    private SettingsSingleTon settings;

    /**
     * onCreate sets the corresponding ContentView and settings.
     * It also sets the data of the competitionList.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_score);
        settings = SettingsSingleTon.getInstance();
        competitionManager = new CompetitionManager();
        competitionList = (ListView) findViewById(R.id.competitionList);

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

    // TODO: take only active Competitions out of the Competition list

    /**
     * setData sets the data of the competitionsList of the User.
     */
    private void setData(){
        List<Competition> competitionsList = competitionManager.findCompetitionByUserId(settings.getUser().getId());
        List<String> competitionNameList = new ArrayList<>();
        if (competitionsList != null) {
            for (int i = 0; i < competitionsList.size(); i++) {
                competitionNameList.add(competitionsList.get(i).getName());
            }
        }else{
            competitionsList = new ArrayList<>();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listview_addcaloryintake, R.id.label, competitionNameList);
        competitionList.setAdapter(adapter);
    }
}
