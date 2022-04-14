import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.net.ssl.SSLContext;

public class TheMain {
	public static void main(String[] args) {
		readAllData();
	}
	
	public static void insert(String lead,String actress,int year, String director) {
		Connection con = DbConnection.connect();
		PreparedStatement ps=null;
		try {
			String sql="INSERT INTO cinema(lead,actress,year,director) VALUES(?,?,?,?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1,lead);
			ps.setString(2,actress);
			ps.setInt(3,year);
			ps.setString(4,director);
			ps.execute();
			System.out.println("Data has been inserted ");
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}// always remember to close database connections
		finally {
		    try{
		        ps.close();
		        con.close();
		      } catch(SQLException e) {
		        System.out.println(e.toString());
		      }
		      
	    }
	}
	
	private static void readAllData() {
	    Connection con = DbConnection.connect(); 
	    PreparedStatement ps = null; 
	    ResultSet rs = null; 
	    
	    try {
	      String sql = "SELECT * FROM cinema";
	      ps = con.prepareStatement(sql); 
	      rs = ps.executeQuery();
	      System.out.println("ALL DETAILS\n");
	      while(rs.next()) {
	        String lead = rs.getString("lead"); 
	        String actress = rs.getString("actress"); 
	        int year = rs.getInt("year"); 
	        String director = rs.getString("director"); 
	        
	        
	        
	        System.out.println("Lead Actor: "+lead);
	        System.out.println("Actress: "+actress);
	        System.out.println("Year of Release: "+year);
	        System.out.println("Director : "+director+"\n\n");
	        
	      }
	    } catch(SQLException e) {
	      System.out.println(e.toString());
	    } finally {
	      try {
	        rs.close();
	        ps.close();
	        con.close(); 
	      } catch(SQLException e) {
	        System.out.println(e.toString());
	      }
	    }
	    
	    
	  }
	private static void readSpecificRow() {
	    // lets read specific row on the database
	    Connection con = DbConnection.connect(); 
	    PreparedStatement ps = null; 
	    ResultSet rs = null; 
	    try {
	      String sql = "Select director from cinema where year=2015 "; 
	      ps = con.prepareStatement(sql); 
	      ps.setString(1, "eunice@test.com");
	      rs = ps.executeQuery(); 
	      
	      // we are reading one row, so no need to loop 
	      String firstName = rs.getString(1); 
	      System.out.println(firstName);// it should give us eunice
	      
	    } catch(SQLException e) {
	      System.out.println(e.toString());
	    } finally {
	      // close connections
	      try{
	        rs.close(); 
	        ps.close();
	        con.close(); 
	      } catch (SQLException e) {
	        // TODO: handle exception
	        System.out.println(e.toString());
	      }
	      
	    }
	  }
}
