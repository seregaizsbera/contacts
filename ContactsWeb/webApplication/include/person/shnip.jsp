<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%@ taglib prefix="fmt" uri="jstl_fmt" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<tr>
 <td align="left" colspan="4"><input type="checkbox" name="group" value="shnip" <jstl:if test="${person.attributes.shnip || (person == null && personSearchParameters.groupMode == 3)}">checked</jstl:if>>ШНИП</td>
</tr>
<tr>
 <td align="right">Год выпуска</td>
 <td align="left"><input type="text" name="shnip.graduateDate" size="5" maxLength="4" value="<fmt:formatDate value="${person.attributes.shnipInfo.graduateDate}" pattern="yyyy"/>">&nbsp;&nbsp;Преподаватель<input type="checkbox" name="shnip.tutor" value="true"<jstl:if test="${person.attributes.shnipInfo.tutor}"> checked</jstl:if>></td>
 <td align="right">Буква класса</td>
 <td align="left"><input type="text" name="shnip.formLetter" size="8" maxLength="1" value="<jstl:out value="${person.attributes.shnipInfo.formLetter}"/>"/></td>
</tr>
<tr>
 <td align="right">Доп. инфо</td>
 <td align="left"><input type="text" name="shnip.description" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.shnipInfo.description}"/>"></td>
 <td align="right">Клас. рук.</td>
 <td align="left">
  <select name="shnip.formLeaderId" class="wide_elem">
   <option value="">------- ------------ -------</option>
   <logic:iterate name="inquire_shnippers_2" id="formLeader" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
    <jstl:if test="${person.handle.id != formLeader.id}">
     <option value="<jstl:out value="${formLeader.id}"/>" <jstl:if test="${person.attributes.shnipInfo.formLeader.id == formLeader.id}">selected</jstl:if>><jstl:out value="${formLeader.name}"/></option>
    </jstl:if>
   </logic:iterate>
  </select>
 </td>
</tr>
