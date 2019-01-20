<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


	<link rel="shortcut icon" href="Bilder/favicon.png" type="image/vnd.microsoft.icon" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" media="all" />
</head>
<body>
	<div class="bs-component">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-new" href="ShopController" style="font-size: 18px; color: black; margin-right:15px;">Apotheke zur heilenden Pille</a>
			<img src="Bilder/favicon_old.png" alt="Home" style="max-width: 40px; height: 40px; margin-right:10px;"/>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse changefont" id="navbarColor03" style="font-size: 15px;">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item">
						<a class="nav-link" href="ShopController">Home <span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="SalbeHerstellenController">Herstellung beauftragen</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/OnlineApotheke2.0/meinebestellungen.jsp">Meine Bestellungen</a>
					</li>
				</ul>
				<form class="form-inline my-2 my-lg-0 log" action="Logincontroller" style="padding-right: 10px;">
					<button class="btn btn-success btn-sm my-2 my-sm-0" type="submit" value="Logout" name="logout">Logout</button>
				</form>
				<form class="form-inline my-2 my-lg-0" action="ProduktsucheController" method="POST">
					<input class="form-control mr-sm-2" type="text" placeholder="Search" name="suchwert"/>
					<button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</div>
</body>
