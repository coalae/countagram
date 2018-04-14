package at.ac.univie.countagram.logic;

import android.app.Activity;
import android.app.ProgressDialog;

import at.ac.univie.countagram.model.User;

/**
 * This class takes care of the settings for the app.
 * It sets data variables that the data that is frequently used does
 * not have to be set again every time.
 */

public class SettingsSingleTon {

    //Settings of SingleTon
    private static SettingsSingleTon settings;
    private ProgressDialog progress;
    private UserManager userManager;
    private User user; // once the user is logged in, the user's data can be used in every class
    //Elements
    private String dbURL = "jdbc:mysql://a0750881.mysql.univie.ac.at";
    private String dbUsername = "a0750881";
    private String dbPassword = "coalacoala1";

    private SettingsSingleTon(){
        user = null;
    }

    public static SettingsSingleTon getInstance(){
        if (settings == null)
            settings = new SettingsSingleTon();

        return settings;

    }

    public String getDbUsername(){
        return dbUsername;
    }
    public String getDbPassword(){
        return dbPassword;
    }
    public String getDbURL(){return dbURL;}

    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return user;
    }
    public void refreshUser(){
        userManager = new UserManager();
        user = userManager.getUserById(settings.getUser().getId());
    }

    public void startLoading(Activity activity){
        progress = new ProgressDialog(activity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

    }
    public void stopLoading(){
        progress.dismiss();
    }

}
