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
 <util:size var="columnsSize" collection="columns"/>
 <body onLoad="setFocus('searchRecordsForm', 'parameter0')">
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p><jstl:out value="${description}"/></p>
  <util:pageIterator dispatcherName="/controller?action=directory" iterationName="Records">
   <util:startText><table width="100%"><tr><td align="center"></util:startText>
   <util:endText></td></tr></table></util:endText>
  </util:pageIterator>  
  <table align="center">
   <tr align="center">
    <th width="*%"></th>
    <jstl:choose>
     <jstl:when test="${columnsSize <= 3}">
      <jstl:set var="w" value="30"/>
     </jstl:when>
     <jstl:otherwise>
      <jstl:set var="w" value="${100 div columnsSize}"/>
     </jstl:otherwise>
    </jstl:choose>
    <logic:iterate name="columns" id="column" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata" indexId="i">
     <th width="<jstl:out value="${w}"/>%"><jstl:out value="${column.fullName}"/></th>
    </logic:iterate>
    <th width="*%"></th>
   </tr>
   <jstl:if test="${records != null}">
    <logic:iterate name="records" id="record" type="su.sergey.contacts.directory.valueobjects.DirectoryRecord" indexId="i">
     <tr>
      <td align="left"></td>
      <logic:iterate name="columns" id="column" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata" indexId="j">
       <td align="left"><jstl:if test="${columnsSize == 1 && j == 0 || j == 1}"><a href="<%=request.getContextPath()%>/controller?action=directory.showModifyRecord&tableName=<jstl:out value="${tableName}"/>&recordPrimaryKey=<jstl:out value="${record.oid}"/>"><jstl:if test="${empty record.values[j]}">^</jstl:if></jstl:if><jstl:out value="${record.values[j]}"/><jstl:if test="${j == 1}"></a></jstl:if></td>
      </logic:iterate>
      <td><a href="<%=request.getContextPath()%>/controller?action=directory.showModifyRecord&tableName=<jstl:out value="${tableName}"/>&recordPrimaryKey=<jstl:out value="${record.oid}"/>"><img src="<%=request.getContextPath()%>/images/ico_id.gif" width="16" height="16" border="0" alt="Просмотр"></a></td>
     </tr>
    </logic:iterate>
   </jstl:if>
   <form name="searchRecordsForm" method="GET" action="<%=request.getContextPath()%>/controller">
    <input type="hidden" name="action" value="directory.searchRecords">
    <input type="hidden" name="tableName" value="<jstl:out value="${tableName}"/>">
    <input type="submit" class="hidden">
    <tr>
     <td></td>
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
                class="fixed"
               </jstl:when>
               <jstl:otherwise>
               </jstl:otherwise>
              </jstl:choose>
              value="<jstl:out value="${directoryRecordsSearchParameters.parameters[column.dbColumnName]}"/>">
      </td>
     </logic:iterate>
     <td></td>
    </tr>
   </form>
  </table>
  <util:pageIterator dispatcherName="/controller?action=directory" iterationName="Records">
   <util:startText><table width="100%"><tr><td align="center"></util:startText>
   <util:endText></td></tr></table></util:endText>
  </util:pageIterator>  
  <table align="center">
   <tr>
    <td><button type="button" onClick="document.searchRecordsForm.submit()">Найти</button></td>
    <td><button type="button" onClick="clearSearchForm(document.searchRecordsForm)">Очистить</button></td>
   </tr>
  </table>
  <table align="center">
   <tr>
    <td>
     <a accessKey="д" href="<%=request.getContextPath()%>/controller?action=directory.showModifyRecord&tableName=<jstl:out value="${tableName}"/>">Добавить</a>
    </td>
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
   </tr>
  </table>
 </body>
</html>
