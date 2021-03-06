package su.sergey.contacts.directory;

public interface DirectoryDefinitions {
    /** ����� ������ �� �������� �� ��������� */
    int DEFAULT_BIG_PAGE_SIZE = 10;

    /** �������� - ����� ������ ��������� ������ */
    String ACTION_SHOW_DIRECTORIES_SUFFIX = "showDirectories";
    
    /** �������� - ����� ������� */
    String ACTION_SEARCH_RECORDS_SUFFIX = "searchRecords";
    
    /** �������� - ����� ���� ������� ������� */
    String ACTION_SHOW_RECORDS_SUFFIX = "showRecords";
    
    /** �������� - ����� ���������� ������� */
    String ACTION_SHOW_HEADER_SUFFIX = "showHeader";
    
    /** �������� - ����� ��������� ��� ����������� ������ */
    String ACTION_SHOW_MODIFY_RECORD_SUFFIX = "showModifyRecord";
    
    /** �������� - ���������� ������ � � ������� */
    String ACTION_ADD_RECORD_SUFFIX = "addRecord";
    
    /** �������� - �������� ������ �� ������� */
    String ACTION_REMOVE_RECORD_SUFFIX = "removeRecord";
    
    /** �������� - �������������� ������ */
    String ACTION_EDIT_RECORD_SUFFIX = "editRecord";
    
    /** �������� - ������ ������ �������� ����������� ��� ������ ��������*/
    String ACTION_PAGE_SELECT_RECORDS_SUFFIX = "pageSelectRecords";
    
    /** �������� - ������� �� �������� �� ������� ������� ������� */
    String ACTION_PAGE_RECORDS_SUFFIX = "pageRecords";
    
    /** �������� - ������� �� �������� �� ������� ������ */
    String ACTION_PAGE_DIRECTORIES_SUFFIX = "pageDirectories";

    /** �������� ������� - �������� ��������� ���� ������ */
    String PN_RECORD_PRIMARY_KEY = "recordPrimaryKey";
    
    /** �������� ������� - ��� ������� */
    String PN_TABLE_NAME = "tableName";
    
    /** �������� ������� - ��� ������� */
    String PN_SCHEMA_NAME = "schemaName";
    
    /** �������� ������� - ����� �������� */
    String PN_PAGE = "page";
    
    /** �������� ������� - �������� ������ */
    String PN_SEARCH_PARAMETER = "parameter";
    
    /** �������� ������� - �������� ������ */
    String PN_VALUE ="value";

    /** ������� ������� - �������� �������, ��������������� ��� ������ �� ����� (�������) ��������. */
    String AN_DIRECTORIES = "directories";
    
    /** ������� ������� - �������� ������ �������, ��������������� ��� ������ �� ����� (�������) ��������. */
    String AN_RECORDS = "records";
    
    /** ������� ������� - �������� ������ �������, ��������������� ��� ��������������. */
    String AN_RECORD = "record";
    
    /** ������� ������� - �������� ������� �������, ��������������� ��� ������ �� ����� (�������) ��������. */
    String AN_COLUMNS = "columns";
    
    /** ������� ������� - �������� ��� ������� */
    String AN_TABLE_NAME = "tableName";
    
    /** ������� ������� - �������� ��� ����� ������� */
    String AN_TABLE_SCHEMA = "schemaName";
    
    /** ������� ������� - �������� �������� ������� */
    String AN_TABLE_DESCRIPTION = "description";
    
    /** �������� ������� - ���������� �� �������� �������*/
    String AN_ITERATION_INFO = "iterationInfo";
    
    /** ��������� */
    String AN_MESSAGE = "message";

    /** ������ - �������� �������� �� ������� ������� */
    String SESSION_ITERATOR_RECORDS = "records";
    
    /** ������ - �������� ��������� ������ �� ������� ������� */
    String SESSION_DIRECTORY_RECORDS_SEARCH_PARAMETERS = "directoryRecordsSearchParameters";
    
    /** ������ - �������� ��������� ������ �� ������� ������� */
    String SESSION_DIRECTORIES_SEARCH_PARAMETERS = "directoriesSearchParameters";
    
    /** ������ - �������� �������� �� �������� */
    String SESSION_ITERATOR_DIRECTORIES = "directories";
    
    /** ������ - �������� ���������� ������� */
    String SESSION_DIRECTORY_META_DATA = "directoryMetadata";

    /** ������ �� ��������� ������ */
    String MESSAGE_INPUT_ERROR = "������������ ������ ����: ";
    
    /** ������ �� ��������� ������ */
    String MESSAGE_INPUT_SIZE_ERROR = "������������ ������ �������� ����: ";
    
    /** ������ �� ��������� ������ */
    String MESSAGE_INPUT_EMPTY_ERROR = "�� ������ �������� ����: ";
    
    /** ������������ ��������� ���� */
    String MESSAGE_ERROR_PK = "������ ��� ������ �� ��������� ���������� ����� ������: ";
    
    /** ������������ ��� ������� */
    String MESSAGE_ERROR_TABLE_NAME = "������ ��� ������ �� ��������� ����� �������: ";
    
    /** ������������ ����� �������� */
    String MESSAGE_ERROR_PAGE = "������ ��� ������ �� ��������� ������ ��������: ";
}
