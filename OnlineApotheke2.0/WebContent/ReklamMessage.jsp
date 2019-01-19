<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link rel="shortcut icon" href="Bilder/favicon.png" type="image/vnd.microsoft.icon" />
	<title>Apotheke zur heilenden Pille</title>
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" media="all" />

	<!--<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.2.1/litera/bootstrap.min.css" rel="stylesheet" integrity="sha384-DkdUb51XC4VPExQljj9mtMYspLBzNJscXpAnuo0rcJcLd7aeOH3jnz6cS1v8OFMW" crossorigin="anonymous">
	-->
</head>

<body>
		<div class="bs-component">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">Apotheke zur heilenden Pille</a>
			<img src="Bilder/favicon.png" alt="Home" style="max-width: 40px; height: 40px; margin-right:10px;"/>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarColor03">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item">
						<a class="nav-link" href="ShopController">Home <span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="SalbeHerstellenController">Herstellung beauftragen</a>
					</li>
					<li class="nav-item active">
						<a class="nav-link" href="/OnlineApotheke2.0/meinebestellungen.jsp">Meine Bestellungen</a>
					</li>
				</ul>
				<form class="form-inline my-2 my-lg-0" action="Logincontroller">
					<button class="btn btn-danger btn-sm my-2 my-sm-0 login" type="submit" value="Logout" name="logout">Logout</button>
				</form>
				<form class="form-inline my-2 my-lg-0" action="ProduktsucheController" method="POST">
					<input class="form-control mr-sm-2" type="text" placeholder="Search" name="suchwert"/>
					<button class="btn btn-danger my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</div>
	
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
