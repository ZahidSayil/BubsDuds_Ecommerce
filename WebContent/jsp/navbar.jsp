
<%@page import="com.bidbuds.ecomm.entity.Customer" %>

<%

//else if rendering for navigation

Customer user1 =  (Customer)session.getAttribute("currentLoggedCustomer");

%>





<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp" %>

</head>
<body>


<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="index.jsp">BidBuds Toys</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Categories</a>
      </li>
      
        <li class="nav-item">
        <a class="nav-link" href="#">About Us</a>
      </li>
      </ul>
      
      <!-- login and register navigation -->
    
      <ul class="navbar-nav mr-auto">
      
      <%
      
       if(user1 == null)
    	   
       {
    	   %>
    <li class="nav-item">
        <a class="nav-link" href="login.jsp">Login</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="register.jsp">Register</a>
      </li>
      
      
    	   <% 
       } else {
    	   
    	   
    	 %>  
    	   
    	   
    	<li class="nav-item">
        <a class="nav-link" href="#"><%= user1.getLastName() %></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="LogoutServlet">Logout</a>
      </li> 
    	   
    	   
    	   
    	   
    	   
    	   <% 
       }
   
      
      %>
   
      
      </ul>
  
  </div>
</nav>

</body>
</html>