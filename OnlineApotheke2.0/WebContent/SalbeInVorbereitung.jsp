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
	
	
	<title>SalbeInVorbereitung</title>
</head>
<body>	
	<jsp:include page="/Navigation.jsp"/>
	<!-- End Header -->
		
	<div class="container theme-showcase">
		<div class="jumbotron">
			

			
			<%
				if (request.getSession().getAttribute("fehler") != null) {
			%>
					<h2><%=request.getSession().getAttribute("fehler")%></h2>
			<%
					request.getSession().setAttribute("fehler", null); //nach ausgabe auf null setzen
				}
			%>
			
		<h2><%=request.getSession().getAttribute("HinweisText")%></h2>

		</div>
	</div>	
		
</body>
</html>