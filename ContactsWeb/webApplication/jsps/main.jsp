<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <title>Добро пожаловать - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
 </head>
 <body>
  <jsp:include flush="true" page="/include/menu.jsp"/>
  Добро пожаловать!
  <center>
   <table>
    <tr>
     <td align="right">Версия:&nbsp;</td>
     <td><jstl:out value="${productInfo.version}"/></td>
    </tr>
    <tr>
     <td align="right">Собрано:&nbsp;</td>
     <td><jstl:out value="${productInfo.buildDate}"/>&nbsp;<jstl:out value="${productInfo.buildTime}"/></td>
    </tr>
    <tr>
     <td></td>
     <td></td>
    </tr>
    <tr>
     <td align="right">Пользователь:&nbsp;</td>
     <td><%=request.getUserPrincipal().getName()%></td>
    </tr>
   </table>
  </center>
 </body>
</html>
