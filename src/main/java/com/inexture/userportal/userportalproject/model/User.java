package com.inexture.userportal.userportalproject.model;

import javax.persistence.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

@Entity
//@Table(name = "userportal_users")
public class User implements Serializable {
    /** By implementing the Serializable interface, you are telling Java that instances of these classes can be
     safely serialized and deserialized, which prevents potential issues when storing them in the session.

     * Additionally, make sure that any other classes you use in your servlet that are stored in the session also
     implement Serializable if they are part of the session attributes.
     */

    private static final long serialVersionUID = 1L;

    /*COLUMNDEFINITIION in the @Column annotation is something useful that i removed to experiment*/
    private int userId;
    private String userRole;
    private String userFirstname;
    private String userMiddlename;
    private String userLastname;
    private String userEmailID;
    private String userUsername;
    private String userPassword;
    private String userConfirmPassword;
    private int userAge;
    private String userDOB;
    private String userHobbies;
    private transient InputStream userProfile;
    private String base64Image;
    private transient FileInputStream defaultProfile;
    private Boolean userStatus;
    private int isAdmin;

    private List<Address> addressList;



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserMiddlename() {
        return userMiddlename;
    }

    public void setUserMiddlename(String userMiddlename) {
        this.userMiddlename = userMiddlename;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserEmailID() {
        return userEmailID;
    }

    public void setUserEmailID(String userEmailID) {
        this.userEmailID = userEmailID;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserConfirmPassword() {
        return userConfirmPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        this.userConfirmPassword = userConfirmPassword;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(String userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserHobbies() {
        return userHobbies;
    }

    public void setUserHobbies(String userHobbies) {
        this.userHobbies = userHobbies;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userFirstname=" + userFirstname + ", userMiddlename=" + userMiddlename + ", userLastname=" + userLastname + ", userEmail=" + userEmailID + ", userUsername=" + userUsername +", userPassword="
                + userPassword + ", userHobby=" + userHobbies + ", userDOB=" + userDOB + ", userAge =" + userAge + ", userProfile=" + userProfile + ", defaultProfile="
                + defaultProfile + ", userStatus=" + userStatus + "]";
    }


    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}