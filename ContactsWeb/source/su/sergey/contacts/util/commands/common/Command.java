package su.sergey.contacts.util.commands.common;

import javax.servlet.http.HttpServletRequest;

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
    String execute(HttpServletRequest request) throws CommandException;
}
