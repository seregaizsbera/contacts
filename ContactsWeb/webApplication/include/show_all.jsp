<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%-- <META http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<% Collection stackTraces = new ArrayList();; %>
<h4>Запрос</h4>
<h5>Атрибуты</h5>
<table cellspacing="3" cellpadding="5">
 <tr><th>Имя</th><th>Класс</th><th>Значение</th></tr>
 <% for (Enumeration names = request.getAttributeNames(); names.hasMoreElements();) {
        String name = (String) names.nextElement();
        Object object = request.getAttribute(name);
        String className = "";
        String value = "";
        if (object == null) {
            value = "null";
            className = "null";
        } else {
	        className = object.getClass().getName();
	        value = object.toString();
        }
        if (object instanceof Exception) {
            StringWriter writer = new StringWriter();
            Exception error = (Exception) object;
            error.printStackTrace(new PrintWriter(writer));
            writer.close();
            stackTraces.add(writer.toString());
            value = error.getMessage();
        }
 %>
 <tr valign="top">
  <td><%=name%></td>
  <td><%=className%></td>
  <td><pre><%=value%></pre></td>
 </tr>
 <% } %>
</table>
<h5>Параметры</h5>
<table>
 <tr><th>Имя</th><th>Значение</th></tr>
 <% for (Enumeration names = request.getParameterNames(); names.hasMoreElements();) {
        String name = (String) names.nextElement();
        String values[] = request.getParameterValues(name);
        for (int i = 0; i < values.length; i++) { %>
         <tr>
          <td><%=name%></td>
          <td><%=values[i]%></td>
         </tr>
 <%     }
    }
 %>
</table>
<hr>
<h4>Сессия</h4>
<h5>Атрибуты</h5>
<table>
 <tr><th>Имя</th><th>Класс</th><th>Значение</th></tr>
 <% for (Enumeration names = session.getAttributeNames(); names.hasMoreElements();) {
        String name = (String) names.nextElement();
        Object object = session.getAttribute(name);
        String className = object.getClass().getName();
        String value = object.toString();
 %>
 <tr valign="top">
  <td><%=name%></td>
  <td><%=className%></td>
  <td><pre><%=value%></pre></td>
 </tr>
 <% } %>
</table>
<hr>
<h4>Cтраница</h4>
<h5>Атрибуты</h5>
<table cellspacing="3" cellpadding="5">
 <tr><th>Имя</th><th>Класс</th><th>Значение</th></tr>
  <% for (Enumeration names = pageContext.getAttributeNamesInScope(PageContext.PAGE_SCOPE); names.hasMoreElements();) {
        String name = (String) names.nextElement();
        Object object = pageContext.getAttribute(name);
        String className = object.getClass().getName();
        String value = object.toString();
        if (object instanceof Exception) {
            StringWriter writer = new StringWriter();
            Exception error = (Exception) object;
            error.printStackTrace(new PrintWriter(writer));
            writer.close();
            stackTraces.add(writer.toString());
            value = error.getMessage();
        }
 %>
 <tr valign="top">
  <td><%=name%></td>
  <td><%=className%></td>
  <td><pre><%=value%></pre></td>
 </tr>
 <% } %>
</table>
<hr>
<h4>Приложение</h4>
<h5>Атрибуты</h5>
<table cellspacing="3" cellpadding="5">
 <tr><th>Имя</th><th>Класс</th><th>Значение</th></tr>
  <% for (Enumeration names = pageContext.getAttributeNamesInScope(PageContext.APPLICATION_SCOPE); names.hasMoreElements();) {
        String name = (String) names.nextElement();
        Object object = pageContext.getAttribute(name, PageContext.APPLICATION_SCOPE);
        String className = object.getClass().getName();
        String value = object.toString();
        if (object instanceof Exception) {
            StringWriter writer = new StringWriter();
            Exception error = (Exception) object;
            error.printStackTrace(new PrintWriter(writer));
            writer.close();
            stackTraces.add(writer.toString());
            value = error.getMessage();
        }
 %>
 <tr valign="top">
  <td><%=name%></td>
  <td><%=className%></td>
  <td><pre><%=value%></pre></td>
 </tr>
 <% } %>
</table>
<% if (!stackTraces.isEmpty()) { %>
    <hr>
    <h4>Найденные исключения</h4>
    <hr>
    <% for (Iterator i = stackTraces.iterator(); i.hasNext();) { %>
       <pre><%=(String) i.next()%></pre>
       <%=i.hasNext() ? "<hr>" : ""%>
    <% } 
   }
%>
