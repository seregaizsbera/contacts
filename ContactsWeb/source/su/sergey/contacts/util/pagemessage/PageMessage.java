package su.sergey.contacts.util.pagemessage;

/**
 * Содержит сообщение для вывода на страницу
 */
public class PageMessage {
    /*Строка сообщения*/
    private String message;

    /**
     * Конструктор
     *
     * @param message Строка сообщения
     */
    public PageMessage(String message) {
        this.message = message;
    }

    /**
     * Возвращает строку сообщения
     *
     * @return Строка сообщения
     */
    public String getMessage() {
        return message;
    }
}
