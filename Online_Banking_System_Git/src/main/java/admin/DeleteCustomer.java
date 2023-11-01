package admin;

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


@WebServlet("/DeleteCustomer")
public class DeleteCustomer extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String pincode=req.getParameter("pincode");
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root");
			PreparedStatement ps=conn.prepareStatement("delete from customer where id=? and pincode=?");
			ps.setString(1, id);
			ps.setString(2, pincode);
			int count=ps.executeUpdate();
			PrintWriter pw=resp.getWriter();
			if(count!=0)
			{
				
				pw.print("Data Deleted Successfully");
			}
			else
			{
				pw.print("Data not found");
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
