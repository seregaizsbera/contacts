<%@ page import="su.sergey.contacts.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<form name="chooser" method="GET" action="<%=request.getContextPath()%>/controller">
 <table cellspacing="10" cellpadding="10">
  <tr>
   <td>
     <% { String action = request.getParameter(RequestConstants.PN_ACTION); %>
     Переход: <select name="action" onChange="submit()">
     <option value="main"      <%=(action == null || action.equals("") || action.startsWith("main"))      ? "selected" : ""%>>Начало</option>
     <option value="directory" <%=(action != null && action.startsWith("directory")) ? "selected" : ""%>>Редактирование данных</option>
     <option value="person"   <%=(action != null && action.startsWith("person"))    ? "selected" : ""%>>Данные о личностях</option>
     <option value="query"   <%=(action != null && action.startsWith("query"))    ? "selected" : ""%>>Доступ к базе данных</option>
     <% } %>
    </select>
   </td>
   <td>
    <input type="submit" value="Вперед">
   </td>
   <td><jstl:if test="${backURL != null}"><a href="<jstl:out value="${backURL}"/>"></jstl:if>Назад<jstl:if test="${backURL != null}"></a></jstl:if></td>
   <td>
    <a href="<%=request.getContextPath()%>/logout.jsp" onClick="logoutForm.submit(); return false;">Выйти</a>
   </td>
   <td>
    <a href="<%=request.getContextPath()%>/controller">В начало</a>
   </td>
   <td>
    <util:showVersion/>
   </td>
  </tr>
 </table>
</form>
<form name="logoutForm" method="POST" action="<%=request.getContextPath()%>/controller">
 <input type="hidden" name="action" value="logout">
 <input type="hidden" name="logoutExitPage" value="/">
</form>
<hr>
