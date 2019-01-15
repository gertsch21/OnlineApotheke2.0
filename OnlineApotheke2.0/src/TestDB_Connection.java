
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.*;

/**
 * @author Gerhard
 *
 * 
 */

public class TestDB_Connection{
//Daten für DB Verbindung
	private final String dbUrl = "jdbc:postgresql://gerhardscloud.ddns.net/Online_Apotheke";
	private final String user = "postgres";
	private final String pwd = "bullet2111";
	
//Statements um Daten aus db zu holen(über SQL)
	private PreparedStatement saveProdStmt;
	private PreparedStatement saveCatStmt;
	private PreparedStatement loadAllProdStmt;
	private PreparedStatement loadAllCatStmt;
	private PreparedStatement loadProductByIdStmt;
	private PreparedStatement loadCategoryByIdStmt;
	private PreparedStatement deleteProdStmt;
	private PreparedStatement deleteCatStmt;
	
	
	public static void main(String[] args) {
		TestDB_Connection tb = new TestDB_Connection();
		for(Produkt_mit_annotation p : tb.getProduktList()){
			System.out.println(p);
		}
	}
	
	
	public TestDB_Connection() {
		try {
			Class.forName("org.postgresql.Driver");
			Properties props = new Properties();
			props.setProperty("user", "postgres");
			props.setProperty("password", "bullet2111");

			Connection con = DriverManager.getConnection(dbUrl,props);
			
			loadAllProdStmt = con.prepareStatement("SELECT * FROM produkt");
			loadAllCatStmt = con.prepareStatement("SELECT * FROM produktgruppe");
			
			loadProductByIdStmt = con.prepareStatement("SELECT * FROM produkt WHERE produkt_id=?");
			loadCategoryByIdStmt = con.prepareStatement("SELECT * FROM produktgruppe WHERE produktgruppe_id=?");
			
			deleteProdStmt = con.prepareStatement("DELETE FROM produkt WHERE produkt_id=?");
			deleteCatStmt = con.prepareStatement("DELETE FROM produktgruppe WHERE produktgruppe_id=?");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Verbindungsaufbau zur DB nicht möglich!! ("+e.getMessage()+")");
		}

	}


	
	/**
	 * 
	 * query list of all products from database
	 */
	public List<Produkt_mit_annotation> getProduktList() {
		List<Produkt_mit_annotation> liste = new ArrayList<Produkt_mit_annotation>();
		try {
			ResultSet result = loadAllProdStmt.executeQuery();
			int anzProdukte = 0;
			
			while(result.next()){
			    String pName = result.getString("name");
			    String pDescr = result.getString("wirkstoff");

				try {
					int produkt_id = Integer.parseInt(result.getString("produkt_id"));
					String name = result.getString("name");
					double preis = Double.parseDouble(result.getString("preis"));
					int menge_enthalten = Integer.parseInt(result.getString("menge_enthalten"));
					String hersteller = result.getString("hersteller");
					String wirkstoff = result.getString("wirkstoff");
					String wirkungsweise = result.getString("wirkungsweise");
					String anwendungsweise = result.getString("anwendungsweise");
					String anmerkung = result.getString("anmerkung");
					int ersteller_id = Integer.parseInt(result.getString("ersteller_id"));
					int produktgruppe_id = Integer.parseInt(result.getString("produktgruppe_id"));
					
					liste.add(new Produkt_mit_annotation(produkt_id, name, preis, menge_enthalten, hersteller, wirkstoff, wirkungsweise, anwendungsweise, anmerkung, ersteller_id, produktgruppe_id));
					anzProdukte++;
				} catch (NumberFormatException e) {
					System.out.println("DBProduktDao: getProduktList: Error beim Parsen des Strings in der DB in int wert");
					return null;
				}catch(Exception e){
					System.out.println("DBProduktDao: getProduktList: Error beim Parsen: "+e.getMessage());
					return null;
				}	
			}
			
			System.out.println("DBProduktDao: getProduktList: Anzahl Produkte: " + anzProdukte);

			return liste;
			
		} catch (Exception e) {
			System.out.println("DBProduktDAO: getProduktListe: Error");
			return null;
		}
	}
//	
//	/**
//	 * 
//	 * query list of all products from database
//	 */
//	@Override
//	public List<Category> getCategoryList() {
//		List<Category> liste = new ArrayList<Category>();
//		try {
//			ResultSet result = loadAllCatStmt.executeQuery();
//			int anzCategory = 0;
//			
//			while(result.next()){
//			    String cName = result.getString("NAME");
//			    String cDescr = result.getString("DESCRIPTION");
//
//				try {
//					int cID = Integer.parseInt(result.getString("CATEGORYID"));
//					
//					
//					liste.add(new Category(cID, cName, cDescr));
//					anzCategory++;
//				} catch (NumberFormatException e) {
//					System.out.println("DBProduktDao: getCategoryList: Error beim Parsen des Strings in der DB in int wert");
//					return null;
//				}catch(Exception e){
//					System.out.println("DBProduktDao: getCategoryList: Error beim Parsen: "+e.getMessage());
//					return null;
//				}	
//			}
//			
//			System.out.println("DBProduktDao: getCategoryList: Anzahl Categories: " + anzCategory);
//
//			return liste;
//			
//		} catch (Exception e) {
//			System.out.println("DBProduktDAO: getCategoryListe: Error");
//			return null;
//		}
//	}
//
//	/**
//	 * 
//	 * query product from DB by productID
//	 */
//	@Override
//	public Produkt getProduktByProduktID(String prodID) {
//		
//		try {
//			loadProductByIdStmt.setInt(1, Integer.parseInt(prodID));
//			ResultSet result = loadProductByIdStmt.executeQuery();
//			if (!result.next()){
//				System.out.println("DBProduktDAO: getProduktByProduktID: Kein Produkt gefunden!");
//				return null;
//			}
//			
//			
//			String pName = result.getString("NAME");
//		    String pDescr = result.getString("DESCRIPTION");
//
//			try {
//				int pID = Integer.parseInt(result.getString("PRODUCTID"));
//				double pPrice = Double.parseDouble(result.getString("PRICE"));
//				int pCatID = Integer.parseInt(result.getString("CATEGORYID"));
//			
//				return new Produkt(pID, pName, pPrice, pDescr, pCatID);
//				
//			} catch (NumberFormatException e) {
//				System.out.println("DBProduktDao: getProduktByProduktID: Error beim Parsen des Strings in der DB in int wert");
//				return null;
//			}catch(Exception e){
//				System.out.println("DBProduktDao: getProduktByProduktID: Error beim Parsen: "+e.getMessage());
//				return null;
//			}	
//			
//
//		} catch (Exception e) {
//			System.out.println("DBProduktDAO: getProduktByProduktID: Error");
//			return null;
//		}
//	}
//
//
//	/**
//	 * 
//	 * query category from DB by productID
//	 */
//	@Override
//	public  Category getCategoryByCategoryID(String categoryID) {
//		
//		try {
//			loadCategoryByIdStmt.setInt(1, Integer.parseInt(categoryID));
//			ResultSet result = loadCategoryByIdStmt.executeQuery();
//			if (!result.next()){
//				System.out.println("DBProduktDAO: getCategoryByCategoryID: Keine Kategorie gefunden!");
//				return null;
//			}
//			
//			
//			String cName = result.getString("NAME");
//		    String cDescr = result.getString("DESCRIPTION");
//
//			try {
//				int cID = Integer.parseInt(result.getString("CATEGORYID"));
//			
//				return new Category(cID, cName, cDescr);
//				
//			} catch (NumberFormatException e) {
//				System.out.println("DBProduktDao: getCategoryByCategoryID: Error beim Parsen des Strings in der DB in int wert");
//				return null;
//			}catch(Exception e){
//				System.out.println("DBProduktDao: getCategoryByCategoryID: Error beim Parsen: "+e.getMessage());
//				return null;
//			}	
//			
//
//		} catch (Exception e) {
//			System.out.println("DBProduktDAO: getCategoryByCategoryID: Error");
//			return null;
//		}
//	}
//	
//	/**
//	 * 
//	 * delete product from DB
//	 */
//	@Override
//	public boolean loescheProduktByProdID(String prodID) {
//		if(prodID.equals(""))
//			return false; //no product with this ID
//		
//		try{
//			deleteProdStmt.setString(1, prodID);
//			deleteProdStmt.executeUpdate();
//		}catch(SQLException e){
//			System.out.println("DBProduktDAO: LoescheProduktByProdID: "+e.getMessage());
//			return false;
//		}
//		return true;
//	}
//	
//	/**
//	 * 
//	 * delete product from DB
//	 */
//	@Override
//	public boolean loescheCategoryByCategoryID(String catID) {
//		if(catID.equals(""))
//			return false; //no product with this ID
//		
//		try{
//			deleteCatStmt.setString(1, catID);
//			deleteCatStmt.executeUpdate();
//		}catch(SQLException e){
//			System.out.println("DBProduktDAO: LoescheCategoryByCategoryID: "+e.getMessage());
//			return false;
//		}
//		return true;
//	}

}
