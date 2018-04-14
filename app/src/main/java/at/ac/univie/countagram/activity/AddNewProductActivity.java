package at.ac.univie.countagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import at.ac.univie.countagram.R;
import at.ac.univie.countagram.logic.ProductManager;
import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.model.Product;

/**
 * AddNewProductActivity adds a new Product to the Product list.
 */

public class AddNewProductActivity extends AppCompatActivity {

    /**
     * Instance variables
     */
    private ProductManager productManager;
    private EditText productNameInput, caloriesInput, descriptionInput, categoryInput;
    private CheckBox hasRecipeCheckbox;
    private SettingsSingleTon settings;

    /**
     * onCreate sets the corresponding ContentView and the settings.
     * It sets the EditText fields as ready to take input data.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewproduct);
        settings = SettingsSingleTon.getInstance();
        productManager = new ProductManager();

        productNameInput = (EditText) findViewById(R.id.productNameInput);
        caloriesInput = (EditText) findViewById(R.id.caloriesInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);
        categoryInput = (EditText) findViewById(R.id.categoryInput);
        hasRecipeCheckbox = (CheckBox) findViewById(R.id.hasRecipeCheckbox);

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
     * homeOnClick back to CaloryIntakeActivity upon click of the homeButton.
     * @param view
     */
    protected  void homeOnClick(View view){
        startActivity(new Intent(this, CaloryIntakeActivity.class));

    }

    /**
     * SaveProductOnClick takes the input data for a new Product. The Product (if successfully created)
     * is the added to the repository via the productManager.
     * Then the User is forwarded to the AddcaloryintakeActivty screen.
     * @param view
     */
    protected void SaveProductOnClick(View view){

        settings.startLoading(this);
        String productName = productNameInput.getText().toString();
        String caloriesStr = caloriesInput.getText().toString();
        String description = descriptionInput.getText().toString();
        String category = categoryInput.getText().toString();
        if (productName == null || productName.isEmpty())
            productNameInput.setBackgroundResource(R.color.enterError);
        if (description == null || description.isEmpty())
            descriptionInput.setBackgroundResource(R.color.enterError);
        if (category == null || category.isEmpty())
            categoryInput.setBackgroundResource(R.color.enterError);
        if (caloriesStr == null || caloriesStr.isEmpty())
            caloriesInput.setBackgroundResource(R.color.enterError);
        if (!productName.isEmpty() && !description.isEmpty() && !category.isEmpty() && !caloriesStr.isEmpty()){
            int calories = Integer.parseInt(caloriesStr);
            Product product = new Product();
            product.setName(productName);
            product.setCalories(calories);
            product.setDescription(description);
            product.setCategory(category);
            product.setHasRecipe(hasRecipeCheckbox.isChecked());
            productManager.addProduct(product);
            startActivity(new Intent(this, AddcaloryintakeActivty.class));
            finish();
        }
        settings.stopLoading();
    }
}
