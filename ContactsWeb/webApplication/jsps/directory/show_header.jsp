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
  <meta http-equiv="Expires" content="0">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
 </head>
 <body>
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p>Описание таблицы</p>
  <table width="100%" cellSpacing="0" cellPadding="3">
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
  <table width="100%" cellSpacing="0" cellPadding="3" border="1">
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
  <table cellSpacing="0" cellPadding="3" align="center">
   <tr>
    <td>
     <jstl:choose>
      <jstl:when test="${not empty backURL}">
       <a href="<jstl:out value="${backURL}"/>">
      </jstl:when>
      <jstl:when test="${not empty directoriesSearchParameters}">
       <a href="<%=request.getContextPath()%>/controller?action=directory.pageDirectories">
      </jstl:when>
      <jstl:otherwise>
       <a href="<%=request.getContextPath()%>/controller?action=directory">
      </jstl:otherwise>
     </jstl:choose>Вернуться</a>
    </td>
    </td>
   </tr>
  </table>
 </body>
</html>
