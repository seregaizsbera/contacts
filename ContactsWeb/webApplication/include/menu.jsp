<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<table cellspacing="10" cellpadding="10">
 <form name="chooser" method="GET" action="<%=request.getContextPath()%>/controller">
  <tr>
   <td>
     <jstl:if test="${not empty AllAuthenticated}">
      <% { String action = request.getParameter("action"); %>
       Переход:
       <select name="action" onChange="submit()">
        <option value="main"      <%=(action == null || action.equals("") || action.startsWith("main"))      ? "selected" : ""%>>Начало</option>
        <option value="person"    <%=(action != null && action.startsWith("person"))    ? "selected" : ""%>>Данные о личностях</option>
        <option value="supply"    <%=(action != null && action.startsWith("supply"))    ? "selected" : ""%>>Данные об организациях</option>
        <jstl:if test="${not empty Sergey}">
         <option value="directory" <%=(action != null && action.startsWith("directory")) ? "selected" : ""%>>Редактирование данных</option>
         <option value="query"     <%=(action != null && action.startsWith("query"))     ? "selected" : ""%>>Доступ к базе данных</option>
         <option value="call"      <%=(action != null && action.startsWith("call"))      ? "selected" : ""%>>Расходы на мобильный</option>
        </jstl:if>
      <% } %>
     </select>
    </jstl:if>
   </td>
   <td><jstl:if test="${backURL != null}"><a accesskey="щ" href="<jstl:out value="${backURL}"/>"></jstl:if>Назад<jstl:if test="${backURL != null}"></a></jstl:if></td>
   <td>
    <a href="<%=request.getContextPath()%>/logout.jsp" accesskey="ч" onClick="logoutForm.submit(); return false;">Выйти</a>
   </td>
   <td>
    <a href="<%=request.getContextPath()%>/controller?action=main" accesskey="й">В начало</a>
   </td>
   <td>
    Версия:&nbsp;<jstl:out value="${productInfo.version}"/>
   </td>
  </tr>
 </form>
 <form name="logoutForm" method="POST" action="<%=request.getContextPath()%>/controller">
  <input type="hidden" name="action" value="logout">
  <input type="hidden" name="logoutExitPage" value="/">
 </form>
</table>
<hr>
