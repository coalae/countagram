package at.ac.univie.countagram.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.GregorianCalendar;
import java.util.List;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.CompetitionManager;
import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.logic.UserManager;
import at.ac.univie.countagram.model.CaloryIntake;
import at.ac.univie.countagram.model.Competition;
import at.ac.univie.countagram.model.Product;
import at.ac.univie.countagram.model.User;

/**
 * CompetitionAddNewActivity starts a new Competition between two Users.
 */

public class CompetitionAddNewActivity extends AppCompatActivity {

    /**
     * Instance variables
     */
    private EditText competitorInput, competitionNameInput, dailyCaloryTargetInput;
    private CompetitionManager competitionManager;
    private SettingsSingleTon settings;
    private UserManager userManager;

    /**
     * onCreate sets the corresponding ContentView and settings.
     * It sets the EditText fields as ready to take information.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_addnew);
        userManager = new UserManager();
        settings = SettingsSingleTon.getInstance();
        competitionManager = new CompetitionManager();
        competitorInput = (EditText) findViewById(R.id.competitorInput);
        competitionNameInput = (EditText) findViewById(R.id.competitionNameInput);
        dailyCaloryTargetInput = (EditText) findViewById(R.id.dailyCaloryTargetInput);

    }

    /**
     * logoOnClick forwards the User to CaloryIntakeActivity upon click of the countagram logo.
     * @param view
     */
    protected  void logoOnlick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));
        finish();
    }

    /** statisticsOnclick forwards the User to NutritionstatisticsMainActivity upon click of the statistics button.
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
     * homeOnClick back to CaloryIntakeActivity upon click of the homeButton.
     * @param view
     */
    protected  void homeOnClick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));

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
     * startCompetitionOnClick sets the input that the User gave and creates the new Competition,
     * if the User has no Competititon running yet. The new Competition data is added to the
     * existing Competition list.
     * If the User already has a Competition, no further Competition can be added and thus the
     * User is notified by a message.
     * @param view
     */
    protected void startCompetitionOnClick(View view){
        List<Competition> compList = competitionManager.findCompetitionByUserId(settings.getUser().getId());
        String competitionName = competitionNameInput.getText().toString();
        String competitior = competitorInput.getText().toString();
        String dailyCaloryTarget = dailyCaloryTargetInput.getText().toString();
        User competitorUser = userManager.getUserByUsername(competitior);
        List<Competition> competitiorCompList = competitionManager.findCompetitionByUserId(competitorUser.getId());
        if (checkCompetition(compList) && checkCompetition(competitiorCompList)){
            Competition competition = new Competition();
            competition.setName(competitionName);
            try {
                competition.setDailyCaloryIntakeTarget(Integer.parseInt(dailyCaloryTarget));
            } catch (Exception e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Daily Calory Target must be Integer")
                        .setTitle(R.string.FalscheEingabe);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            competition.setUserId1(settings.getUser().getId());
            competition.setUserId2(competitorUser.getId());
            competition.setActive(1);
            GregorianCalendar today = new GregorianCalendar();
            competition.setStartDate(today);
            GregorianCalendar endDate = new GregorianCalendar();
            int date = today.get(GregorianCalendar.DAY_OF_MONTH);
            int month = today.get(GregorianCalendar.MONTH);
            int year = today.get(GregorianCalendar.YEAR);
            endDate.set(year, month, date + 7);
            competition.setEndDate(endDate);
            competitionManager.addCompetition(competition);  // adds Competition to the User's Competition list
            goCompetitionMainActivity();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You or your competitor have one competition!!").setPositiveButton("Ok", dialogClickListener)
                    .setNegativeButton("Cancel", dialogClickListener).show();
        }
    }

    /**
     * checkCompetition checks if one of the Users involved in the new Competition already has a
     * Competition running at the moment.
     * @param compList
     * @return
     */
    private boolean checkCompetition(List<Competition> compList){
        if (compList != null)
        for (Competition comp : compList){
            if (comp.getActive() == 1)
                return false;
        }
        return true;
    }

    /**
     * dialogClickListener implements the onClick method, which forwards the User to the
     * goCompetitionMainActivity screen upon click of the return button.
     */
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    goCompetitionMainActivity();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    /**
     * goCompetitionMainActivity forwards the User to the CompetitionMainActivity upon click of
     * the return button
     */
    private void goCompetitionMainActivity(){
        startActivity(new Intent(this, CompetitionMainActivity.class));
        finish();
    }
}
