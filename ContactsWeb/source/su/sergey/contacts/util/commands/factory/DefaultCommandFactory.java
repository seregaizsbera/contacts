package su.sergey.contacts.util.commands.factory;

import java.util.HashMap;

import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.Command;

/**
 * ���������� ����������� ������� ������
 *
 */
public class DefaultCommandFactory implements CommandFactory {
    /** ������������ ��������� ������� */
    private static CommandFactory instance = null;

    /** ������ ���������� ������ � ������������ � �� ������� */
    private HashMap commandsHash;

    /** ����������� */
    private DefaultCommandFactory() {
        commandsHash = new HashMap();
    }

    /**
     * ���������� �������. ���� ������� ����� �� ��������������, �� ���������
     * ����� � �����������, ����� ������������ �����Σ���� �������
     *
     * @param commandClass ����� ��������. ����� ������ �������������
     * ���������� <code>Command</code>
     * @return ������� ��������� ������ ��� <code>null</code> ���� �������
     * ������� �� �������.
     */
    public Command getCommand(Class commandClass) {
        Command command = (Command) commandsHash.get(commandClass);
        if (command == null) {
          try {
              command = createCommand(commandClass);
          } catch (ContactsException e) {
          	  e.printStackTrace();
          	  command = null;
          }
        }
        return command;
    }

    /** ������� ������� ��������� ���� */
    private Command createCommand(Class commandClass) throws ContactsException {
    	if (commandClass == null) {
    		return null;
    	}
        Command command = null;
        if (Command.class.isAssignableFrom(commandClass)) {
            try {
                command = (Command) commandClass.newInstance();
            } catch (IllegalAccessException e) {
            	throw new ContactsException(e);
            } catch (InstantiationException e) {
            	throw new ContactsException(e);
            }
        } else {
            throw new ContactsException("Command class must implement Command interface");
        }
        if (command != null) {
            commandsHash.put(commandClass, command);
        }
        return command;
    }
    
    /**
     * ���������� ��������� �������
     */
    public static CommandFactory getInstance() {
    	if (instance == null) {
			instance = new DefaultCommandFactory();
    	}
        return instance;
    }
}
