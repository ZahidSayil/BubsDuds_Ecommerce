
<%@page import="com.bidbuds.ecomm.entity.Customer" %>

<%

//determining if a user is admin

Customer user =  (Customer)session.getAttribute("currentLoggedCustomer");

if(user == null || user.getRole().equals("normal")) {
	
	
	session.setAttribute("message", "You are not logged In or has no admin authority.");
	response.sendRedirect("login.jsp");
	return;
}


%>




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Panel</title>
</head>
<body>

<%@ include file="jsp/navbar.jsp" %>

<h1>Hello to admin page</h1>
</body>
</html>