<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<jstl:if test="${queryResult != null}">
 <table border="1" cellspacing="0" cellpadding="5" width="100%">
  <caption>Результат запроса</caption>
  <tr align="center">
   <jstl:set var="metaData" value="${queryResult.metaData.columnNames}"/>
   <logic:iterate name="metaData" id="columnName" type="java.lang.String">
    <th>
     <jstl:out value="${columnName}"/>
    </th>
   </logic:iterate>
  </tr>
  <jstl:set var="records" value="${queryResult.records}"/>
  <logic:iterate name="records" id="record" type="su.sergey.contacts.query.valueobjects.QueryRecord">
   <tr align="left">
    <logic:iterate name="metaData" id="columnName" type="java.lang.String" indexId="i">
     <td>
      <jstl:out value="${record.values[i]}"/>
     </td>
    </logic:iterate>
   </tr>
  </logic:iterate>
 </table>
</jstl:if>
