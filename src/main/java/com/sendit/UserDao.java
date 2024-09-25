package com.sendit;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {

    public UserDao() {
        Database.getConnection();
    }


    public void saveUser(UsersTable user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()){
            transaction = session.beginTransaction();

            session.persist(user);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    public void getUser(String username) {
        try (Session session = HibernateUtil.openSession()) {
            String hql = "FROM UsersTable U WHERE U.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            List results = query.list();
        }catch (SessionException e) {
            System.out.println("Error: " + e);
        }
    }
}
