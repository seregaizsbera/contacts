<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <title>Содержимое таблицы - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript"><!--
      function clearSearchForm(form) {
          <logic:iterate name="columns" indexId="index" id="column">
              form.parameter<jstl:out value="${index}"/>.value = "";
          </logic:iterate>
      }
  --></script>
 </head>
 <jstl:set var="columnsSize" value="0"/>
 <logic:iterate name="columns" id="column" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata" indexId="index">
  <jstl:set var="columnsSize" value="${columnsSize + 1}"/>
 </logic:iterate>
 <jstl:set var="startText"><tr align="center"><td colspan="<jstl:out value="${columnsSize + 3}"/>" height="25">&nbsp;&nbsp;</jstl:set>
 <jstl:set var="endText"><tr align="center"></td></tr></jstl:set>
 <body onLoad="setFocus('searchRecordsForm', 'parameter0')">
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p><jstl:out value="${description}"/></p>
  <util:message/>
  <table width="100%" cellSpacing="1" cellPadding="3">
   <jstl:if test="${records != null}">
    <util:pageIterator dispatcherName="/controller?action=directory" iterationName="ShowRecords"/>
   </jstl:if>
   <tr align="center">
    <th width="*%"></th>
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
    <th width="5%"></th>
    <th width="*%"></th>
   </tr>
   <jstl:if test="${records != null}">
    <logic:iterate name="records" id="record" type="su.sergey.contacts.directory.valueobjects.DirectoryRecord" indexId="i">
     <tr>
      <td width="*%"></td>
      <logic:iterate name="columns" id="column" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata" indexId="j">
       <td height="25" align="left">
        <jstl:out value="${record.values[j]}"/>
       </td>
      </logic:iterate>
      <td align="left">
       <a href="<%=request.getContextPath()%>/controller?action=directory.showModifyRecord&tableName=<jstl:out value="${tableName}"/>&recordPrimaryKey=<jstl:out value="${record.oid}"/>">Редактирование</a>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/controller?action=directory.removeRecord&tableName=<jstl:out value="${tableName}"/>&recordPrimaryKey=<jstl:out value="${record.oid}"/>">Удаление</a>
      </td>
      <td width="*%"></td>
     </tr>
    </logic:iterate>
   </jstl:if>
   <tr>
    <td width="*%"></td>
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
              <jstl:choose>
               <jstl:when test="${column.width>0}">
                maxLength="<jstl:out value="${w}"/>"
                style="font-family: monospace"
               </jstl:when>
               <jstl:otherwise>
                style="padding: 0;"
               </jstl:otherwise>
              </jstl:choose>
              value="<jstl:out value="${directoryRecordsSearchParameters.parameters[column.dbColumnName]}"/>">
      </td>
     </logic:iterate>
     <td align="left">
      <button type="submit">Найти</button>&nbsp;<button type="button" onClick="clearSearchForm(document.searchRecordsForm)">Очистить</button>
     </td>
     <td width="*%"></td>
    </form>
   </tr>
   <jstl:if test="${records != null}">
    <util:pageIterator dispatcherName="/controller?action=directory" iterationName="ShowRecords"/>
   </jstl:if>
  </table>
  <table cellSpacing="1" cellPadding="3" align="center">
   <tr>
    <td>
     <a accessKey="д" href="<%=request.getContextPath()%>/controller?action=directory.showModifyRecord&tableName=<jstl:out value="${tableName}"/>">Добавить</a>
    </td>
    <td>
     <a href="<%=request.getContextPath()%>/controller?action=directory.pageDirectories">Вернуться</a>
    </td>
   </tr>
  </table>
 </body>
</html>
