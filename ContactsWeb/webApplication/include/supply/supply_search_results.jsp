<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<table width="100%" border="0" cellspacing="1" cellpadding="3">
 <util:pageIterator dispatcherName="/controller?action=supply"
                    iterationName="Search"
                    startText="<tr align='center'><td colspan='5'>"
                    endText="</td></tr>"/>
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
      <jstl:if test="${not empty supply.attributes.propertyForm}">
       <jstl:out value="${supply.attributes.propertyForm}"/>
       &nbsp;
      </jstl:if>
      <jstl:out value="${supply.attributes.name}"/>
     </th>
     <th align="left"><jstl:out value="${supply.attributes.parentName}"/></th>
     <th align="left"><jstl:out value="${supplyKinds_4[kind]}"/></th>
    </jstl:when>
    <jstl:otherwise>
     <td align="left">
      <jstl:if test="${not empty supply.attributes.propertyForm}"><jstl:out value="${supply.attributes.propertyForm}"/>&nbsp;</jstl:if><jstl:out value="${supply.attributes.name}"/>
     </td>
     <td align="left"><jstl:out value="${supply.attributes.parentName}"/></td>
     <td align="left"><jstl:out value="${supplyKinds_4[kind]}"/></td>
    </jstl:otherwise>
   </jstl:choose>
   <td align="left"><jstl:out value="${supply.attributes.metro}"/></td>
   <td align="center"><a href="<%=request.getContextPath()%>/controller?action=supply.view&id=<jstl:out value="${supply.handle.id}"/>" accesskey="р">Просмотр</a></td>
  </tr>
 </logic:iterate>
 <util:pageIterator dispatcherName="/controller?action=supply"
                    iterationName="Search"
                    startText="<tr align='center'><td colspan='5'>"
                    endText="</td></tr>"/>
</table>
