package at.ac.univie.countagram.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.logic.UserManager;
import at.ac.univie.countagram.model.CaloryIntake;

/**
 * ResetAccountInfoActivity enables the reset of the current account information of the User.
 */

public class ResetAccountInfoActivity extends AppCompatActivity {
    /**
     * Instance variables
     */
    private EditText newPasswordInput, newEmailInput;
    private TextView usernameTxt;
    private SettingsSingleTon settings;
    private UserManager userManager;

    /**
     * onCreate sets the corresponding ContentView and the settings.
     * It also makes the TextView and EditText fields available to be
     * shown on the screen and for use.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile_resetaccountinfo);
        settings = SettingsSingleTon.getInstance();
        userManager = new UserManager();

        usernameTxt = (TextView) findViewById(R.id.usernameTxt);
        newPasswordInput = (EditText) findViewById(R.id.newPasswordInput);
        newEmailInput = (EditText) findViewById(R.id.newEmailInput);
        usernameTxt.setText("Username: " + settings.getUser().getUsername());

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
     * doneOnClick asks the User if the data should really be reset if there is an input from the User's part.
     * If there is no input from the User, then nothing is changed.
     * @param view
     */
    protected void doneOnClick(View view){
        boolean change = false;
        String newPassword = newPasswordInput.getText().toString();
        if (newPassword != null && !newPassword.isEmpty())
        {
            settings.getUser().setPassword(newPassword);
            change = true;
        }
        String newEmail = newEmailInput.getText().toString();
        if (newEmail != null && !newEmail.isEmpty())
        {
            settings.getUser().setEmail(newEmail);
            change = true;
        }
        if (!change)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("No data was changed.").setPositiveButton("Ok", dialogClickListener)
                    .setNegativeButton("Cancel", dialogClickListener).show();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure?").setPositiveButton("Ok", dialogClickListener)
                    .setNegativeButton("Cancel", dialogClickListener).show();
        }

    }

    /**
     * dialogClickListener updates the User if s/he clicks "ok".
     */
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    userManager.updateUser(settings.getUser());
                    goCaloryIntakeActivity();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    /**
     * goCaloryIntakeActivity forwards the User to CaloryIntakeActivity.
     */
    private void goCaloryIntakeActivity(){
        startActivity(new Intent(this, CaloryIntakeActivity.class));
        finish();
    }

    /**
     * createDialog creates an AlertDialog that contains a message and title.
     * @param title
     * @param message
     */
    private void createDialog(int title, int message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
