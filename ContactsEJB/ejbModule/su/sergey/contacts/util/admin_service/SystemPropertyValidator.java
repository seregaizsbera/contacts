/**
 * Класс обеспечивающий проверку вводимых пользователем системных параметров
 * @author: 
 * @date 22/08/2002
 */
package su.sergey.contacts.util.admin_service;

import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.text.ParseException;
import java.util.Date;

public class SystemPropertyValidator {
    /**
     * Тип - время (чч:мм:сс)
     */
    public final static String TYPE_TIME = "TIME";


    private static SystemPropertyValidator instance;

    protected SystemPropertyValidator() { }

    public static SystemPropertyValidator getInstance() {
        if (instance == null) {
            instance = new SystemPropertyValidator();
        }
        return instance;
    }

    /**
     * Проверяет правильность введенного значения
     * @param value проверяемое значение
     * @param type тип требуемой проверки
     * @param pattern шаблон, если он необходим для данного типа
     */
    public boolean checkValidity(String value, String type, String pattern) {
        boolean validity = true;
        if (type.equals(TYPE_TIME)) {
            return vaidateTime(value, pattern);
        }
        return validity;
    }

    protected boolean vaidateTime(String value, String pattern) {
        Date time = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            ParsePosition pos = new ParsePosition(0);
            dateFormat.setLenient(false);
            time = dateFormat.parse(value, pos);
        } catch (Exception e) {
            time = null;
        }
        return time != null ? true : false;
    }


}
