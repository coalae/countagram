package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.model.CaloryIntake;

/**
 * RegisterPersonalDetails2Activity takes input on allergies, products that the User likes and products
 * that the User does not like.
 * The User can either skip the screen or enter input data and is then forwarded to the screen
 * CaloryIntakeActivity.
 */

public class RegisterPersonalDetails2Activity extends AppCompatActivity {
    /**
     * Instance variables
     */
    private Bundle bundle;
    private EditText allergiesInput, likeInput, dislikeInput;

    /**
     * onCreate sets the corresponding ContentView.
     * It sets the EditText fields allergiesInput, likeInput and dislikeInput as ready for data input
     * and takes the information from the Bundle.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_personaldetails2);
        bundle = getIntent().getExtras();
        allergiesInput = (EditText) findViewById(R.id.allergiesInput);
        likeInput = (EditText) findViewById(R.id.likeInput);
        dislikeInput = (EditText) findViewById(R.id.dislikeInput);
    }


    /**
     * logoOnClick forwards the User to CaloryIntakeActivity upon click of the countagram logo.
     * @param view
     */
    protected void logoOnlick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));
        finish();
    }


    /**
     * doneOnClick takes the input data for allergies, liked and disliked products and
     * puts the values in the bundle.
     * @param view
     */
    protected void doneOnClick(View view){
        String allergies = allergiesInput.getText().toString();
        String like = likeInput.getText().toString();
        String dislike = dislikeInput.getText().toString();
        if (allergies.isEmpty())
            allergiesInput.setBackgroundResource(R.color.enterError);
        if (like.isEmpty())
            likeInput.setBackgroundResource(R.color.enterError);
        if (dislike.isEmpty())
            dislikeInput.setBackgroundResource(R.color.enterError);
        if (!allergies.isEmpty() && !like.isEmpty() && !dislike.isEmpty())
        {
            Intent intent = new Intent(this, CaloryIntakeActivity.class);
            bundle.putString("allergies", allergies);
            bundle.putString("like", like);
            bundle.putString("dislike", dislike);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
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

}
