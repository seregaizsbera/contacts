<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<table cellspacing="5" cellpadding="1">
 <jstl:if test="${person!=null}">
  <caption><a href="<%=request.getContextPath()%>/controller?action=person.emails&id=<jstl:out value="${person.handle.id}"/>">Электронная&nbsp;почта</a></caption>
  <jstl:set var="emails" value="${person.attributes.emails}"/>
  <logic:iterate name="emails" id="email" indexId="i" type="su.sergey.contacts.email.valueobjects.EmailAttributes">
   <tr>
    <td align="right"><jstl:out value="${i+1}"/>.</td>
    <td align="left">
     <jstl:if test="${email.basic}"><b></jstl:if>
     <a href="mailto:<jstl:out value="${email.email}"/>"><jstl:out value="${email.email}"/></a>
     <jstl:if test="${email.basic}"><b></jstl:if></td>
    <td></td>
    <td></td>
   </tr>
  </logic:iterate>
 </jstl:if>
</table>
