<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<table align="center" width="50%" cellspacing="5" cellpadding="1">
 <jstl:if test="${supply!=null}">
  <tr>
   <td colspan="4"><a href="<%=request.getContextPath()%>/controller?action=supply.phones&id=<jstl:out value="${supply.handle.id}"/>">Телефоны</a></td>
  </tr>
  <jstl:set var="phones" value="${supply.attributes.phones}"/>
  <logic:iterate name="phones" id="phone" indexId="i" type="su.sergey.contacts.phone.valueobjects.PhoneAttributes">
   <tr>
    <td></td>
    <td align="right"><jstl:out value="${i+1}"/>.</td>
    <td align="left"><jstl:out value="${phone.phone}"/></td>
    <td align="left"><jstl:out value="${phone.note}"/></td>
   </tr>
  </logic:iterate>
 </jstl:if>
</table>
