<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	
	<title>Apotheke zur heilenden Pille</title>
	
	<link rel="shortcut icon" href="Bilder/favicon_old.png" type="image/vnd.microsoft.icon" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" media="all" />
	
</head>

<body>
	<jsp:include page="/Navigation.jsp"/>
	<!-- End Header -->
	
	<div class="container">
		<div class="row" id="title" style="display: none;">
			<h1>Title</h1>
		</div>
		<div class="row" id="order">
			<h4>Reklamation erfolgreich Abgeschickt!</h4>
		</div>
				
			<%
				if(session.getAttribute("message")!=null){ %>
					<h2>Neue Meldung: <%=session.getAttribute("message") %></h2>
			<% 		request.getSession().setAttribute("message", null);
				} 
				if(request.getSession().getAttribute("fehler")!=null){ %> 
					<h2>Achtung Fehler aufgetreten: <%=request.getSession().getAttribute("fehler")%></h2>
			<% 		request.getSession().setAttribute("fehler", null);
				} 
			%>	
				
		<div class="row">
			<div class="col-lg-10">
				<p>Vielen Dank Für Ihr Feedback zu unseren Produkten!</p>
			</div>
		</div>
	</div>
</body>

</html>
