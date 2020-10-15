<%@page import="com.bidbuds.ecomm.util.FactoryProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp" %>

<title>Welcome to BidBud</title>
</head>
<body>

<%@ include file="navbar.jsp" %>

<h2>Welcome to The Home JSP</h2>


<% 

out.println(FactoryProvider.getFactory());
%>
</body>
</html>