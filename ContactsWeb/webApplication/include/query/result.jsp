<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<jstl:if test="${queryResult != null}">
 <h4>Результат запроса</h4>
 <table width="100%" cellSpacing="0" cellPadding="5" border="1">
  <tr align="center">
   <jstl:set var="metaData" value="${queryResult.metaData.columnNames}"/>
   <logic:iterate name="metaData" id="columnName">
    <th><jstl:out value="${columnName}"/></th>
   </logic:iterate>
  </tr>
  <jstl:set var="records" value="${queryResult.records}"/>
  <logic:iterate name="records" id="record" type="su.sergey.contacts.query.valueobjects.QueryRecord">
   <tr align="left">
    <logic:iterate name="metaData" id="columnName" indexId="i">
     <td><jstl:if test="${record.values[i] != null}"><jstl:out value="${record.values[i]}"/></jstl:if></td>
    </logic:iterate>
   </tr>
  </logic:iterate>
 </table>
 <p></p>
</jstl:if>
