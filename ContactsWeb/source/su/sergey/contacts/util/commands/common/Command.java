package su.sergey.contacts.util.commands.common;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.exceptions.ContactsException;

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
    String execute(HttpServletRequest request) throws ContactsException;
}
