package su.sergey.contacts.util.commands.factory;

import su.sergey.contacts.util.commands.common.Command;

/**
 * ��������� ��� ����������� ������� ������.
 * 
 * @author ������ ��������
 */
public interface CommandFactory {

    /**
     * ���������� ������� ��������� ����
     *
     * @param commandClass ����� �������
     */
    Command getCommand(Class commandClass);
}
