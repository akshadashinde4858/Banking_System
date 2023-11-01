package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String id=req.getParameter("id");
		String pincode=req.getParameter("pincode");
		String newname=req.getParameter("newname");
		String newpin=req.getParameter("newpin");	
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root");
			PreparedStatement ps=conn.prepareStatement("update customer set name=?, pincode=? where id=? and pincode=?");
			ps.setString(1, newname);
			ps.setInt(2, Integer.parseInt(newpin));
			ps.setInt(3, Integer.parseInt(id));
			ps.setInt(4, Integer.parseInt(pincode));
			
			int ct=ps.executeUpdate();
			PrintWriter pw=resp.getWriter();
			if(ct!=0)
			{
				pw.print("<h1>Data Updated Successfull</h1>");
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
