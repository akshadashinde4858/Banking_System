package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/allcustomers")
public class ViewAllCustomer extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root");
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select * from customer");
			PrintWriter pw=resp.getWriter();
			while(rs.next())
			{
				 // Retrieve data from the ResultSet and print it here
	            int id = rs.getInt("id");
	            String name = rs.getString("name"); // Add more fields as needed
	            long accountnumber=rs.getLong("accountnumber");
	            int pincode=rs.getInt("pincode");
	            float balance=rs.getFloat("balance");

	            pw.println("ID: " + id + ", Name: " + name+ " Account Number: "+accountnumber+ " Pincode: "+pincode+ " Balance: "+balance); // Adjust the format as needed
			}
	}
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
