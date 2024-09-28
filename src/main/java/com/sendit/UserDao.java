package com.sendit;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDao {

    static final Logger logger = LoggerFactory.getLogger(UserDao.class);


    public UserDao() {
        Database.getConnection();
    }


    public void saveUser(UsersTable user) {
        Transaction transaction;
        try (Session session = HibernateUtil.openSession()){
            transaction = session.beginTransaction();

            session.persist(user);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    public UsersTable getUser(String username) {
        UsersTable loginUser = null;
        try (Session session = HibernateUtil.openSession()) {
            String hql = "FROM UsersTable U WHERE U.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            loginUser = (UsersTable) query.getSingleResult();
            if (loginUser != null) {
                logger.info("Username found.");
                return loginUser;
            }
        }catch (SessionException e) {
            logger.error("Error: {}", String.valueOf(e));
        }
        logger.info("Username not found.");
        return loginUser;
    }
}
