package su.sergey.contacts.util.commands.factory;

import java.util.HashMap;

import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.commands.common.CommandException;

/**
 * Реализация абстрактной фабрики команд
 *
 */
public class DefaultCommandFactory implements CommandFactory {
    /** Единственный экземпляр фабрики */
    private static CommandFactory instance = null;

    /** Хранит экземпляры команд в соответствии с их классом */
    private HashMap commandsHash;

    /** Конструктор */
    private DefaultCommandFactory() {
        commandsHash = new HashMap();
    }

    /**
     * Возвращает команду. Если команда ранее не использовалась, то создаётся
     * новая и сохраняется, иначе возвращается сохранённая команда
     *
     * @param commandClass Класс комманды. Класс должен удовлетворять
     * интерфейсу <code>Command</code>
     * @return Команду заданного класса или <code>null</code> если создать
     * команду не удалось.
     */
    public Command getCommand(Class commandClass) {
        Command command = (Command) commandsHash.get(commandClass);
        if (command == null) {
          try {
              command = createCommand(commandClass);
          } catch (CommandException e) {
          	  e.printStackTrace();
          	  command = null;
          }
        }
        return command;
    }

    /** Создаёт команду заданного типа */
    private Command createCommand(Class commandClass) throws CommandException {
    	if (commandClass == null) {
    		return null;
    	}
        Command command = null;
        if (Command.class.isAssignableFrom(commandClass)) {
            try {
                command = (Command) commandClass.newInstance();
            } catch (IllegalAccessException e) {
            	throw new CommandException(e);
            } catch (InstantiationException e) {
            	throw new CommandException(e);
            }
        } else {
            throw new CommandException("Command class must implement Command interface");
        }
        if (command != null) {
            commandsHash.put(commandClass, command);
        }
        return command;
    }
    
    /**
     * Возвращает экземпляр фабрики
     */
    public static CommandFactory getInstance() {
    	if (instance == null) {
			instance = new DefaultCommandFactory();
    	}
        return instance;
    }
}
