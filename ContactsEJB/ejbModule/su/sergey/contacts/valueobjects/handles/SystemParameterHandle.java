package su.sergey.contacts.valueobjects.handles;

/**
 * ���������� ���������� ���������.
 * @author 
 * @version 1.0 
 */
public class SystemParameterHandle {
    /**
     * ��� ���������.
     */
    private String name;

    /**
     * ������� ������.
     */
    public SystemParameterHandle(String name) {
        this.name = name;
    }
    /**
     * ���������� ��� ���������.
     */
    public String getName() {
        return name;
    }
}