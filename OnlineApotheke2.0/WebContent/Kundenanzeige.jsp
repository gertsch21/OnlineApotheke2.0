<%@page import="model.Kunde"%>
<%@page import="management.Benutzermanagement"%>
<%@page import="model.Benutzer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	if (session.getAttribute("username") == null
			|| session.getAttribute("username").equals("null") || session.getAttribute("alleKunden")==null) {
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

<title>KundenAnzeige</title>

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
			<h1>Kunden:</h1>

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
				<table class="table">
					<tr>
						<th>username</th>
						<th>password</th>
						<th>Vorname</th>
						<th>Nachname</th>
						<th>Land</th>
						<th>PLZ</th>
						<th>Ort</th>
						<th>Strasse</th>
						<th>Hnr</th>
						<th>Update</th>
						<th>Entfernen</th>
					</tr>
<%for(Kunde b : (List<Kunde>) Benutzermanagement.getInstance().getAlleKunden()){ %>
			<form action="Benutzerverwaltungscontroller" method="POST">
			
					<tr>
						<td><%=b.getBenutzername()%></td>
						<input type="hidden" id="username" name="username" value="<%=b.getBenutzername()%>">
						<td><input type="text" name="kundePasswort<%=b.getBenutzername()%>" size="10" value="<%=b.getPasswort()%>"/></td>
						<td><input type="text" name="kundeVorname<%=b.getBenutzername()%>" size="10" value="<%=b.getVorname()%>"/></td>
						<td><input type="text" name="kundeNachname<%=b.getBenutzername()%>" size="10" value="<%=b.getNachname()%>"/></td>
						<td><input type="text" name="kundeLand<%=b.getBenutzername()%>" size="10" value="<%=b.getLand()%>"/></td>
						<td><input type="text" name="kundePLZ<%=b.getBenutzername()%>" size="10" value="<%=b.getPlz()%>"/></td>
						<td><input type="text" name="kundeOrt<%=b.getBenutzername()%>" size="10" value="<%=b.getOrt()%>"/></td>
						<td><input type="text" name="kundeStrasse<%=b.getBenutzername()%>" size="10" value="<%=b.getStrasse()%>"/></td>
						<td><input type="text" name="kundeHausnummer<%=b.getBenutzername()%>" size="5" value="<%=b.getHausnummer()%>"/></td>
						
						
						<td><input type="submit" name="zuUpdaten" value="<%=b.getBenutzername()%>"/></td>
						<td><input type="submit" name="zuLoeschen" value="<%=b.getBenutzername()%>"/></td>
					</tr>
<%} %>
</table>	
Anzahl an Kunden: <%=Benutzermanagement.getInstance().getAlleKunden().size()%>			
			</form>

<!-- Einfaches Retour zur Hauptseite -->
			<form method="get" action="MitarbeiterController">
			    <button type="submit">Back</button>
			</form>

		</div>
	</div>
</body>
</html>