<%@page import="management.Benutzermanagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.text.DecimalFormat, model.Einkaufswagen, servlets.ReklamierenController, management.Bestellungsmanagement, model.Item, java.util.Set, java.util.Objects, model.Kunde, management.Benutzermanagement" %>
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
			<h4>Meine Bestellungen: <%=session.getAttribute("username")%></h4>
		</div>
		
<%

if (session.getAttribute("username") == null) {
%>
	<b>Keine Bestellungen vorhanden</b>
<% 	
}
else {
	
%>
<%	String uname=String.valueOf(session.getAttribute("username"));
	Kunde user = Benutzermanagement.getInstance().getKundeByUname(uname);
	String benutzer = user.getBenutzername();
	Set<Einkaufswagen> bestellungen = user.getEinkaufswagen(); %>
	
<% for(Einkaufswagen order : bestellungen) {%>
		<div class="row">
			<div class="col-lg-10">
				<ul class="list-group list-group-flush">
					<li class="list-group-item">
						<div class="container" id="full_order">
							<div class="row">
								<div class="col-9" id="order_txt">
								Bestellung #<%=order.getEinkaufswagen_id() %>
								</div>
								<div class="col-3" id="total">
									<p class="pr">
									<p>
								</div>
							</div>
							<ul class="list-group list-group-flush">
							<% Set<Item> itemlist = order.getItems(); %>
							<% for(Item item : itemlist) {%>
								<li class="list-group-item">
									<div class="row">
										<div class="col-lg-12" id="order_product">
											<div class="row">
												<div class="col-sm-12" id="product_label_order">
												<%=item.getProdukt().getName() %>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-6" id="price">
													<p class="pr"> <%double price = item.getProdukt().getPreis();%>
													<b>Price:</b> <%=item.getProdukt().getPreis() %> EUR
													</p>
												</div>
												<div class="col-sm-4">
													<p class="pr"> <%int amount = item.getAnzahl();%>
													<b>Amount:</b> <%=item.getAnzahl() %> 
													<p>
												</div>
												<div class="col-sm-2" id="total_item">
													<p class="pr" id="total">
													<%= price*amount %> 
													<p>
												</div>
											</div>
									
											<div class="row">
												<div class="col-12">
													<form class="form-inline my-2 my-lg-0" action="reklam.jsp" method="GET">
														<input type="hidden" name="uname" value="<%=user.getBenutzername() %>"/>
														<input type="hidden" name="prodname" value="<%=item.getProdukt().getName() %>"/>
														<input type="hidden" name="prodid" value="<%=item.getProdukt().getProdukt_id()%>"/>
														<input type="hidden" name="userid" value="<%=user.getBenutzer_id() %>"/>
														<button class="btn btn-outline-warning" type="submit">Reklamieren</button>
													</form>
												</div>
											</div>
										</div>
									</div>
								</li>
								<% }%>
								
							</ul>
						</div>
					</li>	
				</ul>
				
			</div>
		</div>
		<%} } %>
	</div>
	
</body>

</html>
