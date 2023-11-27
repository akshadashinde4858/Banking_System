package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import admin.AdminLogIn;

public class Authorization implements Filter
{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession s=req.getSession();
		 AdminLogIn admin=(AdminLogIn)s.getAttribute("admin");
		if(admin!=null)
		{
			chain.doFilter(request, response);
		}
		else
		{
			response.setContentType("text/html");
			response.getWriter().println("<h1>You are not login please login</h1>");
		}
	}

}
