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
  <util:size var="count" collection="phones"/>
  <title>Редактирование телефонов - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript"><!--
      <jstl:if test="${count > 0 && (not empty Sergey || not empty Editor)}">
       function executePhoneCommand(action) {
           var targetForm = document.phoneForm;
           var sourceForm = document.sourceForm;
           targetForm.action.value = action;
           <jstl:choose>
            <jstl:when test="${count == 1}">
             targetForm.phoneNumber.value = sourceForm.phoneNumbers.value;
             targetForm.phoneType.value = sourceForm.phoneTypes.value;
             targetForm.phoneId.value = sourceForm.phoneIds.value;
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
            </jstl:otherwise>
           </jstl:choose>
	   if (action != 'person.removePhone'
	       || confirm("Вы уверены, что хотите удалить телефон \"" + targetForm.phoneNumber.value + "\"?")) {
               targetForm.submit();
	   }
       }
      </jstl:if>
      function resetForms() {
          <jstl:if test="${not empty Sergey or not empty Editor}">
           document.newPhoneForm.reset();
          </jstl:if>
          document.sourceForm.reset();
      }
  --></script>
 </head>
 <jstl:choose>
  <jstl:when test="${not empty Sergey or not empty Editor}">
   <body onLoad="setFocus('newPhoneForm', 'phoneNumber')">
  </jstl:when>
  <jstl:otherwise>
   <body>
  </jstl:otherwise>
 </jstl:choose>
  <jsp:include page="/include/menu.jsp" flush="true"/>
  <p>Редактирование телефонов</p>
  <table cellspacing="1" cellPadding="3" align="center" border="1">
   <tr>
    <td>
     <table align="center">
      <jstl:if test="${count > 0}">
       <tr>
        <td></td>
        <td>Телефон *</td>
        <td>Тип *</td>
        <jstl:if test="${not empty Sergey}">
         <td>Выбор</td>
        </jstl:if>
       </tr>
       <form name="phoneForm" method="POST" action="<%=request.getContextPath()%>/controller">
        <input type="hidden" name="action" value="">
        <input type="hidden" name="id" value="<jstl:out value="${handle.id}"/>">
        <input type="hidden" name="phoneNumber" value="">
        <input type="hidden" name="phoneType" value="">
        <input type="hidden" name="phoneId" value="">
       </form>
       <form name="sourceForm" method="POST" action="<%=request.getContextPath()%>">
        <logic:iterate name="phones" id="phone" indexId="i" type="su.sergey.contacts.phone.valueobjects.Phone2">
         <input type="hidden" name="phoneIds" value="<jstl:out value="${phone.handle.id}"/>">
         <tr>
          <td><jstl:out value="${i+1}"/></td>
          <td><input name="phoneNumbers" type="text" maxLength="25" size="25" value="<jstl:out value="${phone.attributes.phone}"/>" class="fixed"><jstl:if test="${phone.attributes.basic}"><b>!</b></jstl:if></td>
          <td>
           <select name="phoneTypes">
            <logic:iterate name="inquire_phone_types_1" id="phoneType" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
             <option value="<jstl:out value="${phoneType.id}"/>"<jstl:if test="${phone.attributes.type == phoneType.id}"> selected</jstl:if>><jstl:out value="${phoneType.name}"/></option>
            </logic:iterate>
           </select>
          </td>
          <jstl:if test="${not empty Sergey}">
           <td><input type="radio" name="phoneChoice" value="<jstl:out value="${i}"/>"<jstl:if test="${i==0}"> checked</jstl:if>></td>
          </jstl:if>
         </tr>
        </logic:iterate>
       </form>
       <jstl:if test="${not empty Sergey}">
        <tr>
         <td colSpan="4">
          <table align="center">
           <tr align="center">
            <td><button type="button" onClick="executePhoneCommand('person.updatePhone')">Изменить</button></td>
            <td><button type="button" onClick="executePhoneCommand('person.removePhone')">Удалить</button></td>
            <td><button type="button" onClick="executePhoneCommand('person.setBasicPhone')">Сделать основным</></td>
           </tr>
          </table>
         </td>
        </tr>
       </jstl:if>
       <jstl:if test="${not empty Editor || not empty Sergey}">
        <tr><td colSpan="4"></td></tr>
       </jstl:if>
      </jstl:if>
      <jstl:if test="${not empty Editor || not empty Sergey}">
       <tr>
        <td colSpan="2">Новый номер *</td>
        <td>Тип *</td>
        <jstl:if test="${not empty Sergey}">
         <td></td>
        </jstl:if>
       </tr>
       <form name="newPhoneForm" method="POST" action="<%=request.getContextPath()%>/controller">
        <input type="hidden" name="id" value="<jstl:out value="${handle.id}"/>">
        <input type="hidden" name="action" value="person.addPhone"/>
        <tr>
         <td>?</td>
         <td><input type="text" name="phoneNumber" value="<jstl:out value="${personSearchParameters.phone}"/>" style="font-family: monospace;" maxLength="25" size="25" class="fixed"></td>
         <td>
          <select name="phoneType">
           <logic:iterate name="inquire_phone_types_1" id="phoneType" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
            <option value="<jstl:out value="${phoneType.id}"/>"><jstl:out value="${phoneType.name}"/></option>
           </logic:iterate>
          </select>
         </td>
         <jstl:if test="${not empty Sergey}">
          <td></td>
         </jstl:if>
        </tr>
       </form>
       <tr>
        <td colSpan="4">
         <table align="center">
          <tr align="center">
           <td><button type="button" onClick="document.newPhoneForm.submit()">Добавить</button></td>
           <td>
            <jstl:choose>
             <jstl:when test="${not empty backURL}">
              <a href="<jstl:out value="${backURL}"/>">
             </jstl:when>
             <jstl:otherwise>
              <a href="<%=request.getContextPath()%>/controller?action=person.view&id=<jstl:out value="${paramValues['id'][0]}"/>">
             </jstl:otherwise>
            </jstl:choose>Вернуться</a>
           </td>
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
