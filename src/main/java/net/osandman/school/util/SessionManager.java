package net.osandman.school.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

public final class SessionManager {
    private SessionManager() {
    }

    public static SessionFactory getSessionFactory() {
        try {
            return new Configuration()
//                    .setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy())
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
