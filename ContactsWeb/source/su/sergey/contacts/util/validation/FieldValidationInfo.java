package su.sergey.contacts.util.validation;

/**
 * Этот класс используется для вывода на jsp название полей, которые
 * были неправильно заполнены.
 * @author Сергей Богданов
 * @version  1.0
 */
public class FieldValidationInfo {

    private String fieldTitle;

    private String message;

    /**
     * Создает объект <tt>FieldValidationInfo</tt>.
     * @param   название неправильно заполненного поля
     * @param   сообщение о причинах ошибки
     */
    public FieldValidationInfo(String fieldTitle, String message) {
        this.fieldTitle = fieldTitle;
        this.message = message;
    }

    /**
     * Возвращает название неправильно заполненного поля.
     * @return  название неправильно заполненного поля
     */
    public String getFieldTitle() {
        return fieldTitle;
    }

    /**
     * Возвращает сообщение о причинах ошибки.
     * @return  сообщение о причинах ошибки
     */
    public String getMessage() {
        return message;
    }
}
