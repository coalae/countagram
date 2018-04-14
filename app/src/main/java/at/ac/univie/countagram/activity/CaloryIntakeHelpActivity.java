package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import at.ac.univie.countagram.R;

/**
 * Created by Coala on 01.06.17.
 * The calory intake help activity gives information on the components of the app for new users and
 * in general for users who need help with the navigation.
 */

public class CaloryIntakeHelpActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caloryintake_help);
    }

    /**
     * backToCaloryIntakeOnClick back to CaloryIntakeActivity.
     * @param view
     */
    protected void backToCaloryIntakeOnClick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));

    }
}
