package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/saveCustomertoDB")
public class SaveCustomer extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		String id=req.getParameter("id");
		String name=req.getParameter("un");
		String pincode=req.getParameter("pin");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root");
			PreparedStatement ps=conn.prepareStatement("insert into customer(id,name,pincode) values(?,?,?)");
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3,pincode);
			
			ps.execute();
			
			RequestDispatcher rd=req.getRequestDispatcher("CustomerLogIn.html");
			rd.forward(req, res);
			
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
