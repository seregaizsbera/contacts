<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <jstl:choose>
  <jstl:when test="${record != null}">
   <jstl:set var="action">directory.editRecord</jstl:set>
   <jstl:set var="title">Редактирование записи</jstl:set>
   <jstl:set var="oid" value="${record.oid}"/>
  </jstl:when>
  <jstl:otherwise>
   <jstl:set var="action">directory.addRecord</jstl:set>
   <jstl:set var="title">Создание новой записи</jstl:set>
   <jstl:set var="oid"/>
  </jstl:otherwise>
 </jstl:choose>
 <head>
  <title><jstl:out value="${title}"/> - База данных &quot;Контакты&quot;</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <jstl:set var="focusSet" value="0"/>
  <logic:iterate name="columns" id="column" indexId="index" type="su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata">
   <jstl:if test="${focusSet == 0 && !column.generated}">
    <jstl:set var="focusSet" value="1"/>
    <script language="javascript"><!--
        function setInitialFocus() {
            setFocus("updateRecordForm", "value<jstl:out value="${index}"/>");
        }
    --></script>
   </jstl:if>
  </logic:iterate>
 </head>
 <body onLoad="setInitialFocus()">
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p><jstl:out value="${title}"/></p>
  <jstl:if test="${record != null}">
   <form name="removeRecordForm" method="POST" action="<%=request.getContextPath()%>/controller">
    <input type="hidden" name="action" value="directory.removeRecord">
    <input type="hidden" name="tableName" value="<jstl:out value="${tableName}"/>">
    <input type="hidden" name="recordPrimaryKey" value="<jstl:out value="${oid}"/>">
   </form>
  </jstl:if>
  <form name="updateRecordForm" method="POST" action="<%=request.getContextPath()%>/controller">
   <input type="hidden" name="action" value="<jstl:out value="${action}"/>">
   <input type="hidden" name="tableName" value="<jstl:out value="${tableName}"/>">
   <input type="hidden" name="recordPrimaryKey" value="<jstl:out value="${oid}"/>">
   <table cellSpacing="0" cellPadding="3" align="center" border="1">
    <tr>
     <td>
      <table cellSpacing="0" cellPadding="3" align="center">
       <tr height="20">
        <th align="center">Имя поля</th>
        <th align="center">Описание поля</th>
        <th align="center">Значение</th>
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
        <tr height="25">
         <td align="left"><jstl:out value="${column.dbColumnName}"/></td>
         <td align="right"><jstl:if test="${!column.nullable && !column.generated}">*&nbsp;</jstl:if><jstl:out value="${column.fullName}"/></td>
         <td align="left">
	  <jstl:choose>
	   <jstl:when test="${(column.width < 0 || column.width >= 100) && !column.generated && column.type != 2}">
	    <textarea name="value<jstl:out value="${index}"/>" rows="3" cols="<jstl:out value="${w}"/>"><jstl:out value="${value}"/></textarea>
	   </jstl:when>
	   <jstl:otherwise>
            <input <jstl:if test="${column.generated}">readOnly="yes"</jstl:if>
                   name="value<jstl:out value="${index}"/>"
                   type="text"
                   size="<jstl:out value="${w}"/>"
                   <jstl:if test="${column.width>0}">
                    maxLength="<jstl:out value="${w}"/>"
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
	   </jstl:otherwise>
	  </jstl:choose>
         </td>
        </tr>
       </logic:iterate>
      </table>
      <p></p>
      <table cellSpacing="0" cellPadding="3" align="center">
       <tr>
        <td>
         <button type="submit">Сохранить</button>
        </td>
	<jstl:if test="${record != null}">
         <td>
          <button type="button" onClick="if (confirm('Вы уверены, что хотите удалить текущую запись?')) { removeRecordForm.submit() }">Удалить</button>
         </td>
	</jstl:if>
        <td>
         <jstl:choose>
          <jstl:when test="${not empty backURL}">
           <a href="<jstl:out value="${backURL}"/>">
          </jstl:when>
          <jstl:when test="${not empty directoryRecordsSearchParameters}">
           <a href="<%=request.getContextPath()%>/controller?action=directory.pageRecords">
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
     </td>
    </tr> 
   </table>
  </form>
 </body>
</html>
