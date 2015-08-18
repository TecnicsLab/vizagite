import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
 

public class Registered_Users_Servlet extends HttpServlet
{
	// public static void main(String[] args) throws Exception{
	public 	void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try
		{
			//		MODEL-------------------------------------------------

			RegisterdUsers obj_reg_usrs=new RegisterdUsers();
			ArrayList<String> reg_usr_List=new ArrayList<String>();
			reg_usr_List=obj_reg_usrs.get_Reg_Users();
			request.setAttribute("reguserslist",reg_usr_List);

			//		VIEW--------------------------------------------------

			ServletContext sc=getServletContext();
			RequestDispatcher rd=sc.getRequestDispatcher("/displayRegUsers.jsp");
			rd.forward(request,response);
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}
}