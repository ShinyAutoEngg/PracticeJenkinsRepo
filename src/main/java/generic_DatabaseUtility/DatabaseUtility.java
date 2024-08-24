package generic_DatabaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility 
{	
	public Driver driverRef;
	public Connection conn;
	public void getConnectionOfDatabase(String url,String username,String password) throws SQLException
	{
		try
		{
		driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		conn=DriverManager.getConnection(url, username, password);
		}
		catch(Exception e)
		{
			System.out.println("Exception in DatabaseUtility.getConnectionOfDatabase()");
		}
	}
	
	public String verifyDataInDB(String url,String username,String password,String projectName, int index)
	{
		String data="";
		try
		{
			DriverManager.registerDriver(driverRef);
			conn=DriverManager.getConnection(url, username, password);
			Statement stat = conn.createStatement();
			ResultSet result = stat.executeQuery("select * from "+projectName+"");
			while(result.next())
			{
				data=result.getString(3);
			}
		}
		catch(Exception e)
		{
			System.out.println("SQL EXCEPTION====> NEEDS TO BE HANDLED");
		}
		return data;
	}
	
	public void closeDatabaseConnection() throws SQLException 
	{
		try
		{
		conn.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while closing the connetion");
		}
	}
	

}
