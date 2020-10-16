package com.bidbuds.ecomm.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bidbuds.ecomm.dao.CustomerDao;
import com.bidbuds.ecomm.entity.Customer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	private CustomerDao loginDao;

    public void init() {
        loginDao = new CustomerDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    	
    	   HttpSession httpsession = request.getSession();
    	
        String email = request.getParameter("email");       
        String password = request.getParameter("password");
     
       //verfiying logining customer with database 
        Customer customer = loginDao.getCustomerByEmailAndPassword(email, password);
        
        //saving customer in session for verificaiton on admin side
        httpsession.setAttribute("currentLoggedCustomer", customer);
        
        //login authentication- normal users
        if (loginDao.validate(email, password) && (customer !=null && customer.getRole().equals("normal"))) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
            
        }
         
        //admin authentication
         else if(loginDao.validate(email, password) && (customer !=null && customer.getRole().equals("admin"))){
            	   RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                   dispatcher.forward(request, response);
                   
            }
                
        
        //no authentication error message
         else {
        	
			httpsession.setAttribute("message", "Invalid Email or Password");
			
			 response.sendRedirect("login.jsp");
			 return;
        }
    
}
}
