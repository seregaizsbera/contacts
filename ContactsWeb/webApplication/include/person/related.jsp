<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<tr>
 <td align="left" colSpan="4"><input type="checkbox" name="group" value="related" <jstl:if test="${person.attributes.related || (person == null && personSearchParameters.groupMode == 5)}">checked</jstl:if>>Родственник</td>
</tr>
<tr>
 <td align="right">* Родство</td>
 <td align="left"><input type="text" name="related.relationship" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.relatedInfo.relationship}"/>"></td>
 <td align="right">Доп. инфо</td>
 <td align="left"><input type="text" name="related.description" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.relatedInfo.description}"/>"></td>
</tr>
