<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="definitions.jsp"%>

<html>
<head>
<title>Содержимое справочника</title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link rel="stylesheet" href="style.css" type="text/css">


<%
    int currentPage = 0;
    int directoryPage = 0;
    try {
        currentPage = Integer.parseInt(request.getParameter(PN_PAGE));
        directoryPage = Integer.parseInt(request.getParameter(PN_DIRECTORY_PAGE));
    } catch (Exception e) {};
    String description = (String)request.getAttribute(AN_TABLE_DESCRIPTION);
    String tableName = (String)request.getAttribute(AN_TABLE_NAME);
    ArrayList columns = (ArrayList)request.getAttribute(AN_COLUMNS);
    ArrayList records = (ArrayList)request.getAttribute(AN_RECORDS);
%>

<script language="JavaScript">
<%@ include file="js/functions.js"%>
</script>

</head>

<body>

<%@ include file="main_menu.jsp"%>


<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
  <TR>
    <TD width="5" valign="top"><IMG src="images/spacer.gif" width="5" height="1"></TD>
    <TD width="115" valign="top" background="images/gradient.jpg" height="158" style="background-repeat: no-repeat"><img src="images/spacer.gif" width="115" height="1">
    </td>
    <TD width="8" valign="top"><IMG src="images/spacer.gif" width="8" height="1"></TD>
    <TD valign="top">
      <!--Begin Body-->
      <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
        <TR>
          <TD background="images/pix_light.gif" bgcolor="#5D9790" height="4"><IMG src="images/spacer.gif" width="1" height="4"></TD>
        </TR>
      </TABLE>
      <sb:message/>
      <P class="header">&nbsp;&nbsp;&nbsp;&nbsp;<%=description%></P>

      <table width="100%" border="0" cellspacing="1" cellpadding="3">
            <% if (records != null) { %>
                <sb:pageIterator dispatcherName = "controller?action=directory" iterationName = "ShowRecords" additionalParameter="<%=PN_DIRECTORY_PAGE + '=' + directoryPage%>" startText = "<tr><td class='nav' colspan='4' height='25'>&nbsp;&nbsp;" endText = "</td></tr>"/>
                <tr>
                    <s:iterate name="columns" id="column" type="com.sberbank.sbclients.valueobjects.DirectoryColumnMetadata">
                    <td class="td_head" height="20" width="<%=column.getWidth()%>"><%=column.getFullName()%></td>
                    </s:iterate>
                    <td class="td_head" width="5%">&nbsp;Редактирование&nbsp;</td>
                    <td class="td_head" width="5%">&nbsp;Удаление&nbsp;</td>
                </tr>

                <s:iterate name="records" id="record" type="com.sberbank.sbclients.valueobjects.DirectoryRecord" indexId="i">
                <% int oddEven = i.intValue()%2 + 1; %>
                <tr>
                    <s:iterate name="columns" id="column" type="com.sberbank.sbclients.valueobjects.DirectoryColumnMetadata" indexId="j">
                    <td class="td_<%=oddEven%>" height="25" align="center"><%=record.getValues()[j.intValue()]%></td>
                    </s:iterate>
                    <td class="td_<%=oddEven%>" align="center"><a href="controller?action=directory.showModifyRecord&Page=<%=currentPage%>&directoryPage=<%=directoryPage%>&directoryCode=<%=tableName%>&recordPrimaryKey=<%=record.getPrimaryKeyValue()%>"><img src="images/ico_details.gif" width="16" height="16" hspace="5" border="0"></a></td>
                    <td class="td_<%=oddEven%>" align="center"><a href="controller?action=directory.deleteRecord&Page=<%=currentPage%>&directoryPage=<%=directoryPage%>&directoryCode=<%=tableName%>&recordPrimaryKey=<%=record.getPrimaryKeyValue()%>"><img src="images/ico_delete.gif" width="16" height="16" border="0"></a></td>
                </tr>
                </s:iterate>

                <sb:pageIterator dispatcherName = "controller?action=directory" iterationName = "ShowRecords" additionalParameter="<%=PN_DIRECTORY_PAGE + '=' + directoryPage%>" startText = "<tr><td class='nav' colspan='4' height='25'>&nbsp;&nbsp;" endText = "</td></tr>"/>
            <% } %>
      </table>
      <table border="0" cellspacing="0" cellpadding="3" align="center">
          <tr>
            <td align="left" colspan="6">&nbsp;</td>
          </tr>
          <tr>
            <td>
                <% String nextURL = "javascript:window.location.href = 'controller?action=directory.showModifyRecord&directoryCode=" + tableName + "&Page=" + currentPage + "&directoryPage=" + directoryPage + "';"; %>
                <sb:button value="ДОБАВИТЬ" onClick="<%= nextURL%>"/>
            </td>
            <td>
                <% String backURL = "javascript:window.location.href = 'controller?action=directory.pageDirectories&Page=" + directoryPage + "';"; %>
                <sb:button value="ВЕРНУТЬСЯ" onClick="<%= backURL%>" type="reset"/>
            </td>
          </tr>
      </table>
      </TD>
    <TD width="8" valign="top"><IMG src="images/spacer.gif" width="8" height="1"></TD>
  </TR>
</TABLE>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td rowspan="2" valign="bottom" align="center" width="128">&nbsp;</td>
    <td>&nbsp;</td>
  </tr> 
  <tr>
    <td valign="bottom" height="4">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td background="images/pix_light.gif" bgcolor="#5D9790" height="4"><img src="images/spacer.gif" width="1" height="4"></td>
          <td width="8" height="4"><img src="images/spacer.gif" width="8" height="4"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td colspan="2" background="images/pix_white.gif"><img src="images/spacer.gif" width="1" height="3"></td>
  </tr>
  <tr>
    <td colspan="2" height="1" background="images/pix_dark.gif" bgcolor="#45716C"><img src="images/spacer.gif" width="1" height="1"></td>
  </tr>
</table>

</body>
</html>
