package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Registerd Successfully");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root"); // online_banking_system database is selected
		System.out.println("Establish connection successfullly");
		Statement st=conn.createStatement();
		System.out.println("statement created successfully");
		st.execute("create table admin(id int primary key,name varchar(30),email varchar(30),password varchar(30))");
		System.out.println("admin table created successfully");
		st.execute("create table customer(id int primary key,name varchar(30),accountnumber long,pincode int,balance float)");
		System.out.println("customer table created successfully");
	}
}
