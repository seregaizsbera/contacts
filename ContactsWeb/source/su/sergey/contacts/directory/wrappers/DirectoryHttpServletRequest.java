package su.sergey.contacts.directory.wrappers;

import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.impl.DefaultDirectoryRecord;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectoryRecordSearchParameters;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectorySearchParameters;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.pageiterator.businessdelegate.PageIteratorBusinessDelegate;
import su.sergey.contacts.util.ContactsDateTimeFormat;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.pageiteration.PageIterationInfo;
import su.sergey.contacts.util.pagemessage.PageMessage;
import su.sergey.contacts.validation.NotNullValidator;
import su.sergey.contacts.validation.NumberValidator;
import su.sergey.contacts.validation.StringSizeValidator;
import su.sergey.contacts.validation.Validator;


public class DirectoryHttpServletRequest implements DirectoryDefinitions {
    private HttpServletRequest request;
    private DirectoryHttpSession session;

    public DirectoryHttpServletRequest(HttpServletRequest request) {
        this.request = request;
        this.session = new DirectoryHttpSession(request.getSession());
    }

    private void setSearchValue(String value, DirectoryColumnMetadata column, Properties parameters) {
        parameters.put(column.getDbColumnName(), value);
    }

    /**
     * ��������� ��������� ������������� �������� �� ������������
     * � ������ ����������� ���������� �������� ������� FieldValidationException
     */
    private void validateValue(String value, DirectoryColumnMetadata column)
            throws FieldValidationException {
        Validator notNullValidator = new NotNullValidator(column.getFullName());
        Validator numberValidator = new NumberValidator(column.getFullName());
        int size = column.getWidth() == -1 ? Integer.MAX_VALUE : column.getWidth();
        int type = column.getType();
        Validator stringSizeValidator = new StringSizeValidator(column.getFullName(), 1, size);
        SimpleDateFormat dateFormat = new SimpleDateFormat(ContactsDateTimeFormat.DATABASE_DATE_FORMAT);
        SimpleDateFormat timeFormat = new SimpleDateFormat(ContactsDateTimeFormat.DATABASE_TIME_FORMAT);
        SimpleDateFormat timestampFormat = new SimpleDateFormat(ContactsDateTimeFormat.DATABASE_TIMESTAMP_FORMAT);
        if (column.isGenerated()) {
        	return;
        }
        boolean isNullable = column.isNullable();
    	if (!isNullable && notNullValidator.validate(value) != null) {
    		throw new FieldValidationException(MESSAGE_INPUT_EMPTY_ERROR + column.getDbColumnName());
    	}
        if (type == Types.SMALLINT || type == Types.INTEGER) {
        	if (numberValidator.validate(value) != null) {
        		throw new FieldValidationException(MESSAGE_INPUT_ERROR + column.getDbColumnName());
        	}
        	if (stringSizeValidator.validate(value) != null) {
        		throw new FieldValidationException(MESSAGE_INPUT_SIZE_ERROR + column.getDbColumnName());
        	}
        } else if (type == Types.DATE) {
        	if (value != null) {
        	    try {
        	    	dateFormat.parse(value);
        	    } catch (ParseException e) {
	        		throw new FieldValidationException(MESSAGE_INPUT_ERROR + column.getDbColumnName());
        	    }
        	}
        } else if (type == Types.TIME) {
        	if (value != null) {
        	    try {
        	    	timeFormat.parse(value);
        	    } catch (ParseException e) {
	        		throw new FieldValidationException(MESSAGE_INPUT_ERROR + column.getDbColumnName());
        	    }
        	}
        } else if (type == Types.TIMESTAMP) {
        	if (value != null) {
        	    try {
        	    	timestampFormat.parse(value);
        	    } catch (ParseException e) {
	        		throw new FieldValidationException(MESSAGE_INPUT_ERROR + column.getDbColumnName());
        	    }
        	}
        } else {
        	if (stringSizeValidator.validate(value) != null) {
        		throw new FieldValidationException(MESSAGE_INPUT_SIZE_ERROR + column.getDbColumnName());
        	}
        }
    }

    /**
     * ����� ��������� ������ - ������� ������� "������" ��� ������ ��������
     * ���������� �����������
     */
    public DirectorySearchParameters getSearchParameters() {
    	DirectorySearchParameters result = new DirectorySearchParameters();
    	result.setSchemaName(ParameterUtil.getString(request, PN_SCHEMA_NAME));
    	result.setTableName(ParameterUtil.getString(request, PN_TABLE_NAME));
        return result;
    }

    /**
     * ����� ��������� ������ - ������� ������� "������" ��� ������ ��������
     * ���������� ������ �� �����������
     */
    public DirectoryRecordSearchParameters getSearchParameters(DirectoryColumnMetadata[] columns)
            throws FieldValidationException {
        Properties parameters = new Properties();
        String value;
        for (int i = 0; i < columns.length; i++) {
            value = ParameterUtil.getString(request, PN_SEARCH_PARAMETER + i);
            if (value == null) {
            	continue;
            }
            setSearchValue(value, columns[i], parameters);
        }
        return new DirectoryRecordSearchParameters(new DirectoryMetadataHandle(getTableName()), parameters);
    }

    /**
     * ���������� ��������� ������� - ����� �������� ��� �����������
     */
    public Integer getPage() throws FieldValidationException {
        String value = request.getParameter(PN_PAGE);
        if (value == null) {
        	return null;
        }
        if (new StringSizeValidator(PN_PAGE, 1, Integer.MAX_VALUE).validate(value) != null) {
    		throw new FieldValidationException(MESSAGE_ERROR_PAGE);
        }
        if (new NumberValidator(PN_PAGE).validate(value) != null) {
    		throw new FieldValidationException(MESSAGE_ERROR_PAGE);
        }
        Integer result = Integer.valueOf(value);
        return result;
    }

    /**
     * ���������� ��� �������, �� ������� ����� ���������� ��������
     */
    public String getTableName() throws FieldValidationException {
        String value = request.getParameter(PN_TABLE_NAME);
        if (new NotNullValidator(PN_TABLE_NAME).validate(value) != null) {
    		throw new FieldValidationException(MESSAGE_ERROR_TABLE_NAME);
        }
        if (new StringSizeValidator(PN_TABLE_NAME, 1, Integer.MAX_VALUE).validate(value) != null) {
    		throw new FieldValidationException(MESSAGE_ERROR_TABLE_NAME);
        }
        return value;
    }

    /**
     * ���������� primary key �������������� ������
     */
    public Integer getRecordPrimaryKey() throws FieldValidationException {
        String recordPrimaryKey = request.getParameter(PN_RECORD_PRIMARY_KEY);
        if (new NotNullValidator(PN_RECORD_PRIMARY_KEY).validate(recordPrimaryKey) != null) {
    		throw new FieldValidationException(MESSAGE_ERROR_PK);
        }
        if (new StringSizeValidator(PN_RECORD_PRIMARY_KEY, 1, Integer.MAX_VALUE).validate(recordPrimaryKey) != null) {
    		throw new FieldValidationException(MESSAGE_ERROR_PK);
        }
        if (new NumberValidator(PN_RECORD_PRIMARY_KEY).validate(recordPrimaryKey) != null) {
    		throw new FieldValidationException(MESSAGE_ERROR_PK);
        }
        Integer result = new Integer(recordPrimaryKey);
        return result;
    }
    
    /**
     * ����� ������ � ������ �� �����
     */
    public DirectoryRecord getDirectoryRecordFromForm(DirectoryColumnMetadata[] columns)
            throws FieldValidationException {
        String[] values = new String[columns.length];
        List errors = new ArrayList();
        Integer oid = ParameterUtil.getInteger(request, PN_RECORD_PRIMARY_KEY, errors);
        if (!errors.isEmpty()) {
        	throw new FieldValidationException(MESSAGE_ERROR_PK);
        }
        for (int i = 0; i < values.length; i++) {
            String currentValue = ParameterUtil.getString(request, PN_VALUE + i);
            validateValue(currentValue, columns[i]);
            values[i] = currentValue;
        }
        return new DefaultDirectoryRecord(oid, values);
    }

    /**
     * ���������� request
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * ��������� ������� �����������, ��������������� ��� ������ �� ����� �������� � �������� ��������� �������
     * � ����� ������ ��������� �����������
     */
    public void setDirectoryMetadata(DirectoryMetadata directoryMetadata) {
        Collection metadataCollection = new ArrayList();
        for (int i = 0; i < directoryMetadata.getColumnMetadata().length; i++) {
            metadataCollection.add(directoryMetadata.getColumnMetadata()[i]);
        }
        request.setAttribute(AN_COLUMNS, metadataCollection);
        request.setAttribute(AN_TABLE_SCHEMA, directoryMetadata.getDbSchemaName());
        request.setAttribute(AN_TABLE_NAME, directoryMetadata.getDbTableName());
        request.setAttribute(AN_TABLE_DESCRIPTION, directoryMetadata.getDescription());
    }

    /**
     * ����� ���������� ����������� �� ������
     * ��������� �� � �������� ��������� �������
     */
    public void setDirectoryMetadata() throws ServletException {
        setDirectoryMetadata(session.getDirectoryMetadata());
    }

    /**
     * ������������� �������� � ������
     */
    public void setSessionPageIterator(PageIteratorBusinessDelegate iterator, String iteratorName) throws ServletException {
        session.setPageIterator(iterator, iteratorName);
    }

    /**
     * ����� �������� �� ������
     */
    public PageIteratorBusinessDelegate getSessionPageIterator(String iteratorName) throws ServletException {
        return session.getPageIterator(iteratorName);
    }

    /**
     * ������� �������� �� ������
     */
    public void removeSessionPageIterator(String iteratorName) throws ServletException {
        session.removePageIterator(iteratorName);
    }
    
    /**
     * ���������� ���������� ����������� � ������ � � �������� �������
     */
    public void setSessionDirectoryMetadata(DirectoryMetadata directoryMetadata) throws ServletException {
        session.setDirectoryMetadata(directoryMetadata);
        setDirectoryMetadata();
    }

    /**
     * ���������� � ������ ��������� ������ ������
     */
    public void setSessionDirectorySearchParameters(DirectorySearchParameters searchParameters) throws ServletException {
        session.setDirectorySearchParameters(searchParameters);
    }

    /**
     * ���������� � ������ ��������� ������ �� ������� �������
     */
    public void setSessionDirectoryRecordSearchParameters(DirectoryRecordSearchParameters searchParameters) throws ServletException {
        session.setDirectoryRecordSearchParameters(searchParameters);
    }

    /**
     * ��������� �����������, ��������������� ��� ������ �� ����� �������� � ��������� �������
     */
    public void setDirectories(DirectoryMetadata[] directories) {
        Collection directoriesCollection = new ArrayList();
        for (int i = 0; i < directories.length; i++) {
            directoriesCollection.add(directories[i]);
        }
        request.setAttribute(AN_DIRECTORIES, directoriesCollection);
    }

    /**
     * ��������� ������ �����������, ��������������� ��� ������ �� ����� �������� � ��������� �������
     */
    public void setRecords(DirectoryRecord[] records) {
        Collection recordsCollection = new ArrayList();
        for (int i = 0; i < records.length; i++) {
            recordsCollection.add(records[i]);
        }
        request.setAttribute(AN_RECORDS, recordsCollection);
    }

    /**
     * ��������� ������ �����������, ��������������� ��� �������������� � ��������� �������
     */
    public void setRecord(DirectoryRecord record) {
        request.setAttribute(AN_RECORD, record);
    }

    /**
     * ��������� iteration info ��� ������������ ������� � ��������� �������
     */
    public void setPageIterationInfo(PageIteratorBusinessDelegate iterator)
            throws ContactsException {
        PageIterationInfo iterationInfo = new PageIterationInfo(
                                               iterator.getNumberOfPages(),
                                               iterator.getCurrentPage(),
                                               iterator.getPageSize());
        request.setAttribute(AN_ITERATION_INFO, iterationInfo);
    }

    public void setMessage(String message) {
        request.setAttribute(AN_MESSAGE, new PageMessage(message));
    }
}
