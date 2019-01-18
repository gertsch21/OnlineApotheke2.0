package myRestPackage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import management.Produktmanagement;
import model.Produkt;

@Path("/produkte")
public class Produktdetail {
	@Path("{produktid}")
	@GET
	@Produces("application/xml")
	public String convertCtoFfromInput(@PathParam("produktid") int produktid) {
		Produktmanagement proman = Produktmanagement.getInstance();
		System.out.println("Restcall: /produkte/"+produktid);
		
		Produkt p = proman.getProduktByProduktID(produktid);
		
		return "<Produkt><name>" + p.getName() + "</name></Produkt>";
		//return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
}
