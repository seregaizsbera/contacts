<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="su.sergey.contacts.*" %>
<%@ page import="su.sergey.contacts.directory.*" %>
<%@ page import="su.sergey.contacts.directory.valueobjects.*" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <META http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<table width="100%" border="0" cellspacing="1" cellpadding="3">
 <util:pageIterator dispatcherName="/controller?action=person.search"
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
   <td align="left"><jstl:out value="${person.attributes.lastName}" default=""/>&nbsp;</td>
   <td align="left"><jstl:out value="${person.attributes.firstName}" default=""/>&nbsp;</td>
   <td align="left"><jstl:out value="${person.attributes.middleName}" default=""/>&nbsp;</td>
   <td align="center"><a href="<%=request.getContextPath()%>/controller?action=persons.view&id=<jstl:out value="${person.handle.id}"/>">Просмотр</a></td>
  </tr>
 </logic:iterate>
 <util:pageIterator dispatcherName="/controller?action=person.search"
                    startText="<tr align='center'><td colspan='5'>"
                    endText="</td></tr>"/>
</table>
