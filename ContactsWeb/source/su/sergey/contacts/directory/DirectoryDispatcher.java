package su.sergey.contacts.directory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.directory.commands.PageDirectoriesCommand;
import su.sergey.contacts.directory.commands.PageRecordsCommand;
import su.sergey.contacts.directory.commands.ShowDirectoriesCommand;
import su.sergey.contacts.directory.commands.ShowHeaderCommand;
import su.sergey.contacts.directory.commands.ShowRecordsCommand;
import su.sergey.contacts.directory.commands.UpdateHeaderCommand;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.commands.factory.CommandFactory;
import su.sergey.contacts.util.commands.factory.DefaultCommandFactory;

/**
 * ��������� ������.
 * 
 * @author: ������ ��������
 */
public class DirectoryDispatcher extends DefaultDispatcher implements DirectoryDefinitions {
	private static final Map actionToCommands;
    static {
    	actionToCommands = new HashMap();
    	actionToCommands.put("", ShowDirectoriesCommand.class);
    	actionToCommands.put(ACTION_SHOW_DIRECTORIES_SUFFIX, ShowDirectoriesCommand.class);
    	actionToCommands.put(ACTION_PAGE_DIRECTORIES_SUFFIX, PageDirectoriesCommand.class);
    	actionToCommands.put(ACTION_SHOW_HEADER_SUFFIX, ShowHeaderCommand.class);
    	actionToCommands.put(ACTION_UPDATE_HEADER_SUFFIX, UpdateHeaderCommand.class);
    	actionToCommands.put(ACTION_SHOW_RECORDS_SUFFIX, ShowRecordsCommand.class);
    	actionToCommands.put(ACTION_PAGE_SHOW_RECORDS_SUFFIX, PageRecordsCommand.class);
    }
	
    /**
     * ������������ ������.
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = null;
        String action = getAction(request);
        CommandFactory factory = DefaultCommandFactory.getInstance();
        Class commandClass = (Class) actionToCommands.get(getSuffix(action));
        Command command = factory.getCommand(commandClass);
        if (command == null) {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND,
        	                   "������� ������� �� ��������������� ������: action = "
                               + action + ". ���������� � ��������������.");
            return;
        }
        try {
        	nextPage = command.execute(request);
        } catch (ContactsException e) {
            throw new ServletException(e);
        }
        redirect(request, response, nextPage);
    }

	//    /**
	//     * ������������ ����� ������� �� ��������� ����������
	//     */
	//    private String processSearchRecords (DirectoryHttpServletRequest request) throws SberbankException, ServletException {
	//        return processRecords(request, DEFAULT_BIG_PAGE_SIZE);
	//    }
	//
	//
	//    /**
	//     * ������������ ���������� ��������� �����������
	//     */
	//
	//    /**
	//     * ������������ ���������� ��������� �����������
	//     */
	//    private String processShowModifyRecord(DirectoryHttpServletRequest request) throws SberbankException, ServletException {
	//        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getDirectoryCode());
	//        DirectoryMetadata directoryMetadata = getDirectoryMetadata(request, directoryMetadataHandle);
	//
	//        request.setDirectoryMetadata(directoryMetadata);
	//        try {
	//            DirectoryRecord directoryRecord = getDirectoryRecord(request, directoryMetadataHandle);
	//            request.setRecord(directoryRecord);
	//        } catch (IllegalArgumentException e) {
	//            // ���� �� ������� ����� Pk ��� ����� ����� ������ � ���� - ������ �� ������, ���������� �������� ��� ����������
	//        };
	//
	//        return PageNames.DIRECTORY_MODIFY_RECORD;
	//    }
	//
	//    /**
	//     * ������������ ���������� ������
	//     */
	//    private String processAddRecord(DirectoryHttpServletRequest request) throws SberbankException, ServletException {
	//        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getDirectoryCode());
	//        DirectoryMetadata directoryMetadata = getDirectoryMetadata(request, directoryMetadataHandle);
	//
	//        try {
	//            DirectoryRecord directoryRecord = request.getDirectoryRecordFromForm(directoryMetadata.getColumnMetadata());
	//            getDAOBusinessDelegate(request.getRequest()).addDirectoryRecord(directoryMetadataHandle, directoryRecord);
	//            request.setMessage(MESSAGE_RECORD_ADDED);
	//        } catch (Exception e) {
	//            request.setMessage(MESSAGE_RECORD_NOT_ADDED);
	//        }
	//
	//        processRecords(request, DEFAULT_BIG_PAGE_SIZE);
	//        return PageNames.DIRECTORY_SHOW_RECORDS;
	//    }
	//
	//    /**
	//     * ������������ �������������� ������
	//     */
	//    private String processEditRecord(DirectoryHttpServletRequest request) throws SberbankException, ServletException {
	//        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getDirectoryCode());
	//        DirectoryMetadata directoryMetadata = getDirectoryMetadata(request, directoryMetadataHandle);
	//
	//        try {
	//            DirectoryRecord directoryRecord = request.getDirectoryRecordFromForm(directoryMetadata.getColumnMetadata());
	//            getDAOBusinessDelegate(request.getRequest()).updateDirectoryRecord(directoryMetadataHandle, directoryRecord);
	//            request.setMessage(MESSAGE_RECORD_UPDATED);
	//        } catch (Exception e) {
	//            request.setMessage(MESSAGE_RECORD_NOT_UPDATED);
	//        }
	//        processRecords(request, DEFAULT_BIG_PAGE_SIZE);
	//
	//        return PageNames.DIRECTORY_SHOW_RECORDS;
	//    }
	//
	//    /**
	//     * ������������ �������� ������
	//     */
	//    private String processDeleteRecord(DirectoryHttpServletRequest request) throws SberbankException, ServletException {
	//        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getDirectoryCode());
	//        DirectoryRecordHandle directoryRecordHandle =
	//                new DirectoryRecordHandle(directoryMetadataHandle, request.getRecordPrimaryKey());
	//
	//        try {
	//            getDAOBusinessDelegate(request.getRequest()).deleteDirectoryRecord(directoryRecordHandle);
	//            request.setMessage(MESSAGE_RECORD_DELETED);
	//        } catch (Exception e) {
	//            request.setMessage(MESSAGE_RECORD_NOT_DELETED);
	//        }
	//        processRecords(request, DEFAULT_BIG_PAGE_SIZE);
	//
	//        return PageNames.DIRECTORY_SHOW_RECORDS;
	//    }
	//
	//    /**
	//     * ������� ����� ������-�������� ������ � primary ������, ������� ���������� � request
	//     */
	//    private DirectoryRecord getDirectoryRecord(DirectoryHttpServletRequest request, DirectoryMetadataHandle handle)
	//            throws SberbankException {
	//        String primaryKey = request.getRecordPrimaryKey();
	//        DirectoryRecordHandle directoryRecordHandle = new DirectoryRecordHandle(handle, primaryKey);
	//        return getDAOBusinessDelegate(request.getRequest()).findDirectoryRecord(directoryRecordHandle);
	//    }
	//
	//    /**
	//     * ������������� ����� ������-�������� ������ ������� �������� �����������
	//     */
	//    private void setDirectoryMetadata(DirectoryHttpServletRequest request, DirectoryMetadata directoryMetadata)
	//            throws SberbankException {
	//        getDAOBusinessDelegate(request.getRequest()).updateDirectoryMetadata(directoryMetadata);
	//    }
}
