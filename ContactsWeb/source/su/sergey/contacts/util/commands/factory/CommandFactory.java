package su.sergey.contacts.util.commands.factory;

import su.sergey.contacts.util.commands.common.Command;

/**
 * Интерфейс для абстрактной фабрики команд.
 * 
 * @author Сергей Богданов
 */
public interface CommandFactory {

    /**
     * Возвращает команду заданного типа
     *
     * @param commandClass Класс команды
     */
    Command getCommand(Class commandClass);
}
