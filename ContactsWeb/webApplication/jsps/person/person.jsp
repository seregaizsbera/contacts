<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <jstl:choose>
   <jstl:when test="${person != null}">
    <title>Person &quot;Контакты&quot;</title>
   </jstl:when>
   <jstl:otherwise>
    <title>New person &quot;Контакты&quot;</title>
   </jstl:otherwise>
  </jstl:choose>
 </head>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5">
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <jstl:if test="${person != null}">
   <i>There is such a person with id=<jstl:out value="${person.handle.id}"/></i>
   <form name="removeForm" method="POST" action="<%=request.getContextPath()%>/controller">
    <input type="hidden" name="action" value="person.remove">
    <input type="hidden" name="id" value="<jstl:out value="${person.handle.id}"/>">
    <button type="submit">Remove</button>
   </form>
  </jstl:if>
  <table border="0" cellspacing="1" cellpadding="3">
   <form name="personForm" action="<%=request.getContextPath()%>/controller" method="POST">
    <jstl:choose>
     <jstl:when test="${person != null}">
      <input type="hidden" name="action" value="person.update">
      <input type="hidden" name="id" value="<jstl:out value="${person.handle.id}"/>">
     </jstl:when>
     <jstl:otherwise>
      <input type="hidden" name="action" value="person.create">
     </jstl:otherwise>
    <jstl:choose>
    <tr>
     <td align="right">Last name</td>
     <td align="left"><input type="text" name="lastName" size="25" value="<jstl:out value="${person.attributes.lastName}"/>"></td>
     <td align="right">First name</td>
     <td align="left"><input type="text" name="firstName" size="25" value="<jstl:out value="${person.attributes.firstName}"/>"></td>
    </tr>
    <tr>
     <td align="right">Middle name</td>
     <td align="left"><input type="text" name="middleName" size="25" value="<jstl:out value="${person.attributes.middleName}"/>"></td>
     <td align="right">Birthday</td>
     <td align="left"><input type="text" name="birthday" size="10" maxLength="10" value="<jstl:out value="${person.attributes.birthdayStr}"/>"></td>
    </tr>
    <tr>
     <td align="right">Address</td>
     <td align="left"><textarea" name="address" rows="5" cols="25" wordwrap="true"><jstl:out value="${person.attributes.address}"/></textarea></td>
     <td align="right">Note</td>
     <td align="left"><textarea" name="note" rows="5" cols="25" wordwrap="true"><jstl:out value="${person.attributes.note}"/></textarea></td>
    </tr>
    <tr>
     <td align="right">ICQ</td>
     <td align="left"><input type="text" name="icqUin" size="25" maxLength="25" value="<jstl:out value="${person.attributes.icq.icq}"/>"/></td>
     <td align="right">Nickname</td>
     <td align="left"><input type="text" name="icqNickname" size="25" value="<jstl:out value="${person.attributes.icq.nickname}"/>"></td>
    </tr>
    <jsp:include flush="true" page="/include/person/emails.jsp"/>
    <jsp:include flush="true" page="/include/person/phones.jsp"/>
    <jsp:include flush="true" page="/include/person/coworker.jsp"/>
    <jsp:include flush="true" page="/include/person/msu.jsp"/>
    <jsp:include flush="true" page="/include/person/shnip.jsp"/>
    <jsp:include flush="true" page="/include/person/friend.jsp"/>
    <jsp:include flush="true" page="/include/person/related.jsp"/>
    <tr>
     <td></td>
     <td align="center"><input type="submit" value="submit"></td>
     <td align="center"><input type="reset" value="reset"></td>
     <td></td>
    </tr>
   </form>
  </table>
 </body>
</html>
