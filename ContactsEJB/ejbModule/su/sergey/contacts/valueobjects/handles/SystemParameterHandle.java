package su.sergey.contacts.valueobjects.handles;

/**
 * Дескриптор системного параметра.
 * @author 
 * @version 1.0 
 */
public class SystemParameterHandle {
    /**
     * Имя параметра.
     */
    private String name;

    /**
     * Создает объект.
     */
    public SystemParameterHandle(String name) {
        this.name = name;
    }
    /**
     * Возвращает имя параметра.
     */
    public String getName() {
        return name;
    }
}