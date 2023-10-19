package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminLogIn")
public class AdminLogIn extends HttpServlet
 {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String email=req.getParameter("email");
		String pass=req.getParameter("pwd");
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root");
			
			PreparedStatement ps=conn.prepareStatement("select * from admin where email=? and password=?");
				
			ps.setString(1,email);
			ps.setString(2, pass);
			
			ResultSet rs=ps.executeQuery();
			
			PrintWriter pw=resp.getWriter();
			
			if(rs.next())
			{
				if(rs.getString("email").equals(email) && rs.getString("password").equals(pass))
				{
					RequestDispatcher rd=req.getRequestDispatcher("AdminMenu.html");
					rd.forward(req, resp);
					resp.setContentType("text/html");
				}
				else
				{
					pw.println("<h1 style=color:red>invalid credentials!</h1>");
					RequestDispatcher rd1=req.getRequestDispatcher("AdminLogIn.html");
					rd1.include(req, resp);
					resp.setContentType("text/html");
				}
				
			}
			else
			{
				pw.println("<h1 style=color:red>invalid credentials!</h1>");
				RequestDispatcher rd1=req.getRequestDispatcher("AdminLogIn.html");
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
