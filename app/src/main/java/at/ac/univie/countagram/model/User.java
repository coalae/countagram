package at.ac.univie.countagram.model;

/**
 * The class describes the User.
 * @param id
 *      ID of User
 * @param username
 *      Username
 * @param password
 *      Password of User
 * @param email
 *      Email address of User
 * @param goal
 *      Nutrition goal of User
 * @param firstname
 *      First name of User
 * @param lastname
 *      Last name of User
 * @param gender
 *      Gender of User
 * @param birthday
 *      Birthday of User
 * @param height
 *      Height (in cm) of User
 * @param weight
 *      Weight (in kg) of User
 * @param targetweight
 *      Target weight (in kg) of User
 * @param dailyCaloryIntakeAllowance
 *      Daily calories that User is allowed to consume (is automatically calculated)
 * @param allergyList
 *      List of allergies
 * @param likeList
 *      List of Products that the User likes
 * @param dislikeList
 *      List of Products that the User does not like
 * @param caloryIntakeList
 *      List of CaloryIntake objects for the User
 * @param competitionList
 *      List of Competitions of the User
 * @param competitionScore
 *      Competition Score of the User
 * @param active
 *      Status of the User (true means active, false means banned)
 */

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String goal;
    private String firstname;
    private String lastname;
    private String gender;
    private String birthday;
    private Integer height;
    private Integer weight;
    private Integer targetweight;
    private Integer dailyCaloryIntakeAllowance;
    private ArrayList<String> allergyList;
    private ArrayList<String> likeList;
    private ArrayList<String> dislikeList;
    private ArrayList<CaloryIntake> caloryIntakeList;
    private ArrayList<Competition> competitionList;
    private Integer competitionScore;
    private boolean active;

    // construtor
    public User(){};
    public User (String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
        setLists();
    };

    // construtor
    public User(String username, String email, String goal, String firstname, String lastname, String gender,
                String birthday, int height, int targetweight, ArrayList<String> allergyList, ArrayList<String> likeList,
                ArrayList<String> dislikeList, ArrayList<CaloryIntake> caloryIntakeList, ArrayList<Competition> competitionList,
                int competitionScore, boolean active) {
        this.username = username;
        this.email = email;
        this.goal = goal;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthday = birthday;
        this.height = height;
        this.targetweight = targetweight;
        this.allergyList = allergyList;
        this.likeList = likeList;
        this.dislikeList = dislikeList;
        this.caloryIntakeList = caloryIntakeList;
        this.competitionList = competitionList;
        this.competitionScore = competitionScore;
        this.active = active;
    }

    // getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Integer getTargetweight() {
        return targetweight;
    }

    public void setTargetweight(int targetweight) {
        this.targetweight = targetweight;
    }

    public Integer getDailyCaloryIntakeAllowance() {
        return dailyCaloryIntakeAllowance;
    }

    public void setDailyCaloryIntakeAllowance(int dailyCaloryIntakeAllowance) {
        this.dailyCaloryIntakeAllowance = dailyCaloryIntakeAllowance;
    }

    public ArrayList<String> getAllergy() {
        return allergyList;
    }

    public void setAllergy(ArrayList<String> allergyList) {
        this.allergyList = allergyList;
    }
    public void putAllergy(String allergy){allergyList.add(allergy);}

    public ArrayList<String> getLike() {
        return likeList;
    }

    public void setLike(ArrayList<String> likeList) {
        this.likeList = likeList;
    }
    public void putLike(String like){likeList.add(like);}

    public ArrayList<String> getDislike() {
        return dislikeList;
    }

    public void setDislike(ArrayList<String> dislikeList) {
        this.dislikeList = dislikeList;
    }
    public void putDislike(String dislike){dislikeList.add(dislike);}

    public ArrayList<CaloryIntake> getCaloryIntakeList() {
        return caloryIntakeList;
    }

    public void setCaloryIntakeList(ArrayList<CaloryIntake> caloryIntakeList) {
        this.caloryIntakeList = caloryIntakeList;
    }

    public ArrayList<Competition> getCompetitionList() {
        return competitionList;
    }

    public void setCompetitionList(ArrayList<Competition> competitionList) {
        this.competitionList = competitionList;
    }

    public Integer getCompetitionScore() {
        return competitionScore;
    }

    public void setCompetitionScore(int competitionScore) {
        this.competitionScore = competitionScore;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private void setLists(){
        allergyList = new ArrayList<String>();
        likeList = new ArrayList<String>();
        dislikeList = new ArrayList<String>();
        caloryIntakeList = new ArrayList<CaloryIntake>();
        competitionList = new ArrayList<Competition>();
    }


}
