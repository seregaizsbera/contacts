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
 </head>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5">
  <p align="center">Введите имя пользователя и пароль</p>
  <form name="loginForm" method="POST" action="<%=request.getContextPath()%>/j_security_check"><BR>
   <table width="50%" border="0" cellpadding="3" cellspacing="1" align="center">
    <tr> 
     <td align="right">&nbsp;&nbsp;Имя пользователя:</td> 
     <td><input type="text" name="j_username"></td>
    </tr>
    <tr> 
     <td align="right">Пароль:</td>
     <td><input type="password" name="j_password"></td>
    </tr>
    <tr> 
     <td colspan=2 align="center"><input type="submit" value="Войти"></td>
    </tr>
   </table>
  </form>
 </body>
</html>
