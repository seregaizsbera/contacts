<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<HTML>
 <HEAD>
  <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>Состояние диалога - База данных &quot;Контакты&quot;</TITLE>
 </HEAD>
 <BODY>
  <h2>Сессия</h2>
  <h3>Атрибуты</h3>
  <table>
   <tr><th>Имя</th><th>Значение</th></tr>
   <% for (Enumeration names = request.getAttributeNames(); names.hasMoreElements();) {
          String name = (String) names.nextElement();
          Object object = request.getAttribute(name);
          String value = object.getClass().getName() + ": " + object.toString();
   %>
   <tr>
    <td><%=name%></td>
    <td><%=value%></td>
   </tr>
   <% } %>
  </table>
  <h2>Запрос</h2>
  <h3>Параметры</h3>
  <table>
   <tr><th>Имя</th><th>Значение</th></tr>
   <% for (Enumeration names = request.getParameterNames(); names.hasMoreElements();) {
          String name = (String) names.nextElement();
          String values[] = request.getParameterValues(name);
   %>
   <tr>
    <td><%=name%></td>
    <td><% for (int i = 0; i < values.length; i++) { %>
            <%=values[i]%>
            <% if (i < values.length) { %>
            &amp;&nbsp;
            <% } %>
        <% } %>
    </td>
   </tr>
   <% } %>
  </table>
  <h3>Атрибуты</h3>
  <table>
   <tr><th>Имя</th><th>Значение</th></tr>
   <% for (Enumeration names = session.getAttributeNames(); names.hasMoreElements();) {
          String name = (String) names.nextElement();
          Object object = session.getAttribute(name);
          String value = object.getClass().getName() + ": " + object.toString();
   %>
   <tr>
    <td><%=name%></td>
    <td><%=value%></td>
   </tr>
   <% } %>
  </table>
 </BODY>
</HTML>
