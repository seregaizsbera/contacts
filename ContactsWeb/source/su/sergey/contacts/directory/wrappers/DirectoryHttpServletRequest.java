package su.sergey.contacts.directory.wrappers;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.businessdelegate.PageIteratorBusinessDelegate;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.pageiteration.PageIterationInfo;
import su.sergey.contacts.util.pagemessage.PageMessage;
import su.sergey.contacts.validation.NotNullValidator;
import su.sergey.contacts.validation.NumberValidator;
import su.sergey.contacts.validation.StringSizeValidator;
import su.sergey.contacts.validation.ValidatorsCol;
import su.sergey.contacts.valueobjects.DirectoryColumnMetadata;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.DirectoryRecord;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.valueobjects.impl.DefaultDirectoryRecord;
import su.sergey.contacts.valueobjects.searchparameters.DirectoryRecordSearchParameters;


public class DirectoryHttpServletRequest implements DirectoryDefinitions {
    private HttpServletRequest request;
    private DirectoryHttpSession session;

    public DirectoryHttpServletRequest(HttpServletRequest request) {
        this.request = request;
        this.session = new DirectoryHttpSession(request.getSession());
    }

    /**
     * ��������� ��������� ������������� �������� �� ������������
     * � ������ ����������� ���������� �������� ������� IllegalArgumenException
     */
    private void setSearchValue(String value, DirectoryColumnMetadata column, Properties parameters)
            throws MultipleFieldsValidationException {
        if (column.getType() == Types.SMALLINT || column.getType() == Types.INTEGER) {
        	List errors = null;
        	errors = ValidatorsCol.addError(errors, new NotNullValidator(column.getFullName()).validate(value));
        	errors = ValidatorsCol.addError(errors, new NumberValidator(column.getFullName()).validate(value));
        	errors = ValidatorsCol.addError(errors, new StringSizeValidator(column.getFullName(), 1, column.getWidth()).validate(value));
        	if (errors != null) {
        		throw new MultipleFieldsValidationException(MESSAGE_INPUT_ERROR, errors);
        	}
            parameters.put(column.getDbColumnName(), value);
        } else {
        	List errors = null;
        	errors = ValidatorsCol.addError(errors, new NotNullValidator(column.getFullName()).validate(value));
        	errors = ValidatorsCol.addError(errors, new StringSizeValidator(column.getFullName(), 1, column.getWidth()).validate(value));
        	if (errors != null) {
        		throw new MultipleFieldsValidationException(MESSAGE_INPUT_ERROR, errors);
        	}
            parameters.put(column.getDbColumnName(), value);
        }
    }

    /**
     * ��������� ��������� ������������� �������� �� ������������
     * � ������ ����������� ���������� �������� ������� MultipleFieldsValidationException
     */
    private void validateValue(String value, DirectoryColumnMetadata column)
            throws MultipleFieldsValidationException {
        if (column.getType() == Types.SMALLINT || column.getType() == Types.INTEGER) {
        	List errors = null;
        	errors = ValidatorsCol.addError(errors, new NotNullValidator(column.getFullName()).validate(value));
        	errors = ValidatorsCol.addError(errors, new NumberValidator(column.getFullName()).validate(value));
        	errors = ValidatorsCol.addError(errors, new StringSizeValidator(column.getFullName(), 1, column.getWidth()).validate(value));
        	if (errors != null) {
        		throw new MultipleFieldsValidationException(MESSAGE_INPUT_ERROR, errors);
        	}
        } else {
        	List errors = null;
        	errors = ValidatorsCol.addError(errors, new NotNullValidator(column.getFullName()).validate(value));
        	errors = ValidatorsCol.addError(errors, new StringSizeValidator(column.getFullName(), 1, column.getWidth()).validate(value));
        	if (errors != null) {
        		throw new MultipleFieldsValidationException(MESSAGE_INPUT_ERROR, errors);
        	}
        }
    }

    /**
     * ����� ��������� ������ - ������� ������� "������" ��� ������ ��������
     * ���������� ������ �� �����������
     */
    public DirectoryRecordSearchParameters getSearchParameters(DirectoryColumnMetadata[] columns)
            throws MultipleFieldsValidationException {
        Properties parameters = new Properties();
        String value = "";
        for (int i = 0; i < columns.length; i++) {
            value = request.getParameter(PN_SEARCH_PARAMETER + i);
            setSearchValue(value, columns[i], parameters);
        }
        return new DirectoryRecordSearchParameters(new DirectoryMetadataHandle(getTableName()), parameters);
    }

    /**
     * ���������� ��������� ������� - ����� �������� ��� �����������
     */
    public int getPage() throws MultipleFieldsValidationException {
        String value = request.getParameter(PN_PAGE);
        List errors = null;
        errors = ValidatorsCol.addError(errors, new NotNullValidator(PN_PAGE).validate(value));
        errors = ValidatorsCol.addError(errors, new StringSizeValidator(PN_PAGE, 1, Integer.MAX_VALUE).validate(value));
        errors = ValidatorsCol.addError(errors, new NumberValidator(PN_PAGE).validate(value));
        if (errors != null) {
        	throw new MultipleFieldsValidationException(MESSAGE_ERROR_PAGE, errors);
        }
        int page = Integer.parseInt(value);
        return page;
    }

    /**
     * ���������� ��� �������, �� ������� ����� ���������� ��������
     */
    public String getTableName() throws MultipleFieldsValidationException {
        String value = request.getParameter(PN_TABLE_NAME);
        List errors = null;
        errors = ValidatorsCol.addError(errors, new NotNullValidator(PN_TABLE_NAME).validate(value));
        errors = ValidatorsCol.addError(errors, new StringSizeValidator(PN_TABLE_NAME, 1, Integer.MAX_VALUE).validate(value));
        if (errors != null) {
        	throw new MultipleFieldsValidationException(MESSAGE_ERROR_TABLE_NAME, errors);
        }
        return value;
    }

    /**
     * ���������� primary key �������������� ������
     */
    public String getRecordPrimaryKey() throws MultipleFieldsValidationException {
        String recordPrimaryKey = request.getParameter(PN_RECORD_PRIMARY_KEY);
        List errors = null;
        errors = ValidatorsCol.addError(errors, new NotNullValidator(PN_RECORD_PRIMARY_KEY).validate(recordPrimaryKey));
        errors = ValidatorsCol.addError(errors, new StringSizeValidator(PN_RECORD_PRIMARY_KEY, 1, Integer.MAX_VALUE).validate(recordPrimaryKey));
        if (errors != null) {
        	throw new MultipleFieldsValidationException(MESSAGE_ERROR_PK, errors);
        }
        return recordPrimaryKey;
    }


    /**
     * ��������� ���������� ����������� �� ������ �����
     */
    public DirectoryMetadata updateDirectoryMetadataFromForm(DirectoryMetadata directoryMetadata)
            throws MultipleFieldsValidationException {
        String columnFullName;
        String description;
        DirectoryColumnMetadata[] columns = directoryMetadata.getColumnMetadata();

        for (int i = 0; i < columns.length; i++) {
            columnFullName = request.getParameter(PN_COLUMN_FULL_NAME + i);
            
            validateColumnComment(columnFullName, columns[i].getDbColumnName());
            columns[i].setFullName(columnFullName);
        }

        description = request.getParameter(AN_TABLE_DESCRIPTION);
        validateTableComment(description);
        directoryMetadata.setDescription(description);

        return directoryMetadata;
    }
    
    private void validateColumnComment(String comment, String fieldName)
            throws MultipleFieldsValidationException {
        List errors = null;
        errors = ValidatorsCol.addError(errors, new NotNullValidator(fieldName).validate(comment));
        errors = ValidatorsCol.addError(errors, new StringSizeValidator(fieldName, 1, 254).validate(comment));
        if (errors != null) {
        	throw new MultipleFieldsValidationException(fieldName, errors);
        }
    }

    private void validateTableComment(String comment)
            throws MultipleFieldsValidationException {
        validateColumnComment(comment, "��� �������");
    }

    /**
     * ����� ������ � ������ �� �����
     */
    public DirectoryRecord getDirectoryRecordFromForm(DirectoryColumnMetadata[] columns)
            throws MultipleFieldsValidationException {
        String[] values = new String[columns.length];
        List errors = new ArrayList();
        Integer oid = ParameterUtil.getInteger(request, PN_RECORD_PRIMARY_KEY, errors);
        if (!errors.isEmpty()) {
        	throw new MultipleFieldsValidationException(errors);
        }
        for (int i = 0; i < values.length; i++) {
            String currentValue = request.getParameter(PN_VALUE + i);
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
     * ���������� ���������� ����������� � ������ � � �������� �������
     */
    public void setSessionDirectoryMetadata(DirectoryMetadata directoryMetadata) throws ServletException {
        session.setDirectoryMetadata(directoryMetadata);
        setDirectoryMetadata();
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
     * ��������� iteration info ��� ������������ ������� � ��������� ������� (������ ���)
     */
    public void setFirstPageIterationInfo(PageIteratorBusinessDelegate iterator)
            throws ContactsException {
        PageIterationInfo iterationInfo = new PageIterationInfo(
            iterator.getNumberOfPages());
        request.setAttribute(AN_ITERATION_INFO, iterationInfo);
    }

    /**
     * ��������� iteration info ��� ������������ ������� � ��������� �������
     */
    public void setPageIterationInfo(PageIteratorBusinessDelegate iterator)
            throws ContactsException {
        PageIterationInfo iterationInfo = new PageIterationInfo(
            iterator.getNumberOfPages(), iterator.getCurrentPage());
        request.setAttribute(AN_ITERATION_INFO, iterationInfo);
    }


    public void setMessage(String message) {
        request.setAttribute(AN_MESSAGE, new PageMessage(message));
    }
}
