<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <title>Регистрация - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
 </head>
 <body onLoad="setFocus('loginForm', 'j_username')">
  <p align="center"><a href="<%=request.getContextPath()%>/controller?action=main">^</a> Введите имя пользователя и пароль</p>
  <form name="loginForm" method="POST" action="<%=request.getContextPath()%>/j_security_check"><BR>
   <table width="50%" cellspacing="1" cellpadding="3" align="center">
    <tr> 
     <td width="50%" align="right">Имя пользователя:</td> 
     <td width="50%"><input type="text" name="j_username"></td>
    </tr>
    <tr> 
     <td width="50%" align="right">Пароль:</td>
     <td width="50%"><input type="password" name="j_password"></td>
    </tr>
    <tr> 
     <td colspan=2 align="center"><button type="submit">Войти</button></td>
    </tr>
   </table>
  </form>
 </body>
</html>
