package com.ecom.dao;

import com.ecom.model.Review;
import com.ecom.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReviewDAO {

    private static final Logger logger = LoggerFactory.getLogger(ReviewDAO.class);

    //Save Review
    public void saveReview(Review review) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(review);
            tx.commit();
            logger.info("Review saved for: {}", review.getCustomerName());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error saving review", e);
        }
    }

    //Get Review By ID
    public Review getReviewById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Review.class, id);
        } catch (Exception e) {
            logger.error("Error fetching review with ID {}", id, e);
            return null;
        }
    }

    //Get All Review
    public List<Review> getAllReviews() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Review", Review.class).list();
        } catch (Exception e) {
            logger.error("Error fetching all reviews", e);
            return null;
        }
    }

    //delete Review
    public void deleteReview(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Review review = session.get(Review.class, id);
            if (review != null) {
                session.remove(review);
                logger.info("Review deleted for: {}", review.getCustomerName());
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error deleting review with ID {}", id, e);
        }
    }

    //update review
    public void updateReview(Review review) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(review);
            tx.commit();
            logger.info("Review updated for customer: {}", review.getCustomerName());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error updating review", e);
        }
    }
}

