<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<tr>
 <td><input type="checkbox" name="group" value="related" <jstl:if test="${person.attributes.related}">checked</jstl:if>>Related</td>
</tr>
<tr>
 <td align="right">Relationship</td>
 <td align="left"><input type="text" size="25" name="related.relationship" value="<jstl:out value="${person.attributes.relatedInfo.relationship}"/>"></td>
 <td align="right">Note</td>
 <td align="left"><input type="text" name="related.description" size="25" value="<jstl:out value="${person.attributes.relatedInfo.description}"/>"></td>
</tr>
