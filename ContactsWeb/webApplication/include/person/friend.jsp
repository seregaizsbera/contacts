<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<tr>
 <td align="left" colspan="4"><input type="checkbox" name="group" value="friend" <jstl:if test="${person.attributes.friend}">checked</jstl:if>>В числе друзей</td>
</tr>
<tr>
 <td align="right">Доп. инфо</td>
 <td align="left"><input type="text" name="friend.description" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.friendInfo.description}"/>"></td>
 <td></td>
 <td></td>
</tr>
