package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.model.CaloryIntake;

/**
 * RegisterChoosGoalActivity offers the selection menu to set a nutrition goal.
 * The input screen can also be skipped.
 */
public class RegisterChoosGoalActivity extends AppCompatActivity {
    /**
     * Variable
     */
    private Bundle bundle;

    //TODO create User

    /**
     * onCreate sets the corresponding ContentView and extracts the Extras information
     * from the Bundle.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_choosegoal);
        bundle = getIntent().getExtras();
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
     * loseWeightOnClick forwards the User to RegisterPersonalDetailsActivity upon click of the "Lose Weight" button.
     * @param view
     */
    protected void loseWeightOnClick(View view){
        bundle.putString("goal", "LoseWeight");
        Intent intent = new Intent(this, RegisterPersonalDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * GainWeightOnClick forwards the User to RegisterPersonalDetailsActivity upon click of the "Gain Weight" button.
     * @param view
     */
    protected void GainWeightOnClick(View view){
        bundle.putString("goal", "GainWeight");
        Intent intent = new Intent(this, RegisterPersonalDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * stayHealthyOnClick forwards the User to RegisterPersonalDetailsActivity upon click of the "Stay Healthy" button.
     * @param view
     */
    protected void stayHealthyOnClick(View view){
        bundle.putString("goal", "StayHealthy");
        Intent intent = new Intent(this, RegisterPersonalDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
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


}
