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
  <script language="javacript" src="<%=request.getContextPath()%>/js/utils.js"/>
  <title>Расходы на мобильную связь - База данных &quot;Контакты&quot;</title>
 </head>
 <body onLoad="setFocus('callExpenseForm', 'report')">
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p>Расходы на мобильную связь</p>
  <form name="callExpenseForm" method="POST" action="<%=request.getContextPath()%>/controller">
   <jstl:choose>
    <jstl:when test="${not empty expense}">
     <input type="hidden" name="action" value="call.updateExpense">
     <input type="hidden" name="id" value="<jstl:out value="${expense.id}"/>">
    </jstl:when>
    <jstl:otherwise>
     <input type="hidden" name="action" value="call.createExpense">
    </jstl:otherwise>
   </jstl:choose>
   <util:message/>
   <table cellSpacing="0" cellPadding="3" align="center" border="1">
    <tr>
     <td>
      <table cellSpacing="0" cellPadding="3" align="center">
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
        <td align="center" colspan="2">
         <button type="submit">Сохранить</button><jstl:if test="${not empty expense}">&nbsp;<button type="button" onClick="document.removeExpenseForm.submit()">Удалить</button></jstl:if>
        </td>
       </tr>
      </table>
     </td>
    </tr>
   </table>
  </form>
  <jstl:if test="${not empty expense}">
   <form name="removeExpenseForm" method="POST" action="<%=request.getContextPath()%>/controller">
    <input type="hidden" name="action" value="call.removeExpense">
    <input type="hidden" name="id" value="<jstl:out value="${expense.id}"/>">
   </form>
  </jstl:if>
 </body>
</html>
