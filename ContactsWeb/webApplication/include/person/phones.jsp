<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<table width="100%" cellSpacing="1" cellPadding="3">
 <jstl:if test="${person != null}">
  <tr>
   <td colspan="3"><a accessKey="т" href="<%=request.getContextPath()%>/controller?action=person.phones&id=<jstl:out value="${person.handle.id}"/>">Телефоны</a></td>
  </tr>
  <jstl:set var="phones" value="${person.attributes.phones}"/>
  <logic:iterate name="phones" id="phone" indexId="i" type="su.sergey.contacts.phone.valueobjects.PhoneAttributes">
   <jstl:set var="type"><jstl:out value="${phone.type}"/></jstl:set>
   <tr>
    <td align="right" width="5%"><jstl:out value="${i+1}"/>.</td>
    <td align="left" width="25%"><jstl:if test="${phone.basic}"><b></jstl:if><jstl:out value="${phone.phone}"/><jstl:if test="${phone.basic}"><b></jstl:if></td>
    <td align="left">
     <jstl:if test="${type != '100'}">
      <jstl:out value="${inquire_phone_types_4[type]}"/>
     </jstl:if>
    </td>
   </tr>
  </logic:iterate>
 </jstl:if>
</table>
