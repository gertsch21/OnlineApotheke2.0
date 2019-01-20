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
	
	<link rel="shortcut icon" href="Bilder/favicon_old.png" type="image/vnd.microsoft.icon" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" media="all" />
	
	<title>SalbeHerstellen</title>
</head>
<body>
	<jsp:include page="/Navigation.jsp"/>
	<!-- End Header -->
	
	
	<div class="container">
		<div class="jumbotron" style="font-size: 16px;">
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
				
				<table class="table">
					<tr>
						<td>
							<b>Salbenname</b> 
						</td>
						<td>
							<input type="text" name="Salbenname" />
						</td>
					</tr>
					<tr>
						<td>	
			 				<b>Inhaltsstoff</b>
						</td>
						<td>
							<b>Menge (g/ml)</b>
						</td>
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
					<tr>
						<td>
							<b>Zusatzinformation</b>
						</td>
						<td>
							<textarea rows="4" cols="50" name="Zusatzinformation">Enter text here...</textarea>
						</td>
					</tr>
					<tr>
						<td>
							<b>Anmerkung</b> 
						</td>
						<td>
							<input type="text" name="Anmerkung" />
						</td>
					</tr>
					
				</table>		
				<input class="btn btn-success" type="submit" value="Salbe in Auftrag geben" />
			</form>

		</div>
	</div>	
		
</body>
</html>