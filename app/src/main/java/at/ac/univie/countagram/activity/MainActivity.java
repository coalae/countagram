package at.ac.univie.countagram.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.logic.UserManager;

/**
 * MainActivity first checks of there is an internet connection. If there is no internet connection,
 * the User receives a signal message.
 * If there is an internet connection, the User can login, register or go to the "About Us" screen.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Instance variables
     */
    private EditText usernameInput, passwordInput;
    private UserManager userManager;

    /**
     * onCreate sets the corresponding ContentView. If there is an internet connection,
     * the EditText field for username and password are set as ready to take input data.
     * If there is no internet connection, the message "No internet connection!!" appears.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isConnected()) {
            userManager = new UserManager();
            usernameInput = (EditText) findViewById(R.id.usernameInput);
            passwordInput = (EditText) findViewById(R.id.passwordInput);
        }else{
            ProgressDialog progress = new ProgressDialog(MainActivity.this);
            progress.setTitle("No Internet");
            progress.setMessage("No internet connection!!");
            progress.setCancelable(false);
            progress.show();

        }
    }

    /**
     * loginOnClick performs the login for the User, by taking username and password as input.
     * @param view
     */
    protected void loginOnClick(View view){

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        if (username != null && password != null) {
            if (userManager.loginUser(username, password)) {
                Intent intent = new Intent(this, CaloryIntakeActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                intent.putExtra("startedActivityName", "Login");
                startActivity(intent);
                finish();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.usernameOrPasswordWrong)
                        .setTitle(R.string.FalscheEingabe);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.loginInformation)
                    .setTitle(R.string.FalscheEingabe);
            AlertDialog dialog = builder.create();
            dialog.show();
        }


    }

    /**
     * registerOnClick forwards the User to the RegisterActivity.
     * @param view
     */
    protected void registerOnClick(View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    /**
     * aboutUsOnClick forwards the User to the AboutusActivity.
     * @param view
     */
    protected void aboutUsOnClick(View view){
        startActivity(new Intent(this, AboutusActivity.class));
    }

    /**
     * isConnected checks if there is an internet connection at the moment.
     * @return boolean
     */
    public boolean isConnected()
    {
        String command = "ping -c 1 google.com";
        try {
            return (Runtime.getRuntime().exec (command).waitFor() == 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
