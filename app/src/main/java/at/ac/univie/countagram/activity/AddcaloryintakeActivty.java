package at.ac.univie.countagram.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.ProductManager;
import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.model.CaloryIntake;
import at.ac.univie.countagram.model.Product;
import at.ac.univie.countagram.repository.CaloryIntakeRepository;

/**
 * The class AddcaloryintakeActivty records the calories consumed by the user (in terms of
 * Product that the User selects)
 */

public class AddcaloryintakeActivty extends AppCompatActivity {

    /**
     * Instance variables
     */
    private SettingsSingleTon settings;
    private CaloryIntakeRepository caloryIntakeRepository;
    private EditText searchProductInput;
    private ListView searchListView;
    private List<Product> productList;
    private ProductManager productManager;
    private ArrayAdapter<String> adapter;
    private String selectedName = "";

    /**
     * onCreate sets the ContentView for the addcaloryintake screen and sets the settings.
     * Then it searches the Product (i.e. searchProductInput) selected by the User by its ID in the
     * repository and then sets the searchListView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcaloryintake);
        settings = SettingsSingleTon.getInstance();
        settings.startLoading(this);
        caloryIntakeRepository = new CaloryIntakeRepository();
        searchProductInput = (EditText) findViewById(R.id.searchProductInput);
        searchListView = (ListView) findViewById(R.id.searchListView);
        productManager = new ProductManager();
        searchProductInput.addTextChangedListener(filterTextWatcher);
        setDataInTheList();
        searchListView.setOnItemClickListener(onItemClickListenerListener);
        settings.stopLoading();
    }

    /**
     * logoOnClick forwards the User to CaloryIntakeActivity upon click of the countagram logo.
     * @param view
     */
    protected  void logoOnlick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));
        finish();
    }

    /**
     * statisticsOnclick forwards the User to NutritionstatisticsMainActivity upon click of the statistics button.
     * @param view
     */
    protected void statisticsOnclick(View view){
        startActivity(new Intent(this, NutritionstatisticsMainActivity.class));
        finish();
    }

    /**
     * recipesOnclick forwards the User to SearchRecipeActivity upon click of the recipes button.
     * @param view
     */
    protected void recipesOnclick(View view){
        startActivity(new Intent(this, SearchRecipeActivity.class));
        finish();
    }

    /**
     * addIntakeOnclick forwards the User to AddcaloryintakeActivty upon click of the plus symbol button.
     * @param view
     */
    protected void addIntakeOnclick(View view){
        startActivity(new Intent(this, AddcaloryintakeActivty.class));
        finish();
    }

    /**
     * competitionOnclick forwards the User to CompetitionMainActivity upon click of the gamepad symbol button.
     * @param view
     */
    protected void competitionOnclick(View view){
        startActivity(new Intent(this, CompetitionMainActivity.class));
        finish();
    }

    /**
     * settingsOnclick forwards the User to MyProfileActivity upon click of the settings symbol button.
     * @param view
     */
    protected void settingsOnclick(View view){
        startActivity(new Intent(this, MyProfileActivity.class));
        finish();
    }

    /**
     * addNewProductOnClick forwards the User to AddNewProductActivity upon click of the "add new product" button.
     * @param view
     */
    protected void addNewProductOnClick(View view) {
        startActivity(new Intent(this, AddNewProductActivity.class));
    }

    /**
     * setDataInTheList takes the Product names from all Products in the repository and makes them
     * available in a list, which is presented to the User to make a selection
     */
    private void setDataInTheList(){
        productList = productManager.findAllProducts();
        List<String> productNameList = new ArrayList<>();
        if (productList != null) {
            for (int i = 0; i < productList.size(); i++) {
                productNameList.add(productList.get(i).getName() + "\n" + "Calories: " +  productList.get(i).getCalories());
            }
        }else{
            productList = new ArrayList<>();
        }
        adapter = new ArrayAdapter<String>(this,
                R.layout.listview_addcaloryintake, R.id.label, productNameList);
        searchListView.setAdapter(adapter);
    }

    /**
     * onItemClickListenerListener listens for the Product name and item position that the User selected.
     */
    private AdapterView.OnItemClickListener  onItemClickListenerListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedName = (String) parent.getItemAtPosition(position);
            createDialog();
        }

    };

    /**
     * filterTextWatcher captures what happens before, on, and after text changed.
     * Here, only the method onTextChanged is implemented and it sets the text string as
     * the selected name of the list.
     */
    private TextWatcher filterTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String text = searchProductInput.getText().toString().toLowerCase(Locale.getDefault());
            adapter.getFilter().filter(text);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void createDialog(String titel, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(titel);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * createDialog creates a new AlertDialog.Builder that is set to "Are you sure?"
     * and has the two buttons "Yes" and "No", which are presented to the User.
     * Upon click on either of the buttons, the dialogClickListener carries out the
     * addition to the caloryintakelist of the User or it stops it.
     */
    private void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    /**
     * homeOnClick back to CaloryIntakeActivity upon click of the homeButton.
     * @param view
     */
    protected  void homeOnClick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));

    }

    /**
     * For the dialogClickListener the method onClick is implemented.
     * Depending on the button ("Yes" or "No") that the User has selected from the selection
     * AlertDialog, the corresponding action is taken.
     * In case of YES:
     * A new CaloryIntake is created for the User and added to the caloryIntakeRepository.
     * The User is notified via the Dialog "Product added!" that appears once the Product that was consumed
     * was successfully added to the caloryintakelist.
     * In case of NO:
     * No changes in the User's caloryintakelist are made.
     */
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    if (selectedName != null){
                        for (Product product : productList){

                            if((product.getName() + "\n" + "Calories: " +  product.getCalories()).toLowerCase().equals(selectedName.toLowerCase())){

                                CaloryIntake caloryIntake = new CaloryIntake();
                                caloryIntake.setUserId(settings.getUser().getId());
                                caloryIntake.setProduct(product.getId());
                                caloryIntake.setDate(new GregorianCalendar());
                                caloryIntakeRepository.addCaloryIntake(caloryIntake);
                                createDialog("", "Product added!");
                                break;
                            }
                        }
                    }
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };
}
