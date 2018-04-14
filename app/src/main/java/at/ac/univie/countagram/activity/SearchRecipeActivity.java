package at.ac.univie.countagram.activity;

import android.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.ProductManager;
import at.ac.univie.countagram.model.CaloryIntake;
import at.ac.univie.countagram.model.Product;
import at.ac.univie.countagram.repository.ProductRepository;

/**
 * SearchRecipeActivity searches a list of recipes / a certain recipe for the User and displays
 * the recipe (i.e. description instance variable) of the Product on the screen.
 * If the recipe that the User is looking for cannot be found, the User can click the "Add
 * Recipe" button.
 *
 */

public class SearchRecipeActivity extends AppCompatActivity {
    /**
     * Instance variables
     */
    private ListView recipesList;
    private ProductManager productManager;
    private EditText keywordInput;
    private ArrayAdapter<String> adapter;
    private List<Product> productList;

    /**
     * onCreate sets the corresponding ContentView.
     * It makes the ListView for the recipeList as well as the keywordInput EditText field
     * available for use on screen.
     * It also sets the data for the ListView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_searchrecipe);
        recipesList = (ListView) findViewById(R.id.recipesList);
        productManager = new ProductManager();
        keywordInput = (EditText) findViewById(R.id.keywordInput);
        keywordInput.addTextChangedListener(filterTextWatcher);
        recipesList.setOnItemClickListener(onItemSelectedListener);
        setData();

    }

    /**
     * onItemSelectedListener takes the Product item at the position selected by the User.
     * It gets the Product's name and description and displays it in an AlertDialog.
     */
    private AdapterView.OnItemClickListener  onItemSelectedListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String name = (String) parent.getItemAtPosition(position);
            if (name != null){
                for (Product product : productList){
                    if(product.getName().toLowerCase().equals(name.toLowerCase())){
                        createDialog(product.getName(), product.getDescription());
                        break;
                    }
                }
            }
        }

    };

    /**
     * filterTextWatcher implements the methods beforeTextChanged, onTextChanged and afterTextChanged.
     */
    private TextWatcher filterTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        /**
         * onTextChanged enables the search by keyword (or parts of a keyword) in the recipeList.
         * @param s
         * @param start
         * @param before
         * @param count
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String text = keywordInput.getText().toString().toLowerCase(Locale.getDefault());
            adapter.getFilter().filter(text);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * logoOnClick forwards the User to CaloryIntakeActivity upon click of the countagram logo.
     * @param view
     */
    protected  void logoOnlick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));
        finish();
    }

    /**
     * homeOnClick back to CaloryIntakeActivity upon click of the homeButton.
     * @param view
     */
    protected  void homeOnClick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));

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
     * addNewRecipeOnClick forwards the User to AddNewProductActivity upon click of the "Add New Recipe" button.
     * @param view
     */
    protected void addNewRecipeOnClick(View view){
        startActivity(new Intent(this, AddNewProductActivity.class));
        finish();

    }

    /**
     * setData sets the entries of the recipeList. It includes all Products that have a recipe
     * in the recipeList and sets the Adapter accordingly.
     */
    private void setData(){

        productList = productManager.findAllProducts();
        List<String> productNameList = new ArrayList<>();
        if (productList != null) {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).isHasRecipe())
                    productNameList.add(productList.get(i).getName());
            }
        }else{
            productList = new ArrayList<>();
        }
        adapter = new ArrayAdapter<String>(this,
                R.layout.listview_addcaloryintake, R.id.label, productNameList);
        recipesList.setAdapter(adapter);
    }

    /**
     * By onDestroy the filter is adapted once the text entered in the search bar is removed.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        keywordInput.removeTextChangedListener(filterTextWatcher);
    }

    /**
     * createDialog creates a new AlertDialog in order to display a message and title.
     * @param titel
     * @param message
     */
    private void createDialog(String titel, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(titel);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
