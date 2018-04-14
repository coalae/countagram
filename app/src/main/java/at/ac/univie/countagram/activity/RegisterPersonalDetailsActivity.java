package at.ac.univie.countagram.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.model.CaloryIntake;
import at.ac.univie.countagram.model.User;

/**
 * RegisterPersonalDetailsActivity takes the personal details as input from the User.
 * It puts the information in the bundle and forwards the User to the screen
 * RegisterPersonalDetailsActivity.
 */

public class RegisterPersonalDetailsActivity extends AppCompatActivity{
    /**
     * Instance variables
     */
    private Bundle bundle;
    private EditText firstNameInput, lastNameInput;
    private Spinner genderSpinner, dateOfBirthSpinner, heightSpinner, weightSpinner, targetWeightSpinner;

    /**
     * onCreate sets the corresponding ContentView and sets the EditText fields as available for input.
     * It also sets the drop-down list for the Spinners.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_personaldetails);
        bundle = getIntent().getExtras();
        firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        lastNameInput = (EditText) findViewById(R.id.lastNameInput);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        dateOfBirthSpinner = (Spinner) findViewById(R.id.dateOfBirthSpinner);
        heightSpinner = (Spinner) findViewById(R.id.heightSpinner);
        weightSpinner = (Spinner) findViewById(R.id.weightSpinner);
        targetWeightSpinner = (Spinner) findViewById(R.id.targetWeightSpinner);

        setDateOfBirthItems();
        setHeightsItems();
        setWeightsItems();
        setTargetWeightSpinnerItems();

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
     * continueOnClick first takes the User's input with regard to the personal detail questions and then puts the
     * information in the bundle. It finally forwards the User to RegisterPersonalDetails2Activity upon click of
     * the continue button.
     * @param view
     */
    protected void continueOnClick(View view){
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String gender = genderSpinner.getSelectedItem().toString();
        String dateOfBirth = dateOfBirthSpinner.getSelectedItem().toString();
        String height = heightSpinner.getSelectedItem().toString();
        String weight = weightSpinner.getSelectedItem().toString();
        String targetWeight = targetWeightSpinner.getSelectedItem().toString();
        if (firstName.isEmpty())
            firstNameInput.setBackgroundResource(R.color.enterError);
        if (lastName.isEmpty())
            lastNameInput.setBackgroundResource(R.color.enterError);
        if (gender.isEmpty())
            genderSpinner.setBackgroundResource(R.color.enterError);
        if (dateOfBirth.isEmpty())
            dateOfBirthSpinner.setBackgroundResource(R.color.enterError);
        if (height.isEmpty())
            heightSpinner.setBackgroundResource(R.color.enterError);
        if (weight.isEmpty())
            weightSpinner.setBackgroundResource(R.color.enterError);
        if (targetWeight.isEmpty())
            targetWeightSpinner.setBackgroundResource(R.color.enterError);

        if (!firstName.isEmpty() && !lastName.isEmpty() && !gender.isEmpty() && !dateOfBirth.isEmpty()
                && !height.isEmpty() && !weight.isEmpty() && !targetWeight.isEmpty()){
            bundle.putString("firstName", firstName);
            bundle.putString("lastName", lastName);
            bundle.putString("gender", gender);
            bundle.putString("dateOfBirth", dateOfBirth);
            bundle.putString("height", height);
            bundle.putString("weight", weight);
            bundle.putString("targetWeight", targetWeight);

            Intent intent = new Intent(this, RegisterPersonalDetails2Activity.class);
            intent.putExtras(bundle);
            startActivity(intent);

            /*
            String username = bundle.getString("username");
            String password = bundle.getString("password");
            String email = bundle.getString("email");
            String goal = bundle.getString("goal");


            String username, String email, String goal, String firstname, String lastname, char gender,
            GregorianCalendar birthday, int height, int targetweight, ArrayList<String> allergy, ArrayList<String> like,
                    ArrayList<String> dislike, ArrayList<CaloryIntake> caloryIntakeList, ArrayList<Competition> competitionList,
            int competitionScore, boolean active

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            GregorianCalendar dateOfBirthCal = new GregorianCalendar();
            try {
                dateOfBirthCal.setTime(format.parse(dateOfBirth));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            User user = new User(username, email, goal, firstName, lastName, gender, dateOfBirthCal,
                    Integer.parseInt(height), Integer.parseInt(weight), Integer.parseInt(targetWeight));
            */
        }
    }

    /**
     * skipOnClick forwards the User to CaloryIntakeActivity upon click of the "Skip for now" button.
     * @param view
     */
    protected void skipOnClick(View view){
        Intent intent = new Intent(this, CaloryIntakeActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }

    /**
     * setDateOfBirthItems sets the list of drop-down options available in the date of birth spinner.
     */
    private void setDateOfBirthItems(){
        ArrayAdapter<String> adapter;
        List<String> dateOfBirthYears = new ArrayList<String>();

        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear - 14; i >= 1960; i--) {
            dateOfBirthYears.add(Integer.toString(i));
        }

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dateOfBirthYears);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        dateOfBirthSpinner.setAdapter(adapter);
    }

    /**
     * setHeightsItems sets the list of drop-down options available in the height spinner.
     */
    private void setHeightsItems(){
        ArrayAdapter<String> adapter;
        List<String> heightItemList = new ArrayList<String>();

        for (int i = 130; i <= 250; i++) {
            heightItemList.add(Integer.toString(i));
        }

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, heightItemList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        heightSpinner.setAdapter(adapter);
    }

    /**
     * setWeightsItems sets the list of drop-down options available in the weight spinner.
     */
    private void setWeightsItems(){
        ArrayAdapter<String> adapter;
        List<String> weightItemList = new ArrayList<String>();

        for (int i = 30; i <= 250; i++) {
            weightItemList.add(Integer.toString(i));
        }

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, weightItemList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        weightSpinner.setAdapter(adapter);
    }

    /**
     * setTargetWeightSpinnerItems sets the list of drop-down options available in the target weight spinner.
     */
    private void setTargetWeightSpinnerItems(){
        ArrayAdapter<String> adapter;
        List<String> targetWeightSpinnerItemList = new ArrayList<String>();

        for (int i = 30; i <= 250; i++) {
            targetWeightSpinnerItemList.add(Integer.toString(i));
        }

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, targetWeightSpinnerItemList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        targetWeightSpinner.setAdapter(adapter);
    }

}
