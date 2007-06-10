<%--
--%><%@ page language="java" %><%--
--%><%@ page contentType="text/html; charset=ISO8859-1" %><%--
--%><%@ page isErrorPage="true" %><%--
--%><?xml version="1.0" encoding="ISO8859-1"?>
<%--
--%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%--
--%><html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en"><%--
 --%><head><%--
  --%><meta http-equiv="Content-Type" content="text/html; charset=ISO8859-1"/><%--
  --%><meta http-equiv="Content-Style-Type" content="text/css"/><%--
  --%><meta http-equiv="Content-Script-Type" content="text/javascript"/><%--
  --%><link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css"/><%--
  --%><title>Resource not found - Database &quot;Contacts&quot;</title><%--
 --%></head><%--
 --%><body><%--
  --%><h1>Error - Resource not found</h1><%--
  --%><% if (exception != null && exception.getMessage() != null && !exception.getMessage().equals("")) { %><%--
      --%><h3><%=exception.getMessage()%></h3><%--
  --%><% } %><%--
  --%><p>You'd better start from the <a href="<%=request.getContextPath()%>">very beginning</a></p><%--
 --%></body><%--
--%></html>