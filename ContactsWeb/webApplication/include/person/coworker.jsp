<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<tr>
 <td align="left" colspan="4"><input type="checkbox" name="group" value="coworker" <jstl:if test="${person.attributes.coworker}">checked</jstl:if>>Сослуживец</td>
</tr>
<tr>
 <td align="right">* Место работы</td>
 <td align="left"><input type="text" name="coworker.job" size="25" value="<jstl:out value="${person.attributes.coworkerInfo.job}"/>"></td>
 <td align="right">Доп. инфо</td>
 <td align="left"><input type="text" name="coworker.description" size="25" value="<jstl:out value="${person.attributes.coworkerInfo.description}"/>"></td>
</tr>
