package at.ac.univie.countagram.model;

/**
 * The class describes the model for Product.
 * id
 *      ID of Product
 * caloryIntake_id
 *      ID of CaloryIntake
 * name
 *      Name of Product
 * calories
 *      Calories of Product
 * description
 *      Description of Product(or recipe if hasRecipe is true)
 * category
 *      Category of Product
 * hasRecipe
 *      true of Product has a recipe (e.g. home-made apple pie), false if Product is without reccipe(e.g. banana)

 */

public class Product {

    private int id;
    private int caloryIntake_id;
    private String name;
    private int calories;
    private String description; // recipe or product description can be inserted here
    private String category;
    private boolean hasRecipe;  // if Product has a recipe: mark as true

    // constructor
    public Product(){}
    public Product(int caloryIntake_id, String name, int calories, String description, String category, boolean hasRecipe) {
        //this.id = id;
        this.caloryIntake_id = caloryIntake_id;
        this.name = name;
        this.calories = calories;
        this.description = description;
        this.category = category;
        this.hasRecipe = hasRecipe;
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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCaloryIntekeId() {
        return caloryIntake_id;
    }

    public void setCategoryIntakeId(int categoryIntakeId) {
        this.caloryIntake_id = categoryIntakeId;
    }

    public boolean isHasRecipe() {
        return hasRecipe;
    }

    public void setHasRecipe(boolean hasRecipe) {
        this.hasRecipe = hasRecipe;
    }

}