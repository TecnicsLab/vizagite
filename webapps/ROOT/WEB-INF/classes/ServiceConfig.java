package Services;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ServiceConfig
{
	public static Statement statement=null;
	public static ResultSet result=null;
	public static ResultSetMetaData metadata=null;
	public static Connection connect=null;
	public ServiceConfig() throws Exception
	{
        File fobj=new File("UserRegistration.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fobj);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("dbConnection");
        for (int temp = 0; temp < nList.getLength(); temp++) 
        {
            Node nNode = nList.item(temp);
            if(nNode.getNodeType() == Node.ELEMENT_NODE) 
            {
                Element eElement = (Element) nNode;
                String serverIp=eElement.getElementsByTagName("serverIp").item(0).getTextContent();
                String database=eElement.getElementsByTagName("database").item(0).getTextContent();
                String user=eElement.getElementsByTagName("user").item(0).getTextContent();
                String password=eElement.getElementsByTagName("password").item(0).getTextContent();                   
                Class.forName("com.mysql.jdbc.Driver");
				connect=DriverManager.getConnection("jdbc:mysql://"+serverIp+"/"+database+"",""+user+"",""+password+"");
				statement=connect.createStatement();
            }
        }
	}
	public static ArrayList<String> showRegistrationFields(String language,String serviceId) throws SQLException,ClassNotFoundException
	{
		String sqlFieldCount,sqlGetFields,sqlColumnCount,fieldName;
		int columnsCount,columns;
		ArrayList<String> list = new ArrayList<String>();
		sqlFieldCount="select * from tblserviceconfig where serviceid='"+serviceId+"'";
		result = statement.executeQuery(sqlFieldCount);
		metadata=result.getMetaData();
		columnsCount=metadata.getColumnCount();
		int fieldCount=0;
		for(fieldCount=1;fieldCount<columnsCount;fieldCount++){
		sqlGetFields="select "+language+" from tbl_language where labelid in (select fieldname"+fieldCount+" from tbl_serviceconfig where serviceid='"+serviceId+"')";
		result = statement.executeQuery(sqlGetFields);
		while(result.next())
		{
			fieldName=result.getString(1);
			if(fieldName!=null)
				list.add(fieldName);
		}
		}
		return list;
	}
}
