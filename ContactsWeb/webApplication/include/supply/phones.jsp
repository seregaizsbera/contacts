<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<table width="100%" cellspacing="1" cellpadding="3">
 <jstl:if test="${supply != null}">
  <tr>
   <td colspan="4"><a accesskey="т" href="<%=request.getContextPath()%>/controller?action=supply.phones&id=<jstl:out value="${supply.handle.id}"/>">Телефоны</a></td>
  </tr>
  <jstl:set var="phones" value="${supply.attributes.phones}"/>
  <logic:iterate name="phones" id="phone" indexId="i" type="su.sergey.contacts.phone.valueobjects.PhoneAttributes">
   <jstl:set var="type"><jstl:out value="${phone.type}"/></jstl:set>
   <tr>
    <td align="right" width="5%"><jstl:out value="${i+1}"/>.</td>
    <td align="left" width="25%"><jstl:out value="${phone.phone}"/></td>
    <td align="left"><jstl:out value="${phone.note}"/></td>
    <td align="left">
     <jstl:if test="${type != '100'}">
      <jstl:out value="${inquire_phone_types_4[type]}"/>
     </jstl:if>
    </td>
   </tr>
  </logic:iterate>
 </jstl:if>
</table>
