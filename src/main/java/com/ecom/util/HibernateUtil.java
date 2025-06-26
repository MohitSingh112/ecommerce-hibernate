package com.ecom.util;

import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.model.Review;
import com.ecom.model.Vendor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    public static final SessionFactory sessionFactory;

    static{
        try{
            Configuration config = new Configuration();

            config.configure("hibernate.cfg.xml");
            config.addAnnotatedClass(Product.class);
            config.addAnnotatedClass(Category.class);
            config.addAnnotatedClass(Vendor.class);
            config.addAnnotatedClass(Review.class); //addAnnotatedClass() is used instead of XML <mapping>
            //You can still keep <mapping class="..."/> in hibernate.cfg.xml

            sessionFactory = config.buildSessionFactory();
            
        }catch (Throwable ex){
            System.out.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutDown(){
        sessionFactory.close();
    }

}
