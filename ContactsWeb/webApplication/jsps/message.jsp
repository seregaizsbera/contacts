<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <title>Сообщение - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
 </head>
 <body>
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p>Сообщение</p>
  <table width="75%" align="center"><tr><td align="center"><table><tr><td align="left"><jstl:out value="${message}"/></td></tr></table></td></tr>
  <jstl:set var="defaultNextURL"><%=request.getContextPath()%>/controller?action=main</jstl:set>
  <jstl:set var="defaultNextMessage">Продолжить</jstl:set>
  <table align="center" class="message">
   <tr>
    <td>
     <a accessKey="р" href="<jstl:out value="${nextURL}" default="${defaultNextURL}"/>">
      <jstl:out value="${nextMessage}" default="${defaultNextMessage}"/>
     </a>
    </td>
    <jstl:if test="${not empty alternateURL}">
     <td>
      <a accessKey="о" href="<jstl:out value="${alternateURL}" default="${defaultNextURL}"/>">
      <jstl:out value="${alternateMessage}" default="${defaultNextMessage}"/>
      </a>
     </td>  
    </jstl:if>
   </tr>
  </table>
 </body>
</html>
