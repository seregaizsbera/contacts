<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
<tr>
 <td><input type="checkbox" name="group" value="shnip" <jstl:if test="${person.attributes.shnip}">checked</jstl:if>>Shnip</td>
</tr>
<tr>
 <td align="right">Graduate year</td>
 <td align="left"><input type="text" name="shnip.graduateDate" size="8" maxLength="4" value="<jstl:out value="${person.attributes.shnipInfo.graduateDateStr}"/>"></td>
 <td align="right">Form letter</td>
 <td align="left"><input type="text" name="shnip.formLetter" size="8" maxLength="1" value="<jstl:out value="${person.attributes.shnipInfo.formLetter}"/>"/></td>
</tr>
 <td align="right">Note</td>
 <td align="left"><input type="text" name="shnip.description" size="25" value="<jstl:out value="${person.attributes.shnipInfo.description}"/>"></td>
 <td align="right">Form leader id</td>
 <td align="left"><input type="text" name="shnip.formLeaderId" maxLenght="10" size="25" value="<jstl:out value="${person.attributes.shnipInfo.formLeader.id}"/>"></td>
</tr>
