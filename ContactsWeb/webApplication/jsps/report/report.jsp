<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Content-Style-Type" content="text/css">
  <link href="/contacts/style.css" rel="stylesheet" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <title>Создание отчета - База данных &quot;Контакты&quot;</title>
 </head>
 <body onLoad="setFocus('reportForm', 'description')">
  <form name="reportForm" method="GET" action="<%=request.getContextPath()%>/controller">
   <input type="hidden" name="action" value="report.build<jstl:out value="${reportType}"/>">
   <table align="center">
    <tr>
     <td align="right">Описание отчета</td>
     <td><input type="text" name="description" class="wide_elem"></td>
    </tr>
    <tr>
     <td align="center" colSpan="2"><button type="submit">Создать отчет</button></td>
    </tr>
   </table>
  </form>
 </body>
</html>
