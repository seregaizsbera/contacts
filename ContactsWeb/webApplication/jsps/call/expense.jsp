<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="util" uri="contacts" %>
<%@ taglib prefix="logic" uri="struts_logic" %>
<%@ taglib prefix="jstl" uri="jstl_core" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Content-Style-Type" content="text/css">
  <link href="/contacts/style.css" rel="stylesheet" type="text/css">
  <link rel="SHORTCUT ICON" href="<jstl:out value="${pageContext.request.contextPath}"/>/shortcut.ico"></link>
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <title>Расходы на мобильную связь - База данных &quot;Контакты&quot;</title>
 </head>
 <body onLoad="setFocus('callExpenseForm', 'report')">
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p>Расходы на мобильную связь</p>
   <jstl:choose>
    <jstl:when test="${not empty expense}">
     <jstl:set var="action" value="call.updateExpense"/>
    </jstl:when>
    <jstl:otherwise>
     <jstl:set var="action" value="call.createExpense"/>
    </jstl:otherwise>
   </jstl:choose>

  <form name="callExpenseForm" method="post" action="<%=request.getContextPath()%>/controller?action=<jstl:out value="${action}"/>">
   <jstl:if test="${not empty expense}">
    <input type="hidden" name="id" value="<jstl:out value="${expense.id}"/>">
   </jstl:if>
   <center><b><jstl:out value="${message}"/></b></center>
   <table align="center" border="1">
    <tr>
     <td>
      <table align="center">
       <tr>
        <td align="right">* Идентификатор отчета за период</td>
        <td align="left">
         <input type="text" name="report" class="wide_elem" value="<jstl:out value="${expense.report}" default="${directoryRecordsSearchParameters.parameters['report']}"/>">
        </td>
       </tr>
       <tr>
        <td align="right">* Вид расхода</td>
        <td align="left">
         <select name="kind" class="wide_elem">
          <logic:iterate name="expense_kinds" id="kind" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
           <option value="<jstl:out value="${kind.id}"/>"<jstl:if test="${kind.id == expense.kind}"> selected</jstl:if>><jstl:out value="${kind.name}"/></option>
          </logic:iterate>
         </select>
        </td>
       </tr>
       <tr>
        <td align="right">Размер предоставленных услуг</td>
        <td align="left">
         <input type="text" name="expense" class="wide_elem" value="<jstl:out value="${expense.expense}"/>">
        </td>
       </tr>
       <tr>
        <td align="right">* Стоимость предоставленных услуг</td>
        <td align="left">
         <input type="text" name="price" class="wide_elem" value="<jstl:out value="${expense.price}"/>">
        </td>
       </tr>
       <tr>
        <td align="center" colSpan="2">
         <button type="submit">Сохранить</button><jstl:if test="${not empty expense}">&nbsp;<button type="button" onClick="document.removeExpenseForm.submit()">Удалить</button></jstl:if>
        </td>
       </tr>
      </table>
     </td>
    </tr>
   </table>
  </form>
  <jstl:if test="${not empty expense}">
   <form name="removeExpenseForm" method="post" action="<%=request.getContextPath()%>/controller">
    <input type="hidden" name="action" value="call.removeExpense">
    <input type="hidden" name="id" value="<jstl:out value="${expense.id}"/>">
   </form>
  </jstl:if>
 </body>
</html>
