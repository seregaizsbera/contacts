<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%@ taglib prefix="fmt" uri="jstl_fmt" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<tr>
 <td align="left" colspan="4"><input type="checkbox" name="group" value="msu" <jstl:if test="${person.attributes.msu}">checked</jstl:if>>МГУ</td>
</tr>
<tr>
 <td align="right">Год выпуска</td>
 <td align="left"><input type="text" name="msu.graduateDate" size="5" maxLength="4" value="<fmt:formatDate value="${person.attributes.msuInfo.graduateDate}" pattern="yyyy"/>"></td>
 <td align="right">* Факультет</td>
 <td align="left">
  <select name="msu.departmentId" class="wide_elem">
   <logic:iterate name="inquire_msu_departments_2" id="msuDepartment" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
    <option value="<jstl:out value="${msuDepartment.id}"/>" <jstl:if test="${person.attributes.msuInfo.departmentId == msuDepartment.id}">selected</jstl:if>><jstl:out value="${msuDepartment.name}"/></option>
   </logic:iterate>
  </select>
 </td>
</tr>
<tr>
 <td align="right">Кафедра</td>
 <td align="left"><input type="text" name="msu.subfaculty" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.msuInfo.subfaculty}"/>"></td>
 <td align="right">Общежитие</td>
 <td align="left"><input type="checkbox" name="msu.hospice" value="true" <jstl:if test="${person.attributes.msuInfo.hospice}">checked</jstl:if>></td>
</tr>
<tr>
 <td align="right">Доп. инфо</td>
 <td align="left"><input type="text" name="msu.description" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.msuInfo.description}"/>"></td>
 <td>Преподаватель</td>
 <td align="left"><input type="checkbox" name="msu.tutor" value="true" <jstl:if test="${person.attributes.msuInfo.tutor}">checked</jstl:if>></td>
</tr>
