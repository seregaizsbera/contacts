package su.sergey.contacts.util.validation;

public class FieldException extends Exception {
    /**
     * Код ошибки : значение null
     */
    public static final int NULL_ERROR = 1;
    
    /**
     * Код ошибки : Неверный размер
     */
    public static final int SIZE_ERROR = 2;
    /**
     * Код ошибки : Неверный формат
     */
    public static final int FORMAT_ERROR = 3;
    /**
     * Код ошибки : Пустое значение (например пустая строка)
     */
    public static final int EMPTY_ERROR = 4;

    /*Код ошибки*/
    private int errorCode;

    /**
     * Конструктор
     *
     * @param errorCode Код ошибки
     */
    public FieldException(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Возвращает код ошибки
     *
     * @return Код ошибки
     */
    public int getErrorCode() {
        return errorCode;
    }
}
