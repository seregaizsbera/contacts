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
  <title>Поиск организации - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript"><!--
      function clearSearchForm(form) {
          form.address.value = "";
          form.email.value = "";
          form.importantOnly.checked = false;
          form.inn.value = "";
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
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <p align="left">Поиск организации</p>
  <jstl:if test="${supplySearchParameters != null}">
   <table cellspacing="1" cellpadding="3" align="right">
    <tr align="right">
     <jstl:if test="${not empty Sergey}">
      <td><a href="<%=request.getContextPath()%>/controller?action=report.pageSupplies" target="_blank">Отчет</a></td>
     </jstl:if>
     <jstl:if test="${not empty Sergey || not empty Editor}">
      <td><a href="<%=request.getContextPath()%>/controller?action=supply.view" accesskey="д">Создать</a></td>
     </jstl:if>
    </tr>
   </table>
  </jstl:if>
  <util:message/>
  <util:searchResults page="/include/supply/search_results.jsp"
                      collection="supplies"
                      notFoundPage="/include/not_found.jsp"/>
  <form name="searchForm" method="GET" action="<%=request.getContextPath()%>/controller">
   <input type="hidden" name="action" value="supply.search">
   <table width="100%" cellspacing="1" cellpadding="3">
    <tr align="center">
     <th colspan="6">Параметры поиска</th>
    </tr>
    <tr>
     <td width="16%" align="right">Название</td>
     <td width="15%">
      <input type="text" class="elem" name="name" size="20" value="<jstl:out value="${supplySearchParameters.name}"/>">
     </td>
     <td width="16%" align="right" colspan="1">Группа</td>
     <td width="15%">
      <input type="text" class="elem" name="parentName" size="20" value="<jstl:out value="${supplySearchParameters.parentName}"/>">
     </td>
     <td width="16%" align="right" colspan="1">Телефон</td>
     <td width="15%">
      <input type="text" class="elem" name="phone" size="20" value="<jstl:out value="${supplySearchParameters.phone}"/>">
     </td>
    </tr>
    <tr>
     <td width="16%" align="right">Сайт</td>
     <td width="15%">
      <input type="text" class="elem" name="url" size="20" value="<jstl:out value="${supplySearchParameters.url}"/>">
     </td>
     <td width="16%" align="right" colspan="1">ИНН</td>
     <td width="15%">
      <input type="text" class="elem" name="inn" size="20" value="<jstl:out value="${supplySearchParameters.inn}"/>">
     </td>
     <td width="16%" align="right" colspan="1">Адрес</td>
     <td width="15%">
      <input type="text" class="elem" name="address" size="20" value="<jstl:out value="${supplySearchParameters.address}"/>">
     </td>
    </tr>
    <tr>
     <td width="16%" align="right">Вид деятельности</td>
     <td width="15%">
      <select name="kind" class="elem">
       <option value="">Не имеет значения</option>
       <logic:iterate name="supplyKinds_2" id="kind">
        <option value="<jstl:out value="${kind.id}"/>"<jstl:if test="${supplySearchParameters.kind == kind.id}"> selected</jstl:if>><jstl:out value="${kind.name}"/></option>
       </logic:iterate>
      </select>
     </td>
     <td width="16%" align="right">Электронная почта</td>
     <td width="15%">
      <input name="email" class="elem" size="20" value="<jstl:out value="${supplySearchParameters.email}"/>">
     </td>
     <jstl:if test="${not empty Sergey}">
      <td width="16%" align="right">Примечание</td>
      <td width="15%">
       <input name="note" class="elem" size="20" value="<jstl:out value="${supplySearchParameters.note}"/>">
      </td>
     </jstl:if>
    </tr>
    <tr>
     <td width="16%" align="right">Форма собственности</td>
     <td width="15%">
      <select name="propertyForm" class="elem">
       <option value="">Не имеет значения</option>
       <logic:iterate name="inquire_supply_property_forms_2" id="propertyForm">
        <option value="<jstl:out value="${propertyForm.id}"/>"<jstl:if test="${supplySearchParameters.propertyForm == propertyForm.id}"> selected</jstl:if>><jstl:out value="${propertyForm.name}"/></option>
       </logic:iterate>
      </select>
     </td>
     <td width="16%" align="right">Метро</td>
     <td width="15%">
      <input name="metro" class="elem" size="20" value="<jstl:out value="${supplySearchParameters.metro}"/>">
     </td>
     <td width="16%" align="right">Краткое название</td>
     <td width="15%">
      <input name="shortName" class="elem" size="20" value="<jstl:out value="${supplySearchParameters.shortName}"/>">
     </td>
    </tr>
    <tr>
     <td width="16%" align="right">Только самые важные</td>
     <td width="15%"><input type="checkbox" name="importantOnly"<jstl:if test="${supplySearchParameters.importantOnly}"> checked</jstl:if>></td>
     <td width="16%" align="right"></td>
     <td width="15%"></td>
     <td width="16%" align="right"></td>
     <td width="15%"></td>
    </tr>
    <tr align="center">
     <td colspan="6">
      <button type="submit">Найти</button>
      <button type="button" onClick="clearSearchForm(document.searchForm)">Очистить</button>
     </td>
    </tr>        
   </table>
  </form>
 </body>
</html>
