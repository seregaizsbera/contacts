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
  <meta http-equiv="expires" content="0">
  <title>Поиск личности - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
 </head>
 <body>
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <p align="left">Поиск личности</p>
  <p align="right"><form name="addPersonForm" method="GET" action="<%=request.getContextPath()%>/controller"><input type="hidden" name="action" value="person.view"><button type="submit">Создать</button></form></p>
  <util:message/>
  <util:searchResults page="/include/person/person_search_results.jsp"
                      collection="persons"
                      notFoundPage="/include/person/person_not_found.jsp"/>
  <form name="searchForm" method="GET" action="<%=request.getContextPath()%>/controller">
   <input type="hidden" name="action" value="person.search">
   <table width="100%" border="0" cellspacing="1" cellpadding="3">
    <tr align="center">
     <th colspan="6">Параметры поиска</th>
    </tr>
    <tr>
     <td width="17%" align="right">Фамилия:</td>
     <td width="16%">
      <input type="text" name="lastName" size="20" value="<jstl:out value="${searchParameters.lastName}"/>">
     </td>
     <td width="17%" align="right" colspan="1">Имя</td>
     <td width="16%">
      <input type="text" name="firstName" size="20" value="<jstl:out value="${searchParameters.firstName}"/>">
     </td>
     <td width="17%" align="right" colspan="1">Телефон</td>
     <td width="16%">
      <input type="text" name="phone" size="20" value="<jstl:out value="${searchParameters.phone}"/>">
     </td>
    </tr>
    <tr>
     <td width="17%" align="right">День рождения после</td>
     <td width="16%">
      <input type="text" name="beforeBirthday" size="20" maxLength="10" value="<util:formatDate value="${searchParameters.beforeBirthday}"/>">
     </td>
     <td width="17%" align="right">до</td>
     <td width="16%">
      <input type="text" name="afterBirthday" size="20" maxLength="10" value="<util:formatDate value="${searchParameters.afterBirthday}"/>">
     </td>
     <td width="17%" align="right">Месяц рождения</td>
     <td width="16%">
      <select name="monthOfBirthday" width="20">
       <option value="0"  <jstl:if test="${searchParameters.monthOfBirthday == 0}">selected</jstl:if>>------- ------------ -------</option>
       <logic:iterate name="inquire_months_1" id="month" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
        <option value="<jstl:out value="${month.id}"/>"<jstl:if test="${searchParameters.monthOfBirthday == month.id}"> selected</jstl:if>><jstl:out value="${month.name}"/></option>
       </logic:iterate>
      </select>
     </td>
    </tr>
    <tr>
     <td width="17%" align="right">Электронная почта</td>
     <td width="16%">
      <input name="email" size="20" value="<jstl:out value="${searchParameters.email}"/>">
     </td>
     <td width="17%" align="right">ICQ</td>
     <td width="16%">
      <input name="icq" size="20" value="<jstl:out value="${searchParameters.icq}"/>">
     </td>
     <td width="17%" align="right">Адрес</td>
     <td width="16%">
      <input name="address" size="20" value="<jstl:out value="${searchParameters.address}"/>">
     </td>
    </tr>
    <tr>
     <td align="right">Пол</td>
     <td>
      <select name="gender" width="20">
       <option value="">Не имеет значения</option>
       <logic:iterate name="inquire_genders_1" id="gender">
        <option value="<jstl:out value="${gender.id}"/>"<jstl:if test="${searchParameters.gender == gender.id}"> selected</jstl:if>><jstl:out value="${gender.name}"/></option>
       </logic:iterate>
      </select>
     </td>
     <td align="right">Искать в группе</td>
     <td>
      <select name="groupMode" width="20">
       <logic:iterate name="inquire_psgm_1" id="mode">
        <option value="<jstl:out value="${mode.id}"/>"<jstl:if test="${searchParameters.groupMode == mode.id}"> selected</jstl:if>><jstl:out value="${mode.name}"/></option>
       </logic:iterate>
      </select>
     </td>
    </tr>
    <tr align="center">
     <td colspan="6">
      <input type="submit" value="Найти"/>
     </td>
    </tr>        
   </table>
  </form>      
 </body>
</html>
