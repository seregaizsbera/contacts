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
  <title>Регистрация - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="JavaScript" src="<%=request.getContextPath()%>/js/utils.js"></script>
 </head>
 <body onLoad="setFocus('loginForm', 'j_username')">
  <p align="center">Введите имя пользователя и пароль</p>
  <form name="loginForm" method="POST" action="<%=request.getContextPath()%>/j_security_check"><BR>
   <table width="50%" border="0" cellpadding="3" cellspacing="1" align="center">
    <tr> 
     <td align="right"><a href="<%=request.getContextPath()%>/controller?action=main">*</a> Имя пользователя:</td> 
     <td><input type="text" name="j_username"></td>
    </tr>
    <tr> 
     <td align="right">Пароль:</td>
     <td><input type="password" name="j_password"></td>
     <td></td>
    </tr>
    <tr> 
     <td colspan=3 align="center"><button type="submit">Войти</button></td>
    </tr>
   </table>
  </form>
 </body>
</html>
