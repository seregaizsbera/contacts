package su.sergey.contacts.util.exceptions;

/**
 * ���������� ��� ������������ �������� ��������� �������
 */
public class InvalidParameterException extends Exception {
    /**
     * �������� ����������� ��������� ���������
     */
    private String paramName;
    
    /**
     * ���������
     */
    private String message;

    /**
     * ������� ����� ����������
     */
    public InvalidParameterException(String message, String paramName) {
        super(message);
		this.message = message;
        this.paramName = paramName;
    }

    /**
     * ���������� �������� ����������� ��������� ���������
     */
    public String getParamName() {
        return paramName;
    }
    
    /**
     * ���������� �������� ������
     */
    public String getMessage() {
        return message;
    }    
}
