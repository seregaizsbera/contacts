<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ page import="su.sergey.contacts.*" %>
<%@ page import="su.sergey.contacts.directory.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <head>
  <title>Просмотр и редактирование заголовока таблицы</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
 </head>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5">
  <%@ include file="/include/menu.jsp" %>
  <p>&nbsp;&nbsp;&nbsp;&nbsp;Заголовок таблицы</p>
  <util:message/>
  <form name="directoryHeader" method="POST" action="<%=request.getContextPath()%>/controller">
   <%--input type="hidden" name="check" value="проверка"--%>
   <input type="hidden" name="action" value="directory.updateHeader">
   <input type="hidden" name="tableName" value="<jstl:out value="${tableName}"/>">
   <table width="100%" border="0" cellspacing="1" cellpadding="3">
    <tr>
     <td><b>Название таблицы</b></td>
     <td><jstl:out value="${tableName}"/></td>
    </tr>
    <tr>
     <td><b>Комментарий</b></td>
     <td><input name="description" type="text" size="60" value="<jstl:out value="${description}"/>"/></td>
    </tr>
   </table>
   <p>Список столбцов таблицы</P>
   <table width="100%" border="0" cellspacing="1" cellpadding="3">
    <tr>
     <td>Название столбца</td>
     <td>Описание столбца</td>
     <td>Длина максимального значения</td>
    </tr>
    <logic:iterate name="columns" id="column" indexId="index" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata">
     <tr>
      <td align="left"><jstl:out value="${column.dbColumnName}"/></td>
      <td align="left"><input name="columnFullName<jstl:out value="${index}"/>" type="text" size="60" value="<jstl:out value="${column.fullName}"/>"/></td>
      <td align="left"><jstl:out value="${column.width}"/></td>
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
      <a href="<%=request.getContextPath()%>/controller?action=directory.pageDirectories">Вернуться</a>
     </td>
    </tr>
   </table>
  </form>
 </body>
</html>
