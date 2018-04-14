package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.model.CaloryIntake;
import at.ac.univie.countagram.model.User;

/**
 * RegisterActivity collects informaion for the creation of a new User.
 * It takes the input of username, password and email address, saves them in a bundle
 * and forwards to the next registration screen.
 */

public class RegisterActivity extends AppCompatActivity {
    /**
     * Instance variables
     */
    private EditText usernameInput, passwordInput, emailInput;
    private TextView usernameTxt, passwordTxt, emailTxt;

    /**
     * onCreate sets the corresponding ContentView.
     * It sets the EditText fields as ready for taking input data.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        emailInput = (EditText) findViewById(R.id.emailInput);

        usernameTxt = (TextView) findViewById(R.id.usernameTxt);
        passwordTxt = (TextView) findViewById(R.id.passwordTxt);
        emailTxt = (TextView) findViewById(R.id.emailTxt);
    }

    /**
     * logoOnClick forwards the User to CaloryIntakeActivity upon click of the countagram logo.
     * @param view
     */
    protected void logoOnlick(View view) {
        startActivity(new Intent(this, CaloryIntakeActivity.class));
        finish();
    }

    /**
     * createAccountOnClick creates a new User.
     * First it takes the input data for username, password and email address entered by the User.
     * Then it checks the input. If the input is usable, then the User is forwarded to the next
     * regitration screen (i.e. RegisterChoosGoalActivity).
     * @param view
     */
    protected void createAccountOnClick(View view){
        Bundle bundle = new Bundle();
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String email = emailInput.getText().toString();
        if (username.isEmpty()) {
            usernameInput.setBackgroundResource(R.color.enterError);
        }
        if(password.isEmpty()){
            passwordInput.setBackgroundResource(R.color.enterError);
        }
        if(email.isEmpty()){
            emailInput.setBackgroundResource(R.color.enterError);
        }
        if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
            bundle.putString("username", username);
            bundle.putString("password", password);
            bundle.putString("email", email);
            Intent intent = new Intent(this, RegisterChoosGoalActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

}
