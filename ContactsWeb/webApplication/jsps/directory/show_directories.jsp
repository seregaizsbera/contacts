<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ page import="su.sergey.contacts.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<html>
 <head>
  <title>Список таблиц</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%
	    int currentPage = 0;
	    try {
	        currentPage = Integer.parseInt(request.getParameter(RequestConstants.PN_PAGE));
	    } catch (Exception e) {};
	    ArrayList directories = (ArrayList)request.getAttribute(RequestConstants.AN_DIRECTORIES);
  %>
 </head>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5">
  <%@ include file="/include/menu.jsp" %>
  <p>Список таблиц</p>
  <table width="100%" border="0" cellspacing="1" cellpadding="3">
    <% if (directories != null) { %>
    <util:pageIterator dispatcherName="/controller?action=directory" iterationName="Directories" startText="<tr align='center'><td colspan='4' height='25'>&nbsp;&nbsp;" endText="</td></tr>"/>
    <tr>
        <th height="25" width="10%">Номер</th>
        <th height="25" width="10%">Имя</th>
        <th width="60%">Описание</th>
        <th nowrap>Просмотр</th>
    </tr>
    <logic:iterate name="directories" id="directory" indexId="index" type="su.sergey.contacts.valueobjects.DirectoryMetadata">
     <tr>
        <% int oddEven = index.intValue() % 2 + 1; %>
        <td height="25" align="center"><a href="<%=request.getContextPath()%>/controller?action=directory.showHeader&directoryCode=<%=directory.getHandle().getTableName()%>&Page=<%=currentPage%>"><%=index.intValue() + 1 + currentPage * 10 %></a></td>
        <td height="25" align="left"><%=directory.getDbTableName()%></td>
        <td height="25" align="left"><%--a href="<%=request.getContextPath()%>/controller?action=directory.showHeader&directoryCode=<%=directory.getHandle().getTableName()%>&Page=<%=currentPage%>"--%><%=directory.getDescription()%><%--/a--%></td>
        <td align="center"><a href="<%=request.getContextPath()%>/controller?action=directory.showRecords&directoryCode=<%=directory.getHandle().getTableName()%>&Page=0&directoryPage=<%=currentPage%>">данные</a></td>
     </tr>
    </logic:iterate>
    <util:pageIterator dispatcherName="/controller?action=directory" iterationName="Directories" startText="<tr align='center'><td colspan='4' height='25'>&nbsp;&nbsp;" endText="</td></tr>"/>
    <% } %>
  </table>
</body>
</html>
