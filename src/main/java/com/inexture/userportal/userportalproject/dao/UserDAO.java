package com.inexture.userportal.userportalproject.dao;

import com.inexture.userportal.userportalproject.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;


public interface UserDAO {

    boolean compareUserLogin(User user);

    int userRegister(User user);

    User showUser(User user);

    List<User> displayUser(User user);

    List<User> displayUser(int currentPage, int recordsPerPage);

    void deleteUser(String userId);

    /**
     * @param user
     * @return
     * @throws SQLException
     */
    List<User> displayAdmin(User user) throws SQLException;

    void updatePassword(User user);

    User displaySpecificUser(User user);

    int updateProfile(User user);

    List<User> getUserDetails(String userId);

    boolean checkEmail(String emailid);

    Integer getNumberOfRows();

}
