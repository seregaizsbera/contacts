package su.sergey.contacts.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.validation.ErrorCodes;
import su.sergey.contacts.valueobjects.validation.FieldValidationError;

/**
 * Этот утилитный класс используется для получения параметров по имени из request.
 *
 * @author Сергей Богданов  
 */
public class ParameterUtil {

    /**
     * Если параметр <code>paramName</code> не пустой возращает Integer; иначе null;
     */
    public static Integer getInteger(HttpServletRequest request, String paramName, List errors) {
        String param = request.getParameter(paramName);
        Integer integer = null;
        if ((param != null) && !param.equals("")) {
            try {
                integer =  new Integer(param);
            } catch (NumberFormatException e) {
                errors.add(new FieldValidationError(paramName, ErrorCodes.NUMBER_FORMAT_VALIDATION, param));
            }
        }
        return integer;
    }
    
    public static boolean getBoolean(HttpServletRequest request, String parameterName) {
    	return request.getParameter(parameterName) != null;
    }

    /**
     * Если параметр <code>paramName</code> не пустой возращает Integer; иначе null;
     */
    public static Integer getInteger(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);
        Integer integer = null;
        if ((param != null) && !param.equals("")) {
            try {
                integer =  new Integer(param);
            } catch (NumberFormatException e) {
            }
        }
        return integer;
    }

    public static Long getLong(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);
        Long result = null;
        if ((param != null) && !param.equals("")) {
            try {
                result =  new Long(param);
            } catch (NumberFormatException e) {
            }
        }
        return result;
    }

    /**
     * Если параметр <code>paramName</code> не пустой возращает Float; иначе null;
     */
    public static Float getFloat(HttpServletRequest request, String paramName, List errors) {
        String param = request.getParameter(paramName);
        Float aFloat = null;
        if (param != null && !param.equals("")) {
            try {
                aFloat =  new Float(param);
            } catch (NumberFormatException e) {
                errors.add(new FieldValidationError(paramName, ErrorCodes.NUMBER_FORMAT_VALIDATION, param));
            }
        }
        return aFloat;
    }

    /**
     * Если параметр <code>paramName</code> не пустой возращает BigDecimal; иначе null;
     */
    public static BigDecimal getBigDecimal(HttpServletRequest request, String paramName, List errors) {
        String param = request.getParameter(paramName);
        BigDecimal bigDecimal = null;
        if (param != null && !param.equals("")) {
            try {
                bigDecimal =  new BigDecimal(param);
            } catch (NumberFormatException e) {
                errors.add(new FieldValidationError(paramName, ErrorCodes.NUMBER_FORMAT_VALIDATION, param));
            }
        }
        return bigDecimal;
    }

    /**
     * Если параметр <code>paramName</code> не пустой возращает String; иначе null;
     */
    public static Date getDate(HttpServletRequest request, String paramName, List errors) {
        String param = request.getParameter(paramName);
        Date date = null;
        if (param != null && !param.equals("")) {
            try {
                date = new DateToString().toDate(param);
            } catch (Exception e) {
            } finally {
                if (date == null) {
                   errors.add(new FieldValidationError(paramName, ErrorCodes.DATE_FORMAT_VALIDATION, param));
                }
            }
        }
        return date;
    }
    
    /**
     * Если параметр <code>paramName</code> не пустой возращает String; иначе null;
     */
    public static String getString(HttpServletRequest request, String paramName, List errors) {
        String param = request.getParameter(paramName);
        if (param != null && param.trim().length() == 0) {
                param = null;
        }
        return param;
    }
    
    /**
     * Если параметр <code>paramName</code> не пустой возращает String; иначе null;
     */
    public static String getString(HttpServletRequest request, String paramName) {
    	String result = getString(request, paramName, null);
    	return result;
    }
}
