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
  <logic:iterate name="phones" indexId="i1" id="phone"/>
  <jstl:choose><jstl:when test="${i1!=null}"><jstl:set var="count" value="${i1+1}"/></jstl:when><jstl:otherwise><jstl:set var="count" value="0"/></jstl:otherwise></jstl:choose>
  <title>Редактирование телефонов - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript">
  <!--
      <jstl:if test="${count!=0}">
       function executePhoneCommand(action) {
           var targetForm = document.phoneForm;
           var sourceForm = document.sourceForm;
           targetForm.action.value = action;
           <jstl:choose>
           <jstl:when test="${count==1}">
             targetForm.phoneNumber.value = sourceForm.phoneNumbers.value;
             targetForm.phoneType.value = sourceForm.phoneTypes.value;
             targetForm.phoneId.value = sourceForm.phoneIds.value;
             targetForm.phoneNote.value = sourceForm.phoneNotes.value;
            </jstl:when>
            <jstl:otherwise>
             var choices = sourceForm['phoneChoice'];
             var choice = 0;
             for (var i = 0; i < <jstl:out value="${count}"/>; i++) {
                 if (choices[i].checked) {
                     choice = i;
                     break;
                 }
             }
             targetForm.phoneNumber.value = sourceForm.phoneNumbers[choice].value;
             targetForm.phoneType.value = sourceForm.phoneTypes[choice].value;
             targetForm.phoneId.value = sourceForm.phoneIds[choice].value;
             targetForm.phoneNote.value = sourceForm.phoneNotes[choice].value;
            </jstl:otherwise>
           </jstl:choose>
           targetForm.submit();
       }
      </jstl:if>
      function resetForms() {
          document.newPhoneForm.reset();
          document.sourceForm.reset();
      }
  -->
  </script>
 </head>
 <body>
  <jsp:include flush="true" page="/include/menu.jsp"/>
  <table cellspacing="1" cellpadding="3">
   <tr>
    <td></td>
    <td>Телефон *</td>
    <td>Тип *</td>
    <td>Примечание</td>
    <td>Выбор</td>
   </tr>
   <form name="phoneForm" method="POST" action="<%=request.getContextPath()%>/controller">
    <input type="hidden" name="action" value="">
    <input type="hidden" name="id" value="<jstl:out value="${handle.id}"/>">
    <input type="hidden" name="phoneNumber" value="">
    <input type="hidden" name="phoneType" value="">
    <input type="hidden" name="phoneId" value="">
    <input type="hidden" name="phoneNote" value="">
   </form>
   <form name="sourceForm" method="POST" action="<%=request.getContextPath()%>">
    <logic:iterate name="phones" id="phone" indexId="i" type="su.sergey.contacts.phone.valueobjects.Phone2">
     <input type="hidden" name="phoneIds" value="<jstl:out value="${phone.handle.id}"/>">
     <tr>
      <td><jstl:out value="${i+1}"/></td>
      <td><input name="phoneNumbers" type="text" style="font-family: monospace;" maxlength="25" size="25" value="<jstl:out value="${phone.attributes.phone}"/>"><jstl:if test="${phone.attributes.basic}"><b>!</b></jstl:if></td>
      <td>
       <select name="phoneTypes">
        <logic:iterate name="inquire_phone_types_1" id="phoneType" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
         <option value="<jstl:out value="${phoneType.id}"/>"<jstl:if test="${phone.attributes.type == phoneType.id}"> selected</jstl:if>><jstl:out value="${phoneType.name}"/></option>
        </logic:iterate>
       </select>
      </td>
      <td><input name="phoneNotes" type="text" size="25" value="<jstl:out value="${phone.attributes.note}"/>"></td>
      <td><input type="radio" name="phoneChoice" value="<jstl:out value="${i}"/>"<jstl:if test="${i==0}"> checked</jstl:if>></td>
     </tr>
    </logic:iterate>
   </form>
   <form name="newPhoneForm" method="POST" action="<%=request.getContextPath()%>/controller">
    <input type="hidden" name="id" value="<jstl:out value="${handle.id}"/>">
    <input type="hidden" name="action" value="<jstl:out value="${entity}" default="person"/>.addPhone"/>
    <tr>
     <td></td>
     <td><input name="phoneNumber" type="text" style="font-family: monospace;" maxlength="25" size="25"></td>
      <td>
       <select name="phoneType">
        <option value="" selected>--- ------- ---</option>
        <logic:iterate name="inquire_phone_types_1" id="phoneType" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
         <option value="<jstl:out value="${phoneType.id}"/>"><jstl:out value="${phoneType.name}"/></option>
        </logic:iterate>
       </select>
      </td>
     <td><input name="phoneNote" type="text" size="25"></td>
     <td></td>
    </tr>
   </form>
  <table>
  <table cellspacing="1" cellpadding="3">
   <tr align="center">
    <td><button type="button" onclick="executePhoneCommand('<jstl:out value="${entity}" default="person"/>.updatePhone')"<jstl:if test="${count==0}"> disabled</jstl:if>>Изменить</button></td>
    <td><button type="button" onclick="executePhoneCommand('<jstl:out value="${entity}" default="person"/>.removePhone')"<jstl:if test="${count==0}"> disabled</jstl:if>>Удалить</button></td>
    <td><button type="button" onclick="document.newPhoneForm.submit()">Добавить</button></td>
    <td><button type="button" onclick="resetForms()">Восстановить</button></td>
   </tr>
   <tr align="right">
    <td colspan="4"><a href="<%=request.getContextPath()%>/controller?action=<jstl:out value="${entity}"/>.view&id=<jstl:out value="${handle.id}"/>">Вернуться</a></td>
   </tr>
  </table>
 </body>
</html>
