function setFocus(formName, elementName) {
    document.forms[formName].elements[elementName].focus();
}

function isLeapYear(year) {
    return (year % 4 == 0) && !((year % 100 == 0) && (year / 100 % 4 != 0));
}

function getNumberOfDaysInMonth(month, year) {
    var days = new Array(12);
	days[0] = 31;                           // January
	days[1] = (isLeapYear(year)) ? 29 : 28; // February
	days[2] = 31;                           // March
	days[3] = 30;                           // April
	days[4] = 31;                           // May
	days[5] = 30;                           // June
	days[6] = 31;                           // July
	days[7] = 31;                           // August
	days[8] = 30;                           // September
	days[9] = 31;                           // October
	days[10] = 30;                          // November
    days[11] = 31;                          // December
    return days[month];
}

function formatDate(date) {
    var result = "";
    var day = date.getDate();
    if (day < 10) {
	    result += "0";
	}
	result += day;
	result += ".";
	var month = date.getMonth() + 1;
	if (month < 10) {
	    result += "0";
	}
	result += month;
	result += ".";
	var year = getYear(date);
	result += year;
	return result;
}

function isInteger(value) {
    return value != null && !isNaN(value) && value == parseInt(value);
}
    
function checkFormat(value, format) {
    if(value.length != format.length) {
        return false;
    }
    for(var i = 0; i < format.length; i++) {
        if (format.charAt(i) == "#" && !isInteger(value.charAt(i))) {
            return false;
        } else if (format.charAt(i) != "#" && format.charAt(i) != value.charAt(i)) {
            return false;
        }
    }
    return true;
}

function extractIntParameter(request, name, defaultValue) {
    var param = extractParameter(request, name, defaultValue);
    if (!isInteger(param)) {
	    return defaultValue;
    }
	return parseInt(param);
}
    
function extractParameter(request, name, defaultValue) {
    var pos = request.lastIndexOf(name + "=");
    if (pos == -1) {
	    return defaultValue;
	}
	var m = "";
	for (var i = pos + name.length + 1; i < request.length; i++) {
	    var c = request.charAt(i);
	    if (c == "&") {
	        break;
	    } else {
	        m += c;
	    }
	}
	if (m == "") {
	    return defaultValue;
	}
	return m;
}
