<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ page import="su.sergey.contacts.*" %>
<%@ page import="su.sergey.contacts.directory.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<html>
 <head>
  <title>Содержимое таблицы</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 </head>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5">
  <%@ include file="/include/menu.jsp" %>
  <% int currentPage = 0;
     int directoryPage = 0;
     try {
         currentPage = Integer.parseInt(request.getParameter(DirectoryDefinitions.PN_PAGE));
         directoryPage = Integer.parseInt(request.getParameter(DirectoryDefinitions.PN_DIRECTORY_PAGE));
     } catch (Exception e) {};
     String description = (String)request.getAttribute(DirectoryDefinitions.AN_TABLE_DESCRIPTION);
     String tableName = (String)request.getAttribute(DirectoryDefinitions.AN_TABLE_NAME);
     ArrayList columns = (ArrayList)request.getAttribute(DirectoryDefinitions.AN_COLUMNS);
     ArrayList records = (ArrayList)request.getAttribute(DirectoryDefinitions.AN_RECORDS);
     String startText = "<tr align=\"center\"><td colspan=\""
                        + (columns.size() + 2)
			+ "\" height=\"25\">&nbsp;&nbsp;";
  %>
  <util:message/>
  <p><%=description%></P>
  <table width="100%" border="0" cellspacing="1" cellpadding="3">
   <% if (records != null) { %>
    <util:pageIterator dispatcherName="/controller?action=directory"
                       iterationName="ShowRecords"
		       additionalParameter="<%=DirectoryDefinitions.PN_DIRECTORY_PAGE + '=' + directoryPage%>"
		       startText="<%=startText%>"
		       endText="</td></tr>"/>
    <tr align="center">
     <logic:iterate name="columns" id="column" type="su.sergey.contacts.valueobjects.DirectoryColumnMetadata">
      <th height="20" width="<%=column.getWidth()%>"><%=column.getFullName()%></th>
     </logic:iterate>
     <th width="5%">&nbsp;</th>
     <th width="5%">&nbsp;</th>
    </tr>
    <logic:iterate name="records" id="record" type="su.sergey.contacts.valueobjects.DirectoryRecord" indexId="i">
     <% int oddEven = i.intValue() % 2 + 1; %>
     <tr>
      <logic:iterate name="columns" id="column" type="su.sergey.contacts.valueobjects.DirectoryColumnMetadata" indexId="j">
       <% String value = record.getValues()[j.intValue()]; %>
       <td height="25" align="left"><%=(value != null) ? value : "&nbsp;"%></td>
      </logic:iterate>
      <td align="center">
       <a href="<%=request.getContextPath()%>/controller?action=directory.showModifyRecord&page=<%=currentPage%>&directoryPage=<%=directoryPage%>&tableName=<%=tableName%>&recordPrimaryKey=<%=record.getOid()%>">Редактирование</a>
      </td>
      <td align="center">
       <a href="<%=request.getContextPath()%>/controller?action=directory.deleteRecord&Page=<%=currentPage%>&directoryPage=<%=directoryPage%>&tableName=<%=tableName%>&recordPrimaryKey=<%=record.getOid()%>">Удаление</a>
      </td>
     </tr>
    </logic:iterate>
    <util:pageIterator dispatcherName="/controller?action=directory"
                       iterationName="ShowRecords"
		       additionalParameter="<%=DirectoryDefinitions.PN_DIRECTORY_PAGE + '=' + directoryPage%>"
		       startText="<%=startText%>"
		       endText="</td></tr>"/>
   <% } %>
  </table>
  <table border="0" cellspacing="0" cellpadding="3" align="center">
   <tr>
    <td>
     <a href="<%=request.getContextPath()%>/controller?action=directory.showModifyRecord&tableName=<%=tableName%>&page=<%=currentPage%>&directoryPage=<%=directoryPage%>">Добавить</a>
    </td>
    <td>
     <a href="<%=request.getContextPath()%>/controller?action=directory.pageDirectories&page=<%=directoryPage%>">Вернуться</a>
    </td>
   </tr>
  </table>
 </body>
</html>
