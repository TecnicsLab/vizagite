import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Services.ServiceConfig;
public class ServiceConfigServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try
		{
			String language=request.getParameter("language");
			String serviceId=request.getParameter("serviceId");
			ServiceConfig objService = new ServiceConfig();
			ArrayList<String> fieldNames= objService.showRegistrationFields(language,serviceId);
			//out.print(fieldNames);
			request.setAttribute("fieldNames",fieldNames);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserRegistration.jsp");
            requestDispatcher.forward(request, response);		 	
		}
		catch(Exception error)
		{
			out.print(error);
		}			
	}
}