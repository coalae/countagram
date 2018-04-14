package at.ac.univie.countagram.model;

/**
 * This class describes the model of Competition between two users.
 * id
 *      ID of Competition
 * name
 *      Name of Competition
 * userId1
 *      ID of User who added the Competition
 * userId2
 *      ID of competiting User
 * startDate
 *      Date when Competition started
 * endDate
 *      Date when Competition ended
 * dailyCaloryIntakeTarget
 *      Calory target for both Users
 * active
 *      Status of Competition
 * winnerUserId
 *      ID of winning User
 */

import java.util.GregorianCalendar;

public class Competition {

    private int id;
    private String name;
    private int userId1;
    private int userId2;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private int dailyCaloryIntakeTarget;
    private int active;
    private int winnerUserId;

    public Competition(){

    }
    // constructor
    public Competition(String name, int userId1, int userId2, GregorianCalendar startDate,
                       GregorianCalendar endDate, int dailyCaloryIntakeTarget, int active, int winnerUserId) {
        this.name = name;
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCaloryIntakeTarget = dailyCaloryIntakeTarget;
        this.active = active;
        this.winnerUserId = winnerUserId;
    }

    // getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public int getDailyCaloryIntakeTarget() {
        return dailyCaloryIntakeTarget;
    }

    public void setDailyCaloryIntakeTarget(int dailyCaloryIntakeTarget) {
        this.dailyCaloryIntakeTarget = dailyCaloryIntakeTarget;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getWinnerUserId() {
        return winnerUserId;
    }

    public void setWinnerUserId(int winnerUserId) {
        this.winnerUserId = winnerUserId;
    }

}
