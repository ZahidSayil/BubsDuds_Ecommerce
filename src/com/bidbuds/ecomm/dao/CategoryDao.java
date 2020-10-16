package com.bidbuds.ecomm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bidbuds.ecomm.entity.Category;
import com.bidbuds.ecomm.entity.Customer;
import com.bidbuds.ecomm.entity.Product;
import com.bidbuds.ecomm.util.FactoryProvider;

public class CategoryDao {
	
	private SessionFactory factory ; 
	
	
	
    public CategoryDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
    

    public void saveCategory(Category c) {
        Transaction transaction = null;
        try (Session session = FactoryProvider.getFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(c);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

  
    public List<Category> getAllCategories() {
    	
    	
    	Session session = FactoryProvider.getFactory().openSession();
    	
    	Query query = session.createQuery("FROM Category");
    	List<Category> categoryList = query.list();
    	
    	return categoryList;
    	 	
    }
    
    
    public Category getCategoryById(int cid) {
    	
    	Category category = null;
    	
    	try {
    		
    		Session session = FactoryProvider.getFactory().openSession();
    		category = session.get(Category.class, cid);
    		session.close();
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return category;
    }
    
    
}