<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="su.sergey.contacts.*" %>
<%@ page import="su.sergey.contacts.directory.*" %>
<%@ page import="su.sergey.contacts.directory.valueobjects.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <title>Содержимое таблицы</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="JavaScript" src="<%=request.getContextPath()%>/js/utils.js"></script>
 </head>
 <jstl:set var="columnsSize" value="0"/>
 <logic:iterate name="columns" id="column" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata" indexId="index">
  <jstl:set var="columnsSize" value="${columnsSize + 1}"/>
 </logic:iterate>
 <jstl:set var="startText"><tr align="center"><td colspan="<jstl:out value="${columnsSize + 2}"/>" height="25">&nbsp;&nbsp;</jstl:set>
 <jstl:set var="endText"><tr align="center"></td></tr></jstl:set>
 <body onLoad="setFocus('searchRecordsForm', 'parameter0')">
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <util:message/>
  <p><jstl:out value="${description}"/></p>
  <table width="100%" border="0" cellspacing="1" cellpadding="3">
   <jstl:if test="${records != null}">
    <util:pageIterator dispatcherName="/controller?action=directory" iterationName="ShowRecords"/>
   </jstl:if>
   <tr align="center">
    <logic:iterate name="columns" id="column" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata" indexId="i">
     <jstl:choose>
      <jstl:when test="${column.width > 0}">
	   <jstl:set var="w" value="${column.width}"></jstl:set>
	  </jstl:when>
	  <jstl:otherwise>
	   <jstl:set var="w">20</jstl:set>
	  </jstl:otherwise>
	 </jstl:choose>
	 <jstl:if test="${column.type == 91}">
	  <jstl:set var="w">10</jstl:set>
	 </jstl:if>
     <th height="20" width="<jstl:out value="${w}"/>%"><jstl:out value="${column.fullName}"/></th>
    </logic:iterate>
    <th width="5%">&nbsp;</th>
    <th width="5%">&nbsp;</th>
   </tr>
   <jstl:if test="${records != null}">
    <logic:iterate name="records" id="record" type="su.sergey.contacts.directory.valueobjects.DirectoryRecord" indexId="i">
     <tr>
      <logic:iterate name="columns" id="column" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata" indexId="j">
       <td height="25" align="left">
        <jstl:out value="${record.values[j]}" default="&nbsp;" escapeXml="false"/>
       </td>
      </logic:iterate>
      <td align="right">
       <a href="<%=request.getContextPath()%>/controller?action=directory.showModifyRecord&tableName=<jstl:out value="${tableName}"/>&recordPrimaryKey=<jstl:out value="${record.oid}"/>">Редактирование</a>
      </td>
      <td align="left">
       <a href="<%=request.getContextPath()%>/controller?action=directory.removeRecord&tableName=<jstl:out value="${tableName}"/>&recordPrimaryKey=<jstl:out value="${record.oid}"/>">Удаление</a>
      </td>
     </tr>
    </logic:iterate>
   </jstl:if>
   <tr>
    <form name="searchRecordsForm" method="GET" action="<%=request.getContextPath()%>/controller">
     <input type="hidden" name="action" value="directory.searchRecords">
     <input type="hidden" name="tableName" value="<jstl:out value="${tableName}"/>">
     <logic:iterate name="columns" indexId="index" id="column" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata">
      <jstl:choose>
       <jstl:when test="${column.width > 0}">
        <jstl:set var="w" value="${column.width}"></jstl:set>
       </jstl:when>
       <jstl:otherwise>
        <jstl:set var="w">15</jstl:set>
       </jstl:otherwise>
      </jstl:choose>
      <td align="left">
       <input type="text"
              name="parameter<jstl:out value="${index}"/>"
              size="<jstl:out value="${w}"/>"
              <jstl:if test="${column.width>0}">
               maxlength="<jstl:out value="${w}"/>"
               style="font-family: monospace"
              </jstl:if>
              value="<jstl:out value="${directoryRecordsSearchParameters.parameters[column.dbColumnName]}"/>">
      </td>
     </logic:iterate>
     <td align="left">
      <input type="submit" value="Найти">
     </td>
     <td>&nbsp;</td>
    </form>
   </tr>
   <jstl:if test="${records != null}">
    <util:pageIterator dispatcherName="/controller?action=directory" iterationName="ShowRecords"/>
   </jstl:if>
  </table>
  <table border="0" cellspacing="0" cellpadding="3" align="center">
   <tr>
    <td>
     <a accesskey="д" href="<%=request.getContextPath()%>/controller?action=directory.showModifyRecord&tableName=<jstl:out value="${tableName}"/>">Добавить</a>
    </td>
    <td>
     <a href="<%=request.getContextPath()%>/controller?action=directory.pageDirectories">Вернуться</a>
    </td>
   </tr>
  </table>
 </body>
</html>
