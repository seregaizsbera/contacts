<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<util:pageIterator dispatcherName="/controller?action=person" iterationName="Search">
 <util:startText><table width="100%"><tr><td align="center"></util:startText>
 <util:endText></td></tr></table></util:endText>
</util:pageIterator>
<table width="100%">
 <tr>
  <th width="*"></th>
  <th width="5%">ID</th>
  <th width="15%">Фамилия</th>
  <th width="15%">Имя</th>
  <th width="15%">Отчество</th>
  <th width="5%"></th>
  <th width="*"></th>
 </tr>
 <logic:iterate name="persons" id="person" type="su.sergey.contacts.person.valueobjects.Person2">
  <tr>
   <td></td>
   <td align="center"><jstl:out value="${person.handle.id}"/></td>
   <td align="left"><a href="<%=request.getContextPath()%>/controller?action=person.view&id=<jstl:out value="${person.handle.id}"/>"><jstl:out value="${person.attributes.lastName}"/></a></td>
   <td align="left"><jstl:out value="${person.attributes.firstName}"/></td>
   <td align="left"><jstl:out value="${person.attributes.middleName}"/></td>
   <td align="left"><a href="<%=request.getContextPath()%>/controller?action=person.view&id=<jstl:out value="${person.handle.id}"/>" accessKey="р"><img src="<%=request.getContextPath()%>/images/ico_id.gif" width="16" height="16" border="0" alt="Просмотр"></a></td>
   <td></td>
  </tr>
 </logic:iterate>
</table>
<util:pageIterator dispatcherName="/controller?action=person" iterationName="Search">
 <util:startText><table width="100%"><tr><td align="center"></util:startText>
 <util:endText></td></tr></table></util:endText>
</util:pageIterator>
