<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <title>Выход - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
 </head>
 <body>
  <center>
   <form method="POST" action="<%=request.getContextPath()%>/controller" name="logout">
    <input type="hidden" name="action" value="logout">
    <input type="hidden" name="logoutExitPage" value="/">
    <input type="submit" name="logout" value="Выйти">
   </form>
  </center>
 </body>
</html>
