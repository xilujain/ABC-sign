package abcsign;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration configuration = new Configuration().configure("abcsign/hibernate.cfg.xml");
            ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
             configuration.addAnnotatedClass(user.class);
             configuration.addAnnotatedClass(Level.class);
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);  
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
