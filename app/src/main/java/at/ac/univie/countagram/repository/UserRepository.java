package at.ac.univie.countagram.repository;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.model.User;

/**
 * The UserRepository takes data from the repository, adds data to the repository and makes
 * changes in the repository
 */

public class UserRepository {

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
    public UserRepository(){
        settings = SettingsSingleTon.getInstance();
        dbUsername = settings.getDbUsername();
        dbPassword = settings.getDbPassword();
        dbURL = settings.getDbURL();
    }

    /**
     * Gets a list of all Users
     * @return
     */
    public List<User> findAll(){
        /**
         * Inner class using derived from AsyncTask does the SQL query in the background
         */
        class FindAll extends AsyncTask<Void, Void, List<User>>{

            /**
             * doInBackground method does the SQL query (via a prepared statement) in the background
             * @param params
             * @return
             */
            @Override
            protected List<User> doInBackground(Void... params) {
                Connection con = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL,dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ArrayList<User> userList = new ArrayList<User>();
                String sql;
                sql = "SELECT user_id, username, pass, email, goal, firstname, lastname, gender, birthday," +
                        " height, weight, targetweight, dailyCaloryIntakeAllowance," +
                        " competitionScore, active FROM a0750881.USERS";
                PreparedStatement prest = null;
                try {

                    prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();

                    while (rs.next()){
                        User user = new User();
                        //int id = rs.getInt(0);
                        user.setId(rs.getInt(1));
                        user.setUsername(rs.getString(2));
                        user.setPassword(rs.getString(3));
                        user.setEmail(rs.getString(4));
                        user.setGoal(rs.getString(5));
                        user.setFirstname(rs.getString(6));
                        user.setLastname(rs.getString(7));
                        user.setGender(rs.getString(8));
                        user.setBirthday(rs.getString(9));
                        user.setHeight(rs.getInt(10));
                        user.setWeight(rs.getInt(11));
                        user.setTargetweight(rs.getInt(12));
                        user.setDailyCaloryIntakeAllowance(rs.getInt(13));
                        user.setCompetitionScore(rs.getInt(14));
                        user.setActive(rs.getBoolean(15));
                        userList.add(user);
                        //Log.e("Background", "\n" + vorname +"\n"+nachname);
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
                return userList;
            }
        }
        FindAll findAll = new FindAll();
        List<User> userList = new ArrayList<>();
        try {
            userList = findAll.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * Gets a list of Users by username (using the parameter username)
     * @param username
     * @return
     */
    public User getUserByUsername(String username){
        /**
         * Inner class using derived from AsyncTask does the SQL query in the background
         */
        class GetUserByUsername extends AsyncTask<String, Void, User>{

            /**
             * doInBackground method does the SQL query (via a prepared statement) in the background
             * @param params
             * @return
             */
            @Override
            protected User doInBackground(String... params) {
                Connection con = null;
                User user = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String sql;
                sql = "SELECT user_id, username, pass, email, goal, firstname, lastname, gender,birthday," +
                        " height, weight, targetweight, dailyCaloryIntakeAllowance," +
                        " competitionScore, active FROM a0750881.USERS WHERE username='" + params[0] + "'";
                PreparedStatement prest = null;
                try {

                    prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    if (rs.isBeforeFirst())
                        user = new User();
                    while (rs.next()) {
                        user.setId(rs.getInt(1));
                        user.setUsername(rs.getString(2));
                        user.setPassword(rs.getString(3));
                        user.setEmail(rs.getString(4));
                        user.setGoal(rs.getString(5));
                        user.setFirstname(rs.getString(6));
                        user.setLastname(rs.getString(7));
                        user.setGender(rs.getString(8));
                        user.setBirthday(rs.getString(9));
                        user.setHeight(rs.getInt(10));
                        user.setWeight(rs.getInt(11));
                        user.setTargetweight(rs.getInt(12));
                        user.setDailyCaloryIntakeAllowance(rs.getInt(13));
                        user.setCompetitionScore(rs.getInt(14));
                        user.setActive(rs.getBoolean(15));
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
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
                return user;
            }


        }
        GetUserByUsername getUserByUsername = new GetUserByUsername();
        User user = null;
        try {
            user = getUserByUsername.execute(username).get();
          //  Log.e("Username", "\n" + user.getUsername() +"\n"+user.getPassword());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * addUser method adds a User given as a parameter to the respective repository
     * @param user
     */
    public boolean addUser(User user){
        /**
         * Inner class to add the User derived from AsyncTask
         */
        class AddUser extends AsyncTask<User, Void, Boolean>{

            /**
             * doInBackground method:
             * Add User is performed via SQL query in the background
             * @param params
             * @return
             */
            @Override
            protected Boolean doInBackground(User... params) {
                Connection con = null;
                User user = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String sql;
                sql = "INSERT INTO a0750881.USERS(username, pass, email, goal, firstname, " +
                        "lastname, gender, birthday, height, weight, targetweight, " +
                        "dailyCaloryIntakeAllowance, competitionScore, active) VALUES " +
                        "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStmt = null;
                try {
                    preparedStmt = con.prepareStatement(sql);

                    preparedStmt.setString(1, params[0].getUsername());
                    preparedStmt.setString(2, params[0].getPassword());
                    preparedStmt.setString(3, params[0].getEmail());
                    preparedStmt.setString(4, params[0].getGoal());
                    preparedStmt.setString(5, params[0].getFirstname());
                    preparedStmt.setString(6, params[0].getLastname());
                    preparedStmt.setString(7, params[0].getGender());
                    preparedStmt.setString(8, params[0].getBirthday());
                    if (params[0].getHeight() != null)
                        preparedStmt.setInt(9, params[0].getHeight());
                    else
                        preparedStmt.setNull(9, Types.INTEGER);
                    if (params[0].getWeight() != null)
                        preparedStmt.setInt(10, params[0].getWeight());
                    else
                        preparedStmt.setNull(10, Types.INTEGER);
                    if (params[0].getTargetweight() != null)
                        preparedStmt.setInt(11, params[0].getTargetweight());
                    else
                        preparedStmt.setNull(11, Types.INTEGER);
                    if (params[0].getDailyCaloryIntakeAllowance() != null)
                        preparedStmt.setInt(12, params[0].getDailyCaloryIntakeAllowance());
                    else
                        preparedStmt.setNull(12, Types.INTEGER);
                    if (params[0].getCompetitionScore() != null)
                        preparedStmt.setInt(13, params[0].getCompetitionScore());
                    else
                        preparedStmt.setNull(13, Types.INTEGER);

                    preparedStmt.setBoolean(14, true);
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

                return true;
            }
        }
        AddUser addUser = new AddUser();
        boolean check = false;
        try {
            check = addUser.execute(user).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return check;

    }

    /**
     * updateUser updates the information on the User given as a parameter
     * @param user
     */
    public boolean updateUser(User user) {
        /**
         * Inner class derived from AsyncTask to perform SQL update query in the background
         */
        class UpdateUser extends AsyncTask<User, Void, Boolean> {

            /**
             * doInBackground performs the SQL update query in the background
             * @param params
             * @return
             */
            @Override
            protected Boolean doInBackground(User... params) {

                Connection con = null;
                User user = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String sql;
                sql = "UPDATE a0750881.USERS set pass=?, email=?, goal=?, firstname=?," +
                        "lastname=?, gender=?, birthday=?, height=?, weight=?, targetweight=?," +
                        "dailyCaloryIntakeAllowance=?, competitionScore=?, active=?" +
                        " where username = ?";


                PreparedStatement preparedStmt = null;
                try {
                    preparedStmt = con.prepareStatement(sql);
                    preparedStmt.setString(1, params[0].getPassword());
                    preparedStmt.setString(2, params[0].getEmail());
                    preparedStmt.setString(3, params[0].getGoal());
                    preparedStmt.setString(4, params[0].getFirstname());
                    preparedStmt.setString(5, params[0].getLastname());
                    preparedStmt.setString(6, params[0].getGender());
                    preparedStmt.setString(7, params[0].getBirthday());
                    if (params[0].getHeight() != null)
                        preparedStmt.setInt(8, params[0].getHeight());
                    else
                        preparedStmt.setNull(8, Types.INTEGER);
                    if (params[0].getWeight() != null)
                        preparedStmt.setInt(9, params[0].getWeight());
                    else
                        preparedStmt.setNull(9, Types.INTEGER);
                    if (params[0].getTargetweight() != null)
                        preparedStmt.setInt(10, params[0].getTargetweight());
                    else
                        preparedStmt.setNull(10, Types.INTEGER);
                    if (params[0].getDailyCaloryIntakeAllowance() != null)
                        preparedStmt.setInt(11, params[0].getDailyCaloryIntakeAllowance());
                    else
                        preparedStmt.setNull(11, Types.INTEGER);
                    if (params[0].getCompetitionScore() != null)
                        preparedStmt.setInt(12, params[0].getCompetitionScore());
                    else
                        preparedStmt.setNull(12, Types.INTEGER);

                    preparedStmt.setBoolean(13, params[0].isActive());
                    preparedStmt.setString(14, params[0].getUsername());
                    preparedStmt.executeUpdate();
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

                return true;
            }
        }
        UpdateUser updateUser = new UpdateUser();
        boolean check = false;
        try {
            check = updateUser.execute(user).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return check;
    }

    /**
     * Gets a list of Users by id (using the parameter id)
     * @param id
     * @return
     */
    public User getUserById(int id){
        /**
         * Inner class using derived from AsyncTask does the SQL query in the background
         */
        class GetUserById extends AsyncTask<Integer, Void, User>{

            /**
             * doInBackground method does the SQL query (via a prepared statement) in the background
             * @param params
             * @return
             */
            @Override
            protected User doInBackground(Integer... params) {
                Connection con = null;
                User user = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String sql;
                sql = "SELECT user_id, username, pass, email, goal, firstname, lastname, gender,birthday," +
                        " height, weight, targetweight, dailyCaloryIntakeAllowance," +
                        " competitionScore, active FROM a0750881.USERS WHERE user_id='" + params[0] + "'";
                PreparedStatement prest = null;
                try {

                    prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();
                    if (rs.isBeforeFirst())
                        user = new User();
                    while (rs.next()) {
                        user.setId(rs.getInt(1));
                        user.setUsername(rs.getString(2));
                        user.setPassword(rs.getString(3));
                        user.setEmail(rs.getString(4));
                        user.setGoal(rs.getString(5));
                        user.setFirstname(rs.getString(6));
                        user.setLastname(rs.getString(7));
                        user.setGender(rs.getString(8));
                        user.setBirthday(rs.getString(9));
                        user.setHeight(rs.getInt(10));
                        user.setWeight(rs.getInt(11));
                        user.setTargetweight(rs.getInt(12));
                        user.setDailyCaloryIntakeAllowance(rs.getInt(13));
                        user.setCompetitionScore(rs.getInt(14));
                        user.setActive(rs.getBoolean(15));
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
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
                return user;
            }


        }
        GetUserById getUserByUsername = new GetUserById();
        User user = null;
        try {
            user = getUserByUsername.execute(id).get();
            //  Log.e("Username", "\n" + user.getUsername() +"\n"+user.getPassword());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return user;
    }

}
