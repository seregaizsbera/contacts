package su.sergey.contacts.directory;

public interface DirectoryDefinitions {
    /**
     * ��� ������� �� ���������.
     */
    String DEFAULT_DIRECTORY_CODE = "msu_departments";
    
    /** ����� ������� �� �������� �� ��������� */
    int DEFAULT_TINY_PAGE_SIZE = 2;
    
    /** ����� ������ �� �������� �� ��������� */
    int DEFAULT_BIG_PAGE_SIZE = 10;

    /** �������� - ����� ������ ��������� ������ */
    String ACTION_SHOW_DIRECTORIES_SUFFIX = "showDirectories";
    
    /** �������� - ����� ������ ������� ������� ��� ������ ������ ��������. */
    String ACTION_SELECT_RECORD_SUFFIX = "selectRecords";
    
    /** �������� - ����� ������� */
    String ACTION_SEARCH_RECORDS_SUFFIX = "searchRecords";
    
    /** �������� - ����� ���� ������� ������� */
    String ACTION_SHOW_RECORDS_SUFFIX = "showRecords";
    
    /** �������� - ����� ���������� ������� */
    String ACTION_SHOW_HEADER_SUFFIX = "showHeader";
    
    /** �������� - ��������� ���������� ������� */
    String ACTION_UPDATE_HEADER_SUFFIX = "updateHeader";
    
    /** �������� - ����� ��������� ��� ����������� ������ */
    String ACTION_SHOW_MODIFY_RECORD_SUFFIX = "showModifyRecord";
    
    /** �������� - ���������� ������ � � ������� */
    String ACTION_ADD_RECORD_SUFFIX = "addRecord";
    
    /** �������� - �������� ������ �� ������� */
    String ACTION_DELETE_RECORD_SUFFIX = "deleteRecord";
    
    /** �������� - �������������� ������ */
    String ACTION_EDIT_RECORD_SUFFIX = "editRecord";
    
    /** �������� - ������ ������ �������� ����������� ��� ������ ��������*/
    String ACTION_PAGE_SELECT_RECORDS_SUFFIX = "pageSelectRecords";
    
    /** �������� - ������� �� �������� �� ������� ������� ������� */
    String ACTION_PAGE_SHOW_RECORDS_SUFFIX = "pageShowRecords";
    
    /** �������� - ������� �� �������� �� ������� ������ */
    String ACTION_PAGE_DIRECTORIES_SUFFIX = "pageDirectories";

    /** �������� ������� - �������� ��������� ���� ������ */
    String PN_RECORD_PRIMARY_KEY = "recordPrimaryKey";
    
    /** �������� ������� - ��� ������� */
    String PN_TABLE_NAME = "tableName";
    
    /** �������� ������� - ����� �������� */
    String PN_PAGE = "Page";
    
    /** �������� ������� - �������� ������ */
    String PN_SEARCH_PARAMETER = "parameter";
    
    /** �������� ������� - ���������� ������� ������� */
    String PN_COLUMN_FULL_NAME = "columnFullName";
    
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
    
    /** ������ - �������� �������� �� �������� */
    String SESSION_ITERATOR_DIRECTORIES = "directories";
    
    /** ������ - �������� ���������� ������� */
    String SESSION_DIRECTORY_META_DATA = "directoryMetadata";

    /** ��������� �� ���������� ���������� ���������� */
    String MESSAGE_HEADER_NOT_UPDATED = "���������� ������� �� ���� ���������";
    
    /** ��������� �� �������� ���������� */
    String MESSAGE_RECORD_ADDED = "������ ���������";
    
    /** ��������� � ���������� ���������� */
    String MESSAGE_RECORD_NOT_ADDED = "������ �� ���� ��������� (��������, ������� � ����������� ������������ ���������� �����)";
    
    /** ��������� �� �������� ���������� */
    String MESSAGE_RECORD_UPDATED = "������ ���������";
    
    /** ��������� � ���������� ���������� */
    String MESSAGE_RECORD_NOT_UPDATED = "������ �� ��������� (��������, ������� � ����������� �������� ������)";
    
    /** ��������� �� �������� �������� */
    String MESSAGE_RECORD_DELETED = "������ �������";
    
    /** ��������� � ���������� ���������� */
    String MESSAGE_RECORD_NOT_DELETED = "������ �� �������";
    
    /** ������ �� ��������� ������ */
    String MESSAGE_INPUT_ERROR = "��� ������: ";
    
    /** ������ �� ��������� ������ */
    String MESSAGE_INPUT_SIZE_ERROR = "������������ ������ ����� ���� ";
    
    /** ������ �� ��������� ������ */
    String MESSAGE_INPUT_EMPTY_ERROR = "�� ������ ������ ��� ���� ";
    
    /** ������ �� ��������� ������ */
    String MESSAGE_INPUT_COMMENT_SIZE_ERROR = "������������ ������ �����������";
    
    /** ������ �� ��������� ������ */
    String MESSAGE_INPUT_COMMENT_EMPTY_ERROR = "�� ����� �����������";
    
    /** ������������ ��������� ���� */
    String MESSAGE_ERROR_PK = "������ ��� ������ �� ��������� ���������� ����� ������: ";
    
    /** ������������ ��� ������� */
    String MESSAGE_ERROR_TABLE_NAME = "������ ��� ������ �� ��������� ����� �������: ";
    
    /** ������������ ����� �������� */
    String MESSAGE_ERROR_PAGE = "������ ��� ������ �� ��������� ������ ��������: ";
    
    /** ������ � ���������� ������ */
    String MESSAGE_ERROR_SEARCH = "������ � ���������� ������. ������������ �������� ����: ";

    /** ��������� ��� �� ���������� �������� */
    String NOT_DEFINED = "";
}
