package su.sergey.contacts.util.commands.common;

import javax.servlet.http.HttpServletRequest;

/**
 * Базовый интерфейс для всех команд. Команда это объект, выполняющий
 * определённое действие. Например команда для поиска физических лиц.
 *
 * @author: Сергей Богданов
 */
public interface Command {
    /**
     * Запустить команду. Возвращает название страницы
     * на которую необходимо перейти для отображения
     * результата выполнения команды
     */
    String execute(HttpServletRequest request) throws CommandException;
}
