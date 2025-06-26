package com.ecom.dao;

import com.ecom.model.Product;
import com.ecom.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ProductDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);

    // Save Product
    public void saveProduct(Product product){
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
            logger.info("Product saved : {}",product.getName());
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            logger.error("Error saving Product",e);
        }
    }

    // Get Product by ID
    public Product getProductById(Long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Product.class,id);
        }catch (Exception e){
            logger.error("Error fetching product by ID {}",id,e);
            return null;
        }
    }

    // Get All Products
    public List<Product> getAllProducts(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("From Product",Product.class).list();
        }catch (Exception e){
            logger.error("Error fetching all product ",e);
            return null;
        }
    }

    //Update Product
    public void updateProduct(Product product){
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(product);
            tx.commit();
            logger.info("Product updated {}",product.getName());
        }catch (Exception e){
            if(tx != null) tx.rollback();
            logger.error("Error updating product ",e);
        }
    }

    //Delete Product
    public void deleteProduct(Long id){
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            Product product = session.get(Product.class,id);
            if(product != null){
                session.remove(product);
                logger.info("Product Deleted : {}" , product.getName());
            }
            tx.commit();
        }catch (Exception e){
            if (tx != null) tx.rollback();
            logger.error("Error deleting product with ID {}",id,e);
        }
    }




}
