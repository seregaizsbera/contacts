<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<%@ taglib prefix="fmt" uri="jstl_fmt" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Content-Style-Type" content="text/css">
  <meta http-equiv="Content-Scrip-Type" content="text/javascript">
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

  <link rel="stylesheet" type="text/css" media="all" href="<jstl:out value="${pageContext.request.contextPath}"/>/calendar/calendar.css" title="default" />
  <link rel="SHORTCUT ICON" href="<jstl:out value="${pageContext.request.contextPath}"/>/shortcut.ico"/>
  <script type="text/javascript" src="<jstl:out value="${pageContext.request.contextPath}"/>/calendar/calendar.js"></script>
  <script type="text/javascript" src="<jstl:out value="${pageContext.request.contextPath}"/>/calendar/lang/calendar-en.js"></script>
  <script type="text/javascript" src="<jstl:out value="${pageContext.request.contextPath}"/>/calendar/lang/calendar-ru.js" charset="UTF-8"></script>
  <script type="text/javascript" src="<jstl:out value="${pageContext.request.contextPath}"/>/calendar/calendar-setup.js"></script>

  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript"><!--
      <jstl:if test="${not empty Sergey}">
       function removePerson() {
           var query="Вы уверены, что хотите удалить данные о человеке \"<jstl:out value="${person.attributes.lastName}"/> <jstl:out value="${person.attributes.firstName}"/>\"?";
           if (confirm(query)) {
               document.removeForm.submit();
           }
       }
      </jstl:if>
      
      function toBirthDate() {
          document.personForm.birthdate.value = document.personForm.birthday.value + "." + document.personForm.birthyear.value;
      }
      
      function fromBirthDate() {
          var value = document.personForm.birthdate.value;
          document.personForm.birthday.value = value.substring(0, 5);
          document.personForm.birthyear.value = value.substring(6, 10);
      }
      
      function onLoad() {
          toBirthDate();
	  setFocus('personForm', 'lastName');
	  document.personForm.birthday.readOnly = true;
	  document.personForm.birthyear.readOnly = true;
      }
  --></script>
 </head>
 <body onLoad="onLoad()">
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p>
   <jstl:choose>
    <jstl:when test="${person != null}">
     Личность с идентификатором <jstl:out value="${person.handle.id}"/>
     (<i>Последние изменения от: <fmt:formatDate pattern="dd.MM.yyyy" value="${person.attributes.updateTime}"/></i>)
    </jstl:when>
    <jstl:otherwise>
     Новый человек
    </jstl:otherwise>
   </jstl:choose>
  </p>
  <jstl:if test="${person != null}">
   <jstl:if test="${not empty Sergey}">
    <form name="removeForm" method="post" action="<%=request.getContextPath()%>/controller">
     <input type="hidden" name="action" value="person.remove">
     <input type="hidden" name="id" value="<jstl:out value="${person.handle.id}"/>">
    </form>
   </jstl:if>
  </jstl:if>
  <form name="personForm" action="<%=request.getContextPath()%>/controller" method="post">
   <table align="center" border="1">
    <tr>
     <td>
      <table align="center">
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
        <td align="left"><input type="text" name="lastName" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.lastName}" default="${personSearchParameters.lastName}"/>"></td>
        <td align="right">* Имя</td>
        <td align="left"><input type="text" name="firstName" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.firstName}" default="${personSearchParameters.firstName}"/>"></td>
       </tr>
       <tr>
        <td align="right">Отчество</td>
        <td align="left"><input type="text" name="middleName" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.middleName}" default="${personSearchParameters.middleName}"/>"></td>
        <td align="right">Пол</td>
        <td>
         <select name="gender" class="wide_elem">
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
	 <jstl:if test="${not empty person.attributes.birthYear}"><jstl:set var="by"><fmt:formatDate value="${person.attributes.birthYear}" pattern="yyyy"/></jstl:set></jstl:if>
	 <jstl:if test="${not empty personSearchParameters.yearOfBirthday}"><jstl:set var="bys"><fmt:formatDate value="${personSearchParameters.yearOfBirthday}" pattern="yyyy"/></jstl:set></jstl:if>
         <input type="text" name="birthday" class="day" size="5" maxLength="5" value="<fmt:formatDate value="${person.attributes.birthday}" pattern="dd.MM"/>" onchange="toBirthDate()"><input type="text" class="dot" size="1" maxLength="1" value="." readOnly="yes" disabled="yes"><input type="text" name="birthyear" class="year" size="4" maxLength="4" value="<jstl:out value="${by}" default="${bys}"/>" onchange="toBirthDate()">
         <a id="birthdateImg" href="javascript:void(0)"><img src="<%=request.getContextPath()%>/images/ico_insert.gif" width="14" height="16" border="0" alt="^"></a>
         <input id="birthdateInput" type="text" name="birthdate" class="hidden" onchange="fromBirthDate()">
	 <script>
	  Calendar.setup({
              inputField: "birthdateInput",
              ifFormat: "%d.%m.%Y",
              button: "birthdateImg",
              firstDay: 1,
              weekNumbers: false,
              showOthers: true,
	      singleClick: true
	  });
	 </script>
        </td>
        <td></td>
        <td></td>
       </tr>
       <tr>
        <td align="right">Адрес</td>
        <td align="left"><textarea name="address" class="wide_elem" rows="5" cols="25" wordwrap="true"><jstl:out value="${person.attributes.address}" default="${personSearchParameters.address}"/></textarea></td>
        <jstl:if test="${not empty Sergey}">
         <td align="right">Доп. инфо</td>
         <td align="left"><textarea name="note" class="wide_elem" rows="5" cols="25" wordwrap="true"><jstl:out value="${person.attributes.note}" default="${personSearchParameters.note}"/></textarea></td>
        </jstl:if>
       </tr>
       <tr>
        <td align="right">ICQ</td>
        <td align="left"><input type="text" name="icqUin" class="wide_elem" size="25" maxLength="25" value="<jstl:out value="${person.attributes.icq.icq}"/>"/></td>
        <td align="right">Nickname</td>
        <td align="left"><input type="text" name="icqNickname" class="wide_elem" size="25" value="<jstl:out value="${person.attributes.icq.nickname}" default="${personSearchParameters.icq}"/>"></td>
       </tr>
       <tr>
        <td colSpan="2" align="left" valign="top">
         <jsp:include page="/include/person/phones.jsp" flush="true"/>
        </td>
        <td colSpan="2" align="right" valign="top">
         <jsp:include page="/include/person/emails.jsp" flush="true"/>
        </td>
       </tr>
       <jstl:if test="${not empty Sergey}">
        <jsp:include page="/include/person/coworker.jsp" flush="true"/>
        <jsp:include page="/include/person/msu.jsp" flush="true"/>
        <jsp:include page="/include/person/shnip.jsp" flush="true"/>
        <jsp:include page="/include/person/friend.jsp" flush="true"/>
        <jsp:include page="/include/person/related.jsp" flush="true"/>
       </jstl:if>
      </table>
      <table align="center">
       <tr>
        <jstl:if test="${(person == null && not empty Editor) || not empty Sergey}">
         <td align="center"><button type="submit">Сохранить</button></td>
         <td align="center"><button type="reset">Восстановить</button></td>
        </jstl:if>
        <jstl:if test="${person != null && not empty Sergey}">
         <td><button type="button" onClick="removePerson()">Удалить</button></td>
        </jstl:if>
        <td>
         <jstl:choose>
          <jstl:when test="${not empty backURL}">
           <a href="<jstl:out value="${backURL}"/>">
	  </jstl:when>
          <jstl:when test="${not empty personSearchParameters}">
           <a href="<%=request.getContextPath()%>/controller?action=person.pagePersons">
          </jstl:when>
	  <jstl:otherwise>
           <a href="<%=request.getContextPath()%>/controller?action=person">
	  </jstl:otherwise>
	 </jstl:choose>Вернуться</a>
        </td>
       </tr>
      </table>
     </td>
    </tr>
   </table>
  </form>
 </body>
</html>
