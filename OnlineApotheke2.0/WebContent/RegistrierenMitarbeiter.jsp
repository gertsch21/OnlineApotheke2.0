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

<title>MitarbeiterRegistration</title>

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
			<h1>Mitarbeiter registrieren:</h1>

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
			<form action="MitarbeiterRegistrierController" method="POST">
				<table class="table">
					<tr>
						<th>Parameter</th>
						<th>Wert</th>
					</tr>

					<tr>
						<td>Vorname:</td>
						<td><input type="text" name="vorname" value="Max" /></td>
					</tr>
					<tr>
						<td>Nachname:</td>
						<td><input type="text" name="nachname" value="Mustermann" /></td>
					</tr>

					<tr>
						<td>Email:</td>
						<td><input type="text" name="email" value="MaxMustermann@gmx.at" /></td>
					</tr>
										<tr>
					<td>SVNR:</td>
						<td><input type="text" name="svnr" value="2125231200" /></td>
					</tr>
					<tr>
						<td>Gehalt:</td>
						<td><input type="text" name="gehalt" value="4000" /></td>
					</tr>
				</table>
				<table class="table">
					<tr>
						<td>Land:</td>
						<td><input type="text" name="land" value="Austria" /></td>
					</tr>
					<tr>
						<td>PLZ:</td>
						<td><input type="number" name="plz" value="2020" /></td>
					</tr>
					<tr>
						<td>Wohnort:</td>
						<td><input type="text" name="wohnort" value="Hollabrunn" /></td>
					</tr>
					<tr>
						<td>Strasse:</td>
						<td><input type="text" name="strasse" value="Wiener Strasse" /></td>
					</tr>
					<tr>
						<td>HausNr:</td>
						<td><input type="number" name="nummer" value="11" /></td>
					</tr>
				</table>

				<table class="table">
					<tr>
						<td>Username:</td>
						<td><input type="text" name="username" value="maxxxxi" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" value="maxxxxi" /></td>
					</tr>
					<tr>
						<td>Password wiederholen:</td>
						<td><input type="password" name="passwordW" value="maxxxxi" /></td>
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