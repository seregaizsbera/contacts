<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <title>Выход - База данных &quot;Контакты&quot;</title>
 </head>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5">
  <p align="center">
   <form method="POST" action="<%=request.getContextPath()%>/controller" name="logout">
    <input type="submit" name="logout" value="Выйти">
    <input type="hidden" name="action" value="logout">
    <input type="hidden" name="logoutExitPage" value="/">
   </form>
  </p>
 </body>
</html>
