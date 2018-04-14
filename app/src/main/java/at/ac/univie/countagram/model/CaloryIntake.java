package at.ac.univie.countagram.model;

/**
 * This class describes the model for CaloryIntake.
 * id
 *      ID of CaloryIntake
 * userId
 *      ID of User who consumed the calories
 * productId
 *      ID of product that was consumed
 * date
 *      date of CaloryIntake
 */

import java.util.GregorianCalendar;

import java.util.GregorianCalendar;

public class CaloryIntake {

    private int id;
    private int userId;
    private int productId;
    private GregorianCalendar date;

    // constructor
    public CaloryIntake(){}
    public CaloryIntake(int id, int userId, int productId, GregorianCalendar date) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.date = date;
    }

    // getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProduct() {
        return productId;
    }

    public void setProduct(int productId) {
        this.productId = productId;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

}
