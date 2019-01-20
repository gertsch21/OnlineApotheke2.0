<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content=""/>
<meta name="author" content="Gerhard"/>

<title>ProduktAnlegen</title>

<!-- To ensure proper rendering and touch zooming for mobile -->
<meta name="viewport" content="width=device-width, initial-scale=1"/>

<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"/>

<!-- Bootstrap theme -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	rel="stylesheet"/>

</head>
<body>
	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
			<h1>Produkt anlegen:</h1>

<!-- Möglichen Fehlertext ausgeben -->
			<%
				if (request.getSession().getAttribute("fehler") != null) {
			%>
			<h2><%=request.getSession().getAttribute("fehler")%></h2>
			<%
					request.getSession().setAttribute("fehler", null); //nach ausgabe auf null setzen
				}
			%>


<!-- Eingaben zum Registrieren -->
			<form action="ProduktHinzufuegenController" method="POST">
				<table class="table">
					<tr>
						<th>Parameter</th>
						<th>Wert</th>
					</tr>

					<tr>
						<td>Name:</td>
						<td><input type="text" name="name" value="" /></td>
					</tr>
					<tr>
						<td>Anmerkung:</td>
						<td><input type="text" name="anmerkung" value="" /></td>
					</tr>
					<tr>
						<td>Preis:</td>
						<td><input type="text" name="preis" value="" />(Zahl mit . als Komma)</td>
					</tr>
					<tr>
						<td>Wirkungsweise:</td>
						<td>
							<td><input type="text" name="wirkungsweise" value="" /></td>
						</td>
					</tr>
					<tr>
						<td>Vorhandene Menge:</td>
						<td>
							<td><input type="text" name="vorhandene_menge" value="" /></td>
						</td>
					</tr>
					<tr>
						<td>Wirkstoff:</td>
						<td>
							<td><input type="text" name="wirkstoff" value="" /></td>
						</td>
					</tr>
					<tr>
						<td>Hersteller:</td>
						<td>
							<td><input type="text" name="hersteller" value="" /></td>
						</td>
					</tr>
					<tr>
						<td>Enthaltene_menge:</td>
						<td>
							<td><input type="text" name="enthaltene_menge" value="" /></td>
						</td>
					</tr>
					<tr>
						<td>Anwendungsweise:</td>
						<td>
							<td><input type="text" name="anwendungsweise" value="" /></td>
						</td>
					</tr>
				
					<tr>
						<td><input type="submit" value="Send" /></td>
						<td></td>
					</tr>
				</table>
			</form>

<!-- Einfaches Retour zur Hauptseite -->
			<form method="get" action="HauptseiteMitarbeiter.jsp">
			    <button type="submit">Back</button>
			</form>

		</div>
	</div>
</body>
</html>