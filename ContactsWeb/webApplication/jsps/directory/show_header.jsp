<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <head>
  <title>Просмотр структуры таблицы - База данных &quot;Контакты&quot;</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
 </head>
 <body>
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <p>Описание таблицы</p>
  <table width="100%" cellspacing="1" cellpadding="3">
   <tr>
    <th align="left" width="10%">Название</th>
    <td align="left"><jstl:out value="${tableName}"/></td>
   </tr>
   <tr>
    <th align="left" width="10%">Комментарий</th>
    <td align="left"><jstl:out value="${description}"/></td>
   </tr>
  </table>
  <p>Описание столбцов</p>
  <table width="100%" cellspacing="1" cellpadding="3" border="1">
   <tr>
    <th>Название</th>
    <th>Содержание</th>
    <th>Максимальная длина значения</th>
   </tr>
   <logic:iterate name="columns" id="column" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata">
    <tr>
     <td align="left"><jstl:out value="${column.dbColumnName}"/><jstl:if test="${!column.nullable && !column.generated}"> *</jstl:if></td>
     <td align="left"><jstl:out value="${column.fullName}"/></td>
     <td align="left"><jstl:out value="${column.width}"/></td>
    </tr>
   </logic:iterate>
  </table>
  <table cellspacing="1" cellpadding="3" align="center">
   <tr>
    <td>
     <a href="<%=request.getContextPath()%>/controller?action=directory.pageDirectories">Вернуться</a>
    </td>
   </tr>
  </table>
 </body>
</html>
