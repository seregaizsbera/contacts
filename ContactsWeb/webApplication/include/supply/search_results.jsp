<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<util:pageIterator dispatcherName="/controller?action=supply" iterationName="Search">
 <util:startText><table width="100%"><tr><td align="center"></util:startText>
 <util:endText></td></tr></table></util:endText>
</util:pageIterator>
<table width="100%">
 <tr align="center">
  <th>ID</th>
  <th>Название</th>
  <th>Группа</th>
  <th>Род деятельности</th>
  <th>Метро</th>
  <th></th>
 </tr>
 <logic:iterate name="supplies" id="supply" type="su.sergey.contacts.supply.valueobjects.Supply2">
  <jstl:set var="kind"><jstl:out value="${supply.attributes.kind}"/></jstl:set>
  <tr>
   <td align="center"><jstl:out value="${supply.handle.id}"/></td>
   <jstl:choose>
    <jstl:when test="${supply.attributes.important}">
     <th align="left">
      <a href="<%=request.getContextPath()%>/controller?action=supply.view&id=<jstl:out value="${supply.handle.id}"/>"><jstl:if test="${not empty supply.attributes.propertyForm}"><jstl:out value="${supply.attributes.propertyForm}"/>&nbsp;</jstl:if><jstl:out value="${supply.attributes.name}"/></a>
     </th>
     <th align="left"><jstl:out value="${supply.attributes.parentName}"/></th>
     <th align="left"><jstl:out value="${supplyKinds_4[kind]}"/></th>
    </jstl:when>
    <jstl:otherwise>
     <td align="left">
      <a href="<%=request.getContextPath()%>/controller?action=supply.view&id=<jstl:out value="${supply.handle.id}"/>"><jstl:if test="${not empty supply.attributes.propertyForm}"><jstl:out value="${supply.attributes.propertyForm}"/>&nbsp;</jstl:if><jstl:out value="${supply.attributes.name}"/></a>
     </td>
     <td align="left"><jstl:out value="${supply.attributes.parentName}"/></td>
     <td align="left"><jstl:out value="${supplyKinds_4[kind]}"/></td>
    </jstl:otherwise>
   </jstl:choose>
   <td align="left"><jstl:out value="${supply.attributes.metro}"/></td>
   <td align="left"><a href="<%=request.getContextPath()%>/controller?action=supply.view&id=<jstl:out value="${supply.handle.id}"/>" accessKey="р"><img src="<%=request.getContextPath()%>/images/ico_id.gif" width="16" height="16" border="0" alt="Просмотр"></a></td>
  </tr>
 </logic:iterate>
</table>
<util:pageIterator dispatcherName="/controller?action=supply" iterationName="Search">
 <util:startText><table width="100%"><tr><td align="center"></util:startText>
 <util:endText></td></tr></table></util:endText>
</util:pageIterator>
