
package su.sergey.contacts.util.admin_service;
/**
 * Класс, служащий для хранения системных опций
 * @author: 
 * @date 20/08/2002
 */

public class SystemProperty implements java.io.Serializable {
    /**
     * Имя
     */
    private String name;
    /**
     * Значение
     */
    private String value;
    /**
     * Необязательное словесное описание
     */
    private String description;
    /**
     * Необязательное значение тип валидации.
     * Зарегестрированные типы : 'TIME'
     */
    private String type;

    /**
     * Необязательное значение, необходимое для валидации (паттерн).
     */
    private String validity;

    public final void setName(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public final void setValue(String value) {
        this.value = value;
    }

    public final String getValue() {
        return value;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    public final String getDescription() {
        return description != null ? description : "";
    }

    public final void setType(String type) {
        this.type = type;
    }

    public final String getType() {
        return type;
    }

    public final void setValidity(String validity) {
        this.validity = validity;
    }

    public final String getValidity() {
        return validity;
    }

}
