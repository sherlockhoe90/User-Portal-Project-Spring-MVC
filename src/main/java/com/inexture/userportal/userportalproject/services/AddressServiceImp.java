package com.inexture.userportal.userportalproject.services;

import com.inexture.userportal.userportalproject.dao.AddressDAO;
import com.inexture.userportal.userportalproject.dao.AddressDAOImp;
import com.inexture.userportal.userportalproject.model.Address;
import com.inexture.userportal.userportalproject.utility.HibernateUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AddressServiceImp implements AddressService {
    private static final Logger logger = LogManager.getLogger("UserLoginController");

    private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
    AddressDAO addressdao = new AddressDAOImp();

    @Override
    public void addAddress(int userId, Address address) {
        logger.info("calling the add-address method from AddressServiceImp.");
        addressdao.addAddress(userId, address);
    }

    @Override
    public void updateAddress(Address address, int id) throws SQLException {
        addressdao.updateAddress(address, id);
    }

    @Override
    public List<Address> getAllAddress(int userId) throws SQLException {
        return addressdao.getAllAddress(userId);
    }

}

