<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <title>Нет доступа - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
 </head>
 <body>
  <table cellspacing="10" cellpadding="10" align="center">
   <tr>
    <td>
     <a href="<%=request.getContextPath()%>/logout.jsp" onClick="javascript:logoutForm.submit(); return false;">Выйти</a>
    </td>
    <td>
     <util:showVersion/>
    </td>
   </tr>
  </table>
  <form name="logoutForm" method="POST" action="ibm_security_logout">
   <input type="hidden" name="logoutExitPage" value="/">
  </form>
  <hr>
  <h1>Ошибка - нет доступа</h1>
  <h3><%=exception.getMessage()%></h3>
 </body>
</html>
