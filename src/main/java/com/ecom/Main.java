package com.ecom;

import com.ecom.dao.CategoryDAO;
import com.ecom.dao.ProductDAO;
import com.ecom.dao.ReviewDAO;
import com.ecom.dao.VendorDAO;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.model.Review;
import com.ecom.model.Vendor;
import com.ecom.util.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args){
        CategoryDAO categoryDAO = new CategoryDAO();
        VendorDAO vendorDAO = new VendorDAO();
        ProductDAO productDAO = new ProductDAO();
        ReviewDAO reviewDAO = new ReviewDAO();

        // ----- 1. Create and Save Category -----
        Category electronics = new Category("Electronics", "Electronic devices and gadgets");
        categoryDAO.saveCategory(electronics);

        // ----- 2. Create and Save Vendor -----
        Vendor appleVendor = new Vendor("Apple Inc.", "apple@apple.com", "1234567890", "Cupertino, CA");
        vendorDAO.saveVendor(appleVendor);

        // ----- 3. Create Product with Category and Vendor -----
        Product iphone = new Product("iPhone 15", "Latest iPhone model", 1299.99, 50);
        iphone.setCategory(electronics);
        iphone.setVendor(appleVendor);
        productDAO.saveProduct(iphone);

        // ----- 4. Create and Save Review -----
        Review review = new Review("John Doe", "Amazing phone!", 5);
        review.setProduct(iphone);
        reviewDAO.saveReview(review);

        // ----- 5. Retrieve and Print All Products -----
        System.out.println("=== Product Catalog ===");
        List<Product> products = productDAO.getAllProducts();
        for (Product p : products) {
            System.out.println("Product: " + p.getName());
            System.out.println("Category: " + p.getCategory().getName());
            System.out.println("Vendor: " + p.getVendor().getName());

            if (p.getReviews() != null) {
                for (Review r : p.getReviews()) {
                    System.out.println("Review by " + r.getCustomerName() + ": " + r.getComment() + " (" + r.getRating() + " stars)");
                }
            }
            System.out.println("-----------");
        }

        // ----- 6. Update Review -----
        review.setComment("Still great after 6 months!");
        review.setRating(4);
        reviewDAO.updateReview(review);

        // ----- 7. Delete Product -----
        // productDAO.deleteProduct(iphone.getId());

        // Shutdown Hibernate
        HibernateUtil.shutDown();
    }
}
