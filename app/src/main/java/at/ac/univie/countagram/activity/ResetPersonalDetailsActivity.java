package at.ac.univie.countagram.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.logic.UserManager;

/**
 * ResetPersonalDetailsActivity enables the User to reset his/her personal details.
 */

public class ResetPersonalDetailsActivity extends AppCompatActivity {
    /**
     * Instance variables
     */
    private EditText newFirstnameInput, newLastnameInput, newHeightInput, newWeightInput, newTargetweightInput;
    private SettingsSingleTon settings;
    private UserManager userManager;

    /**
     * onCreate sets the corresponding ContentView and settings.
     * It also makes the EditText fields available for input and sets
     * the current User data as hints in the EditText fields.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile_resetpersonaldetails);
        settings = SettingsSingleTon.getInstance();
        userManager = new UserManager();

        newFirstnameInput = (EditText) findViewById(R.id.newFirstnameInput);
        newLastnameInput = (EditText) findViewById(R.id.newLastnameInput);
        newHeightInput = (EditText) findViewById(R.id.newHeightInput);
        newWeightInput = (EditText) findViewById(R.id.newWeightInput);
        newTargetweightInput = (EditText) findViewById(R.id.newTargetweightInput);

        newFirstnameInput.setHint(settings.getUser().getFirstname());
        newLastnameInput.setHint(settings.getUser().getLastname());
        newHeightInput.setHint(settings.getUser().getHeight().toString());
        newWeightInput.setHint(settings.getUser().getWeight().toString());
        newTargetweightInput.setHint(settings.getUser().getTargetweight().toString());
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
     * saveOnClick saves the data input that the User supplied upon click of the "Save" button,
     * if all input data is correct.
     * @param view
     */
    protected void saveOnClick(View view){
        boolean change = false;
        String username = settings.getUser().getUsername();
        String newFirstname = newFirstnameInput.getText().toString();
        if (newFirstname != null && !newFirstname.isEmpty()){
            settings.getUser().setFirstname(newFirstname);
            change = true;
        }
        String newLastname = newLastnameInput.getText().toString();
        if (newLastname != null && !newLastname.isEmpty()){
            settings.getUser().setLastname(newLastname);
            change = true;
        }
        String newHeightStr = newHeightInput.getText().toString();
        if (newHeightStr != null && !newHeightStr.isEmpty()){
            try{
                int newHeight = Integer.parseInt(newHeightStr);
                settings.getUser().setHeight(newHeight);
                change = true;
            }catch(Exception e)
            {
                createDialog(R.string.FalscheEingabe, R.string.HeightInteger);

            }
        }
        String newWeightStr = newWeightInput.getText().toString();
        if (newWeightStr != null && !newWeightStr.isEmpty()){
            try{
                int newWeight = Integer.parseInt(newWeightStr);
                settings.getUser().setWeight(newWeight);
                change = true;
            }catch(Exception e)
            {
                createDialog(R.string.FalscheEingabe, R.string.WeightInteger);

            }
        }
        String targetweightStr = newTargetweightInput.getText().toString();
        if (targetweightStr != null && !targetweightStr.isEmpty()){
            try{
                int newTargetweight = Integer.parseInt(targetweightStr);
                settings.getUser().setTargetweight(newTargetweight);
                change = true;
            }catch(Exception e)
            {
                createDialog(R.string.FalscheEingabe, R.string.TargetWeightInteger);

            }
        }
        if (change) {
            userManager.updateUser(settings.getUser());
            //settings.updateUser(username);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Update Success!!").setPositiveButton("Ok", dialogClickListener)
                .show();

    }

    /**
     * dialogClickListener forwards the User to CaloryIntakeActivity if there was an update success
     */
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    goCaloryIntake();
                    break;
            }
        }
    };

    /**
     * goCaloryIntake forwards the User to CaloryIntakeActivity if there was an update success
     */
    private void goCaloryIntake(){
        startActivity(new Intent(this, CaloryIntakeActivity.class));
        finish();
    }

    private void createDialog(int title, int message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
