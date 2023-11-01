package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdatePassword extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String id=req.getParameter("id");
		String pincode=req.getParameter("pincode");
		String newpin=req.getParameter("newpin");	
		
		try 
		{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root");
			PreparedStatement ps=conn.prepareStatement("update customer set pincode=? where id=? and pincode=?");
			ps.setInt(1, Integer.parseInt(newpin));
			ps.setInt(2, Integer.parseInt(id));
			ps.setInt(3, Integer.parseInt(pincode));
			
			int ct=ps.executeUpdate();
			PrintWriter pw=resp.getWriter();
			if(ct!=0)
			{
				pw.print("<h1>Password Updated Successfull</h1>");
			}
			else
			{
				pw.println("<h1>Data Not Present</h1>");
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
