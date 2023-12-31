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

import com.mysql.cj.xdevapi.Result;

@WebServlet("/SendMoney")
public class SendMoney extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String accountnumber=req.getParameter("accountnumber");
		String pincode=req.getParameter("pincode");
		String Recipient_accountnumber=req.getParameter("recaccno");
		String amount=req.getParameter("amount");
		float amount1=Float.parseFloat(amount);
		
		PrintWriter pw=resp.getWriter();
		try 
		{

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system","root","root");
			
			PreparedStatement ps1=conn.prepareStatement("select * from customer where accountnumber=? and pincode=?");
			ps1.setString(1, accountnumber);
			ps1.setString(2, pincode);
			ResultSet rs1=ps1.executeQuery();
			float senders_balance=0;
			
			while (rs1.next()) {

				senders_balance= rs1.getFloat(5);
				
			}
			
			PreparedStatement ps2=conn.prepareStatement("select * from customer where accountnumber=?");
			ps2.setString(1, Recipient_accountnumber);
			ResultSet rs2=ps2.executeQuery();
			float recievers_balance = 0;
			while (rs2.next()) {

				recievers_balance= rs2.getFloat(5);
				
			}
			
			if(amount1<senders_balance)
			{
				PreparedStatement ps3=conn.prepareStatement("update customer set balance=? where accountnumber=?");
				float recievers_updatedbalance=recievers_balance+amount1;
				ps3.setFloat(1, recievers_updatedbalance);
				ps3.setLong(2, Long.parseLong(Recipient_accountnumber));
				int ct=ps3.executeUpdate();
				if(ct!=0)
				{
					pw.println("<h1>Money Credited Successfully");
					PreparedStatement ps4=conn.prepareStatement("update customer set balance=? where accountnumber=?");
					float senders_updatedbalance=senders_balance-amount1;
					ps4.setFloat(1, senders_updatedbalance);
					ps4.setString(2, accountnumber);
					ps4.executeUpdate();
				}
				else
				{
					pw.println("<h1>Data Not Found</h1>");
				} 
			}
			else
			{
				pw.println("<h1>insuffiecient Balance</h1>");
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
