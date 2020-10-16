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
    	
        String email = request.getParameter("email");       
        String password = request.getParameter("password");
       

        if (loginDao.validate(email, password)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/index.jsp");
            dispatcher.forward(request, response);
        } else {
        	HttpSession httpsession = request.getSession();
			httpsession.setAttribute("message", "Invalid Email or Password");
			
			 response.sendRedirect("login.jsp");
			 return;
        }
    }

}
