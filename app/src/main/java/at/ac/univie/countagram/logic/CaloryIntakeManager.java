package at.ac.univie.countagram.logic;

import android.app.Activity;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.model.CaloryIntake;
import at.ac.univie.countagram.model.Product;
import at.ac.univie.countagram.repository.CaloryIntakeRepository;

/**
 * CaloryIntakeManager class manages the interaction with the repository, i.e. it extracts data from the
 * repository and also updates data in the repository.
 */

public class CaloryIntakeManager {
    /**
     * Instance variable caloryIntakeRepository
     */
    private CaloryIntakeRepository caloryIntakeRepository;

    /**
     * Constructor
     */
    public CaloryIntakeManager(){
        caloryIntakeRepository = new CaloryIntakeRepository();
    }

    /**
     * Adds new CaloryIntake to the List in IntakeRepository
     * @param caloryIntake
     */
    public void addCaloryIntake(CaloryIntake caloryIntake){
        caloryIntakeRepository.addCaloryIntake(caloryIntake);
    }

    /**
     * Gets CaloryIntake list by UserId
     * @param user_id
     * @return List of CaloryIntake
     */
    public List<CaloryIntake> getCaloryIntakeByUserId(int user_id){
        return caloryIntakeRepository.getCaloryIntakeByUserId(user_id);
    }

    /**
     * Updates the CaloryIntake in the corresponding repository
     * @param caloryIntake
     */
    public void updateCaloryIntake(CaloryIntake caloryIntake){
        caloryIntakeRepository.addCaloryIntake(caloryIntake);
    }

    /**
     * Get values of calory summe on each of the days last week
     * @param userId
     * @return
     */
    public ArrayList<Entry> getValues(int userId){
        ArrayList<Entry> values = new ArrayList<Entry>();
        List<CaloryIntake> caloryIntakeList = caloryIntakeRepository.getCaloryIntakeByUserId(userId);
        ProductManager productManager = new ProductManager();
        List<Product> productList7Ago = new ArrayList<>();
        List<Product> productList6Ago = new ArrayList<>();
        List<Product> productList5Ago = new ArrayList<>();
        List<Product> productList4Ago = new ArrayList<>();
        List<Product> productList3Ago = new ArrayList<>();
        List<Product> productList2Ago = new ArrayList<>();
        List<Product> productList1Ago = new ArrayList<>();
        List<Product> productListToday = new ArrayList<>();
        Calendar sevenAgo = Calendar.getInstance(); // this would default to now
        sevenAgo.add(Calendar.DAY_OF_MONTH, -7);
        Calendar sixAgo = Calendar.getInstance(); // this would default to now
        sixAgo.add(Calendar.DAY_OF_MONTH, -6);
        Calendar fiveAgo = Calendar.getInstance(); // this would default to now
        fiveAgo.add(Calendar.DAY_OF_MONTH, -5);
        Calendar fourAgo = Calendar.getInstance(); // this would default to now
        fourAgo.add(Calendar.DAY_OF_MONTH, -4);
        Calendar threeAgo = Calendar.getInstance(); // this would default to now
        threeAgo.add(Calendar.DAY_OF_MONTH, -3);
        Calendar twoAgo = Calendar.getInstance(); // this would default to now
        twoAgo.add(Calendar.DAY_OF_MONTH, -2);
        Calendar oneAgo = Calendar.getInstance(); // this would default to now
        oneAgo.add(Calendar.DAY_OF_MONTH, -1);
        Calendar today = Calendar.getInstance(); // this would default to now
        if (caloryIntakeList != null)
        for (CaloryIntake caloryIntake : caloryIntakeList){
            if (caloryIntake.getDate().get(Calendar.DAY_OF_MONTH) == sevenAgo.get(Calendar.DAY_OF_MONTH) &&
                    caloryIntake.getDate().get(Calendar.MONTH) == sevenAgo.get(Calendar.MONTH) &&
                    caloryIntake.getDate().get(Calendar.YEAR) == sevenAgo.get(Calendar.YEAR)) {
                Product product = productManager.findProductById(caloryIntake.getProduct());
                if (product != null)
                    productList7Ago.add(product);
            }
            else if(caloryIntake.getDate().get(Calendar.DAY_OF_MONTH) == sixAgo.get(Calendar.DAY_OF_MONTH) &&
                    caloryIntake.getDate().get(Calendar.MONTH) == sixAgo.get(Calendar.MONTH) &&
                    caloryIntake.getDate().get(Calendar.YEAR) == sixAgo.get(Calendar.YEAR)) {
                Product product = productManager.findProductById(caloryIntake.getProduct());
                if (product != null)
                    productList6Ago.add(product);
            }
            else if(caloryIntake.getDate().get(Calendar.DAY_OF_MONTH) == fiveAgo.get(Calendar.DAY_OF_MONTH) &&
                    caloryIntake.getDate().get(Calendar.MONTH) == fiveAgo.get(Calendar.MONTH) &&
                    caloryIntake.getDate().get(Calendar.YEAR) == fiveAgo.get(Calendar.YEAR)) {
                Product product = productManager.findProductById(caloryIntake.getProduct());
                if (product != null)
                    productList5Ago.add(product);
            }
            else if(caloryIntake.getDate().get(Calendar.DAY_OF_MONTH) == fourAgo.get(Calendar.DAY_OF_MONTH) &&
                    caloryIntake.getDate().get(Calendar.MONTH) == fourAgo.get(Calendar.MONTH) &&
                    caloryIntake.getDate().get(Calendar.YEAR) == fourAgo.get(Calendar.YEAR)) {
                Product product = productManager.findProductById(caloryIntake.getProduct());
                if (product != null)
                    productList4Ago.add(product);
            }
            else if(caloryIntake.getDate().get(Calendar.DAY_OF_MONTH) == threeAgo.get(Calendar.DAY_OF_MONTH) &&
                    caloryIntake.getDate().get(Calendar.MONTH) == threeAgo.get(Calendar.MONTH) &&
                    caloryIntake.getDate().get(Calendar.YEAR) == threeAgo.get(Calendar.YEAR)) {
                Product product = productManager.findProductById(caloryIntake.getProduct());
                if (product != null)
                    productList3Ago.add(product);
            }
            else if(caloryIntake.getDate().get(Calendar.DAY_OF_MONTH) == twoAgo.get(Calendar.DAY_OF_MONTH) &&
                    caloryIntake.getDate().get(Calendar.MONTH) == twoAgo.get(Calendar.MONTH) &&
                    caloryIntake.getDate().get(Calendar.YEAR) == twoAgo.get(Calendar.YEAR)) {
                Product product = productManager.findProductById(caloryIntake.getProduct());
                if (product != null)
                    productList2Ago.add(product);
            }
            else if(caloryIntake.getDate().get(Calendar.DAY_OF_MONTH) == oneAgo.get(Calendar.DAY_OF_MONTH) &&
                    caloryIntake.getDate().get(Calendar.MONTH) == oneAgo.get(Calendar.MONTH) &&
                    caloryIntake.getDate().get(Calendar.YEAR) == oneAgo.get(Calendar.YEAR)) {
                Product product = productManager.findProductById(caloryIntake.getProduct());
                if (product != null)
                    productList1Ago.add(product);
            }
            else if(caloryIntake.getDate().get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH) &&
                    caloryIntake.getDate().get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
                    caloryIntake.getDate().get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
                Product product = productManager.findProductById(caloryIntake.getProduct());
                if (product != null)
                    productListToday.add(product);
            }
        }
        values.add(new Entry(0, calorySumme(productList7Ago), R.mipmap.game));
        values.add(new Entry(1, calorySumme(productList6Ago), R.mipmap.game));
        values.add(new Entry(2, calorySumme(productList5Ago), R.mipmap.game));
        values.add(new Entry(3, calorySumme(productList4Ago), R.mipmap.game));
        values.add(new Entry(4, calorySumme(productList3Ago), R.mipmap.game));
        values.add(new Entry(5, calorySumme(productList2Ago), R.mipmap.game));
        values.add(new Entry(6, calorySumme(productList1Ago), R.mipmap.game));
        values.add(new Entry(7, calorySumme(productListToday), R.mipmap.game));
        return values; // returns calory intake sums of each of the days last week
    }

    /**
     * Calculate the calory summe of a given product list
     * @param productList
     * @return
     */
    private int calorySumme(List<Product> productList){
        int calorySumme = 0;
        for (Product product : productList){
            calorySumme += product.getCalories();
        }
        return calorySumme;
    }
}
