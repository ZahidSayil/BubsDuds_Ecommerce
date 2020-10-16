<%@page import="com.bidbuds.ecomm.entity.Category"%>
<%@page import="com.bidbuds.ecomm.dao.CategoryDao"%>
<%@page import="com.bidbuds.ecomm.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.bidbuds.ecomm.dao.ProductDao"%>
<%@page import="com.bidbuds.ecomm.util.FactoryProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
    
  
<!DOCTYPE html>
<html>
<head>
<%@ include file="jsp/header.jsp" %>

<title>Welcome to BidBud</title>
</head>
<body>

<%@ include file="jsp/navbar.jsp" %>

<div class = "container mt-5">

<div class="row">

<%
		ProductDao productDao = new ProductDao(FactoryProvider.getFactory());
		List<Product> productList = null;	
		String categorySelected = null;
		
		//capturing user selected parameter
		categorySelected = request.getParameter("category");
		
		//if no category is selected or all is selected, we show all product
		if(categorySelected == null || categorySelected.equals("all")) {
			
			productList = productDao.getAllProduct();
 		
 		} else {
			
 			//parsign user selected category id 
			int catId = Integer.parseInt(categorySelected.trim());
			
 			//this methods return a list of user's selected's category item
			productList = productDao.getProductByCategoryId(catId);
		} 
		
		CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
		List<Category> categoryList = categoryDao.getAllCategories();

%> 


<!-- Show categories  -->

<div class="col-md-3" >


<div class="list-group">
  <a href="index.jsp?category=all" class="list-group-item list-group-item-action active">
    Bidbuds Categories
  </a>
  
  
  <% for (Category c : categoryList) {%> 
  <a href="index.jsp?category=<%= c.getId()%>" class="list-group-item list-group-item-action"><%= c.getName() %></a>
	<%} %>
</div>



</div>


<!-- Show Prdoucts  -->

<div class="col-md-9">


	<!-- row -->
	
	<div class="row ">
		
		<div class="col-md-12">
		
			<div class="card-columns">
			
				
				<!-- traversing products -->
				
				<%
								
					for(Product p : productList) {			
				%>
				
				
				<div class="card">
 					<img class="card-img-top p-2" style="max-height: 250px; max-width: 100%" src="<%= p.getImage_url() %>"  alt="Card image cap">
  					<div class="card-body">
   					<h5 class="card-title"><%= p.getName() %></h5>
  					<p class="card-text"><%=p.getDescription() %></p>
  					</div>
  					
  					<div class="card-footer">
  						<button href="#" class="btn btn-primary">Add to Cart </button>
  						<button class="btn btn-outline-success ml-3"> $ <%= p.getPrice() %></button>
  					</div>
				</div>
				
			
			<%} 
			
			//if no item in the category show this message 
			
			if(productList.size()== 0)
			{
				
				out.println("<h3> No item is available at this time..!!!</h3>");
			}
			
			
			
			%>
			</div>	
		</div>
	
	
	
	</div>




</div>



</div>


</div>

</body>
</html>