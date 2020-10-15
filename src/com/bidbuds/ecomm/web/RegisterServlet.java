package com.bidbuds.ecomm.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bidbuds.ecomm.entity.Customer;
import com.bidbuds.ecomm.util.FactoryProvider;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		try {
			
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			System.out.println("firstNAme" + fname + "lastName" + lname + "Email " + email + "Password" + password);
			
			//validation
			if(fname.isEmpty()) {
				out.println("First Name is blank");
				return;
			}
			
			//creating customer object to store in the db
			Customer customer = new Customer(fname,lname,email,password);
			
			//creating hibernate factory. 
			Session session = FactoryProvider.getFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			int custId = (int) session.save(customer);
			
			transaction.commit();
			session.close();
			
			out.println("Customer with #   " + custId + "is registered");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
