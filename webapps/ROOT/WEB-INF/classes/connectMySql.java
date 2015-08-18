//////////////////////////////////////////////////////////////////////////////////////////////
//		DATABASE CONNECTOR FOR BOTH MYSQL AND ORACLE  									   //
////////////////////////////////////////////////////////////////////////////////////////////


package dblinker;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.sql.*;
import java.io.*;
import java.util.*;


public class connectMySql   
{
	public Connection connect() throws Exception
	{
		File stocks = new File("/var/lib/tomcat7/webapps/ROOT/WEB-INF/classes/DBConfig.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(stocks);
		doc.getDocumentElement().normalize();
		
		//--------------REMOTE DRIVER------------------------------------------------------

		NodeList driver=doc.getElementsByTagName("Driver");
		String node1=driver.item(0).getChildNodes().item(0).getNodeValue();
		
		//--------------LOCAL DRIVE-------------------------------------------------

		NodeList localdriver=doc.getElementsByTagName("localDrive");
		String node2=localdriver.item(0).getChildNodes().item(0).getNodeValue();
		
		//--------------IP ADDRESS------------------------------------

		NodeList ip=doc.getElementsByTagName("localhost");
		String node3=ip.item(0).getChildNodes().item(0).getNodeValue();
		
		//--------------DATABASE----------------------------------------------------
		
		NodeList db=doc.getElementsByTagName("DataBase");
		String node4=db.item(0).getChildNodes().item(0).getNodeValue();
		
		//--------------USER NAME---------------------------------------------------

		NodeList usrname=doc.getElementsByTagName("UserName");
		String node5=usrname.item(0).getChildNodes().item(0).getNodeValue();
		
		//--------------PASSWORD----------------------------------------------------

		NodeList paswd=doc.getElementsByTagName("Password");
		String node6=paswd.item(0).getChildNodes().item(0).getNodeValue();
		
		String drive=node1;
		Class.forName(drive);
		Connection link=DriverManager.getConnection(node2+"//"+node3+"/"+node4,node5,node6);
		return link;
	}	
}
*