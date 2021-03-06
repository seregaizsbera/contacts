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
  <logic:iterate name="phones" indexId="i1" id="temp"/>
  <jstl:choose><jstl:when test="${i1 != null}"><jstl:set var="count" value="${i1+1}"/></jstl:when><jstl:otherwise><jstl:set var="count" value="0"/></jstl:otherwise></jstl:choose>
  <title>Редактирование телефонов - База данных &quot;Контакты&quot;</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <link rel="SHORTCUT ICON" href="<jstl:out value="${pageContext.request.contextPath}"/>/shortcut.ico"/>
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript"><!--
      <jstl:if test="${count !=0 && (not empty Sergey || not empty Editor)}">
       function executePhoneCommand(action) {
           var targetForm = document.phoneForm;
           var sourceForm = document.sourceForm;
           targetForm.action.value = action;
           <jstl:choose>
            <jstl:when test="${count == 1}">
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
	   if (action != 'supply.removePhone'
	       || confirm("Вы уверены, что хотите удалить телефон \"" + targetForm.phoneNumber.value + "\"?")) {
               targetForm.submit();
	   }
       }
       
       function phoneChanged(i) {
           if (!document.sourceForm['phoneChoice'][i].checked) {
	       document.sourceForm['phoneChoice'][i].checked = true;
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
      <jstl:if test="${count != 0}">
       <tr>
        <td></td>
        <td>Телефон *</td>
        <td>Тип *</td>
        <td>Примечание</td>
        <jstl:if test="${not empty Sergey}">
         <td>Выбор</td>
        </jstl:if>
       </tr>
       <form name="phoneForm" method="post" action="<%=request.getContextPath()%>/controller">
        <input type="hidden" name="action" value="">
        <input type="hidden" name="id" value="<jstl:out value="${handle.id}"/>">
        <input type="hidden" name="phoneNumber" value="">
        <input type="hidden" name="phoneType" value="">
        <input type="hidden" name="phoneId" value="">
        <input type="hidden" name="phoneNote" value="">
       </form>
       <form name="sourceForm" method="post" action="<%=request.getContextPath()%>">
        <logic:iterate name="phones" id="phone" indexId="i" type="su.sergey.contacts.phone.valueobjects.Phone2">
         <input type="hidden" name="phoneIds" value="<jstl:out value="${phone.handle.id}"/>">
         <tr>
          <td><jstl:out value="${i+1}"/></td>
          <td><input name="phoneNumbers" type="text" maxLength="25" size="25" value="<jstl:out value="${phone.attributes.phone}"/>" class="fixed" onKeyPress="phoneChanged(<jstl:out value="${i}"/>)"><jstl:if test="${phone.attributes.basic}"><b>!</b></jstl:if></td>
          <td>
           <select name="phoneTypes" onChange="phoneChanged(<jstl:out value="${i}"/>)">
            <logic:iterate name="inquire_phone_types_1" id="phoneType" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
             <option value="<jstl:out value="${phoneType.id}"/>"<jstl:if test="${phone.attributes.type == phoneType.id}"> selected</jstl:if>><jstl:out value="${phoneType.name}"/></option>
            </logic:iterate>
           </select>
          </td>
          <td><input name="phoneNotes" type="text" size="25" value="<jstl:out value="${phone.attributes.note}"/>" onKeyPress="phoneChanged(<jstl:out value="${i}"/>)"></td>
          <jstl:if test="${not empty Sergey}">
           <td><input type="radio" name="phoneChoice" value="<jstl:out value="${i}"/>"<jstl:if test="${i==0}"> checked</jstl:if>></td>
          </jstl:if>
         </tr>
        </logic:iterate>
       </form>
       <jstl:if test="${not empty Sergey}">
        <tr>
         <td colSpan="5">
          <table align="center">
           <tr>
            <td><button type="button" onClick="executePhoneCommand('supply.updatePhone')">Изменить</button></td>
            <td><button type="button" onClick="executePhoneCommand('supply.removePhone')">Удалить</button></td>
	   </tr>
          </table>
         </td>
        </tr>
       </jstl:if>
       <jstl:if test="${not empty Editor || not empty Sergey}">
        <tr><td colSpan="5"></td></tr>
       </jstl:if>
      </jstl:if>
      <jstl:if test="${not empty Sergey || not empty Editor}">
       <tr>
        <td colSpan="2">Новый номер *</td>
        <td>Тип *</td>
        <td>Примечание</td>	
        <jstl:if test="${not empty Sergey}">
         <td></td>
        </jstl:if>
       </tr>
       <form name="newPhoneForm" method="post" action="<%=request.getContextPath()%>/controller">
        <input type="hidden" name="id" value="<jstl:out value="${handle.id}"/>">
        <input type="hidden" name="action" value="supply.addPhone"/>
        <tr>
         <td>?</td>
         <td><input type="text" name="phoneNumber" value="<jstl:out value="${supplySearchParameters.phone}"/>" maxLength="25" size="25" class="fixed"></td>
         <td>
          <select name="phoneType">
           <logic:iterate name="inquire_phone_types_1" id="phoneType" type="su.sergey.contacts.inquiry.valueobjects.InquiryObject">
            <option value="<jstl:out value="${phoneType.id}"/>"><jstl:out value="${phoneType.name}"/></option>
           </logic:iterate>
          </select>
         </td>
         <td><input name="phoneNote" type="text" size="25"></td>
	 <jstl:if test="${not empty Sergey}">
          <td></td>
	 </jstl:if>
        </tr>
        <tr>
         <td colSpan="5">
          <table align="center">
           <tr align="center">
            <td><button type="submit">Добавить</button></td>
            <td>
             <jstl:choose>
              <jstl:when test="${not empty backURL}">
               <a href="<jstl:out value="${backURL}"/>">
              </jstl:when>
              <jstl:otherwise>
               <a href="<%=request.getContextPath()%>/controller?action=supply.view&id=<jstl:out value="${paramValues['id'][0]}"/>">
              </jstl:otherwise>
             </jstl:choose>Вернуться</a>
            </td>
           </tr>
          </table>
         </td>
        </tr>
       </form>
      </jstl:if>
     </table>
    </td>
   </tr>
  </table>
 </body>
</html>
