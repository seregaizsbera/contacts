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
  <meta http-equiv="expires" content="0">
  <title>Прямой доступ - База данных &quot;Контакты&quot;</title>
  <script language="JavaScript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="JavaScript">
   <!--
    function setQueryText() {
        document.queryForm["queryText"].value = document.historyForm["historySelect"].value;
	    setFocus("queryForm", "queryText");
    }
   -->
  </script>
 </head>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5" onLoad="setFocus('queryForm', 'queryText')">
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <p><b>Прямой доступ к базе данных</b></p>
  <jsp:include flush="true" page="/include/query/query_result.jsp"/>
  <table border="0" cellspacing="5" cellpadding="5" width="80%">
   <tr align="left"><td colspan="2">Введите запрос SQL</td></tr>
   <form name="queryForm" action="<%=request.getContextPath()%>/controller" method="POST">
    <input type="hidden" name="action" value="query.perform">
    <tr align="left">
     <td width="80%" rowspan="2">
      <textarea" name="queryText" rows="10" style="width:600" cols="40" wordwrap="true"><jstl:out value="${queryHistory[0]}" default="select * from persons"/></textarea>
     </td>
     <td>
      <button type="submit">Execute</button>
     </td>
    </tr>
    <tr>
     <td><button type="button" onClick="document.queryForm['queryText'].value=''">Clear</button></td>
    </tr>
   </form>
   <tr align="left"><td colspan="2">История запросов</td></tr>
   <form name="historyForm">
    <tr>
     <td>
      <select name="historySelect" size="4" style="width:600">
       <jstl:if test="${queryHistory != null}">
        <logic:iterate name="queryHistory" id="query" type="java.lang.String" indexId="i">
         <option <jstl:if test="${i == 0}">selected</jstl:if>><jstl:out value="${query}"/></option>
        </logic:iterate>
       </jstl:if>
      </select>
     </td>
     <td>
      <button type="button" onClick="setQueryText()">Get from history</button>
     </td>
    </tr>
   </form>
  </table>
 </body>
</html>
