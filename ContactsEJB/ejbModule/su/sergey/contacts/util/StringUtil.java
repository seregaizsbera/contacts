package su.sergey.contacts.util;

/**
 * Методы-утилиты, работающие со строковыми значениями.
 * @author: 
 * @version: 1.0
 */
public class StringUtil {

    public static String lpad(int source, int length, char c) {
        return lpad(Integer.toString(source), length, c);
    }

    public static String lpad(String source, int length, char c) {
        String target = source;
        for (int i = source.length(); i < length; i++) {
            target = c + target;
        }
        return target;
    }
    public static String rpad(String source, int length, char c) {
        String target = source;
        for (int i = source.length(); i < length; i++) {
            target = target + c;
        }
        return target;
    }

    public static long pow(int b) {
        return Long.parseLong(Integer.toString(1) + rpad("", b, '0'));
    }

}
