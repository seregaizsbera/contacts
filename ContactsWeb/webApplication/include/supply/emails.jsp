<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<table align="center" width="50%" cellspacing="5" cellpadding="1">
 <jstl:if test="${supply!=null}">
  <tr>
   <td colspan="4">
    <a href="<%=request.getContextPath()%>/controller?action=supply.emails&id=<jstl:out value="${supply.handle.id}"/>">Электронная&nbsp;почта</a>
   </td>
  </tr>
  <jstl:set var="emails" value="${supply.attributes.emails}"/>
  <logic:iterate name="emails" id="email" indexId="i" type="su.sergey.contacts.email.valueobjects.EmailAttributes">
   <tr>
    <td align="right"><jstl:out value="${i+1}"/>.</td>
    <td align="left">
     <a href="mailto:<jstl:out value="${email.email}"/>"><jstl:out value="${email.email}"/></a>
    </td>
    <td></td>
    <td></td>
   </tr>
  </logic:iterate>
 </jstl:if>
</table>