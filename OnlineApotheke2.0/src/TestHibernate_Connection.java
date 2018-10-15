import java.util.List; 

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Produkt;
import model.Produktgruppe;

public class TestHibernate_Connection {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      
      try {
         factory = new Configuration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
    	 //factory = new Configuration().configure("/resources/hibernate.cfg.xml").addPackage("model").addAnnotatedClass(Produkt.class).buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      
      TestHibernate_Connection ME = new TestHibernate_Connection();
      System.out.println("alle produkte auflisten");
      ME.listProdukte();
      System.out.println("\n\n\nalle produktgruppen auflisten");
      ME.listProduktgruppen();
     
      
      
      
//      /* Add few employee records in database */
//      Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
//      Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
//      Integer empID3 = ME.addEmployee("John", "Paul", 10000);

  
//      /* Update employee's records */
//      ME.updateEmployee(empID1, 5000);
//
//      /* Delete an employee from the database */
//      ME.deleteEmployee(empID2);

      /* List down new list of the employees */
//      ME.listProdukte();   
   
//   /* Method to CREATE an employee in the database */
//   public Integer addProdukt(String name, String lname, int salary){
//      Session session = factory.openSession();
//      Transaction tx = null;
//      Integer employeeID = null;
//      
//      try {
//         tx = session.beginTransaction();
//         Employee employee = new Employee(fname, lname, salary);
//         employeeID = (Integer) session.save(employee); 
//         tx.commit();
//      } catch (HibernateException e) {
//         if (tx!=null) tx.rollback();
//         e.printStackTrace(); 
//      } finally {
//         session.close(); 
//      }
//      return employeeID;
   }
   
   
   public void listProduktgruppen() {
	   Session session = factory.openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         @SuppressWarnings("unchecked")
			List<Produktgruppe> produktgruppen = session.createQuery("FROM Produktgruppe").list(); 
	         for(Produktgruppe p : produktgruppen) {
	        	 System.out.println(p);
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
   }
   
   /* Method to  READ all the employees */
   public void listProdukte( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         @SuppressWarnings("unchecked")
		List<Produkt> produkte = session.createQuery("FROM Produkt").list(); 
         for(Produkt p : produkte) {
        	 System.out.println(p);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
//   /* Method to UPDATE salary for an employee */
//   public void updateEmployee(Integer EmployeeID, int salary ){
//      Session session = factory.openSession();
//      Transaction tx = null;
//      
//      try {
//         tx = session.beginTransaction();
//         Employee employee = (Employee)session.get(Employee.class, EmployeeID); 
//         employee.setSalary( salary );
//		 session.update(employee); 
//         tx.commit();
//      } catch (HibernateException e) {
//         if (tx!=null) tx.rollback();
//         e.printStackTrace(); 
//      } finally {
//         session.close(); 
//      }
//   }
//   
//   /* Method to DELETE an employee from the records */
//   public void deleteEmployee(Integer EmployeeID){
//      Session session = factory.openSession();
//      Transaction tx = null;
//      
//      try {
//         tx = session.beginTransaction();
//         Employee employee = (Employee)session.get(Employee.class, EmployeeID); 
//         session.delete(employee); 
//         tx.commit();
//      } catch (HibernateException e) {
//         if (tx!=null) tx.rollback();
//         e.printStackTrace(); 
//      } finally {
//         session.close(); 
//      }
//   }
}