<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
  <title>Запись таблицы</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="JavaScript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <jstl:set var="focusSet" value="0"/>
  <logic:iterate name="columns" id="column" indexId="index" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata">
   <jstl:if test="${focusSet == 0 && !column.generated}">
    <jstl:set var="focusSet" value="1"/>
    <script language="JavaScript">
     <!--
      function setInitialFocus() {
          setFocus("directoryRecord", "value<jstl:out value="${index}"/>");
      }
     -->
    </script>
   </jstl:if>
  </logic:iterate>
 </head>
 <jstl:choose>
  <jstl:when test="${record != null}">
   <jstl:set var="action">directory.editRecord</jstl:set>
   <jstl:set var="title">Редактирование записи таблицы</jstl:set>
   <jstl:set var="oid" value="${record.oid}"/>
  </jstl:when>
  <jstl:otherwise>
   <jstl:set var="action">directory.addRecord</jstl:set>
   <jstl:set var="title">Создание новой записи таблицы</jstl:set>
   <jstl:set var="oid"/>
  </jstl:otherwise>
 </jstl:choose>
 <body onLoad="setInitialFocus()">
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <p><jstl:out value="${title}"/></p>
  <form name="directoryRecord" method="POST" action="<%=request.getContextPath()%>/controller">
   <input type="hidden" name="action" value="<jstl:out value="${action}"/>">
   <input type="hidden" name="tableName" value="<jstl:out value="${tableName}"/>">
   <input type="hidden" name="recordPrimaryKey" value="<jstl:out value="${oid}"/>">
   <table width="100%" border="0" cellspacing="1" cellpadding="3">
    <tr>
     <th height="20" width="15%" align="left">Имя поля</th>
     <th height="20" width="35%" align="right">Описание поля</th>
     <th height="20" align="left">Значение</th>
    </tr>
    <logic:iterate name="columns" id="column" indexId="index" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata">
     <jstl:if test="${record != null}">
      <jstl:set var="value" value="${record.values[index]}"/>
     </jstl:if>
     <jstl:choose>
      <jstl:when test="${column.width > 0}">
       <jstl:set var="w" value="${column.width}"></jstl:set>
      </jstl:when>
      <jstl:otherwise>
       <jstl:set var="w">25</jstl:set>
      </jstl:otherwise>
     </jstl:choose>
     <tr>
      <td height="25" align="left"><jstl:out value="${column.dbColumnName}"/></td>
      <td height="25" align="right"><jstl:if test="${!column.nullable && !column.generated}">* </jstl:if><jstl:out value="${column.fullName}"/></td>
      <td height="25" align="left">
       <input <jstl:if test="${column.generated}">readonly</jstl:if>
              name="value<jstl:out value="${index}"/>"
              type="text"
              size="<jstl:out value="${w}"/>"
              <jstl:if test="${column.width>0}">
               maxlength="<jstl:out value="${w}"/>"
               style="font-family: monospace"
              </jstl:if>
              <jstl:choose>
               <jstl:when test="${column.generated}">
                value="<jstl:out value="${value}"/>"
               </jstl:when>
               <jstl:otherwise>
                value="<jstl:out value="${value}" default="${directoryRecordsSearchParameters.parameters[column.dbColumnName]}"/>"
               </jstl:otherwise>
              </jstl:choose>
       >
      </td>
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
      <a href="<%=request.getContextPath()%>/controller?action=directory.pageShowRecords&tableName=<jstl:out value="tableName"/>">Вернуться</a>
     </td>
    </tr>
   </table>
  </form>
 </body>
</html>
