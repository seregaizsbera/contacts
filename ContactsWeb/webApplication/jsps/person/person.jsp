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
   <jstl:when test="${person!=null}">
    <title>Личность <jstl:out value="${person.attributes.lastName}"/> <jstl:out value="${person.attributes.firstName}"/> - База данных &quot;Контакты&quot;</title>
   </jstl:when>
   <jstl:otherwise>
    <title>Новая личность - База данных &quot;Контакты&quot;</title>
   </jstl:otherwise>
  </jstl:choose>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript">
  <!--
      function removePerson() {
          var query="Вы уверены, что хотите удалить данные о человеке <jstl:out value="${person.attributes.lastName}"/> <jstl:out value="${person.attributes.firstName}"/>?";
          if (confirm(query)) {
              document.removeForm.submit();
          }
      }
  -->
  </script>
 </head>
 <body>
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <jstl:if test="${person!=null}">
   <i>Идентификатор человека в базе данных - <jstl:out value="${person.handle.id}"/></i>
   <form name="removeForm" method="POST" action="<%=request.getContextPath()%>/controller">
    <input type="hidden" name="action" value="person.remove">
    <input type="hidden" name="id" value="<jstl:out value="${person.handle.id}"/>">
   </form>
  </jstl:if>
  <table cellspacing="1" cellpadding="3">
   <form name="personForm" action="<%=request.getContextPath()%>/controller" method="POST">
    <jstl:choose>
     <jstl:when test="${person!=null}">
      <input type="hidden" name="action" value="person.update">
      <input type="hidden" name="id" value="<jstl:out value="${person.handle.id}"/>">
     </jstl:when>
     <jstl:otherwise>
      <input type="hidden" name="action" value="person.create">
     </jstl:otherwise>
    <jstl:choose>
    <tr>
     <td align="right">* Фамилия</td>
     <td align="left"><input type="text" name="lastName" size="25" value="<jstl:out value="${person.attributes.lastName}"/>"></td>
     <td align="right">* Имя</td>
     <td align="left"><input type="text" name="firstName" size="25" value="<jstl:out value="${person.attributes.firstName}"/>"></td>
    </tr>
    <tr>
     <td align="right">Отчество</td>
     <td align="left"><input type="text" name="middleName" size="25" value="<jstl:out value="${person.attributes.middleName}"/>"></td>
     <td align="right">День рождения</td>
     <td align="left"><input type="text" name="birthday" size="10" maxLength="10" value="<jstl:out value="${person.attributes.birthdayStr}"/>"></td>
    </tr>
    <tr>
     <td align="right">Адрес</td>
     <td align="left"><textarea" name="address" rows="5" cols="25" wordwrap="true"><jstl:out value="${person.attributes.address}"/></textarea></td>
     <td align="right">Доп. инфо</td>
     <td align="left"><textarea" name="note" rows="5" cols="25" wordwrap="true"><jstl:out value="${person.attributes.note}"/></textarea></td>
    </tr>
    <tr>
     <td align="right">ICQ</td>
     <td align="left"><input type="text" name="icqUin" size="25" maxLength="25" value="<jstl:out value="${person.attributes.icq.icq}"/>"/></td>
     <td align="right">Nickname</td>
     <td align="left"><input type="text" name="icqNickname" size="25" value="<jstl:out value="${person.attributes.icq.nickname}"/>"></td>
    </tr>
   </table>
   <jsp:include flush="true" page="/include/person/phones.jsp"/>
   <jsp:include flush="true" page="/include/person/emails.jsp"/>
   <table cellspacing="1" cellpadding="3">
    <jsp:include flush="true" page="/include/person/coworker.jsp"/>
    <jsp:include flush="true" page="/include/person/msu.jsp"/>
    <jsp:include flush="true" page="/include/person/shnip.jsp"/>
    <jsp:include flush="true" page="/include/person/friend.jsp"/>
    <jsp:include flush="true" page="/include/person/related.jsp"/>
   </table>
   <table cellspacing="1" cellpadding="3">
    <tr>
     <td></td>
     <td align="center"><button type="button" onclick="document.personForm.submit()">Сохранить</button></td>
     <td align="center"><button type="button" onclick="document.personFrom.reset()">Восстановить</button></td>
     <td><jstl:if test="${person!=null}"><button type="button" onclick="removePerson()">Удалить</button></jstl:if></td>
    </tr>
   </form>
  </table>
 </body>
</html>
