package com.inexture.userportal.userportalproject.dao;

import com.inexture.userportal.userportalproject.model.Address;
import com.inexture.userportal.userportalproject.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.List;

public class AddressDAOImp implements AddressDAO {

    SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

//    public AddressDAOImp(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    public void addAddress(int userId, Address address) {
            Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("sessionFactoryRightNow: " + sessionFactory);
            address.setAddUserID(userId);
            session.persist(address);
            session.flush();
            transaction.commit();
    }

    @Override
    public void updateAddress(Address address, int id) {
        Session session = sessionFactory.getCurrentSession();
        session.update(address);

        String remove = address.getRemoveAddressId();
        String[] removeId = remove.split(" ");
        if (!remove.isEmpty()) {
            // delete address called
            removeAddress(removeId);
        }
    }

    @Override
    public List<Address> getAllAddress(int userId) throws SQLException {
        Session session = sessionFactory.openSession();
        NativeQuery<Address> query =  session.createNativeQuery("select * from userportal_addresses where userid = :userId", Address.class);
        query.setParameter("userId", userId);
        List<Address> list = query.list();
        return list;
    }

    @Override
    public Address getAddress(int addressId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Address.class, addressId);
    }

    @Override
    public void removeAddress(String[] addressId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = (Query) session.createNativeQuery("delete FROM userportal_addresses where addressid = :addressId", Address.class);

        for (int counter = 0; counter < addressId.length; counter++) {
            query.setParameter("addressId", addressId[counter]);
            query.executeUpdate();
        }
    }
}
