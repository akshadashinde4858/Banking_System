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

import com.mysql.cj.jdbc.result.ResultSetMetaData;

@WebServlet("/AddMoney")
public class AddMoney extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String AccountNumber=req.getParameter("accno"); 
//		long AccountNumber=Long.parseLong(req.getParameter("accno"));
		String PinCode=req.getParameter("pincode");
//		int PinCode=Integer.parseInt(req.getParameter("pincode"));
		String amount=req.getParameter("amount");
		float amount1=Float.parseFloat(amount);
		
		try 
		{

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root");
			PreparedStatement ps1=conn.prepareStatement("select * from customer where accountnumber=? and pincode=?");
			ps1.setString(1, AccountNumber);
			ps1.setString(2, PinCode);
			
			ResultSet rs1=ps1.executeQuery();
						
			
			PrintWriter pw=resp.getWriter();
			
			if(rs1.next()) 
			{

				float dbbalance=rs1.getFloat(5);
				float updatedbalance=dbbalance+amount1;
				
				PreparedStatement ps2=conn.prepareStatement("update customer set balance=? where accountnumber=? and pincode=?");
				ps2.setFloat(1, updatedbalance);
				ps2.setString(2, AccountNumber);
				ps2.setString(3, PinCode);
				
				ps2.execute();
				
				pw.println("<h1>Money Debited Successfully</h1>");
			}
			else
			{
				pw.println("<h1>Data Not Found</h1>");
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
