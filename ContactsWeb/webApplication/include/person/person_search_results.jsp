<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<table width="100%" border="0" cellspacing="1" cellpadding="3">
 <util:pageIterator dispatcherName="/controller?action=person"
                    iterationName="Search"
                    startText="<tr align='center'><td colspan='5'>"
                    endText="</td></tr>"/>
 <tr align="center">
  <th>ID</th>
  <th>Фамилия</th>
  <th>Имя</th>
  <th>Отчество</th>
  <th></th>
 </tr>
 <logic:iterate name="persons" id="person" type="su.sergey.contacts.person.valueobjects.Person2">
  <tr>
   <td align="center"><jstl:out value="${person.handle.id}"/></td>
   <td align="left"><jstl:out value="${person.attributes.lastName}" default=""/></td>
   <td align="left"><jstl:out value="${person.attributes.firstName}" default=""/></td>
   <td align="left"><jstl:out value="${person.attributes.middleName}" default=""/></td>
   <td align="center"><a href="<%=request.getContextPath()%>/controller?action=person.view&id=<jstl:out value="${person.handle.id}"/>">Просмотр</a></td>
  </tr>
 </logic:iterate>
 <util:pageIterator dispatcherName="/controller?action=person"
                    iterationName="Search"
                    startText="<tr align='center'><td colspan='5'>"
                    endText="</td></tr>"/>
</table>
