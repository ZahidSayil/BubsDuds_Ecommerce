package com.bidbuds.ecomm.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bidbuds.ecomm.entity.Customer;
import com.bidbuds.ecomm.util.FactoryProvider;

public class CustomerDao {

    public void saveCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = FactoryProvider.getFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(customer);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public boolean validate(String email, String password) {


        Customer customer = null;
        boolean isloginSuccessfull = false;
        try (Session session = FactoryProvider.getFactory().openSession()) {
            // start a transaction
       
            // get an user object
            customer = (Customer) session.createQuery("FROM Customer c WHERE c.email = :email").setParameter("email", email)
                .uniqueResult();

            if (customer != null && customer.getPassword().equals(password)) {
              isloginSuccessfull = true;
            }
     
   
        }catch(Exception e ) {
        	e.printStackTrace();
        }
        return isloginSuccessfull;
    }

    
    
    public Customer getCustomerByEmailAndPassword(String email, String password) {


        Customer customer = null;
        
        try (Session session = FactoryProvider.getFactory().openSession()) {
            // start a transaction
       
            // get an user object
            customer = (Customer) session.createQuery("FROM Customer  WHERE email = :e AND password =:p").setParameter("e", email).setParameter("p",password)
                .uniqueResult();

            session.close();
   
        }catch(Exception e ) {
        	e.printStackTrace();
        }
      return customer;
    }

    
}