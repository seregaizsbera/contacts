package su.sergey.contacts.util.commands.common;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

/**
 * ������� ��������� ��� ���� ������. ������� ��� ������, �����������
 * ������̣���� ��������. �������� ������� ��� ������ ���������� ���.
 *
 * @author: ������ ��������
 */
public interface Command {
    /**
     * ��������� �������. ���������� �������� ��������
     * �� ������� ���������� ������� ��� �����������
     * ���������� ���������� �������
     */
    String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException;
}
