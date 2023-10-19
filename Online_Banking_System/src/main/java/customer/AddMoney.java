package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddMoney")
public class AddMoney extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String AccountNumber=req.getParameter("accno");
		String PinCode=req.getParameter("pincode");
		String amount=req.getParameter("amount");
		
		try 
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root");
			PreparedStatement ps=conn.prepareStatement("update customer set money=? where accountnumber=? and pincode=?");
			ps.setString(1,amount);
			ps.setString(2, AccountNumber);
			ps.setString(3, PinCode);
			
			ps.execute();
			
			PrintWriter pw=resp.getWriter();
			pw.println("<h1>Money Debited Successfully</h1>");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
