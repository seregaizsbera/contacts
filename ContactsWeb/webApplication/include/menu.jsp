<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<table width="100%" cellSpacing="5" cellPadding="5">
 <form name="chooser" method="GET" action="<%=request.getContextPath()%>/controller">
  <tr valign="top">
   <td width="5%" align="left">
    <jstl:if test="${not empty AllAuthenticated}">
     <% { String action = request.getParameter("action"); %>
      <select name="action" onChange="submit()">
       <option value="main">Начало</option>
       <option value="person"    <%=(action != null && action.startsWith("person"))    ? "selected" : ""%>>Данные о личностях</option>
       <option value="supply"    <%=(action != null && action.startsWith("supply"))    ? "selected" : ""%>>Данные об организациях</option>
       <jstl:if test="${not empty Sergey}">
        <option value="directory" <%=(action != null && action.startsWith("directory")) ? "selected" : ""%>>Редактирование данных</option>
        <option value="query"     <%=(action != null && action.startsWith("query"))     ? "selected" : ""%>>Доступ к базе данных</option>
        <option value="call"      <%=(action != null && action.startsWith("call"))      ? "selected" : ""%>>Расходы на мобильный</option>
       </jstl:if>
      </select>
     <% } %>
    </jstl:if>
   </td>
   <td width="5%" align="left"><jstl:if test="${backURL != null}"><a class="eternal" accessKey="щ" href="<jstl:out value="${backURL}"/>">Назад</a></jstl:if></td>
   <td width="5%" align="left">
    <jstl:if test="${paramValues['action'][0] != null && paramValues['action'][0] != 'main'}">
     <a href="<%=request.getContextPath()%>/controller?action=main" class="eternal" accessKey="й">
      В&nbsp;начало
     </a>
    </jstl:if>
   </td>
   <td width="*%" align="right">
    Версия:&nbsp;<jstl:out value="${productInfo.version}"/>
   </td>
   <td width="5%" align="right">
    <a href="<%=request.getContextPath()%>/logout.jsp" class="eternal" accessKey="ч" onClick="logoutForm.submit(); return false;">Выйти</a>
   </td>
  </tr>
 </form>
 <form name="logoutForm" method="POST" action="<%=request.getContextPath()%>/controller">
  <input type="hidden" name="action" value="logout">
  <input type="hidden" name="logoutExitPage" value="/">
 </form>
</table>
<hr>
