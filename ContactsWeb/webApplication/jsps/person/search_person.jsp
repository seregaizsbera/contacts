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
 </head>
 <body text="#0A0A0A" bgColor="#FFF5EE" link="#F50A0A" vlink="#F50AF5" alink="#0A0AF5">
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <p align="left">Поиск личности</p>
  <p align="right"><form name="addPersonForm" method="GET" action="<%=request.getContextPath()%>/controller"><input type="hidden" name="action" value="person.view"><button type="submit">Add...</button></form></p>
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
       <option value="1"  <jstl:if test="${searchParameters.monthOfBirthday == 1}">selected</jstl:if>>Январь</option>
       <option value="2"  <jstl:if test="${searchParameters.monthOfBirthday == 2}">selected</jstl:if>>Февраль</option>
       <option value="3"  <jstl:if test="${searchParameters.monthOfBirthday == 3}">selected</jstl:if>>Март</option>
       <option value="4"  <jstl:if test="${searchParameters.monthOfBirthday == 4}">selected</jstl:if>>Апрель</option>
       <option value="5"  <jstl:if test="${searchParameters.monthOfBirthday == 5}">selected</jstl:if>>Май</option>
       <option value="6"  <jstl:if test="${searchParameters.monthOfBirthday == 6}">selected</jstl:if>>Июнь</option>
       <option value="7"  <jstl:if test="${searchParameters.monthOfBirthday == 7}">selected</jstl:if>>Июль</option>
       <option value="8"  <jstl:if test="${searchParameters.monthOfBirthday == 8}">selected</jstl:if>>Август</option>
       <option value="9"  <jstl:if test="${searchParameters.monthOfBirthday == 9}">selected</jstl:if>>Сентябрь</option>
       <option value="10" <jstl:if test="${searchParameters.monthOfBirthday == 10}">selected</jstl:if>>Октябрь</option>
       <option value="11" <jstl:if test="${searchParameters.monthOfBirthday == 11}">selected</jstl:if>>Ноябрь</option>
       <option value="12" <jstl:if test="${searchParameters.monthOfBirthday == 12}">selected</jstl:if>>Декабрь</option>
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
    <tr align="center">
     <td colspan="6">
      <input type="submit" value="Найти"/>
     </td>
    </tr>        
   </table>
  </form>      
 </body>
</html>
