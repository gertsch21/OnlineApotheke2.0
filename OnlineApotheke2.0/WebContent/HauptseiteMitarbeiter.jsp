<%@page import="management.Benutzermanagement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	if (session.getAttribute("username") == null
	|| session.getAttribute("username").equals("null")) {
		System.out
		.println("HauptseiteMitarbeiter: nicht eingeloggt -> Login");
		response.sendRedirect("Login.jsp");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="" />
<meta name="author" content="Gerhard" />

<title>KundenHauptseite</title>

<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/custom.css"
	rel="stylesheet" media="screen">
<!-- Bootstrap theme -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	rel="stylesheet" />

<link rel="shortcut icon" href="Bilder/favicon_old.png"
	type="image/vnd.microsoft.icon" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.css"
	media="all" />

</head>
<body>

	<!-------- HEADER -------->

	<jsp:include page="/Navigation.jsp" />


	<%
	if(session.getAttribute("message")!=null){
%>
	<h2>
		Neue Meldung:
		<%=session.getAttribute("message")%></h2>
	<%
	request.getSession().setAttribute("message", null);
	} 
	if(request.getSession().getAttribute("fehler")!=null){
%>
	<h2>
		Achtung Fehler aufgetreten:
		<%=request.getSession().getAttribute("fehler")%></h2>
	<%
	request.getSession().setAttribute("fehler", null);
	}
%>
	<!-- End HEADER -->

	<!-- Container für Hauptbereich: große Spalte für Produkte + kleine für Warenkorb -->
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<!-- Hauptspalte -->

			</div>
			<form action="MitarbeiterController" method="POST">
				<input class="btn btn-primary" name="prover" type="submit"
					value="Produkte verwalten" /> <input type="hidden" name="prover"
					value="true" />
			</form>

			<form action="MitarbeiterController" method="POST">
				<input class="btn btn-primary" name="produktReg" type="submit"
					value="Produkt hinzufügen" /> <input type="hidden"
					name="produktReg" value="true" />
			</form>
			
			<form action="MitarbeiterController" method="POST">
				<input class="btn btn-primary" name="mitarbeiterReg" type="submit"
					value="Mitarbeiter registrieren" /> <input type="hidden"
					name="mitarbeiterReg" value="true" />
			</form>
			
			<form action="MitarbeiterController" method="POST">
				<input class="btn btn-primary" name="benver" type="submit"
					value="Kunden verwalten" /> <input type="hidden" name="benver"
					value="true" />
			</form>
		</div>



		<!-------- Footer -------->
		<div class="row">
			<div class="col-md-12">
				<br />&copy; Apotheke Online 2019
			</div>
		</div>
		<!-- Ende Footer -->
	</div>

</body>
</html>
