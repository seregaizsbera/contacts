<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.ibm.servlet.engine.webapp.*" %>
<%-- <META http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<hr>
<h4>Сессия</h4>
<h5>Атрибуты</h5>
<% Collection stackTraces = new ArrayList();; %>
<table cellspacing="3" cellpadding="5">
 <tr><th>Имя</th><th>Класс</th><th>Значение</th></tr>
 <% for (Enumeration names = request.getAttributeNames(); names.hasMoreElements();) {
        String name = (String) names.nextElement();
        Object object = request.getAttribute(name);
        String className = object.getClass().getName();
        String value = object.toString();
        if (object instanceof WebAppErrorReport) {
            StringWriter writer = new StringWriter();
            WebAppErrorReport error = (WebAppErrorReport) object;
            error.printFullStackTrace(new PrintWriter(writer));
            writer.close();
            stackTraces.add(writer.toString());
            value = error.getMessage();
        } else if (object instanceof Exception) {
            StringWriter writer = new StringWriter();
            Exception error = (Exception) object;
            error.printStackTrace(new PrintWriter(writer));
            writer.close();
            stackTraces.add(writer.toString());
            value = error.getMessage();
        }
 %>
 <tr>
  <td><%=name%></td>
  <td><%=className%></td>
  <td><%=value%></td>
 </tr>
 <% } %>
</table>
<% for (Iterator i = stackTraces.iterator(); i.hasNext();) { %>
       <pre><%=(String) i.next()%></pre>
       <%=i.hasNext() ? "<hr>" : ""%>
<% } %>
<hr>
<h4>Запрос</h4>
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
<h5>Атрибуты</h5>
<table>
 <tr><th>Имя</th><th>Класс</th><th>Значение</th></tr>
 <% for (Enumeration names = session.getAttributeNames(); names.hasMoreElements();) {
        String name = (String) names.nextElement();
        Object object = session.getAttribute(name);
        String className = object.getClass().getName();
        String value = object.toString();
 %>
 <tr>
  <td><%=name%></td>
  <td><%=className%></td>
  <td><%=value%></td>
 </tr>
 <% } %>
</table>
