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
  <logic:iterate name="emails" indexId="i1" id="temp"/>
  <jstl:choose><jstl:when test="${i1 != null}"><jstl:set var="count" value="${i1+1}"/></jstl:when><jstl:otherwise><jstl:set var="count" value="0"/></jstl:otherwise></jstl:choose>
  <title>Редактирование адресов электронной почты - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript"><!--
      <jstl:if test="${count != 0 && (not empty Sergey || not empty Editor)}">
       function executeEmailCommand(action) {
           var targetForm = document.emailForm;
           var sourceForm = document.sourceForm;
           targetForm.action.value = action;
           <jstl:choose>
            <jstl:when test="${count == 1}">
             targetForm.email.value = sourceForm.emails.value;
             targetForm.emailId.value = sourceForm.emailIds.value;
            </jstl:when>
            <jstl:otherwise>
             var choices = sourceForm['emailChoice'];
             var choice = 0;
             for (var i = 0; i < <jstl:out value="${count}"/>; i++) {
                 if (choices[i].checked) {
                     choice = i;
                     break;
                 }
             }
             targetForm.email.value = sourceForm.emails[choice].value;
             targetForm.emailId.value = sourceForm.emailIds[choice].value;
            </jstl:otherwise>
           </jstl:choose>
	   if (action != 'supply.removeEmail'
	       || confirm("Вы уверены, что хотите удалить адрес электронной почты \"" + targetForm.email.value + "\"?")) {
               targetForm.submit();
	   }
       }
      </jstl:if>
      function resetForms() {
          <jstl:if test="${not empty Sergey or not empty Editor}">
           document.newEmailForm.reset();
          </jstl:if>
          document.sourceForm.reset();
      }
  --></script>
 </head>
 <jstl:choose>
  <jstl:when test="${not empty Sergey or not empty Editor}">
   <body onLoad="setFocus('newEmailForm', 'email')">
  </jstl:when>
  <jstl:otherwise>
   <body>
  </jstl:otherwise>
 </jstl:choose>
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p>Редактирование адресов электронной почты</p>
  <table cellSpacing="0" cellPadding="3" align="center" border="1">
   <tr>
    <td>
     <table cellSpacing="0" cellPadding="3" align="center">
   <jstl:if test="${count != 0}">
       <tr>
        <td></td>
        <td>E-mail *</td>
        <jstl:if test="${not empty Sergey}">
         <td>Выбор</td>
        </jstl:if>
       </tr>
       <form name="emailForm" method="POST" action="<%=request.getContextPath()%>/controller">
        <input type="hidden" name="action" value="">
        <input type="hidden" name="id" value="<jstl:out value="${handle.id}"/>">
        <input type="hidden" name="email" value="">
        <input type="hidden" name="emailId" value="">
       </form>
       <form name="sourceForm" method="POST" action="<%=request.getContextPath()%>">
        <logic:iterate name="emails" id="email" indexId="i" type="su.sergey.contacts.email.valueobjects.Email2">
         <input type="hidden" name="emailIds" value="<jstl:out value="${email.handle.id}"/>">
         <tr>
          <td><a href="mailto:<jstl:out value="${email.attributes.email}"/>"><jstl:out value="${i+1}"/></a></td>
          <td><input name="emails" type="text" style="font-family: monospace;" maxLength="50" size="25" value="<jstl:out value="${email.attributes.email}"/>"></td>
          <jstl:if test="${not empty Sergey}">
           <td><input type="radio" name="emailChoice" value="<jstl:out value="${i}"/>"<jstl:if test="${i==0}"> checked</jstl:if>></td>
          </jstl:if>
         </tr>
        </logic:iterate>
       </form>
       <jstl:if test="${not empty Sergey}">
        <tr align="center">
         <td colspan="3">
          <table cellSpacing="0" cellPadding="3" align="center">
           <tr>
            <td><button type="button" onClick="executeEmailCommand('supply.updateEmail')">Изменить</button></td>
            <td><button type="button" onClick="executeEmailCommand('supply.removeEmail')">Удалить</button></td>
           </tr>
          </table>
         </td>
        </tr>
       </jstl:if>
       <tr>
        <td colspan="3"></td>
       </tr>
      </jstl:if>
      <tr>
       <td></td>
       <td>Новый адрес *</td>
       <jstl:if test="${not empty Sergey}">
        <td></td>
       </jstl:if>
      </tr>
      <jstl:if test="${not empty Sergey || not empty Editor}">
       <form name="newEmailForm" method="POST" action="<%=request.getContextPath()%>/controller">
        <input type="hidden" name="id" value="<jstl:out value="${handle.id}"/>">
        <input type="hidden" name="action" value="supply.addEmail"/>
        <tr>
         <td>?</td>
         <td><input type="text" name="email" value="<jstl:out value="${supplySearchParameters.email}"/>" style="font-family: monospace;" maxLength="50" size="25"></td>
         <jstl:if test="${not empty Sergey}">
          <td></td>
         </jstl:if>
        </tr>
       </form>
       <tr align="center">
        <td colspan="3">
         <table cellSpacing="0" cellPadding="3" align="center">
          <tr>
           <td><button type="button" onClick="document.newEmailForm.submit()">Добавить</button></td>
          </tr>
         </table>
        </td>
       </tr>
      </jstl:if>
     </table>
    </td>
   </tr>
  </table>
 </body>
</html>
