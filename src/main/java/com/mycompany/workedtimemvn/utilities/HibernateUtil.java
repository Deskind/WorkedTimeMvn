
package com.mycompany.workedtimemvn.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {
    public static SessionFactory getSessionFactory(){
        StandardServiceRegistry ssrb = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(ssrb).buildMetadata().buildSessionFactory();
        
        return sessionFactory;
    }
}
