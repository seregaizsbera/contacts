package su.sergey.contacts.util.exceptions;

/**
 * Исключение при недопустимом значении параметра запроса
 */
public class InvalidParameterException extends Exception {
    /**
     * Название неправильно заданного параметра
     */
    private String paramName;
    
    /**
     * Сообщение
     */
    private String message;

    /**
     * Создаёт новое исключение
     */
    public InvalidParameterException(String message, String paramName) {
        super(message);
		this.message = message;
        this.paramName = paramName;
    }

    /**
     * Возвращает название неправильно заданного параметра
     */
    public String getParamName() {
        return paramName;
    }
    
    /**
     * Возвращает описание ошибки
     */
    public String getMessage() {
        return message;
    }    
}
