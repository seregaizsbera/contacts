<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<HTML>
 <HEAD>
  <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>Выход - База данных &quot;Контакты&quot;</TITLE>
 </HEAD>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5">
  <p align="center">
   <FORM method="POST" action="<%=request.getContextPath()%>/controller" name="logout">
    <INPUT type="submit" name="logout" value="Выйти">
    <INPUT type="hidden" name="action" value="logout">
    <INPUT type="hidden" name="logoutExitPage" value="/">
   </FORM>
  </p>
 </BODY>
</HTML>
