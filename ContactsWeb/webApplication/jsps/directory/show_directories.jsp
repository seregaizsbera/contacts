<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <head>
  <title>Список таблиц - База данных &quot;Контакты&quot;</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <link rel="SHORTCUT ICON" href="<jstl:out value="${pageContext.request.contextPath}"/>/shortcut.ico"/>
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
 </head>
 <body onLoad="setFocus('searchDirectoriesForm', 'tableName')">
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p>Список таблиц</p>
  <table align="center">
   <jstl:if test="${directories != null}">
    <util:pageIterator dispatcherName="/controller?action=directory" iterationName="Directories">
     <util:startText><tr><td colSpan="4" align="center"></util:startText>
     <util:endText></td></tr></util:endText>
    </util:pageIterator>
    <tr>
     <th>Название</th>
     <th>Содержание</th>
     <th></th>
     <th></th>
    </tr>
    <logic:iterate name="directories" id="directory" type="su.sergey.contacts.directory.valueobjects.DirectoryMetadata">
     <tr>
      <td align="left"><jstl:out value="${directory.dbTableName}"/></td>
      <td align="left"><a href="<%=request.getContextPath()%>/controller?action=directory.showRecords&tableName=<jstl:out value="${directory.handle.tableName}"/>"><jstl:out value="${directory.description}"/></a></td>
      <td><a href="<%=request.getContextPath()%>/controller?action=directory.showHeader&tableName=<jstl:out value="${directory.handle.tableName}"/>"><img src="<%=request.getContextPath()%>/images/ico_details.gif" width="16" height="16" border="0" alt="Структура"></a></td>
      <td><a href="<%=request.getContextPath()%>/controller?action=directory.showRecords&tableName=<jstl:out value="${directory.handle.tableName}"/>"><img src="<%=request.getContextPath()%>/images/ico_id.gif" width="16" height="16" border="0" alt="Данные"></a></td>
     </tr>
    </logic:iterate>
    <util:pageIterator dispatcherName="/controller?action=directory" iterationName="Directories">
     <util:startText><tr><td colSpan="4" align="center"></util:startText>
     <util:endText></td></tr></util:endText>
    </util:pageIterator>
   </jstl:if>
   <tr>
    <form name="searchDirectoriesForm" method="GET" action="<%=request.getContextPath()%>/controller">
     <input type="hidden" name="action" value="directory">
     <td align="left">
      <input type="text" name="tableName" value="<jstl:out value="${directoriesSearchParameters.tableName}"/>"></input>
     </td>
     <td align="left">
      <input type="submit" value="Найти"></input>
     </td>
     <td></td>
     <td></td>
    </form>
   </tr>
  </table>
 </body>
</html>
