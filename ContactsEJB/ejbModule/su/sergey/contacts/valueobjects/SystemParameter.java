package su.sergey.contacts.valueobjects;

/**
 * �������� ������ �� ����� ��������� ������� (������������ �� �������� "���������")
 * @author: 
 * @version: 1.0
 */
public interface SystemParameter {
    /**
     * ���������� ��� ���������.
     */
    String getName();

    /**
     * ������������� ��� ���������.
     */
    void setName(String name);

    /**
     * ���������� ��� ���������.
     */
    String getType();

    /**
     * ������������� ��� ���������.
     */
    void setType(String type);

    /**
     * ���������� ������������ ����� ���������.
     */
    int getLength();

    /**
     * ������������� ������������ ����� ���������.
     */
    void setLength(int length);

    /**
     * ���������� �������� ���������.
     */
    String getValue();

    /**
     * ������������� �������� ���������.
     */
    void setValue(String value);
}
