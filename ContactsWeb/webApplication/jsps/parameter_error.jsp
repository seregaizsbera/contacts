<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <title>Error page</title>
 </head>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5">
  <jsp:include flush="true" page="/include/menu.jsp"/>
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
    <td>&nbsp;</td>
   </tr>           
   <tr align="center">
    <td colspan="1"><a href="javascript:history.back()">Вернуться</a></td>
   </tr>        
  </table>
 </body>
</html>
