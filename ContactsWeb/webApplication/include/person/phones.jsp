<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<table cellspacing="5" cellpadding="1">
 <jstl:if test="${person!=null}">
  <caption><a accesskey="т" href="<%=request.getContextPath()%>/controller?action=person.phones&id=<jstl:out value="${person.handle.id}"/>">Телефоны</a></caption>
  <jstl:set var="phones" value="${person.attributes.phones}"/>
  <logic:iterate name="phones" id="phone" indexId="i" type="su.sergey.contacts.phone.valueobjects.PhoneAttributes">
   <tr>
    <td align="right"><jstl:out value="${i+1}"/>.</td>
    <td align="left"><jstl:if test="${phone.basic}"><b></jstl:if><jstl:out value="${phone.phone}"/><jstl:if test="${phone.basic}"><b></jstl:if></td>
    <td align="left">
     <jstl:out value="${inquire_phone_types_4[phone.type]}"/>
    </td>
    <td></td>
   </tr>
  </logic:iterate>
 </jstl:if>
</table>
