package su.sergey.contacts.util.validation;

public class FieldException extends Exception {
    /**
     * ��� ������ : �������� null
     */
    public static final int NULL_ERROR = 1;
    
    /**
     * ��� ������ : �������� ������
     */
    public static final int SIZE_ERROR = 2;
    /**
     * ��� ������ : �������� ������
     */
    public static final int FORMAT_ERROR = 3;
    /**
     * ��� ������ : ������ �������� (�������� ������ ������)
     */
    public static final int EMPTY_ERROR = 4;

    /*��� ������*/
    private int errorCode;

    /**
     * �����������
     *
     * @param errorCode ��� ������
     */
    public FieldException(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * ���������� ��� ������
     *
     * @return ��� ������
     */
    public int getErrorCode() {
        return errorCode;
    }
}
