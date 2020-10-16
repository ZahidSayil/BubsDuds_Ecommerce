package com.bidbuds.ecomm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bidbuds.ecomm.entity.Customer;
import com.bidbuds.ecomm.entity.Product;
import com.bidbuds.ecomm.util.FactoryProvider;

public class ProductDao {

	private SessionFactory factory ; 
	
	
	
    public ProductDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}


	public void saveProduct(Product p) {
        Transaction transaction = null;
        try (Session session = FactoryProvider.getFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(p);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

  
    public List<Product> getAllProduct() {
    	
    	
    	Session session = FactoryProvider.getFactory().openSession();
    	
    	Query query = session.createQuery("FROM Product");
    	List<Product> productList = query.list();
    	
    	return productList;
    	
    	
    	
    }
    
    
    public List<Product> getProductByCategoryId(int catId) {
    	
    	
    	Session session = FactoryProvider.getFactory().openSession();
    	
    	Query query = session.createQuery("FROM Product as p where p.categoryId.id =: id");
    	query.setParameter("id", catId);
    	List<Product> productList = query.list();
    	
    	return productList;
    	
    	
    	
    }
}