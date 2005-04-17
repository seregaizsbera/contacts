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
  <title>Поиск организации - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript"><!--
      function clearSearchForm(form) {
          form.address.value = "";
          form.email.value = "";
          form.importantOnly.checked = false;
          form.inn.value = "";
          form.kpp.value = "";
          form.ogrn.value = "";
          form.kind.selectedIndex = 0;
          form.metro.value = "";
          form.name.value = "";
          <jstl:if test="${not empty Sergey}">
           form.note.value = "";
          </jstl:if>
          form.parentName.value = "";
          form.phone.value = "";
          form.propertyForm.selectedIndex = 0;
          form.shortName.value = "";
          form.url.value = "";
      }
  --></script>
 </head>
 <body onLoad="setFocus('searchForm', 'name')">
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p align="left">Поиск организации</p>
  <jstl:if test="${supplySearchParameters != null}">
   <table align="right">
    <tr>
     <jstl:if test="${not empty supplies && not empty Sergey}">
      <td><a href="<%=request.getContextPath()%>/controller?action=report.pageSupplies" target="_blank">Отчет</a></td>
     </jstl:if>
     <jstl:if test="${empty supplies && (not empty Sergey || not empty Editor)}">
      <td><a href="<%=request.getContextPath()%>/controller?action=supply.view" accessKey="д">Создать</a></td>
     </jstl:if>
    </tr>
   </table>
  </jstl:if>
  <jstl:choose>
   <jstl:when test="${not empty supplies}">
    <jsp:include page="/include/supply/search_results.jsp" flush="true"/>
   </jstl:when>
   <jstl:otherwise>
    <jsp:include page="/include/not_found.jsp" flush="true"/>
   </jstl:otherwise>
  </jstl:choose>
  <form name="searchForm" method="GET" action="<%=request.getContextPath()%>/controller">
   <input type="hidden" name="action" value="supply.search">
   <table width="100%">
    <tr>
     <th colSpan="6">Параметры поиска</th>
    </tr>
    <tr>
     <td align="right">Название</td>
     <td>
      <input type="text" class="elem" name="name" size="20" value="<jstl:out value="${supplySearchParameters.name}"/>" tabIndex="10">
     </td>     
     <td align="right">Телефон</td>
     <td>
      <input type="text" class="elem" name="phone" size="20" value="<jstl:out value="${supplySearchParameters.phone}"/>" tabIndex="20">
     </td>
     <td align="right">Электронная почта</td>
     <td>
      <input name="email" class="elem" size="20" value="<jstl:out value="${supplySearchParameters.email}"/>" tabIndex="30">
     </td>
    </tr>
    <tr>
     <td align="right">Группа</td>
     <td>
      <input type="text" class="elem" name="parentName" size="20" value="<jstl:out value="${supplySearchParameters.parentName}"/>" tabIndex="11">
     </td>
     <td align="right">Адрес</td>
     <td>
      <input type="text" class="elem" name="address" size="20" value="<jstl:out value="${supplySearchParameters.address}"/>" tabIndex="21">
     </td>
     <td align="right">Сайт</td>
     <td>
      <input type="text" class="elem" name="url" size="20" value="<jstl:out value="${supplySearchParameters.url}"/>" tabIndex="31">
     </td>
    </tr>
    <tr>
     <td align="right">Вид деятельности</td>
     <td>
      <select name="kind" class="elem" tabIndex="12">
       <option value="">Не имеет значения</option>
       <logic:iterate name="supplyKinds_2" id="kind">
        <option value="<jstl:out value="${kind.id}"/>"<jstl:if test="${supplySearchParameters.kind == kind.id}"> selected</jstl:if>><jstl:out value="${kind.name}"/></option>
       </logic:iterate>
      </select>
     </td>
     <td align="right">Метро</td>
     <td>
      <input name="metro" class="elem" size="20" value="<jstl:out value="${supplySearchParameters.metro}"/>" tabIndex="22">
     </td>
     <td align="right">Краткое название</td>
     <td>
      <input name="shortName" class="elem" size="20" value="<jstl:out value="${supplySearchParameters.shortName}"/>" tabIndex="32">
     </td>
    </tr>
    <tr>
     <td align="right">Форма собственности</td>
     <td>
      <select name="propertyForm" class="elem" tabIndex="13">
       <option value="">Не имеет значения</option>
       <logic:iterate name="inquire_supply_property_forms_2" id="propertyForm">
        <option value="<jstl:out value="${propertyForm.id}"/>"<jstl:if test="${supplySearchParameters.propertyForm == propertyForm.id}"> selected</jstl:if>><jstl:out value="${propertyForm.name}"/></option>
       </logic:iterate>
      </select>
     </td>
     <td align="right">ИНН</td>
     <td>
      <input type="text" class="elem" name="inn" size="20" value="<jstl:out value="${supplySearchParameters.inn}"/>" tabIndex="23">
     </td>
     <jstl:choose>
      <jstl:when test="${not empty Sergey}">
       <td align="right">Примечание</td>
       <td>
        <input type="text" name="note" class="elem" size="20" value="<jstl:out value="${supplySearchParameters.note}"/>" tabIndex="33">
       </td>
      </jstl:when>
      <jstl:otherwise>
       <td colSpan="2"></td>
      </jstl:otherwise>
     </jstl:choose>
    </tr>
    <tr>
     <td align="right">Только самые важные</td>
     <td><input type="checkbox" name="importantOnly" tabIndex="14"<jstl:if test="${supplySearchParameters.importantOnly}"> checked</jstl:if>></td>
     <td align="right">КПП</td>
     <td>
      <input type="text" class="elem" name="kpp" size="20" value="<jstl:out value="${supplySearchParameters.kpp}"/>" tabIndex="24">
     </td>
     <td align="right">ОГРН</td>
     <td>
      <input type="text" class="elem" name="ogrn" size="20" value="<jstl:out value="${supplySearchParameters.ogrn}"/>" tabIndex="34">
     </td>
    </tr>
    <tr>
     <td align="right">Только холдинги</td>
     <td><input type="checkbox" name="holdingsOnly" tabIndex="15"<jstl:if test="${supplySearchParameters.holdingsOnly}"> checked</jstl:if>></td>
     <td colSpan="4"></td>
    </tr>
    <tr>
     <td colSpan="6">
      <table align="center">
       <tr>
        <td><button type="submit" tabIndex="40">Найти</button></td>
	<td><button type="button" onClick="clearSearchForm(document.searchForm)" tabIndex="41">Очистить</button></td>
       </tr>
      </table>
     </td>
    </tr>        
   </table>
  </form>
 </body>
</html>
