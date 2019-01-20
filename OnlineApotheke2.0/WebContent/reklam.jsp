<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Apotheke zur heilenden Pille</title>
	
	<link rel="shortcut icon" href="Bilder/favicon_old.png" type="image/vnd.microsoft.icon" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css" media="all" />

</head>

<body>
	<jsp:include page="/Navigation.jsp"/>
	<!-- End Header -->
	
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
					<form action="ReklamierenController" method="POST">
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
