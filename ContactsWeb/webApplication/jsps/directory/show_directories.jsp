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
  <meta http-equiv="expires" content="0">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
 </head>
 <body onLoad="setFocus('searchDirectoriesForm', 'tableName')">
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <p>Список таблиц</p>
  <table width="100%" cellspacing="1" cellpadding="3">
   <jstl:if test="${directories != null}">
    <util:pageIterator dispatcherName="/controller?action=directory" iterationName="Directories" startText="<tr align='center'><td colspan='4' height='25'>&nbsp;&nbsp;" endText="</td></tr>"/>
    <tr>
     <th height="25" width="10%">Номер</th>
     <th height="25" width="10%">Имя</th>
     <th width="60%">Описание</th>
     <th nowrap>Просмотр</th>
    </tr>
    <logic:iterate name="directories" id="directory" indexId="index" type="su.sergey.contacts.directory.valueobjects.DirectoryMetadata">
     <tr>
        <td height="25" align="center"><a href="<%=request.getContextPath()%>/controller?action=directory.showHeader&tableName=<jstl:out value="${directory.handle.tableName}"/>"><jstl:out value="${index + 1 + iterationInfo.currentPage * 10}"/></a></td>
        <td height="25" align="left"><jstl:out value="${directory.dbTableName}"/></td>
        <td height="25" align="left"><jstl:out value="${directory.description}"/></td>
        <td align="center"><a href="<%=request.getContextPath()%>/controller?action=directory.showRecords&tableName=<jstl:out value="${directory.handle.tableName}"/>">данные</a></td>
     </tr>
    </logic:iterate>
    <util:pageIterator dispatcherName="/controller?action=directory" iterationName="Directories" startText="<tr align='center'><td colspan='4' height='25'>&nbsp;&nbsp;" endText="</td></tr>"/>
   </jstl:if>
   <tr>
    <form name="searchDirectoriesForm" method="GET" action="<%=request.getContextPath()%>/controller">
     <input type="hidden" name="action" value="directory">
     <td align="center">
      <input type="submit" value="Найти"></input>
     </td>
     <td align="left">
      <input type="text" name="tableName" value="<jstl:out value="${directoriesSearchParameters.tableName}"/>"></input>
     </td>
     <td></td>
     <td></td>
    </form>
   </tr>
  </table>
 </body>
</html>
