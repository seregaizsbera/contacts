<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <title>Сообщение - База данных "Контакты"</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
 </head>
 <body>
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <h1><jstl:out value="${message}"/></h1>
  <jstl:set var="defaultNextURL"><%=request.getContextPath()%>/controller?action=main</jstl:set>
  <p align="center"><a accesskey="р" href="<jstl:out value="${nextURL}" default="${defaultNextURL}"/>">Продолжить</a></p>
 </body>
</html>
