<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <title>Ошибка - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <link rel="SHORTCUT ICON" href="<jstl:out value="${pageContext.request.contextPath}"/>/shortcut.ico"/>
 </head>
 <body>
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <table width="100%">
   <tr>
    <td>
     <p><jstl:out value="${error.message}"/></p>
    </td>
   </tr>
   <tr>
    <td><jstl:out value="${error.paramName}"/></td>
   </tr>
   <tr>
    <td></td>
   </tr>           
   <tr align="center">
    <td><a href="<jstl:out value="${backURL}" default="history.back()"/>">Вернуться</a></td>
   </tr>        
  </table>
 </body>
</html>
