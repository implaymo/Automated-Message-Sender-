package com.sendit;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UserDao {

    public UserDao() {
        Database.getConnection();
    }


    public static void saveUser(UsersTable user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()){
            transaction = session.beginTransaction();

            session.persist(user);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean getUser(String username) {
        Long count = null;
        try (Session session = HibernateUtil.openSession()) {
            String hql = "SELECT COUNT(u) FROM UsersTable u WHERE u.username = :username";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("username", username);
            count = query.uniqueResult();
            System.out.println("Usernames match");
            return count != null && count > 0;
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
}
