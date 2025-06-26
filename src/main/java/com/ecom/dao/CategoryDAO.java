package com.ecom.dao;

import com.ecom.model.Category;
import com.ecom.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CategoryDAO {

    private static final Logger logger = LoggerFactory.getLogger(CategoryDAO.class);

    //save Category
    public void saveCategory(Category category) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(category);
            tx.commit();
            logger.info("Category saved: {}", category.getName());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error saving category", e);
        }
    }

    //get category by ID
    public Category getCategoryById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Category.class, id);
        } catch (Exception e) {
            logger.error("Error fetching category with ID {}", id, e);
            return null;
        }
    }

    // get all Categories
    public List<Category> getAllCategories() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Category", Category.class).list();
        } catch (Exception e) {
            logger.error("Error fetching all categories", e);
            return null;
        }
    }

    // update category
    public void updateCategory(Category category) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(category);
            tx.commit();
            logger.info("Category updated: {}", category.getName());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error updating category", e);
        }
    }

    //delete category
    public void deleteCategory(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Category category = session.get(Category.class, id);
            if (category != null) {
                session.remove(category);
                logger.info("Category deleted: {}", category.getName());
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error deleting category with ID {}", id, e);
        }
    }
}