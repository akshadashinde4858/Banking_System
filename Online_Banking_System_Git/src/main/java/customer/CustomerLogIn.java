package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Customerlogin")
public class CustomerLogIn extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String id=req.getParameter("accno");
		String pincode=req.getParameter("pincode");
		CustomerMenu cm=new CustomerMenu();
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root");
			
			PreparedStatement ps=conn.prepareStatement("select * from customer where accountnumber=? and pincode=?");
				
			ps.setString(1,id);
			ps.setString(2, pincode);
			
			ResultSet rs=ps.executeQuery();
			PrintWriter pw=resp.getWriter();
			
			if(rs.next())
			{
					RequestDispatcher rd=req.getRequestDispatcher("CustomerMenu.html");
					rd.forward(req, resp);
					resp.setContentType("text/html");				
			}
			else
			{
				pw.println("<h1 style=color:red>invalid credentials!</h1>");
				RequestDispatcher rd1=req.getRequestDispatcher("CustomerLogIn.html");
				rd1.include(req, resp);
				resp.setContentType("text/html");
			}
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
