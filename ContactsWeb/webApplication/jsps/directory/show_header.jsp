<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ page import="su.sergey.contacts.*" %>
<%@ page import="su.sergey.contacts.directory.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<html>
 <head>
  <title>Просмотр и редактирование заголовока таблицы</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%
    String description = (String)request.getAttribute(DirectoryDefinitions.AN_TABLE_DESCRIPTION);
    String name = (String)request.getAttribute(DirectoryDefinitions.AN_TABLE_NAME);
    ArrayList columns = (ArrayList)request.getAttribute(DirectoryDefinitions.AN_COLUMNS);
    int currentPage = 0;
    try {
        currentPage = Integer.parseInt(request.getParameter(DirectoryDefinitions.PN_PAGE));
    } catch (Exception e) {};
  %>
 </head>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5">
  <%@ include file="/include/menu.jsp" %>
  <p>&nbsp;&nbsp;&nbsp;&nbsp;Заголовок таблицы</p>
  <util:message/>
  <form name="directoryHeader" method="POST" action="<%=request.getContextPath()%>/controller" enctype="multipart/from-data">
   <input type="hidden" name="check" value="проверка">
   <input type="hidden" name="action" value="directory.updateHeader">
   <input type="hidden" name="tableName" value="<%=name%>">
   <input type="hidden" name="page" value="<%=currentPage%>">
   <table width="100%" border="0" cellspacing="1" cellpadding="3">
    <tr>
     <td><b>Название таблицы</b></td>
     <td><%=name%></td>
    </tr>
    <tr>
     <td><b>Комментарий</b></td>
     <td><input name="description" type="text" size="60" value="<%=description%>"/></td>
    </tr>
   </table>
   <p>Список столбцов таблицы</P>
   <table width="100%" border="0" cellspacing="1" cellpadding="3">
    <tr>
     <td>Название столбца</td>
     <td>Описание столбца</td>
     <td>Длина максимального значения</td>
    </tr>
    <logic:iterate name="columns" id="column" indexId="index" type="su.sergey.contacts.valueobjects.DirectoryColumnMetadata">
     <tr>
      <td align="left"><%=column.getDbColumnName()%></td>
      <td align="left"><input name="columnFullName<%=index%>" type="text" size="60" value="<%=column.getFullName()%>"/></td>
      <td align="left"><%=column.getWidth()%></td>
     </tr>
    </logic:iterate>
   </table>
   <table border="0" cellspacing="0" cellpadding="3" align="center">
    <tr>
     <td align="left" colspan="6">&nbsp;</td>
    </tr>
    <tr>
     <td>
      <input type="submit" value="Сохранить">
     </td>
     <td>
      <% String backURL = "javascript:window.location.href = '" + request.getContextPath() + "/controller?action=directory.pageDirectories&page=" + currentPage + "';"; %>
      <a href="<%=backURL%>">Вернуться</a>
     </td>
    </tr>
   </table>
  </form>
 </body>
</html>
