<%--
--%><%@ page language="java" %><%--
--%><%@ page contentType="text/html; charset=UTF-8" %><%--
--%><%@ page isErrorPage="true" %><%--
--%><%@ taglib prefix="jstl" uri="jstl_core" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<%--
--%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%--
--%><html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ru" lang="ru"><%--
 --%><head><%--
  --%><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/><%--
  --%><meta http-equiv="Content-Style-Type" content="text/css"/><%--
  --%><meta http-equiv="Content-Script-Type" content="text/javascript"/><%--
  --%><link rel="stylesheet" href="<jstl:out value="${pageContext.request.contextPath}"/>/style.css" type="text/css"/><%--
  --%><title>Ошибка - База данных &quot;Контакты&quot;</title><%--
 --%></head><%--
 --%><body><%--
  --%><h3>Ошибка</h3><%--
  --%><p><%=exception.getMessage()%></p><%--
  --%><p align="left"><a href="<jstl:out value="${pageContext.request.contextPath}"/>">В начало</a></p><%--
  --%><jstl:if test="${not empty Sergey}"><%--
   --%><%@ include file="/include/show_all.jsp" %><%--
  --%></jstl:if><%--
 --%></body><%--
--%></html>