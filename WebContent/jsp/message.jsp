<%

String notificaitonMessage = (String)session.getAttribute("message");

if(notificaitonMessage != null) {
	
	
	
%>


<!-- nofitication message -->


<div class="alert alert-success alert-dismissible fade show" role="alert">
  <strong><%= notificaitonMessage %></strong> 
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>

<%	
	//remove message from session
	session.removeAttribute("message");
	
	
	
}

%>