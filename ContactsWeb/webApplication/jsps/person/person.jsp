<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%@ taglib prefix="fmt" uri="jstl_fmt" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <jstl:choose>
   <jstl:when test="${person != null}">
    <title>Личность <jstl:out value="${person.attributes.lastName}"/> <jstl:out value="${person.attributes.firstName}"/> - База данных &quot;Контакты&quot;</title>
   </jstl:when>
   <jstl:otherwise>
    <title>Новая личность - База данных &quot;Контакты&quot;</title>
   </jstl:otherwise>
  </jstl:choose>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="JavaScript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript">
  <!--
      function removePerson() {
          var query="Вы уверены, что хотите удалить данные о человеке <jstl:out value="${person.attributes.lastName}"/> <jstl:out value="${person.attributes.firstName}"/>?";
          if (confirm(query)) {
              document.removeForm.submit();
          }
      }
      
      function onCalendar(form, field) {
          if (form == 'personForm' && field == 'birthdate') {
              var value = document.personForm.birthdate.value;
              document.personForm.birthday.value = value.substring(0, 5);
              document.personForm.birthyear.value = value.substring(6, 10);
          }
      }
      
      function openCalendarForBirthday() {
          var url = '<%=request.getContextPath()%>/jsps/calendar.jsp?form=personForm&field=birthdate'
                    + '&currentValue=' + document.personForm.birthdate.value;
          window.open(url, 'calendar', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=320,height=225');
          return void(0);
      }
  -->
  </script>
 </head>
 <body onLoad="setFocus('personForm', 'lastName')">
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <jstl:if test="${person!=null}">
   <i>Идентификатор человека в базе данных - <jstl:out value="${person.handle.id}"/> (Последние изменения от: <fmt:formatDate pattern="dd.MM.yyyy" value="${person.attributes.updateTime}"/>)</i>
   <jstl:if test="${not empty Sergey}">
    <form name="removeForm" method="POST" action="<%=request.getContextPath()%>/controller">
     <input type="hidden" name="action" value="person.remove">
     <input type="hidden" name="id" value="<jstl:out value="${person.handle.id}"/>">
    </form>
   </jstl:if>
  </jstl:if>
  <table cellspacing="1" cellpadding="3">
   <form name="personForm" action="<%=request.getContextPath()%>/controller" method="POST">
    <jstl:choose>
     <jstl:when test="${person!=null and not empty Sergey}">
      <input type="hidden" name="action" value="person.update">
      <input type="hidden" name="id" value="<jstl:out value="${person.handle.id}"/>">
     </jstl:when>
     <jstl:otherwise>
      <input type="hidden" name="action" value="person.create">
     </jstl:otherwise>
    </jstl:choose>
    <tr>
     <td align="right">* Фамилия</td>
     <td align="left"><input type="text" name="lastName" size="25" value="<jstl:out value="${person.attributes.lastName}" default="${personSearchParameters.lastName}"/>"></td>
     <td align="right">* Имя</td>
     <td align="left"><input type="text" name="firstName" size="25" value="<jstl:out value="${person.attributes.firstName}" default="${personSearchParameters.firstName}"/>"></td>
    </tr>
    <tr>
     <td align="right">Отчество</td>
     <td align="left"><input type="text" name="middleName" size="25" value="<jstl:out value="${person.attributes.middleName}"/>"></td>
     <td align="right">Пол</td>
     <td>
      <select name="gender" width="20">
       <jstl:set var="theGender"><jstl:out value="${person.attributes.gender}" default="${personSearchParameters.gender}"/></jstl:set>
       <logic:iterate name="inquire_genders_1" id="gender">
        <option value="<jstl:out value="${gender.id}"/>"<jstl:if test="${theGender == gender.id}"> selected</jstl:if>><jstl:out value="${gender.name}"/></option>
       </logic:iterate>
      </select>
     </td>
    </tr>
    <tr>    
     <td align="right">День рождения</td>
     <td align="left">
      <input type="text" name="birthday" class="day" size="5" maxLength="5" value="<fmt:formatDate value="${person.attributes.birthday}" pattern="dd.MM"/>"><input type="text" class="dot" size="1" maxLength="1" value="." readonly="" disabled=""><input type="text" name="birthyear" class="year" size="4" maxlength="4" value="<fmt:formatDate value="${person.attributes.birthYear}" pattern="yyyy"/>">
      <a href="javascript:openCalendarForBirthday()"><img src="<%=request.getContextPath()%>/images/ico_insert.gif" border="0" alt="^"></a>
      <input type="text" name="birthdate" class="hidden">
     </td>
     <td></td>
     <td></td>
    </tr>
    <tr>
     <td align="right">Адрес</td>
     <td align="left"><textarea" name="address" rows="5" cols="25" wordwrap="true"><jstl:out value="${person.attributes.address}" default="${personSearchParameters.address}"/></textarea></td>
     <jstl:if test="${not empty Sergey}">
      <td align="right">Доп. инфо</td>
      <td align="left"><textarea" name="note" rows="5" cols="25" wordwrap="true"><jstl:out value="${person.attributes.note}"/></textarea></td>
     </jstl:if>
    </tr>
    <tr>
     <td align="right">ICQ</td>
     <td align="left"><input type="text" name="icqUin" size="25" maxLength="25" value="<jstl:out value="${person.attributes.icq.icq}"/>"/></td>
     <td align="right">Nickname</td>
     <td align="left"><input type="text" name="icqNickname" size="25" value="<jstl:out value="${person.attributes.icq.nickname}" default="${personSearchParameters.icq}"/>"></td>
    </tr>
   </table>
   <jsp:include flush="true" page="/include/person/phones.jsp"/>
   <jsp:include flush="true" page="/include/person/emails.jsp"/>
   <jstl:if test="${not empty Sergey}">
    <table cellspacing="1" cellpadding="3">
     <jsp:include flush="true" page="/include/person/coworker.jsp"/>
     <jsp:include flush="true" page="/include/person/msu.jsp"/>
     <jsp:include flush="true" page="/include/person/shnip.jsp"/>
     <jsp:include flush="true" page="/include/person/friend.jsp"/>
     <jsp:include flush="true" page="/include/person/related.jsp"/>
    </table>
   </jstl:if>
   <table cellspacing="1" cellpadding="3">
    <tr>
     <td></td>
     <jstl:if test="${(person == null && not empty Editor) || not empty Sergey}">
      <td align="center"><button type="submit" onclick="document.personForm.submit(); return false">Сохранить</button></td>
      <td align="center"><button type="reset" onclick="document.personForm.reset(); return false">Восстановить</button></td>
     </jstl:if>
     <jstl:if test="${person != null && not empty Sergey}">
      <td><button type="button" onclick="removePerson()">Удалить</button></td>
     </jstl:if>
    </tr>
   </form>
  </table>
 </body>
</html>
