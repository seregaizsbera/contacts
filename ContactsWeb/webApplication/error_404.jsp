<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=ISO8859-1" %>
<%@ page isErrorPage="true" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO8859-1">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <title>Resource not found - Database &quot;Contacts&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
 </head>
 <body>
  <h1>Error - Resource not found</h1>
  <h3><%=exception.getMessage()%></h3>
  <p>You'd better start from the <a href="<%=request.getContextPath()%>">very beginning</a></p>
 </body>
</html>
