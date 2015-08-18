/////////////////////////////////////////////////////////////////////////////////////////////
//      RETURNING REGISTERD USERS(APPROVED BY THE ADMINISTRATOR) FROM DATABASE			  //
///////////////////////////////////////////////////////////////////////////////////////////


import java.sql.*;
import java.io.*;
import java.util.*;
import dblinker.connectMySql;

class RegisterdUsers
{
	public ArrayList<String> get_Reg_Users() throws Exception
	{
		Statement stmt=null;
		ResultSet rs=null;
		connectMySql link=new connectMySql();
		Connection con=link.connect();
		RegUsers obj=new RegUsers();
		stmt=con.createStatement();
		String query;
		ArrayList<String> reg_Usr_List=new ArrayList<String>();
		query="SELECT s.ServiceID, s.EmailAddress, s.UserName, u.Street, u.Location, u.TownID, u.MobilePhone FROM tbl_servicedata s, tbl_users u where u.MobilePhone=s.MobilePhone and s.Approved=1";
		rs=stmt.executeQuery(query);
		while (rs.next()) {
			reg_Usr_List.add(rs.getString("ServiceID"));
			reg_Usr_List.add(rs.getString("UserName"));			
			reg_Usr_List.add(rs.getString("Street"));
			reg_Usr_List.add(rs.getString("Location"));
			reg_Usr_List.add(rs.getString("EmailAddress"));
			reg_Usr_List.add(rs.getString("TownID"));
			reg_Usr_List.add(rs.getString("MobilePhone"));
			reg_Usr_List.add("-");
			System.out.println(reg_Usr_List);
		}
		return reg_Usr_List;
	} 
}