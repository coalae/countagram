package at.ac.univie.countagram.repository;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.model.CaloryIntake;
import at.ac.univie.countagram.model.User;

/**
 * The CaloryIntakeRepository takes data from the repository, adds data to the repository and makes
 * changes in the repository
 */

public class CaloryIntakeRepository {

    /**
     * Instance variables
     */
    private SettingsSingleTon settings;
    private String dbUsername;
    private String dbPassword;
    private String dbURL;

    /**
     * Constructor
     */
    public CaloryIntakeRepository(){
        settings = SettingsSingleTon.getInstance();
        dbUsername = settings.getDbUsername();
        dbPassword = settings.getDbPassword();
        dbURL = settings.getDbURL();

    }

    /**
     * Gets a list of CaloryIntake by userId (using the parameter user_id)
     * @param user_id
     * @return
     */
    public List<CaloryIntake> getCaloryIntakeByUserId(int user_id){

        /**
         * Inner class using derived from AsyncTask does the SQL query in the background
         */
        class GetCaloryIntakeByUserId extends AsyncTask<Integer, Void, List<CaloryIntake>>{

            /**
             * doInBackground method does the SQL query (via a prepared statement) in the background
             * @param params
             * @return
             */
            @Override
            protected List<CaloryIntake> doInBackground(Integer... params) {
                Connection con = null;
                List<CaloryIntake> caloryIntakeList = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String sql;
                sql = "SELECT caloryIntake_id, product_id, createDate FROM a0750881.CaloryIntake WHERE user_id='" + params[0] + "'";
                PreparedStatement prest = null;

                try {

                    prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    if (rs.isBeforeFirst())
                        caloryIntakeList = new ArrayList<>();
                    while (rs.next()) {
                        CaloryIntake caloryIntake = new CaloryIntake();
                        caloryIntake.setId(rs.getInt(1));
                        caloryIntake.setProduct(rs.getInt(2));
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null; // mysql datetime format
                        try {
                            date = format.parse(rs.getString(3));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        GregorianCalendar calendar = new GregorianCalendar();
                        calendar.setTime(date);
                        caloryIntake.setDate(calendar);
                        caloryIntakeList.add(caloryIntake);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (prest != null)
                            prest.close();
                        if (con != null)
                            con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

                return caloryIntakeList;
            }
        }
        GetCaloryIntakeByUserId getCaloryIntakeByUserId = new GetCaloryIntakeByUserId();
        List<CaloryIntake> caloryIntakeList = null;
        try {
            caloryIntakeList = getCaloryIntakeByUserId.execute(user_id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return caloryIntakeList;
    }

    /**
     * addCaloryIntake method adds a CaloryIntake given as a parameter to the respective repository
     * @param caloryIntake
     */
    public void addCaloryIntake(CaloryIntake caloryIntake){
        /**
         * Inner class to add the CaloryIntake derived from AsyncTask
         */
        class AddCaloryIntake extends AsyncTask<CaloryIntake, Void, Void>{

            /**
             * doInBackground method:
             * Add CaloryIntake is performed via SQL query in the background
             * @param params
             * @return
             */
            @Override
            protected Void doInBackground(CaloryIntake... params) {
                Connection con = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String sql;
                sql = "INSERT INTO a0750881.CaloryIntake(product_id, user_id, createDate) VALUES " +
                        "(?,?,?)";
                PreparedStatement preparedStmt = null;
                try {
                    preparedStmt = con.prepareStatement(sql);
                    preparedStmt.setInt(1, params[0].getProduct());
                    preparedStmt.setInt(2, params[0].getUserId());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String date = format.format(params[0].getDate().getTime());
                    preparedStmt.setString(3, date);
                    preparedStmt.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (preparedStmt != null)
                            preparedStmt.close();
                        if (con != null)
                            con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }


                return null;
            }
        }
        AddCaloryIntake addCaloryIntake = new AddCaloryIntake();
        addCaloryIntake.execute(caloryIntake);
    }

    /**
     * updateCaloryIntake updates the information on the CaloryIntake given as a parameter
     * @param caloryIntake
     */
    public void updateCaloryIntake(CaloryIntake caloryIntake){
        /**
         * Inner class derived from AsyncTask to perform SQL update query in the background
         */
        class UpdateCaloryIntake extends AsyncTask<CaloryIntake, Void, Void>{

            /**
             * doInBackground performs the SQL update query in the background
             * @param params
             * @return
             */
            @Override
            protected Void doInBackground(CaloryIntake... params) {
                Connection con = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String sql;
                sql = "UPDATE a0750881.CaloryIntake set user_id=?, createDate=? where caloryIntake_id = ?";


                PreparedStatement preparedStmt = null;
                try {
                    preparedStmt = con.prepareStatement(sql);
                    preparedStmt.setInt(1, params[0].getUserId());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String date = format.format(params[0].getDate().getTime());
                    preparedStmt.setString(2, date);
                    preparedStmt.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (preparedStmt != null)
                            preparedStmt.close();
                        if (con != null)
                            con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                return null;
            }
        }
        UpdateCaloryIntake updateCaloryIntake = new UpdateCaloryIntake();
        updateCaloryIntake.execute();
    }


}
