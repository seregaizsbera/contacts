<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="su.sergey.contacts.*" %>
<%@ page import="su.sergey.contacts.directory.*" %>
<%@ page import="su.sergey.contacts.valueobjects.*" %>
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
	 ArrayList widths =new ArrayList();
	 for (Iterator i = columns.iterator(); i.hasNext();) {
	     DirectoryColumnMetadata column = (DirectoryColumnMetadata) i.next();
	     int width = column.getWidth();
	     int w = (width > 0) ? width : 20;
	     if (column.getType() == Types.DATE) {
	         w = 10;
	     }
	     widths.add(new Integer(w));
	 }
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
   <% } %>
    <tr align="center">
     <logic:iterate name="columns" id="column" type="su.sergey.contacts.valueobjects.DirectoryColumnMetadata" indexId="i">
      <th height="20" width="<%=widths.get(i.intValue())%>%"><%=column.getFullName()%></th>
     </logic:iterate>
     <th width="5%">&nbsp;</th>
     <th width="5%">&nbsp;</th>
    </tr>
   <% if (records != null) { %>
    <logic:iterate name="records" id="record" type="su.sergey.contacts.valueobjects.DirectoryRecord" indexId="i">
     <% int oddEven = i.intValue() % 2 + 1; %>
     <tr>
      <logic:iterate name="columns" id="column" type="su.sergey.contacts.valueobjects.DirectoryColumnMetadata" indexId="j">
       <% String value = record.getValues()[j.intValue()]; %>
       <td height="25" align="left"><%=(value != null) ? value : "&nbsp;"%></td>
      </logic:iterate>
      <td align="right">
       <a href="<%=request.getContextPath()%>/controller?action=directory.showModifyRecord&page=<%=currentPage%>&directoryPage=<%=directoryPage%>&tableName=<%=tableName%>&recordPrimaryKey=<%=record.getOid()%>">Редактирование</a>
      </td>
      <td align="left">
       <a href="<%=request.getContextPath()%>/controller?action=directory.deleteRecord&Page=<%=currentPage%>&directoryPage=<%=directoryPage%>&tableName=<%=tableName%>&recordPrimaryKey=<%=record.getOid()%>">Удаление</a>
      </td>
     </tr>
    </logic:iterate>
   <% } %>
   <tr>
    <form name="searchRecords" method="POST" action="<%=request.getContextPath()%>/controller">
     <input type="hidden" name="check" value="проверка">
     <input type="hidden" name="action" value="directory.searchRecords">
     <input type="hidden" name="tableName" value="<%=tableName%>">
     <input type="hidden" name="page" value="<%=currentPage%>">
     <input type="hidden" name="directoryPage" value="<%=directoryPage%>">
     <logic:iterate name="columns" indexId="index" id="column" type="su.sergey.contacts.valueobjects.DirectoryColumnMetadata">
      <td align="left">
       <input type="text" name="parameter<%=index%>" size="<%=widths.get(index.intValue())%>">
      </td>
     </logic:iterate>
     <td align="left">
      <input type="submit" value="Найти">
     </td>
     <td>&nbsp;</td>
    </form>
   </tr>
   <% if (records != null) { %>
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
