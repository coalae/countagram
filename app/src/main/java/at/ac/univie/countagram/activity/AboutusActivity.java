package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.model.CaloryIntake;

/**
 * The class AboutusActivity shows information on the app developer team
 */
public class AboutusActivity extends AppCompatActivity {

    /**
     * onCreate sets the ContentView to the layout defined for the AboutUs screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
    }

    /**
     * homeOnClick back to MainActivity upon click of the homeButton.
     * @param view
     */
    protected  void homeOnClick(View view){
        startActivity(new Intent(this, MainActivity.class));

    }
}
