<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<tr>
 <td><input type="checkbox" name="group" value="msu" <jstl:if test="${person.attributes.msu}">checked</jstl:if>>MSU</td>
</tr>
<tr>
 <td align="right">Graduate year</td>
 <td align="left"><input type="text" name="msu.graduateDate" size="8" maxLength="4" value="<jstl:out value="${person.attributes.msuInfo.graduateDateStr}"/>"></td>
 <td align="right">Department</td>
 <td align="left">
  <select name="msu.departmentId">
   <option value="">---------------</option>
   <logic:iterate name="su.sergey.contacts.inquiry.msu_departments" id="msuDepartment" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
    <option value="<jstl:out value="${msuDepartment.id}"/>" <jstl:if test="${person.attributes.msuInfo.departmentId == msuDepartment.id}">selected</jstl:if>><jstl:out value="${msuDepartment.name}"/></option>
   </logic:iterate>
  </select>
 </td>
</tr>
<tr>
 <td align="right">Subfaculty</td>
 <td align="left"><input type="text" size="25" name="msu.subfaculty" value="<jstl:out value="${person.attributes.msuInfo.subfaculty}"/>"></td>
 <td align="right">Hospice</td>
 <td align="left"><input type="checkbox" name="msu.hospice" value="true" <jstl:if test="${person.attributes.msuInfo.hospice}">checked</jstl:if>></td>
</tr>
