package Grades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseCollection {
	
	String url = "jdbc:oracle:thin:system/password@localhost"; 
	Properties props = new Properties();
	
	//make sure to have the following steps done
	//1 copy ojdbc6.jar to webcontent/web-inf/lib
	//2 add ojdbc6.jar to build path - libraries / add external jar
	//3 Class.forName ("oracle.jdbc.driver.OracleDriver")
	static{
	    try {
	        Class.forName ("oracle.jdbc.driver.OracleDriver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public DatabaseCollection() {
		props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
	}
	
	public ArrayList<Grades> getGrades(){
		ArrayList<Grades> grades = new ArrayList<Grades>(); 
		
		try{
			Connection conn = DriverManager.getConnection(url,props);
			
			String sql ="select * from GradeBook";
	
	        //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	    
	        ResultSet result = preStatement.executeQuery();
	      
	        while(result.next()){
	        	Grades grd = new Grades();
	        	grd.setAssignment(result.getString("assignment"));
	        	grd.setGrade(result.getInt("grade"));
	        	
	        	grades.add(grd);
	        }
	        
	        conn.close();
		}catch(Exception e){
			grades = null;
			e.printStackTrace();
		}
		
		return grades;
		
	}
	
	public double getAverage(){
		double average = 0;
		
		try{
			Connection conn = DriverManager.getConnection(url,props);
			
			String sql ="select avg(grade) from gradebook";
	
	        //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	    
	        ResultSet result = preStatement.executeQuery();
	      
	        while(result.next()){
	        	average = result.getDouble(1);
	        	break;
	        }
	        
	        conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return average;
		
	}
	
	public boolean insertGrade(String assignment,int grade){
		String sql ="insert into GradeBook (Assignment, Grade) values(?, ?)";
        boolean isSuccess = false;
        try{
        
			//creating connection to Oracle database using JDBC
	        Connection conn = DriverManager.getConnection(url,props);
	        
	        //System.out.println(sql);
	        //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	        preStatement.setString(1, assignment);
	        preStatement.setInt(2, grade);

	        int result = preStatement.executeUpdate();
	        conn.close();
	        
	        if (result != 0){
	        	isSuccess = true;
	        }
        }catch(Exception e){
        	isSuccess = false;
        	e.printStackTrace();
        }
        
        return isSuccess;
	}

}
