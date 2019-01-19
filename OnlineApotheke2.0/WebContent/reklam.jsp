<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link rel="shortcut icon" href="images/favicon.png" type="image/vnd.microsoft.icon" />
	<title>Apotheke zur heilenden Pille</title>
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" media="all" />

	<!--<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.2.1/litera/bootstrap.min.css" rel="stylesheet" integrity="sha384-DkdUb51XC4VPExQljj9mtMYspLBzNJscXpAnuo0rcJcLd7aeOH3jnz6cS1v8OFMW" crossorigin="anonymous">
	-->
</head>

<body>
	<div class="bs-component">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">Apotheke zur heilenden Pille</a>
			<img src="images/favicon.png" alt="Home" style="max-width: 40px; height: 40px; margin-right:10px;"/>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarColor03">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item">
						<a class="nav-link" href="index.html">Home <span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="herstellung.html">Herstellung beauftragen</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="cart.html">Warenkorb</a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="bestellungen.html">Meine Bestellungen</a>
					</li>
				</ul>
				<form class="form-inline my-2 my-lg-0" action="login.html">
					<button class="btn btn-danger btn-sm my-2 my-sm-0 login" type="submit">Sign In</button>
				</form>
				<form class="form-inline my-2 my-lg-0">
					<button class="btn btn-danger btn-sm my-2 my-sm-0 login" type="submit">Sign Out</button>
				</form>
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="text" placeholder="Search"/>
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
			<h4><%=request.getParameter("prodname")%>/#<%=request.getParameter("prodid") %> Reklamieren</h4>
		</div>
		
		<div class="row">
			<div class="col-lg-10">
				<div class="container">
					<form action="ReklamierenController" method="GET">
						<div class="form-group">
							<label for="inputProductName">Beschreibung</label>
							<textarea type="text" name="descr" class="form-control" id="inputProductName" placeholder="Kurzbeschreibung des Problems" rows="4" value="eins"></textarea>
						</div>
						<input type="hidden" name="uname" value="<%=request.getParameter("uname") %>"/>
						<input type="hidden" name="prodname" value="<%=request.getParameter("prodname") %>"/>
						<input type="hidden" name="userid" value="<%=request.getParameter("userid") %>"/>
						<input type="hidden" name="prodid" value="<%=request.getParameter("prodid") %>"/>
						<div class="form-row">
							<button class="btn btn-warning my-2 my-sm-0 confirm" type="submit">Reklamieren</button>
						</div>
					</form>			
				</div>						
			</div>
		</div>
	</div>
	
</body>

</html>