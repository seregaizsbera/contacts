<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ page import="su.sergey.contacts.*" %>
<%@ page import="su.sergey.contacts.directory.*" %>
<%@ page import="su.sergey.contacts.directory.valueobjects.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<html>
 <head>
  <title>Запись таблицы</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 </head>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5">
  <%@ include file="/include/menu.jsp" %>
  <% String name = (String)request.getAttribute(DirectoryDefinitions.AN_TABLE_NAME);
     ArrayList columns = (ArrayList)request.getAttribute(DirectoryDefinitions.AN_COLUMNS);
     int currentPage = 0;
     int directoryPage = 0;
     try {
         currentPage = Integer.parseInt(request.getParameter(DirectoryDefinitions.PN_PAGE));
         directoryPage = Integer.parseInt(request.getParameter(DirectoryDefinitions.PN_DIRECTORY_PAGE));
     } catch (Exception e) {};
     String[] values;
     String action;
     String title;
     String oid;
     if (request.getAttribute(DirectoryDefinitions.AN_RECORD) != null) {
         DirectoryRecord directoryRecord = (DirectoryRecord) request.getAttribute(DirectoryDefinitions.AN_RECORD);
         values = directoryRecord.getValues();
         action = "directory.editRecord";
         title = "Редактирование записи таблицы";
         oid = directoryRecord.getOid().toString();
     } else {
         values = new String[columns.size()];
         for (int i = 0; i < columns.size(); i++) {
             values[i] = "";
   	     }
		 action = "directory.addRecord";
		 title = "Создание новой записи таблицы";
		 oid = "";
     }
  %>
  <p>&nbsp;&nbsp;&nbsp;&nbsp;<%=title%></p>
  <form name="directoryRecord" method="POST" action="<%=request.getContextPath()%>/controller">
   <input type="hidden" name="check" value="проверка">
   <input type="hidden" name="action" value="<%=action%>">
   <input type="hidden" name="tableName" value="<%=name%>">
   <input type="hidden" name="page" value="<%=currentPage%>">
   <input type="hidden" name="directoryPage" value="<%=directoryPage%>">
   <input type="hidden" name="recordPrimaryKey" value="<%=oid%>">
   <table width="100%" border="0" cellspacing="1" cellpadding="3">
    <tr>
     <th height="20" align="left">Описание поля</th>
     <th height="20" align="right">Имя поля</th>
     <th height="20" align="left">Значение</th>
    </tr>
    <logic:iterate name="columns" id="column" indexId="index" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata">
     <% String attributes = column.isGenerated() ? "readonly" : "";
        String value = values[index.intValue()];
        if (value == null) {
            value = "";
        }
     %>
     <tr>
      <td height="25" align="left"><%=column.getFullName()%></td>
      <td height="25" align="right"><%=column.getDbColumnName()%></td>
      <td height="25" align="left"><input <%=attributes%> name="value<%=index%>" type="text" size="<%=column.getWidth()%>" value="<%=value%>"/></td>
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
      <a href="<%=request.getContextPath()%>/controller?action=directory.showRecords&tableName=<%=name%>&page=<%=currentPage%>&directoryPage=<%=directoryPage%>">Вернуться</a>
     </td>
    </tr>
   </table>
  </form>
 </body>
</html>
