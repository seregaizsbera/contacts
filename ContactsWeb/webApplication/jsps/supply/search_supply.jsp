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
  <script language="javascript">
  <!--
      function clearSearchForm(form) {
          form.address.value = '';
          form.email.value = '';
          form.importantOnly.checked = false;
          form.inn.value = '';
          form.kind.selectedIndex = 0;
          form.metro.value = '';
          form.name.value = '';
          form.note.value = '';
          form.parentName.value = '';
          form.phone.value = '';
          form.shortName.value = '';
          form.url.value = '';
          return false;
      }
  -->
  </script>
 </head>
 <body>
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <p align="left">Поиск организации</p>
  <jstl:if test="${supplySearchParameters != null}">
   <form name="addSupplyForm" method="GET" action="<%=request.getContextPath()%>/controller">
    <input type="hidden" name="action" value="supply.view">
    <table align="right">
     <tr align="right">
      <td><a href="<%=request.getContextPath()%>/controller?action=report.pageSupplies" target="_blank">Создать отчет</a></td>
      <td><button type="submit">Создать</button></td>
     <tr>
    </table>
   </form>
  </jstl:if>
  <util:message/>
  <util:searchResults page="/include/supply/supply_search_results.jsp"
                      collection="supplies"
                      notFoundPage="/include/supply/supply_not_found.jsp"/>
  <form name="searchForm" method="GET" action="<%=request.getContextPath()%>/controller">
   <input type="hidden" name="action" value="supply.search">
   <table width="100%" border="0" cellspacing="1" cellpadding="3">
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
     <td width="16%" align="right">Примечание</td>
     <td width="15%">
      <input name="note" class="elem" size="20" value="<jstl:out value="${supplySearchParameters.note}"/>">
     </td>
    </tr>
    <tr>
     <td width="16%" align="right">Краткое название</td>
     <td width="15%">
      <input name="shortName" class="elem" size="20" value="<jstl:out value="${supplySearchParameters.shortName}"/>">
     </td>
     <td width="16%" align="right">Метро</td>
     <td width="15%">
      <input name="metro" class="elem" size="20" value="<jstl:out value="${supplySearchParameters.metro}"/>">
     </td>
     <td width="16%" align="right">Только самые важные</td>
     <td width="15%"><input type="checkbox" name="importantOnly"<jstl:if test="${supplySearchParameters.importantOnly}"> checked</jstl:if>></td>
    </tr>
    <tr align="center">
     <td colspan="6">
      <button type="submit">Найти</button>
      <button type="reset" onClick="javascript:return clearSearchForm(document.searchForm)">Очистить</button>
     </td>
    </tr>        
   </table>
  </form>
 </body>
</html>
