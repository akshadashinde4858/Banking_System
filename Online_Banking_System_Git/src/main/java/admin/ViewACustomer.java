package admin;

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

@WebServlet("/ViewACustomer")
public class ViewACustomer extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_banking_system", "root",
					"root");
			PreparedStatement ps = conn.prepareStatement("select * from customer where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
//			System.out.println(rs.next());
			PrintWriter pw = resp.getWriter();

			if (rs.next()) 
			{
				pw.println("Data of Id: "+id);
				pw.println("Name: "+rs.getString("name")); // Add more fields as needed
				pw.println("Account Number: "+rs.getLong("accountnumber"));
				pw.println("Pincode: "+rs.getInt("pincode"));
				pw.println("Balance: "+rs.getFloat("balance"));
			}
			else 
			{
				pw.print("Data Not found");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
