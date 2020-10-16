<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%@ include file= "jsp/header.jsp"%>
<title>login Pages</title>
</head>
<body>

<%@ include file="jsp/navbar.jsp" %>


<div class="container-fluid h-100 mt-5">
    <div class="row justify-content-center align-items-center h-100">
        <div class="col col-sm-6 col-md-6 col-lg-4 col-xl-3">
            <form action="LoginServlet" method="post">


 				<%@include file="jsp/message.jsp" %> 
                <div class="form-group">
                    <input class="form-control form-control-lg" name="email" placeholder="User email" type="text">
                </div>
                <div class="form-group">
                    <input class="form-control form-control-lg" name="password" placeholder="Password" type="password">
                </div>
                <div class="form-group">
                    <button class="btn btn-info btn-lg btn-block">Sign In</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>



