package su.sergey.contacts.util.validation;

import java.math.BigDecimal;
import java.util.Date;

public interface FieldsValidator {

    /**
     * ��������� ������.
     *
     * @param value ������ ��� ��������
     * @param maxSize ������������ ������ ����������� ������
     * @param canBeEmpty ���������� �����-�� ������ ���� ������.
     * @return ������ ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    String validateString(String value, int maxSize, boolean canBeEmpty)
            throws FieldException;

    /**
     * ��������� ������.
     *
     * @param value ������ ��� ��������
     * @param canBeEmpty ���������� �����-�� ������ ���� ������.
     * @return ������ ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    String validateString(String value, boolean canBeEmpty)
            throws FieldException;

    /**
     * ��������� ������.
     *
     * @param value ������ ��� ��������
     * @param size ������ ������ ����������� ������
     * @return ������ ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    String validateString(String value, int size)
            throws FieldException;

    /**
     * ��������� ����� �����.
     *
     * @param value ������ ��� ��������
     * @return ����� �����, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    int validateInt(String value) throws FieldException;

    /**
     * ��������� ����� �����.
     *
     * @param value ������ ��� ��������
     * @param limit ��������� �������� (������� ��� ������ - � ����������� ��
     * ��������� <code>isMaxLimit</code>)
     * @param isMaxLimit <code>true</code> ���� ������� ������� ���������
     * ��������
     * @return ����� �����, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    int validateInt(String value, int limit, boolean isMaxLimit)
            throws FieldException;

    /**
     * ��������� ����� �����.
     *
     * @param min ����������� �������� �����
     * @param max ������������ �������� �����
     * @return ����� �����, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    int validateInt(String value, int min, int max) throws FieldException;

    /**
     * ��������� ����.
     *
     * @param value ������ ��� ��������
     * @param pattern ������ ��� ����
     * @return ����, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    Date validateDate(String value, String pattern) throws FieldException;

    /**
     * ��������� <code>BigDecimal</code>.
     *
     * @param value ������ ��� ��������
     * @return <code>BigDecimal</code>, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    BigDecimal validateBigDecimal(String value) throws FieldException;
}
