<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <title>Состояние диалога - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
 </head>
 <body>
  <i>Параметры сеанса</i><p align="right"><a href="<%=request.getContextPath()%>/controller">To home</a></p>
  <% if (session.getAttribute("Sergey") == null) {
         throw new SecurityException("Эту страницу нельзя просматривать кому попало");
     }
  %>
  <%@ include file="/include/show_all.jsp" %>
 </body>
</html>
