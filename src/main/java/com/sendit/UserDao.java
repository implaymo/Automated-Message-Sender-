package com.sendit;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDao {

    public UserDao() {
        Database.getConnection();
    }

    public void saveUser(UsersTable user) {
        Transaction transaction = null;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            session.persist(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
