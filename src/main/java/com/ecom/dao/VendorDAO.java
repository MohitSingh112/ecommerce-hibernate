package com.ecom.dao;

import com.ecom.model.Vendor;
import com.ecom.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VendorDAO {

    private static final Logger logger = LoggerFactory.getLogger(VendorDAO.class);

    // Save Vendor
    public void saveVendor(Vendor vendor) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(vendor);
            tx.commit();
            logger.info("Vendor saved: {}", vendor.getName());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error saving vendor", e);
        }
    }

    //Get Vendor By ID
    public Vendor getVendorById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Vendor.class, id);
        } catch (Exception e) {
            logger.error("Error fetching vendor with ID {}", id, e);
            return null;
        }
    }

    //Get all vendor
    public List<Vendor> getAllVendors() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Vendor", Vendor.class).list();
        } catch (Exception e) {
            logger.error("Error fetching all vendors", e);
            return null;
        }
    }

    //Update Vendor
    public void updateVendor(Vendor vendor) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(vendor);
            tx.commit();
            logger.info("Vendor updated: {}", vendor.getName());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error updating vendor", e);
        }
    }

    //Delete Vendor
    public void deleteVendor(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Vendor vendor = session.get(Vendor.class, id);
            if (vendor != null) {
                session.remove(vendor);
                logger.info("Vendor deleted: {}", vendor.getName());
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error deleting vendor with ID {}", id, e);
        }
    }
}
