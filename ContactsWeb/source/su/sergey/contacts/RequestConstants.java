package su.sergey.contacts;

/**
 * Константы запроса.
 *
 * @author   
 */
public interface RequestConstants {
    /** Аттрибут запроса -  содержит сообщение.*/
    String AN_MESSAGE = "message";

    /** Аттрибут запроса -  содержит тип сообщения. */
    String AN_MESSAGE_TYPE = "messageType";

    /** Аттрибут запроса - содержит информацию об итерации страниц. */
    String AN_ITERATION_INFO = "iterationInfo";

    /** Аттрибут запроса - содержит ошибки валидации. */
    String AN_VALIDATION_ERRORS = "validationErrors";

    /** Аттрибут запроса - содержит коллекцию SystemProperty объектов. */
    String AN_SYSPROPS = "systemProperties";
    
    /** Аттрибут запроса - содержит сообщение для страницы ошибки. */
    String AN_ERROR_MESSAGE = "errorMess";
    
    /** Аттрибут запроса - ссылка, куда переходить со страницы */
    String AN_NEXT_URL = "nextURL";

    /** Аттрибут запроса - запись добавляется или изменяется. */
    String AN_IS_NEW = "isNew";
    
    /** Аттрибут запроса содержит список таблиц */
    String AN_DIRECTORIES = "directories";
    
    /** Параметр запроса - содержит имя столбца для сортировки. */
    String PN_COLUMN = "column";

    /** Параметр запроса - содержит направление сортировки. */
    String PN_DIRECTION = "dir";
    
    /** Параметр запроса - содержит название действия. */
    String PN_ACTION = "action";
    
    /** Параметр запроса содержит номер страницы */
    String PN_PAGE = "Page";
}
