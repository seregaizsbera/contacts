<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
 <head>
  <title>Календарь - База данных &quot;Контакты&quot;</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
  <script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
  <script language="javascript"><!--
      var months = new Array(12);
      months[0] = "Январь";
      months[1] = "Февраль";
      months[2] = "Март";
      months[3] = "Апрель";
      months[4] = "Май";
      months[5] = "Июнь";
      months[6] = "Июль";
      months[7] = "Август";
      months[8] = "Сентябрь";
      months[9] = "Октябрь";
      months[10] = "Ноябрь";
      months[11] = "Декабрь";
      
      var weekDays = new Array(7);
      weekDays[0] = "Пн";
      weekDays[1] = "Вт";
      weekDays[2] = "Ср";
      weekDays[3] = "Чт";
      weekDays[4] = "Пт";
      weekDays[5] = "Сб";
      weekDays[6] = "Вс";
	
      function getDayOfWeek(date) {
          var dayOfWeek = date.getDay();
          if (dayOfWeek == 0) {
	      return 6;
	  } else {
	      return dayOfWeek - 1;
	  }
      }
      
      function getYear(date) {
          return date.getYear() + 1900;
      }
      
      function getDayOfWeekFor1stOfMonth(month, year) {
          return getDayOfWeek(new Date(year, month, 1));
      }
      
      function makeURL(month, year, selectedDate, form, field) {
          var result = "javascript:location.href=location.pathname + '?"
                       + "month=" + month
		       + "&year="
		       + year;
	  if (form != null) {
	      result += "&form=" + form;
	  }
          if (field != null) {
	      result += "&field=" + field;
	  }
          if (selectedDate != null) {
              result += "&currentValue=" + formatDate(selectedDate);
          }
          result += "'";
          return result;
      }
      
      function insertValue(form, field, value) {
	  if (opener != null) {
	      opener.document.forms[form].elements[field].value = value;
	      opener.onCalendar(form, field);
	      window.close();
	  }
      }
      
      function makeInsertCommand(month, year, day, form, field) {
          var date = new Date(year, month, day);
	  return "javascript:insertValue('"
	         + form
		 + "', '"
		 + field
		 + "', '"
		 + formatDate(date)
		 + "')";
      }
      
      function drawCalendar(month, year, form, field, selectedDate) {
	  var monthName = months[month];
	  var currentDate = new Date();
	  var selectedDay = selectedDate != null ? selectedDate.getDate() : null;
	  var isCurrentMonth = currentDate.getMonth() == month && getYear(currentDate) == year;
	  var isSelectedMonth = selectedDate != null && selectedDate.getMonth() == month && getYear(selectedDate) == year;
          var numberOfDays = getNumberOfDaysInMonth(month, year);
	  var dayOfWeekFor1stOfMonth = getDayOfWeekFor1stOfMonth(month, year);
	  
	  var text = '<table width="100%">';
	  
	  text += '<tr align="center"><td colSpan="7">';
	  text += '<a href="' + makeURL(month, year - 10, selectedDate, form, field) + '">&lt;&lt;&lt;</a>&nbsp;|&nbsp;';
	  text += '<a href="' + makeURL(month, year - 1, selectedDate, form, field) + '">&lt;&lt</a>&nbsp;|&nbsp;';
	  text += '<a href="' + makeURL(month == 0 ? 11 : month - 1, month == 0 ? year - 1 : year, selectedDate, form, field) + '">&lt</a>&nbsp;|&nbsp;';
	  text += monthName + '&nbsp;' + year;
	  text += '&nbsp;|&nbsp;<a href="' + makeURL(month == 11 ? 0 : month + 1, month == 11 ? year + 1 : year, selectedDate, form, field) + '">&gt;</a>';
	  text += '&nbsp;|&nbsp;<a href="' + makeURL(month, year + 1, selectedDate, form, field) + '">&gt;&gt;</a>';
	  text += '&nbsp;|&nbsp;<a href="' + makeURL(month, year + 10, selectedDate, form, field) + '">&gt;&gt;&gt;</a>';
	  text += '</td></tr>';

	  var openCol = '<td align="center">';
	  var closeCol = '</td>';

	  text += '<tr align="center" valign="center">';
	  for (var i = 0; i < 7; i++) {
	      text += openCol + weekDays[i] + closeCol;
	  }
	  text += '</tr>';
	
	  var day = 1;
	  var currentCell = 1;
	  var numberOfWeeks = Math.ceil((numberOfDays + dayOfWeekFor1stOfMonth) / 7);
	  for (var row = 1; row <= numberOfWeeks; row++) {
	      text += '<tr align="center" valign="center">'
	      for (var column = 1; column <= 7; column++) {
	          text += '<td>';
	          if (day <= numberOfDays) {
		      if (currentCell <= dayOfWeekFor1stOfMonth) {
		          currentCell++;
		      } else {
		          var insertCommand = makeInsertCommand(month, year, day, form, field);
		          if (isCurrentMonth && day == currentDate.getDate()) {
                              text += '<a href="' + insertCommand + '"><b>' + day + '</b></a>';
                          } else if (isSelectedMonth && day == selectedDate.getDate()) {
                              text += '<a href="' + insertCommand + '"><i>' + day + '</i></a>';
		          } else {
		              text += '<a href="' + insertCommand + '">' + day + '</a>';
	                  }
		          day++;
		      }
		  }
		  text += "</td>";
	      }
	      text += '</tr>';
	}
	if (!isCurrentMonth) {
	    text += '<tr align="center"><td colSpan="7"><a href="' + makeURL(currentDate.getMonth(), getYear(currentDate), selectedDate, form, field) + '">Сегодня</a></td></tr>';
    	} else if (!isSelectedMonth && selectedDate != null) {
	    text += '<tr align="center"><td colSpan="7"><a href="' + makeURL(selectedDate.getMonth(), getYear(selectedDate), selectedDate, form, field) + '">К выбранному</a></td></tr>';
    	}
	text += '</table>';

	document.write(text); 
    }
   --></script>
 </head>
 <body>
  <center>
   <script language="javascript"><!--
       var currentValue = extractParameter(location.search, "currentValue", null);
       var selectedDate = null;
       if (currentValue != null && checkFormat(currentValue, "##.##.####")) {
           var str = currentValue.substring(0, 2);
           if (str.charAt(0) == "0") {
               str = str.substring(1, 2);
           }
           var d = parseInt(str);
           
           str = currentValue.substring(3, 5);
           if (str.charAt(0) == "0") {
               str = str.substring(1, 2);
           }
           var m = parseInt(str) - 1;
           
           var y = parseInt(currentValue.substring(6, 10));
           if (m >= 0 && m <= 11 && y > 0) {
               month = m;
               year = y;
               selectedDate = new Date(y, m, d);
           }
       }
       
       var month = null;
       var year = null;
       
       if (selectedDate != null) {
           month = extractIntParameter(location.search, "month", selectedDate.getMonth());
           year = extractIntParameter(location.search, "year", getYear(selectedDate));
       } else {
           var date = new Date();
           month = extractIntParameter(location.search, "month", date.getMonth());
           year = extractIntParameter(location.search, "year", getYear(date));
       }
       
       var form = extractParameter(location.search, "form", null);
       var field = extractParameter(location.search, "field", null);
       
       drawCalendar(month, year, form, field, selectedDate);
   --></script>
  </center>
 </body>
</html>
