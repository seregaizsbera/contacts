package su.sergey.contacts.directory;

public interface DirectoryDefinitions {
    /**
     * Имя таблицы по умолчанию.
     */
    String DEFAULT_DIRECTORY_CODE = "msu_departments";
    
    /** Число записей на странице по умолчанию */
    int DEFAULT_TINY_PAGE_SIZE = 2;
    
    /** Число таблиц на странице по умолчанию */
    int DEFAULT_BIG_PAGE_SIZE = 10;

    /** Действие - показ списка доступных таблиц */
    String ACTION_SHOW_DIRECTORIES_SUFFIX = "showDirectories";
    
    /** Действие - показ списка записей таблицы для выбора одного значения. */
    String ACTION_SELECT_RECORD_SUFFIX = "selectRecords";
    
    /** Действие - поиск записей */
    String ACTION_SEARCH_RECORDS_SUFFIX = "searchRecords";
    
    /** Действие - показ всех записей таблицы */
    String ACTION_SHOW_RECORDS_SUFFIX = "showRecords";
    
    /** Действие - показ метаданных таблицы */
    String ACTION_SHOW_HEADER_SUFFIX = "showHeader";
    
    /** Действие - изменение метаданных таблицы */
    String ACTION_UPDATE_HEADER_SUFFIX = "updateHeader";
    
    /** Действие - показ странички для модификации записи */
    String ACTION_SHOW_MODIFY_RECORD_SUFFIX = "showModifyRecord";
    
    /** Действие - добавление записи в в таблицу */
    String ACTION_ADD_RECORD_SUFFIX = "addRecord";
    
    /** Действие - удаление записи из таблицы */
    String ACTION_DELETE_RECORD_SUFFIX = "deleteRecord";
    
    /** Действие - редактирование записи */
    String ACTION_EDIT_RECORD_SUFFIX = "editRecord";
    
    /** Действие - Выдача данных страницы справочника при выборе значения*/
    String ACTION_PAGE_SELECT_RECORDS_SUFFIX = "pageSelectRecords";
    
    /** Действие - Переход на страницу со списком записей таблицы */
    String ACTION_PAGE_SHOW_RECORDS_SUFFIX = "pageShowRecords";
    
    /** Действие - Переход на страницу со списком таблиц */
    String ACTION_PAGE_DIRECTORIES_SUFFIX = "pageDirectories";

    /** Параметр запроса - содержит первичный ключ записи */
    String PN_RECORD_PRIMARY_KEY = "recordPrimaryKey";
    
    /** Параметр запроса - имя таблицы */
    String PN_TABLE_NAME = "tableName";
    
    /** Параметр запроса - номер страницы */
    String PN_PAGE = "Page";
    
    /** Параметр запроса - параметр поиска */
    String PN_SEARCH_PARAMETER = "parameter";
    
    /** Параметр запроса - коментарий столбца таблицы */
    String PN_COLUMN_FULL_NAME = "columnFullName";
    
    /** Параметр запроса - значение записи */
    String PN_VALUE ="value";

    /** Атрибут запроса - содержит таблицы, предназначенные для показа на одной (текущей) странице. */
    String AN_DIRECTORIES = "directories";
    
    /** Атрибут запроса - содержит записи таблицы, предназначенные для показа на одной (текущей) странице. */
    String AN_RECORDS = "records";
    
    /** Атрибут запроса - содержит запись таблицы, предназначенную для редактирования. */
    String AN_RECORD = "record";
    
    /** Атрибут запроса - содержит столбцы таблицы, предназначенные для показа на одной (текущей) странице. */
    String AN_COLUMNS = "columns";
    
    /** Атрибут запроса - содержит имя таблицы */
    String AN_TABLE_NAME = "tableName";
    
    /** Атрибут запроса - содержит имя схемы таблицы */
    String AN_TABLE_SCHEMA = "schemaName";
    
    /** Атрибут запроса - содержит описание таблицы */
    String AN_TABLE_DESCRIPTION = "description";
    
    /** Аттрибут запроса - информация об итерации страниц*/
    String AN_ITERATION_INFO = "iterationInfo";
    
    /** Сообщение */
    String AN_MESSAGE = "message";

    /** Сессия - содержит итератор по записям таблицы */
    String SESSION_ITERATOR_RECORDS = "records";
    
    /** Сессия - содержит итератор по таблицам */
    String SESSION_ITERATOR_DIRECTORIES = "directories";
    
    /** Сессия - содержит метаданные таблицы */
    String SESSION_DIRECTORY_META_DATA = "directoryMetadata";

    /** Сообщение об неуспешном обновлении метаданных */
    String MESSAGE_HEADER_NOT_UPDATED = "Метаданные таблицы не были обновлены";
    
    /** Сообщение об успешном добавлении */
    String MESSAGE_RECORD_ADDED = "Запись добавлена";
    
    /** Сообщение о неуспешном добавлении */
    String MESSAGE_RECORD_NOT_ADDED = "Запись не была добавлена (возможно, причина в ограничении уникальности первичного ключа)";
    
    /** Сообщение об успешном обновлении */
    String MESSAGE_RECORD_UPDATED = "Запись обновлена";
    
    /** Сообщение о неуспешном обновлении */
    String MESSAGE_RECORD_NOT_UPDATED = "Запись не обновлена (возможно, причина в некорректно заданных данных)";
    
    /** Сообщение об успешном удалении */
    String MESSAGE_RECORD_DELETED = "Запись удалена";
    
    /** Сообщение о неуспешном обновлении */
    String MESSAGE_RECORD_NOT_DELETED = "Запись не удалена";
    
    /** Ошибка во введенных данных */
    String MESSAGE_INPUT_ERROR = "код ошибки: ";
    
    /** Ошибка во введенных данных */
    String MESSAGE_INPUT_SIZE_ERROR = "Недопустимый размер имяни поля ";
    
    /** Ошибка во введенных данных */
    String MESSAGE_INPUT_EMPTY_ERROR = "Не задано полное имя поля ";
    
    /** Ошибка во введенных данных */
    String MESSAGE_INPUT_COMMENT_SIZE_ERROR = "Недопустимый размер комментария";
    
    /** Ошибка во введенных данных */
    String MESSAGE_INPUT_COMMENT_EMPTY_ERROR = "Не задан комментарий";
    
    /** Неправильный первичный ключ */
    String MESSAGE_ERROR_PK = "Ошибка при взятии из параметра первичного ключа записи: ";
    
    /** Неправильное имя таблицы */
    String MESSAGE_ERROR_TABLE_NAME = "Ошибка при взятии из параметра имени таблицы: ";
    
    /** Неправильный номер страницы */
    String MESSAGE_ERROR_PAGE = "Ошибка при взятии из параметра номера страницы: ";
    
    /** Ошибка в параметрах поиска */
    String MESSAGE_ERROR_SEARCH = "Ошибка в параметрах поиска. Неправильное значение поля: ";

    /** Константа для не описанного значения */
    String NOT_DEFINED = "";
}
