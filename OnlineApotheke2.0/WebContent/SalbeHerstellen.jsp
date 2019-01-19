<%@page import="management.Produktmanagement"%>
<%@page import="dao.*"%>
<%@page import="model.*"%>
<%@page import="java.awt.List"%>
<%@page import="java.util.*"%>
<%@ page import="java.util.Collection,
                 java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />

	<!-- Bootstrap core CSS -->
	<link
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
		rel="stylesheet" />
	
	<!-- Bootstrap theme -->
	<link
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
		rel="stylesheet" />
	
	<title>SalbeHerstellen</title>
</head>
<body>
	<h1>Hallo Welt!</h1>
	
	
	<div class="container theme-showcase">
		<div class="jumbotron">
			<img src="Bilder/aon_logo.png" alt="" height="123" width="600"/>
			<h2>Stelle deine individuell Salbe her!</h2>
			
			<%
				if (request.getSession().getAttribute("fehler") != null) {
			%>
					<h2><%=request.getSession().getAttribute("fehler")%></h2>
			<%
					request.getSession().setAttribute("fehler", null); //nach ausgabe auf null setzen
				}
			%>
			
			<br />
			<form action="SalbeHerstellenController" method="POST">
				Salbenname: <input type="text" name="Salbenname" /></br>
				<table>
					<tr>
						<th>Inhaltsstoff</th>
						<th>Menge (g)</th>
					</tr>	
					
					<%
						for(int i = 1; i < 6; i++) {		
					%>
					<tr>
						<td>
							 <select name="InhaltsStoff<%=i%>"> 
							 <option value="Waehle Inhaltsstoff">Waehle Inhaltsstoff</option>
							<%
								ArrayList<Inhaltsstoff> prodlist= (ArrayList<Inhaltsstoff>) session.getAttribute("liste");
								
								for(Inhaltsstoff prod:prodlist){
							%>
								<option value="<%=prod.getStoff_name()%>"><% out.println(prod.getStoff_name());	}%> 	</option>
							</select>
						</td>
						<td><input type="number" name="gStoff<%=i%>" /><br /></td>
					</tr>
					<%
						}
							
					%>
				</table>
				<td>
				</br>
				Zusatzinformation: </br> <textarea rows="4" cols="50" name="Zusatzinformation">Enter text here...</textarea></br></br>
				Anmerkung: <input type="text" name="Anmerkung" /></br>
				<input class="btn btn-primary" type="submit" value="Salbe in Auftrag geben" />
			</form>
			<br />

		</div>
	</div>	
		
</body>
</html>