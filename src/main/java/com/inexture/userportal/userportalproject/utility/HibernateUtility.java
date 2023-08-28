package com.inexture.userportal.userportalproject.utility;

import com.inexture.userportal.userportalproject.model.Address;
import com.inexture.userportal.userportalproject.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            SessionFactory configObject = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addResource("Address.hbm.xml")
                    .addResource("User.hbm.xml")
                    .buildSessionFactory();
//             configObject.addAnnotatedClass(User.class); // in the case of using annotations
//             configObject.addAnnotatedClass(Address.class);
            return configObject;
        } catch (Throwable e) {
            System.err.println("SessionFactory creation failed: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
