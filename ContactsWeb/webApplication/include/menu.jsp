<%@ page import="su.sergey.contacts.*" %>
<%@ taglib prefix="util" uri="contacts" %>
<%-- <META http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<form name="chooser" method="GET" action="<%=request.getContextPath()%>/controller">
 <table cellspacing="10" cellpadding="10">
  <tr>
   <td>
     <% { String action = request.getParameter(RequestConstants.PN_ACTION); %>
     Переход: <select name="action" onChange="submit()">
     <option value="main"      <%=(action == null || action.equals(""))              ? "selected" : ""%>>-- Выберите страницу --</option>
     <option value="main"      <%=(action != null && action.startsWith("main"))      ? "selected" : ""%>>Начало</option>
     <option value="directory" <%=(action != null && action.startsWith("directory")) ? "selected" : ""%>>Редактирование данных</option>
     <option value="person"   <%=(action != null && action.startsWith("person"))    ? "selected" : ""%>>Данные о личностях</option>
     <% } %>
    </select>
   </td>
   <td>
    <input type="submit" value="Вперед">
   </td>
   <td><a href="javascript:history.back()">Назад</a></td>
   <td>
    <a href="<%=request.getContextPath()%>/logout.jsp" onClick="javascript:logoutForm.submit(); return false;">Выйти</a>
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
