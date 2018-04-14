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
import at.ac.univie.countagram.model.Competition;

/**
 * The CompetitionRepository takes data from the repository, and adds data to the repository
 */

public class CompetitionRepository {

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
    public CompetitionRepository(){
        settings = SettingsSingleTon.getInstance();
        dbUsername = settings.getDbUsername();
        dbPassword = settings.getDbPassword();
        dbURL = settings.getDbURL();
    }

    /**
     * Gets a list of Competitions by userId (using the parameter user_id)
     * @param user_id
     * @return
     */
    public List<Competition> getCaloryIntakeByUserId(int user_id) {
        /**
         * Inner class using derived from AsyncTask does the SQL query in the background
         */
        class GetCompetitionByUserId extends AsyncTask<Integer, Void, List<Competition>> {

            /**
             * doInBackground method does the SQL query (via a prepared statement) in the background
             * @param params
             * @return
             */
            @Override
            protected List<Competition> doInBackground(Integer... params) {
                Connection con = null;
                List<Competition> competitionList = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String sql;
                sql = "SELECT competition_id, competition_name, user_id1, user_id2, startDate, endDate, " +
                        "dailyCaloryIntakeTarget, active, winnerUserId FROM a0750881.Competition WHERE user_id1=" + params[0] + " OR user_id2=" + params[0];
                PreparedStatement prest = null;

                try {
                    prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    if (rs.isBeforeFirst())
                        competitionList = new ArrayList<>();
                    while (rs.next()) {
                        Competition competition = new Competition();
                        competition.setId(rs.getInt(1));
                        competition.setName(rs.getString(2));
                        competition.setUserId1(rs.getInt(3));
                        competition.setUserId2(rs.getInt(4));
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = null; // mysql datetime format
                        try {
                            date = format.parse(rs.getString(5));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        GregorianCalendar startDate = new GregorianCalendar();
                        startDate.setTime(date);
                        competition.setStartDate(startDate);



                        Date date2 = null; // mysql datetime format
                        try {
                            date2 = format.parse(rs.getString(6));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        GregorianCalendar endDate = new GregorianCalendar();
                        endDate.setTime(date2);
                        competition.setEndDate(endDate);
                        competition.setDailyCaloryIntakeTarget(rs.getInt(7));
                        competition.setActive(rs.getInt(8));
                        competition.setWinnerUserId(rs.getInt(9));
                        competitionList.add(competition);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return competitionList;
            }
        }
        GetCompetitionByUserId getCompetitionByUserId = new GetCompetitionByUserId();
        List<Competition> competitionsList = null;
        try {
            competitionsList = getCompetitionByUserId.execute(user_id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return competitionsList;
    }

    /**
     * addCompetition method adds a Competition given as a parameter to the respective repository
     * @param competition
     */
    public void addCompetition(final Competition competition){
        /**
         * Inner class to add the Competition derived from AsyncTask
         */
        class AddCompetition extends AsyncTask<Competition, Void, Void>{

            /**
             * doInBackground method:
             * Add Competition is performed via SQL query in the background
             * @param params
             * @return
             */
            @Override
            protected Void doInBackground(Competition... params) {
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
                sql = "INSERT INTO a0750881.Competition(competition_name, user_id1, user_id2, " +
                        "startDate, endDate, dailyCaloryIntakeTarget, active, winnerUserId) VALUES " +
                        "(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStmt = null;

                try {
                    preparedStmt = con.prepareStatement(sql);
                    preparedStmt.setString(1, params[0].getName());
                    preparedStmt.setInt(2, params[0].getUserId1());
                    preparedStmt.setInt(3, params[0].getUserId2());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String startDate = format.format(params[0].getStartDate().getTime());
                    preparedStmt.setString(4, startDate);
                    String endDate = format.format(params[0].getEndDate().getTime());
                    preparedStmt.setString(5, endDate);
                    preparedStmt.setInt(6, params[0].getDailyCaloryIntakeTarget());
                    preparedStmt.setInt(7, params[0].getActive());
                    preparedStmt.setInt(8, params[0].getWinnerUserId());
                    preparedStmt.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        AddCompetition addCompetition = new AddCompetition();
        addCompetition.execute(competition);
    }

}
