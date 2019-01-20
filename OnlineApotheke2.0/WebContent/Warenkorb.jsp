<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Einkaufswagen"%>
<%@page import="model.Item"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Warenkorb</title>

</head>
<body>
 
 <% 

 session = request.getSession();
 Einkaufswagen einkaufswagen = (Einkaufswagen) session.getAttribute("Einkaufswagen");
 
 if(einkaufswagen.getItems().size()>0){ //.getAttribute("cartOut") != null) {
	 StringBuffer cartOut = new StringBuffer();
		
 	cartOut.append(
 			"<table class=\"cart\">"
     		);
 	 for(Item entry:einkaufswagen.getItems()) {
 	        cartOut.append(""
 	        		+ "<tr>"
 	        		+ "<td>" + /*prodman.getProduktByProduktID(Integer.parseInt(keyValue)).getName()*/ entry.getProdukt().getName() + "</td>"
 	        		+ "<td>&nbsp; x &nbsp;</td>"
 	        		+ "<td>" + /*pair.getValue().toString()*/entry.getAnzahl() + "</td>"
 	        		+ "</tr>");
 	    
 	        
 	 }
	 
	    cartOut.append(
     		 "</table>"
     		 + " <form action=\"KassaController\" method=\"GET\"> "
				 + "	<input class=\"btnGreen\" type=\"submit\" " 
				 + "		value=\"zur Kasse\"/>"
				 + "  </form>");

session.setAttribute("cartOut", cartOut);
	out.print(cartOut);
 } else {
	 out.print("Ihr Warenkorb ist leer!");
 }
 
 
 %>
 
</body>
</html>