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
  <logic:iterate name="emails" indexId="i1" id="temp"/>
  <jstl:choose><jstl:when test="${i1!=null}"><jstl:set var="count" value="${i1+1}"/></jstl:when><jstl:otherwise><jstl:set var="count" value="0"/></jstl:otherwise></jstl:choose>
  <title>Редактирование адресов электронной почты - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript">
  <!--
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
           targetForm.submit();
       }
      </jstl:if>
      function resetForms() {
          <jstl:if test="${not empty Sergey or not empty Editor}">
           document.newEmailForm.reset();
          </jstl:if>
          document.sourceForm.reset();
      }
  -->
  </script>
 </head>
 <jstl:choose>
  <jstl:when test="${not empty Sergey or not empty Editor}">
   <body onLoad="setFocus('newEmailForm', 'email')">
  </jstl:when>
  <jstl:otherwise>
   <body>
  </jstl:otherwise>
 </jstl:choose>
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <table cellspacing="1" cellpadding="3">
   <tr>
    <td></td>
    <td>E-mail *</td>
    <td></td>
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
      <td><input name="emails" type="text" style="font-family: monospace;" maxlength="50" size="25" value="<jstl:out value="${email.attributes.email}"/>"><jstl:if test="${email.attributes.basic}"><b>!</b></jstl:if></td>
      <td></td>
      <jstl:if test="${not empty Sergey}">
       <td><input type="radio" name="emailChoice" value="<jstl:out value="${i}"/>"<jstl:if test="${i==0}"> checked</jstl:if>></td>
      </jstl:if>
     </tr>
    </logic:iterate>
   </form>
   <jstl:if test="${not empty Sergey || not empty Editor}">
    <form name="newEmailForm" method="POST" action="<%=request.getContextPath()%>/controller">
     <input type="hidden" name="id" value="<jstl:out value="${handle.id}"/>">
     <input type="hidden" name="action" value="<jstl:out value="${entity}" default="person"/>.addEmail"/>
     <tr>
      <td></td>
      <td><input name="email" type="text" style="font-family: monospace;" maxlength="50" size="25"></td>
      <td></td>
      <td></td>
     </tr>
    </form>
   </jstl:if>
  <table>
  <table cellspacing="1" cellpadding="3">
   <tr align="center">
    <jstl:if test="${not empty Sergey}">
     <td><button type="button" onclick="executeEmailCommand('<jstl:out value="${entity}" default="person"/>.updateEmail')"<jstl:if test="${count==0}"> disabled</jstl:if>>Изменить</button></td>
     <td><button type="button" onclick="executeEmailCommand('<jstl:out value="${entity}" default="person"/>.removeEmail')"<jstl:if test="${count==0}"> disabled</jstl:if>>Удалить</button></td>
    </jstl:if>
    <jstl:if test="${not empty Sergey or not empty Editor}">
     <td><button type="button" onclick="document.newEmailForm.submit()">Добавить</button></td>
     <td><button type="button" onclick="resetForms()">Восстановить</button></td>
    </jstl:if>
   </tr>
   <jstl:if test="${not empty Sergey}">
    <tr align="right">
     <td colspan="4"><button type="button" onclick="executeEmailCommand('<jstl:out value="${entity}" default="person"/>.setBasicEmail')"<jstl:if test="${count==0}"> disabled</jstl:if>>Сделать основным</></td>
    </tr>
   </jstl:if>
  </table>
 </body>
</html>
