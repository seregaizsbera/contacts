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
  <title>Поиск личности - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript"><!--
      function clearSearchForm(form) {
          form.address.value = "";
	  form.birthyear.value="";
          form.email.value = "";
          form.firstName.value = "";
          form.gender.selectedIndex = 0;
          <jstl:if test="${not empty Sergey}">
           form.groupMode.selectedIndex = 0;
           form.note.value = "";
          </jstl:if>
          form.icq.value = "";
          form.lastName.value = "";
	  form.middleName.value = "";
          form.monthOfBirthday.selectedIndex = 0;
          form.phone.value = "";
      }
      
      function toBirthDate() {
          document.searchForm.birthdate.value = '01.01.' + document.searchForm.birthyear.value;
      }
      
      function fromBirthDate() {
          document.searchForm.birthyear.value = document.searchForm.birthdate.value.substring(6, 10);
      }
      
      function onCalendar(form, field) {
          if (form == 'searchForm' && field == 'birthdate') {
              fromBirthDate();
          }
      }
      
      function openCalendarForBirthYear() {
          toBirthDate();
          var url = '<%=request.getContextPath()%>/jsps/calendar.jsp?form=searchForm&field=birthdate'
                    + '&currentValue=' + document.searchForm.birthdate.value;
          window.open(url, 'calendar', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=320,height=225');
      }
  --></script>
 </head>
 <body onLoad="setFocus('searchForm', 'lastName')">
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p align="left">Поиск личности</p>
  <jstl:if test="${personSearchParameters != null}">
   <table cellSpacing="0" cellPadding="3" align="right">
    <tr>
     <jstl:if test="${not empty persons && not empty Sergey}">
      <td>
       <a href="<%=request.getContextPath()%>/controller?action=report.pagePersons" target="_blank">Отчет</a>
      </td>
     </jstl:if>
     <jstl:if test="${empty persons && (not empty Sergey or not empty Editor)}">
      <td>
       <a href="<%=request.getContextPath()%>/controller?action=person.view" accessKey="д">Создать</a>
      </td>
     </jstl:if>
    </tr>
   </table>
  </jstl:if>  
  <util:searchResults page="/include/person/search_results.jsp"
                      collection="persons"
                      notFoundPage="/include/not_found.jsp"/>
		      
  <form name="searchForm" method="GET" action="<%=request.getContextPath()%>/controller">
   <input type="hidden" name="action" value="person.search">
   <table width="100%" cellSpacing="0" cellPadding="3">
    <tr>
     <th colspan="6">Параметры поиска</th>
    </tr>
    <tr>
     <td align="right">Фамилия:</td>
     <td>
      <input type="text" class="elem" name="lastName" size="20" value="<jstl:out value="${personSearchParameters.lastName}"/>" tabIndex="11">
     </td>
     <td align="right">Электронная почта</td>
     <td>
      <input name="email" class="elem" size="20" value="<jstl:out value="${personSearchParameters.email}"/>" tabIndex="15">
     </td>
     <td align="right">Пол</td>
     <td>
      <select name="gender" class="elem" tabIndex="19">
       <option value="">Не имеет значения</option>
       <logic:iterate name="inquire_genders_1" id="gender">
        <option value="<jstl:out value="${gender.id}"/>"<jstl:if test="${personSearchParameters.gender == gender.id}"> selected</jstl:if>><jstl:out value="${gender.name}"/></option>
       </logic:iterate>
      </select>
     </td>
    </tr>
    <tr>
     <td align="right">Имя</td>
     <td>
      <input type="text" class="elem" name="firstName" size="20" value="<jstl:out value="${personSearchParameters.firstName}"/>" tabIndex="12">
     </td>
     <td align="right">Адрес</td>
     <td>
      <input name="address" class="elem" size="20" value="<jstl:out value="${personSearchParameters.address}"/>" tabIndex="16">
     </td>
     <td align="right">Месяц рождения</td>
     <td>
      <select name="monthOfBirthday" class="elem" tabIndex="20">
       <option value="-1">------- ------------ -------</option>
       <logic:iterate name="inquire_months_1" id="month" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
        <option value="<jstl:out value="${month.id}"/>"<jstl:if test="${personSearchParameters.monthOfBirthday == month.id}"> selected</jstl:if>><jstl:out value="${month.name}"/></option>
       </logic:iterate>
      </select>
     </td>
    </tr>
    <tr>
     <td align="right">Отчество</td>
     <td>
      <input type="text" class="elem" name="middleName" size="20" value="<jstl:out value="${personSearchParameters.middleName}"/>" tabIndex="13">
     </td>
     <td align="right">ICQ</td>
     <td>
      <input name="icq" class="elem" size="20" value="<jstl:out value="${personSearchParameters.icq}"/>" tabIndex="17">
     </td>
     <td align="right">Год рождения</td>
     <td>
      <input name="birthyear" size="4" maxLength="4" class="only_year" value="<fmt:formatDate pattern="yyyy" value="${personSearchParameters.yearOfBirthday}"/>" tabIndex="21">
      <a href="javascript:void(0)" onClick="openCalendarForBirthYear()"><img src="<%=request.getContextPath()%>/images/ico_insert.gif" width="14" height="16" border="0" alt="^"></a>
      <input type="text" name="birthdate" class="hidden">
     </td>
    </tr>
    <tr>     
     <td align="right">Телефон</td>
     <td>
      <input type="text" class="elem" name="phone" size="20" value="<jstl:out value="${personSearchParameters.phone}"/>" tabIndex="14">
     </td>
     <td align="right">
      <jstl:if test="${not empty Sergey}">
       Примечание
      </jstl:if>
     </td>
     <td>
      <jstl:if test="${not empty Sergey}">
       <input type="text" class="elem" name="note" size="20" value="<jstl:out value="${personSearchParameters.note}"/>" tabIndex="18">
      </jstl:if>
     </td>
     <td align="right">
      <jstl:if test="${not empty Sergey}">
       Искать в группе
      </jstl:if>
     </td>
     <td>
      <jstl:if test="${not empty Sergey}">
       <select name="groupMode" class="elem" tabIndex="22">
        <logic:iterate name="inquire_psgm_1" id="mode">
         <option value="<jstl:out value="${mode.id}"/>"<jstl:if test="${personSearchParameters.groupMode == mode.id}"> selected</jstl:if>><jstl:out value="${mode.name}"/></option>
        </logic:iterate>
       </select>
      </jstl:if>
     </td>
    </tr>
    <tr>
    <tr>
     <td colspan="6">
      <table cellSpacing="0" cellPadding="3" align="center">
       <tr>
        <td><button type="submit" tabIndex="23">Найти</button></td>
	<td><button type="button" onClick="clearSearchForm(document.searchForm)" tabIndex="24">Очистить</button></td>
       </tr>
      </table>
     </td>
    </tr>        
   </table>
  </form>
 </body>
</html>
