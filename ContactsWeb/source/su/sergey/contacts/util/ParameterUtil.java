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
 * ���� ��������� ����� ������������ ��� ��������� ���������� �� ����� �� request.
 *
 * @author ������ ��������  
 */
public class ParameterUtil {

    /**
     * ���� �������� <code>paramName</code> �� ������ ��������� Integer; ����� null;
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

    /**
     * ���� �������� <code>paramName</code> �� ������ ��������� Integer; ����� null;
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

    /**
     * ���� �������� <code>paramName</code> �� ������ ��������� Float; ����� null;
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
     * ���� �������� <code>paramName</code> �� ������ ��������� BigDecimal; ����� null;
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
     * ���� �������� <code>paramName</code> �� ������ ��������� String; ����� null;
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
    
    private static boolean needCharsetCorrection(HttpServletRequest request) {
    	String check = request.getParameter(RequestConstants.PN_CHECK);
    	if (check == null) {
    		return false;
    	}
    	boolean result = !check.equals(RequestConstants.CHECK);
    	return result;
    }


    /**
     * ���� �������� <code>paramName</code> �� ������ ��������� String; ����� null;
     */
    public static String getString(HttpServletRequest request, String paramName, List errors) {
        String param = request.getParameter(paramName);
        if (param != null) {
            if (param.trim().length() == 0) {
                param = null;
            } else if (needCharsetCorrection(request)) {
            	try {
	            	byte bytes[] = param.getBytes("ISO8859-5");
	            	param = new String(bytes, "UTF-8");
            	} catch (UnsupportedEncodingException e) {}
            }
        }
        return param;
    }
    
    /**
     * ���� �������� <code>paramName</code> �� ������ ��������� String; ����� null;
     */
    public static String getString(HttpServletRequest request, String paramName) {
    	String result = getString(request, paramName, null);
    	return result;
    }
}
