<%--
--%><%@ page language="java" %><%--
--%><%@ page contentType="text/html; charset=UTF-8" %><%--
--%><%@ taglib prefix="jstl" uri="jstl_core" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ru" lang="ru">
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta http-equiv="Content-Style-Type" content="text/css"/>
  <link rel="stylesheet" href="<jstl:out value="${pageContext.request.contextPath}"/>/style.css" type="text/css"/>
  <title>Состояние диалога - База данных &quot;Контакты&quot;</title>
 </head>
 <body>
  <em>Параметры сеанса</em><p style="text-align: left"><a href="<%=request.getContextPath()%>">В начало</a></p>
  <%@ include file="/include/show_all.jsp" %>
 </body>
</html>