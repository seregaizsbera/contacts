<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <title>Ошибка - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
 </head>
 <body>
  <h3>Ошибка</h3>
  <p><%=exception.getMessage()%></p>
  <p align="left"><a href="<%=request.getContextPath()%>/">В начало</a></p>
  <% if (session.getAttribute("Sergey") != null) {%>
   <%@ include file="/include/show_all.jsp" %>
  <% } %>
 </body>
</html>
