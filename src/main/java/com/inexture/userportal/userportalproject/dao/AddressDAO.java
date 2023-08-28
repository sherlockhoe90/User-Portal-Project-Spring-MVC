package com.inexture.userportal.userportalproject.dao;

import com.inexture.userportal.userportalproject.model.Address;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public interface AddressDAO {


    void addAddress(int userId, Address address);


    void updateAddress(Address address, int id);


    List<Address> getAllAddress(int userId) throws SQLException;


    Address getAddress(int addressId) throws SQLException;

    void removeAddress(String[] addressId);
}
