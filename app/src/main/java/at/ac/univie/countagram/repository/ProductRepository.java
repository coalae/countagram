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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import at.ac.univie.countagram.logic.SettingsSingleTon;
import at.ac.univie.countagram.model.Product;
import at.ac.univie.countagram.model.User;

/**
 * The ProductRepository takes data from the repository, adds data to the repository and makes
 * changes in the repository
 */

public class ProductRepository {

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
    public ProductRepository(){
        settings = SettingsSingleTon.getInstance();
        this.dbUsername = settings.getDbUsername();
        this.dbPassword = settings.getDbPassword();
        this.dbURL = settings.getDbURL();
    }

    /**
     * Gets a list of all Products from the repository
     * @return
     */
    public List<Product> findAllProducts(){
        /**
         * Inner class using derived from AsyncTask does the SQL query in the background
         */
        class FindAllProducts extends AsyncTask<Void, Void, List<Product>>{

            /**
             * doInBackground method does the SQL query (via a prepared statement) in the background
             * @param params
             * @return
             */
            @Override
            protected List<Product> doInBackground(Void... params) {
                Connection con = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL,dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ArrayList<Product> productsList = new ArrayList<Product>();
                String sql;
                sql = "SELECT product_id, productName, calories, description, category, hasRecipe" +
                        " FROM a0750881.Product";
                PreparedStatement prest = null;
                try {
                    prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();

                    while (rs.next()){
                        Product product = new Product();
                        product.setId(rs.getInt(1));
                        product.setName(rs.getString(2));
                        product.setCalories(rs.getInt(3));
                        product.setDescription(rs.getString(4));
                        product.setCategory(rs.getString(5));
                        product.setHasRecipe(rs.getBoolean(6));
                        productsList.add(product);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return productsList;
            }
        }
        FindAllProducts findAllProducts = new FindAllProducts();
        List<Product> productList = null;
        try {
            productList = findAllProducts.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return productList;
    }

    /**
     * getProductsById method gets a Product by parameter id from the respective repository
     * @param id
     */
    public Product getProductsById(int id){
        /**
         * Inner class to add the CaloryIntake derived from AsyncTask
         */
        class GetProductsByCaloryIntakeId extends AsyncTask<Integer, Void, Product>{
            /**
             * doInBackground method:
             * getting a Product by id is performed via SQL query in the background
             * @param params
             * @return
             */
            @Override
            protected Product doInBackground(Integer... params) {
                Connection con = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL,dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Product product = new Product();
                String sql;
                sql = "SELECT productName, calories, description, category, hasRecipe" +
                        " FROM a0750881.Product WHERE product_id = " + params[0];
                PreparedStatement prest = null;
                try {
                    prest = con.prepareStatement(sql);
                    ResultSet rs = prest.executeQuery();

                    while (rs.next()){


                        product.setName(rs.getString(1));
                        product.setCalories(rs.getInt(2));
                        product.setDescription(rs.getString(3));
                        product.setCategory(rs.getString(4));
                        product.setHasRecipe(rs.getBoolean(5));
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return product;
            }
        }
        GetProductsByCaloryIntakeId getProductsByCaloryIntakeId = new GetProductsByCaloryIntakeId();
        Product product = null;
        try {
            product = getProductsByCaloryIntakeId.execute(id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * addProduct method adds a Product given as a parameter to the respective repository
     * @param product
     */
    public void addProduct(final Product product){
        /**
         * Inner class to add the Product derived from AsyncTask
         */
        class AddProduct extends AsyncTask<Product, Void, Void>{

            /**
             * doInBackground method:
             * Add Product is performed via SQL query in the background
             * @param params
             * @return
             */
            @Override
            protected Void doInBackground(Product... params) {
                Connection con = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL,dbUsername, dbPassword);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String sql;
                sql = "INSERT INTO a0750881.Product(productName, calories, description, " +
                        "category, hasRecipe) VALUES " +
                        "(?,?,?,?,?)";
                PreparedStatement preparedStmt = null;
                try {
                    preparedStmt = con.prepareStatement(sql);
                    preparedStmt.setString(1, params[0].getName());
                    preparedStmt.setInt(2, params[0].getCalories());
                    preparedStmt.setString(3, params[0].getDescription());
                    preparedStmt.setString(4, params[0].getCategory());
                    preparedStmt.setBoolean(5, false);
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
                return null;
            }
        }
        AddProduct addProduct = new AddProduct();
        addProduct.execute(product);
        Log.e("AddProduct", "Product added!!!");
    }

    /**
     * updateProduct updates the information on the Product given as a parameter
     * @param product
     */
    public Boolean updateProduct(final Product product){
        /**
         * Inner class derived from AsyncTask to perform SQL update query in the background
         */
        class UpdateProduct extends AsyncTask<Product, Void, Boolean>{

            /**
             * doInBackground performs the SQL update query in the background
             * @param params
             * @return
             */
            @Override
            protected Boolean doInBackground(Product... params) {
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
                sql = "UPDATE a0750881.Product set caloryIntake_id=?, productName=?, calories=?, description=?," +
                        "category=?, hasRecipe=?" +
                        " where product_id = ?";


                PreparedStatement preparedStmt = null;
                try {
                    preparedStmt = con.prepareStatement(sql);
                    preparedStmt.setInt(1, params[0].getCaloryIntekeId());
                    preparedStmt.setString(2, params[0].getName());
                    preparedStmt.setInt(3, params[0].getCalories());
                    preparedStmt.setString(4, params[0].getDescription());
                    preparedStmt.setString(5, params[0].getCategory());
                    preparedStmt.setBoolean(6, params[0].isHasRecipe());
                    preparedStmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
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
        UpdateProduct updateProduct = new UpdateProduct();
        boolean check = false;
        try {
            check = updateProduct.execute(product).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return check;
    }

}
