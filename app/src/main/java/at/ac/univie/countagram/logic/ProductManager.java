package at.ac.univie.countagram.logic;

import android.app.Activity;

import java.util.List;

import at.ac.univie.countagram.model.Product;
import at.ac.univie.countagram.repository.ProductRepository;

/**
 * ProductManager class manages the interaction with the respective repository, i.e. it extracts data from the
 * repository and also updates data in the repository.
 */

public class ProductManager {
    /**
     * Instance variable productRepository
     */
    private ProductRepository productRepository;

    /**
     * Constructor
     */
    public ProductManager(){
        productRepository = new ProductRepository();
    }

    /**
     * Provides a list of all Products from the repository
     * @return
     */
    public List<Product> findAllProducts(){
        return productRepository.findAllProducts();
    }

    /**
     * Adds a given Product to the repository
     * @param product
     */
    public void addProduct(Product product){
        productRepository.addProduct(product);
    }

    /**
     * Finds a Product in the repository by using its id as a parameter
     * @param id
     * @return
     */
    public Product findProductById(int id){
        return productRepository.getProductsById(id);
    }
}
