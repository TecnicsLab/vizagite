<%@ page import="java.util.ArrayList" %>
    <%
    ArrayList<String> fieldNames=(ArrayList<String>) request.getAttribute("fieldNames");
    /*fieldNames = fieldNames.replace(/\[/gi,"");
    fieldNames = fieldNames.replace(/\]/gi,"");
    fieldNames=fieldNames.split(",");
    int fieldCount;
    for(fieldCount = 0; fieldCount < fieldNames.length; fieldCount++)
    {           
    %>
    <%
    out.println(fieldNames[fieldCount]);
    %>
    <%
    }*/
                                                                                                                                                                                                                                                                                                                                                                                                                                                                            out.println(fieldNames);
    %>