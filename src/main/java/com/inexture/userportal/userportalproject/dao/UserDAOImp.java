/*uncheckded the unresolved database references in annotations and xml
* File>Settings>Editor>Inspections> unchek Unresolved database references in annotations
* */

package com.inexture.userportal.userportalproject.dao;

import com.inexture.userportal.userportalproject.model.User;
import com.inexture.userportal.userportalproject.utility.HibernateUtility;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImp implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOImp(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }
    public UserDAOImp(){

    }

    @Override
    public boolean compareUserLogin(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createNativeQuery("select * FROM userportal_users WHERE emailid = :emailid AND password = :password", User.class);
            query.setParameter("emailid", user.getUserEmailID());
            query.setParameter("password", user.getUserPassword());
            User userList = query.uniqueResult();
//            user = query.uniqueResult();

            if(userList != null) {
                user.setUserId(userList.getUserId()); //setting the user's id
                //make it check whether isAdmin 0 or 1, if its 1 set userstatus as TRUE i.r. ADMIN, if it is 0 set userSTATUS as FALSE i.e. USER
                user.setUserStatus(userList.getIsAdmin() != 0);
                return true;
            } else {
                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
/*
* THIS CODE BELOW WORKS AS WELL
*  try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createNativeQuery("select * FROM userportal_users WHERE emailid = :email", User.class);
            query.setParameter("email", user.getUserEmailID());
            User userList = query.uniqueResult();
            transaction.commit();
            return !(userList == null);
        }
*
* */
    }

    @Override
    public int userRegister(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            session.flush();
//            User userObject = session.get(User.class, user.getUserEmailID()); and then return userObject.getUserID();
            transaction.commit();
            return user.getUserId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public User showUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, user.getUserId());
        }
    }

    @Override
    public List<User> displayUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User where isAdmin = 0";
            Query<User> query = session.createQuery(hql, User.class);
            return query.list();
        }
    }

    @Override
    public List<User> displayUser(int currentPage, int recordsPerPage) {
        sessionFactory = HibernateUtility.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            query.setFirstResult((currentPage - 1) * recordsPerPage);
            query.setMaxResults(recordsPerPage);
            return query.getResultList();
        }
    }

    @Override
    public void deleteUser(String userId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, Integer.parseInt(userId));
            if (user != null) {
                session.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public List<User> displayAdmin(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createNativeQuery("select * from userportal_users where emailid = :emailId", User.class);
        query.setParameter("emailId", user.getUserEmailID());
        List<User> list = query.list();
        user = query.uniqueResult();
//        list.add(user);
        return list;
    }

    @Override
    public void updatePassword(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public User displaySpecificUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, user.getUserId());
        }
    }

    @Override
    public int updateProfile(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            return user.getUserId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<User> getUserDetails(String userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createNativeQuery("select * FROM userportal_users WHERE id = :id", User.class);
            query.setParameter("id", Integer.parseInt(userId));
            return query.getResultList();
        }
    }

    @Override
    public boolean checkEmail(String emailid) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createNativeQuery("select * FROM userportal_users WHERE emailid = :email", User.class);
            query.setParameter("email", emailid);
            User userList = query.uniqueResult();
            transaction.commit();
            return !(userList == null);
        }
    }

    @Override
    public Integer getNumberOfRows() {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Query<Integer> query = session.createNativeQuery("SELECT COUNT(*) FROM userportal_users", Integer.class);
            int numberOfRows = query.uniqueResult();
            return numberOfRows;
        }
    }

}


//package com.inexture.userportal.userportalproject.dao;
//
//import com.inexture.userportal.userportalproject.model.Address;
//import com.inexture.userportal.userportalproject.model.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//import java.sql.SQLException;
//import java.util.List;
//
//public class UserDAOImp implements UserDAO {
//
//    private SessionFactory sessionFactory;
//
//    @Override
//    public boolean compareUserLogin(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        User existingUser = session.bySimpleNaturalId(User.class)
//                .load(user.getUsername());
//
//        return existingUser != null && existingUser.getUserPassword().equals(user.getUserPassword());
//    }
//
//    @Override
//    public int userRegister(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(user);
//        return user.getUserId();
//    }
//
//    @Override
//    public User showUser(User user) throws SQLException {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(User.class, user.getUserId());
//    }
//
//    @Override
//    public List<User> Userows SQLException {
//        Session session = sessionFactory.getCurrentSession();
//        // Query for displaying user details
//        // ...
//    }
//
//    @Override
//    public List<User> displayUser(int currentPage, int recordsPerPage) throws SQLException {
//        Session session = sessionFactory.getCurrentSession();
//        // Query for displaying user details with pagination
//        // ...
//    }
//
//    @Override
//        public void deleteUser(String userId) throws SQLException {
//        Session session = sessionFactory.getCurrentSession();
//        User userToDelete = session.get(User.class, Integer.parseInt(userId));
//        if (userToDelete != null) {
//            session.delete(userToDelete);
//        }
//    }
//
//    @Override
//    public List<User> displayAdmin(User user) throws SQLException {
//        Session session = sessionFactory.getCurrentSession();
//        // Query for displaying admin user details
//        // ...
//    }
//
//    @Override
//    public void updatePassword(User user) throws SQLException {
//        Session session = sessionFactory.getCurrentSession();
//        session.update(user);
//    }
//
//    @Override
//    public User displaySpecificUser(User user) throws SQLException {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(User.class, user.getUserId());
//    }
//
//    @Override
//    public int updateProfile(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.update(user);
//        return user.getUserId();
//    }
//
//    @Override
//    public List<User> getUserDetails(String userId) throws SQLException {
//        Session session = sessionFactory.getCurrentSession();
//        // Query for getting user details by ID
//        // ...
//    }
//
//    @Override
//    public boolean checkEmail(String emailid) {
//        Session session = sessionFactory.getCurrentSession();
//        // Query for checking if email exists
//        // ...
//    }
//
//    @Override
//    public Integer getNumberOfRows() {
//        Session session = sessionFactory.getCurrentSession();
//        // Query for getting the number of rows
//        // ...
//    }
//
//    @Override
//    public User getUserByUsername(String username) throws SQLException {
//        Session session = sessionFactory.getCurrentSession();
//        // Query for getting user by username
//        // ...
//    }
//
//    @Override
//    public void addUserAddress(int userId, Address address) {
//        Session session = sessionFactory.getCurrentSession();
//        User user = session.get(User.class, userId);
//        if (user != null) {
//            user.setAddress(address);
//            session.update(user);
//        }
//    }
//
//    @Override
//    public void updateUserAddress(Address address) {
//        Session session = sessionFactory.getCurrentSession();
//        session.update(address);
//    }
//
//    @Override
//    public void removeUserAddress(Address address) {
//        Session session = sessionFactory.getCurrentSession();
//        session.delete(address);
//    }
//}
