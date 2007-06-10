<%--
--%><%@ page contentType="text/html; charset=UTF-8" %><%--
--%><%@ taglib prefix="logic" uri="struts_logic" %><%--
--%><%@ taglib prefix="jstl" uri="jstl_core" %><%--
--%><%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%><%--
--%><table class="menu"><%--
 --%><tbody><%--
  --%><tr style="vertical-align: middle"><%--
   --%><form id="chooser" method="get" action="<jstl:out value="${pageContext.request.contextPath}"/>/controller"><%--
    --%><td width="5%" style="text-align: left"><%--
     --%><jstl:if test="${not empty AllAuthenticated}"><%--
      --%><% { String action = request.getParameter("action"); %><%--
       --%><select name="action" onchange="submit()"><%--
        --%><option value="main">Начало</option><%--
        --%><option value="person" <%=(action != null && action.startsWith("person")) ? "selected" : ""%>>Данные о личностях</option><%--
        --%><option value="supply" <%=(action != null && action.startsWith("supply")) ? "selected" : ""%>>Данные об организациях</option><%--
        --%><jstl:if test="${not empty Sergey}"><%--
         --%><option value="directory" <%=(action != null && action.startsWith("directory")) ? "selected" : ""%>>Редактирование данных</option><%--
         --%><option value="query" <%=(action != null && action.startsWith("query")) ? "selected" : ""%>>Доступ к базе данных</option><%--
         --%><option value="call" <%=(action != null && action.startsWith("call")) ? "selected" : ""%>>Расходы на мобильный</option><%--
         --%></jstl:if><%--
       --%></select><%--
      --%><% } %><%--
     --%></jstl:if><%--
    --%></td><%--
   --%></form><%--
   --%><jstl:if test="${not empty backURL}"><%--
    --%><td width="5%" style="text-align: left"><a class="eternal" accessKey="щ" href="<jstl:out value="${backURL}"/>">Назад</a></td><%--
   --%></jstl:if><%--
   --%><jstl:if test="${paramValues['action'][0] != null && paramValues['action'][0] != 'main'}"><%--
    --%><td width="5%" style="text-align: left"><%--
     --%><a href="<jstl:out value="${pageContext.request.contextPath}"/>/controller?action=main" class="eternal" accessKey="й"><%--
      --%>В&nbsp;начало<%--
     --%></a><%--
    --%></td><%--
   --%></jstl:if><%--
   --%><td width="*%" style="text-align: right"><%--
    --%>Версия:&nbsp;<jstl:out value="${productInfo.version}"/><%--
   --%></td><%--
   --%><td width="5%" style="text-align: right"><%--
    --%><a href="<jstl:out value="${pageContext.request.contextPath}"/>/logout.jsp" class="eternal" accessKey="ч" onclick="document.getElementById('logoutForm').submit(); return false;">Выйти</a><%--
   --%></td><%--
  --%></tr><%--
 --%></tbody><%--
--%></table><%--
--%><form id="logoutForm" method="post" action="<jstl:out value="${pageContext.request.contextPath}"/>/controller?action=logout"><%--
 --%><div style="visibility: hidden"><input type="hidden" name="logoutExitPage" value="/"></div><%--
--%></form>