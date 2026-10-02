
package com.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory factory =
            new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static void shutdown() {
        factory.close();
    }
}
