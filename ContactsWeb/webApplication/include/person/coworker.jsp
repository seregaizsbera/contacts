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
 <td align="left"><input type="text" name="coworker.job" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.coworkerInfo.job}"/>"></td>
 <td align="right">Управление</td>
 <td align="left"><input type="text" name="coworker.administration" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.coworkerInfo.administration}"/>"></td>
</tr>
<tr>
 <td align="right">Отдел</td>
 <td align="left"><input type="text" name="coworker.department" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.coworkerInfo.department}"/>"></td>
 <td align="right">Должность</td>
 <td align="left"><input type="text" name="coworker.post" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.coworkerInfo.post}"/>"></td>
</tr>
<tr>
 <td align="right">Доп. инфо</td>
 <td align="left"><input type="text" name="coworker.description" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.coworkerInfo.description}"/>"></td>
 <td colspan="2"></td>
</tr>
