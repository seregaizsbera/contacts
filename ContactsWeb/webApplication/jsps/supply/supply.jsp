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
   <jstl:when test="${supply!=null}">
    <title>Организация <jstl:out value="${supply.attributes.name}"/> - База данных &quot;Контакты&quot;</title>
   </jstl:when>
   <jstl:otherwise>
    <title>Новая организация - База данных &quot;Контакты&quot;</title>
   </jstl:otherwise>
  </jstl:choose>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript">
  <!--
      function removeSupply() {
          var query="Вы уверены, что хотите удалить данные об организации <jstl:out value="${supply.attributes.name}"/>?";
          if (confirm(query)) {
              document.removeForm.submit();
          }
      }
  -->
  </script>
 </head>
 <body>
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <jstl:if test="${supply!=null}">
   <i>Идентификатор организации в базе данных - <jstl:out value="${supply.handle.id}"/></i>
   <form name="removeForm" method="POST" action="<%=request.getContextPath()%>/controller">
    <input type="hidden" name="action" value="supply.remove">
    <input type="hidden" name="id" value="<jstl:out value="${supply.handle.id}"/>">
   </form>
  </jstl:if>
  <table cellspacing="1" cellpadding="3" align="center">
   <form name="supplyForm" action="<%=request.getContextPath()%>/controller" method="POST">
    <jstl:choose>
     <jstl:when test="${supply!=null}">
      <input type="hidden" name="action" value="supply.update">
      <input type="hidden" name="id" value="<jstl:out value="${supply.handle.id}"/>">
     </jstl:when>
     <jstl:otherwise>
      <input type="hidden" name="action" value="supply.create">
     </jstl:otherwise>
    <jstl:choose>
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
     <td></td>
     <td align="left"><input type="checkbox" name="important"<jstl:if test="${supply.attributes.important || (supply == null && supplySearchParameters.importantOnly)}"> checked</jstl:if>>Важные данные</td>
    </tr>
    <tr>
     <td align="right">Адрес</td>
     <td align="left"><textarea" name="address" class="wide_elem" rows="5" cols="25" wordwrap="true"><jstl:out value="${supply.attributes.address}" default="${supplySearchParameters.address}"/></textarea></td>
     <td align="right">Доп. инфо</td>
     <td align="left"><textarea" name="note" class="wide_elem" rows="5" cols="25" wordwrap="true"><jstl:out value="${supply.attributes.note}" default="${supplySearchParameters.note}"/></textarea></td>
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
   </table>
   <jsp:include flush="true" page="/include/supply/phones.jsp"/>
   <jsp:include flush="true" page="/include/supply/emails.jsp"/>
   <table align="center" cellspacing="1" cellpadding="3">
    <tr>
     <td></td>
     <td align="center"><button type="submit" onclick="document.supplyForm.submit(); return false">Сохранить</button></td>
     <td align="center"><button type="reset" onclick="document.supplyForm.reset(); return false">Восстановить</button></td>
     <td><jstl:if test="${supply!=null}"><button type="button" onclick="removeSupply()">Удалить</button></jstl:if></td>
    </tr>
   </form>
  </table>
 </body>
</html>
