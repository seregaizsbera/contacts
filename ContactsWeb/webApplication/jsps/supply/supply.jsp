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
   <jstl:when test="${supply!=null}">
    <title>Организация <jstl:out value="${supply.attributes.name}"/> - База данных &quot;Контакты&quot;</title>
   </jstl:when>
   <jstl:otherwise>
    <title>Новая организация - База данных &quot;Контакты&quot;</title>
   </jstl:otherwise>
  </jstl:choose>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript"><!--
      function removeSupply() {
          var query="Вы уверены, что хотите удалить данные об организации \"<jstl:out value="${supply.attributes.name}"/>\"?";
          if (confirm(query)) {
              document.removeForm.submit();
          }
      }
  --></script>
 </head>
 <body onLoad="setFocus('supplyForm', 'name')">
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <jstl:if test="${supply != null}">
   <form name="removeForm" method="post" action="<%=request.getContextPath()%>/controller">
    <input type="hidden" name="action" value="supply.remove">
    <input type="hidden" name="id" value="<jstl:out value="${supply.handle.id}"/>">
   </form>
  </jstl:if>
  <form name="supplyForm" action="<%=request.getContextPath()%>/controller" method="post">
   <p>
    <jstl:choose>
     <jstl:when test="${supply != null}">
      Организация с идентификатором <jstl:out value="${supply.handle.id}"/>
      (<i>Последние изменения от: <fmt:formatDate pattern="dd.MM.yyyy" value="${supply.attributes.updateTime}"/></i>)
     </jstl:when>
     <jstl:otherwise>
      Новая организация
     </jstl:otherwise>
    </jstl:choose>
   </p>
   <table align="center" border="1">
    <tr>
     <td>
      <table align="center">
       <jstl:choose>
        <jstl:when test="${supply != null && not empty Sergey}">
         <input type="hidden" name="action" value="supply.update">
         <input type="hidden" name="id" value="<jstl:out value="${supply.handle.id}"/>">
        </jstl:when>
        <jstl:otherwise>
         <input type="hidden" name="action" value="supply.create">
        </jstl:otherwise>
       </jstl:choose>
       <tr>
        <td align="right">* Название</td>
        <td align="left"><input type="text" name="name" class="wide_elem" size="25" value="<jstl:out value="${supply.attributes.name}" default="${supplySearchParameters.name}"/>"></td>
        <td align="right">Группа</td>
        <td align="left"><input type="text" name="parentName" class="wide_elem" size="25" value="<jstl:out value="${supply.attributes.parentName}" default="${supplySearchParameters.parentName}"/>"></td>
       </tr>
       <tr>
        <td align="right">* Вид деятельности</td>
        <td align="left">
         <select name="kind" class="wide_elem">
          <jstl:set var="theKind"><jstl:out value="${supply.attributes.kind}" default="${supplySearchParameters.kind}"/></jstl:set>
          <logic:iterate name="supplyKinds_2" id="kind">
           <option value="<jstl:out value="${kind.id}"/>"<jstl:if test="${theKind == kind.id}"> selected</jstl:if>><jstl:out value="${kind.name}"/></option>
          </logic:iterate>
         </select>
        </td>
        <td align="right">Форма собственности</td>
        <td align="left">
         <select name="propertyForm" class="wide_elem">
          <jstl:set var="thePropertyForm"><jstl:out value="${supply.attributes.propertyForm}" default="${supplySearchParameters.propertyForm}"/></jstl:set>
          <option value="">------- ------------ -------</option>
          <logic:iterate name="inquire_supply_property_forms_2" id="propertyForm">
           <option value="<jstl:out value="${propertyForm.id}"/>"<jstl:if test="${thePropertyForm == propertyForm.id}"> selected</jstl:if>><jstl:out value="${propertyForm.name}"/></option>
          </logic:iterate>
         </select>
        </td>
       </tr>
       <tr>
        <td align="right">Адрес</td>
        <td align="left"><textarea name="address" class="wide_elem" rows="5" cols="25" wordwrap="true"><jstl:out value="${supply.attributes.address}" default="${supplySearchParameters.address}"/></textarea></td>
        <jstl:if test="${not empty Sergey}">
         <td align="right">Доп. инфо</td>
         <td align="left"><textarea name="note" class="wide_elem" rows="5" cols="25" wordwrap="true"><jstl:out value="${supply.attributes.note}" default="${supplySearchParameters.note}"/></textarea></td>
        </jstl:if>
       </tr>
       <tr>
        <td align="right">Метро</td>
        <td align="left"><input type="text" name="metro" class="wide_elem" size="25" value="<jstl:out value="${supply.attributes.metro}" default="${supplySearchParameters.metro}"/>"></td>
        <td align="right">Адрес сайта</td>
        <td align="left"><input type="text" name="url" class="wide_elem" size="25" value="<jstl:out value="${supply.attributes.url}" default="${supplySearchParameters.url}"/>"></td>
       </tr>
       <tr>
        <td align="right">Краткое наименование</td>
        <td align="left"><input type="text" name="shortName" class="wide_elem" size="25" value="<jstl:out value="${supply.attributes.shortName}" default="${supplySearchParameters.shortName}"/>"></td>
        <td align="right">ИНН</td>
        <td align="left"><input type="text" name="inn" class="wide_elem" size="25" value="<jstl:out value="${supply.attributes.inn}" default="${supplySearchParameters.inn}"/>"></td>
       </tr>
       <tr>
        <td align="right">КПП</td>
        <td>
         <input type="text" class="wide_elem" name="kpp" size="25" value="<jstl:out value="${supply.attributes.kpp}" default="${supplySearchParameters.kpp}"/>">
        </td>
        <td align="right">ОГРН</td>
        <td>
         <input type="text" class="wide_elem" name="ogrn" size="25" value="<jstl:out value="${supply.attributes.ogrn}" default="${supplySearchParameters.ogrn}"/>">
        </td>	
       </tr>
       <tr>
        <td align="right">Важные данные</td>
        <td>
         <input type="checkbox" name="important"<jstl:if test="${supply.attributes.important || (supply == null && supplySearchParameters.importantOnly)}"> checked</jstl:if>>
        </td>
        <td align="right">Холдинг</td>
        <td>
         <input type="checkbox" name="holding"<jstl:if test="${supply.attributes.holding || (supply == null && supplySearchParameters.holdingsOnly)}"> checked</jstl:if>>
        </td>
       </tr>
       <tr>
        <td colSpan="2" align="left" valign="top">
         <jsp:include page="/include/supply/phones.jsp" flush="true"/>
        </td>
        <td colSpan="2" align="right" valign="top">
         <jsp:include page="/include/supply/emails.jsp" flush="true"/>
        </td>
       </tr>
      </table>
      <table align="center">
       <tr>
        <jstl:if test="${(supply == null && not empty Editor) || not empty Sergey}">
         <td align="center"><button type="submit">Сохранить</button></td>
         <td align="center"><button type="reset">Восстановить</button></td>
        </jstl:if>
        <jstl:if test="${supply != null && not empty Sergey}">
         <td><button type="button" onClick="removeSupply()">Удалить</button></td>
        </jstl:if>
        <td>
         <jstl:choose>
          <jstl:when test="${not empty backURL}">
           <a href="<jstl:out value="${backURL}"/>">
          </jstl:when>
          <jstl:when test="${not empty supplySearchParameters}">
           <a href="<%=request.getContextPath()%>/controller?action=supply.pageSupplies">
	  </jstl:when>
	  <jstl:otherwise>
           <a href="<%=request.getContextPath()%>/controller?action=supply">
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
