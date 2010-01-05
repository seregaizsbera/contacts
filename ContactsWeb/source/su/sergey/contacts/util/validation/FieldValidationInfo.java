package su.sergey.contacts.util.validation;

/**
 * ���� ����� ������������ ��� ������ �� jsp �������� �����, �������
 * ���� ����������� ���������.
 * @author ������ ��������
 * @version  1.0
 */
public class FieldValidationInfo {

    private String fieldTitle;

    private String message;

    /**
     * ������� ������ <tt>FieldValidationInfo</tt>.
     * @param   �������� ����������� ������������ ����
     * @param   ��������� � �������� ������
     */
    public FieldValidationInfo(String fieldTitle, String message) {
        this.fieldTitle = fieldTitle;
        this.message = message;
    }

    /**
     * ���������� �������� ����������� ������������ ����.
     * @return  �������� ����������� ������������ ����
     */
    public String getFieldTitle() {
        return fieldTitle;
    }

    /**
     * ���������� ��������� � �������� ������.
     * @return  ��������� � �������� ������
     */
    public String getMessage() {
        return message;
    }
}
