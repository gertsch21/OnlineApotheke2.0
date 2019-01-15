package management;

import java.util.List;
import java.util.Random;

import model.Produkt_mit_annotation;
import model.Category;
import dao.ProduktDAO;
import dao.DBProduktDAO;

public class Produktmanagement {
	private static Produktmanagement produktmanagementInstance=null;
	private ProduktDAO dao;
	
	private Produktmanagement() {
		dao = new DBProduktDAO();
	}
    
    public static Produktmanagement getInstance(){
		if(produktmanagementInstance == null) produktmanagementInstance = new Produktmanagement();
		return produktmanagementInstance;
	}
    
    
    public boolean produktAnlegen(String prodName, double price, String prodDescription, int categoryID) {
        Random randomGenerator = new Random();
		int id = randomGenerator.nextInt(Integer.MAX_VALUE);
		System.out.println("Produktmanagement:produktAnlegen: "+id+", "+prodName+", "+price+", "+prodDescription+", "+categoryID+", anlegen!");
		return dao.speichereProdukt(new Produkt_mit_annotation(id, prodName, price, prodDescription, categoryID));
    }
  
    public boolean categoryAnlegen(String categoryName, String categoryDescription) {
        Random randomGenerator = new Random();
		int id = randomGenerator.nextInt(Integer.MAX_VALUE);
		System.out.println("Produktmanagement:categoryAnlegen: "+id+", "+categoryName+", "+categoryDescription+", anlegen!");
		return dao.speichereCategory(new Category(id, categoryName, categoryDescription));
    }
    
    
    public List<Produkt_mit_annotation> getAlleProdukt(){
		return dao.getProduktList();
	}
	public List<Category> getAlleCategory(){
		return dao.getCategoryList();
	}
    
    
    public Produkt_mit_annotation getProduktByProduktID(String prodID){
		return dao.getProduktByProduktID(prodID);
	}
	public Category getCategorybyCategoryID(String categoryID){
		return dao.getCategoryByCategoryID(categoryID);
	}
	
	
	public Produkt_mit_annotation getProduktByProduktName(String prodName){
		return dao.getProduktByProduktID(prodName);
	}
    
    public boolean loescheProdukt(String prodID){
		return dao.loescheProduktByProdID(prodID);
	}
	public boolean loescheCategory(String categoryID){
		return dao.loescheCategoryByCategoryID(categoryID);
	}
}
