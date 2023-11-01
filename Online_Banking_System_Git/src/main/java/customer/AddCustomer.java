package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddCustomer")
public class AddCustomer extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String pincode=req.getParameter("pincode");
		
		Random random=new Random();
		long account_number=random.nextLong(100000000000l);
		
		double balance=500;
		
		try
		
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root");
			PreparedStatement ps=connection.prepareStatement("INSERT INTO customer(id,name,accountnumber,pincode,balance) values(?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setLong(3, account_number);
			ps.setString(4, pincode);
			ps.setDouble(5, balance);
			
			ps.executeUpdate();
			PrintWriter pw=resp.getWriter();
			pw.println("<h1>Data Saved Successfully</h1>");
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
